package com.example.suryastore.network;

import com.example.suryastore.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FakeApiService {

    @GET("products/categories")
    Call<List<String>> getCategories();

    @GET("products/category/{categoryName}")
    Call<List<Product>> getProducts(@Path("categoryName") String categoryName);

    @GET("products/{productId}")
    Call<Product> getProductDetails(@Path("productId") int productId);

}
