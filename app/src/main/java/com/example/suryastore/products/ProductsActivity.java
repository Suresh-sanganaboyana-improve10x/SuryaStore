package com.example.suryastore.products;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.suryastore.BaseActivity;
import com.example.suryastore.Constants;
import com.example.suryastore.R;
import com.example.suryastore.carts.CartActivity;
import com.example.suryastore.databinding.ActivityProductsBinding;
import com.example.suryastore.model.Product;
import com.example.suryastore.productdetails.ProductDetailsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends BaseActivity {

    private ArrayList<Product> products = new ArrayList<>();
    private ActivityProductsBinding binding;
    private ProductsAdapter productsAdapter;
    private String categoryName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Products");
        // TODO : explore more about hasExtra, putExtra and getExtra method
        if(getIntent().hasExtra(Constants.KEY_CATEGORY)) {
            categoryName = getIntent().getStringExtra(Constants.KEY_CATEGORY);
        }
        fetchProducts();
        setupProductsAdapter(); // TODO : setup
        setupProductsRv(); // TODO : setup
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
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

    public void fetchProducts() {
        setupProgressBarVisible();
        Call<List<Product>> call = fakeApiService.getProducts(categoryName);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                setupProgressBarGone();
                List<Product> productList = response.body();
                productsAdapter.setProductsData(productList);
                showToast("Successfully get products");
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                setupProgressBarGone();
                showToast("Failed to get products");
            }
        });
    }

    public void setupProductsAdapter() {
        productsAdapter = new ProductsAdapter();
        productsAdapter.setProductsData(products);
        productsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onClick(int productId) {
                Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
                // TODO : key should "productId"
                // TODO : Constants
                intent.putExtra(Constants.KEY_PRODUCT_ID, productId);
                startActivity(intent);
            }
        });
    }
    public void setupProductsRv() {
        binding.productsRv.setLayoutManager(new GridLayoutManager(this,2));
        binding.productsRv.setAdapter(productsAdapter);
    }
    private void setupProgressBarVisible() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }
    private void setupProgressBarGone() {
        binding.progressBar.setVisibility(View.GONE);
    }
}