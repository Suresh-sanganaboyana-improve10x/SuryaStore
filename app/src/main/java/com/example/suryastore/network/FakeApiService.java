package com.example.suryastore.network;

import com.example.suryastore.Constants;
import com.example.suryastore.model.Cart;
import com.example.suryastore.model.CartProduct;
import com.example.suryastore.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FakeApiService {

    @GET(Constants.CATEGORIES_END_POINT)
    Call<List<String>> getCategories();

    @GET(Constants.PRODUCTS_END_POINT+ "{categoryName}")
    Call<List<Product>> getProducts(@Path("categoryName") String categoryName);

    @GET(Constants.PRODUCT_DETAILS_END_POINT+ "{productId}")
    Call<Product> getProductDetails(@Path("productId") int productId);

    @GET("carts/1?userId=1")
    Call<Cart> getCartProducts();
}
