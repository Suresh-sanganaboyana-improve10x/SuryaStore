package com.example.suryastore.productdetails;

import android.content.Intent;
import android.os.Bundle;

import com.example.suryastore.BaseActivity;
import com.example.suryastore.R;
import com.example.suryastore.databinding.ActivityProductDetailsBinding;
import com.example.suryastore.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseActivity {

    // TODO : private, private
    public ActivityProductDetailsBinding binding;
    int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        // TODO : fix it
        intent.hasExtra("ProductDetails");
        productId = getIntent().getIntExtra("ProductDetails", 0);
        fetchProductDetails();
    }

    public void fetchProductDetails() {
        Call<Product> call = fakeApiService.getProductDetails(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                // TODO : product
                Product products = response.body();
                binding.setProduct(products);
                // TODO :  Use data binding
                binding.productRatingBar.setRating(products.rating.getRate());
                showToast("Successfully get product");

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                showToast("Failed to get Product");
            }
        });
    }
}