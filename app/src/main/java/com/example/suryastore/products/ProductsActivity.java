package com.example.suryastore.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.suryastore.BaseActivity;
import com.example.suryastore.R;
import com.example.suryastore.databinding.ActivityProductsBinding;
import com.example.suryastore.model.Product;

import java.util.ArrayList;
import java.util.List;

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
        Intent intent = getIntent();
        intent.hasExtra("category");
        categoryName = getIntent().getStringExtra("category");
        fetchProducts();
        setProductsAdapter();
        setProductsRv();
    }

    public void fetchProducts() {
        Call<List<Product>> call = fakeApiService.getProducts(categoryName);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
                productsAdapter.setProductsData(productList);
                showToast("Successfully get products");

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                showToast("Failed to get products");
            }
        });
    }

    public void setProductsAdapter() {
        productsAdapter = new ProductsAdapter();
        productsAdapter.setProductsData(products);
    }

    public void setProductsRv() {
        binding.productsRv.setLayoutManager(new GridLayoutManager(this,2));
        binding.productsRv.setAdapter(productsAdapter);
    }
}