package com.example.suryastore.productdetails;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.suryastore.BaseActivity;
import com.example.suryastore.Constants;
import com.example.suryastore.R;
import com.example.suryastore.carts.CartActivity;
import com.example.suryastore.databinding.ActivityProductDetailsBinding;
import com.example.suryastore.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseActivity {

    private ActivityProductDetailsBinding binding;
    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Product Details");
        if (getIntent().hasExtra(Constants.KEY_PRODUCT_ID)) {
            productId = getIntent().getIntExtra(Constants.KEY_PRODUCT_ID, 0);
        }
        fetchProductDetails();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon_menu);
        menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart_icon) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void fetchProductDetails() {
        setupProgressBarVisible();
        Call<Product> call = fakeApiService.getProductDetails(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                setupProgressBarGone();
                Product product = response.body();
                binding.setProduct(product);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                setupProgressBarGone();
                showToast("Failed to get Product");
            }
        });
    }

    private void setupProgressBarVisible() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void setupProgressBarGone() {
        binding.progressBar.setVisibility(View.GONE);
    }
}