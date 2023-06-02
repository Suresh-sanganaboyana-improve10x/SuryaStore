package com.example.suryastore.productdetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.suryastore.BaseActivity;
import com.example.suryastore.Constants;
import com.example.suryastore.R;
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