package com.example.suryastore.network;

import com.example.suryastore.Constants;
import com.example.suryastore.apichangeclasses.Category;
import com.example.suryastore.model.Cart;
import com.example.suryastore.model.CartProduct;
import com.example.suryastore.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FakeApiService {

    @GET(Constants.CATEGORIES_END_POINT)
    Call<List<Category>> getCategories();

    @GET("products")
    Call<List<Product>> getProducts(@Query("categoryId") int categoryId);

    @GET(Constants.PRODUCT_DETAILS_END_POINT+ "{productId}")
    Call<Product> getProductDetails(@Path("productId") int productId);

    @GET("carts/1?userId=1")
    Call<Cart> getCartProducts();

    @GET("products/")
    Call<List<Product>> getProductsOnSearch(@Query("title") String title);
}
