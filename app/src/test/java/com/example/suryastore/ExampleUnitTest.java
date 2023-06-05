package com.example.suryastore;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.suryastore.apichangeclasses.Category;
import com.example.suryastore.model.Cart;
import com.example.suryastore.model.CartProduct;
import com.example.suryastore.model.Product;
import com.example.suryastore.network.FakeApi;
import com.example.suryastore.network.FakeApiService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void apiCategoriesTest() throws IOException {
        FakeApiService fakeApiService = new FakeApi().createFakeApiService();
        Call<List<Category>> call = fakeApiService.getCategories();
        List<Category> category = call.execute().body();
        assertNotNull(category);
        assertFalse(category.isEmpty());
        System.out.println(new Gson().toJson(category));

    }

    @Test
    public void apiProductsTest() throws IOException {
        FakeApiService fakeApiService = new FakeApi().createFakeApiService();
        Call<List<Product>> call = fakeApiService.getProducts(1);
        List<Product> products = call.execute().body();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        System.out.println(new Gson().toJson(products));
    }

    @Test
    public void apiProductDetails() throws IOException {
        FakeApiService fakeApiService = new FakeApi().createFakeApiService();
        Call<Product> call = fakeApiService.getProductDetails(1);
        Product products = call.execute().body();
        assertNotNull(products);
        System.out.println(new Gson().toJson(products));
    }

    @Test
    public void apiCart() throws IOException {
        FakeApiService fakeApiService = new FakeApi().createFakeApiService();
        Call<Cart> call = fakeApiService.getCartProducts();
        Cart cart = call.execute().body();
        System.out.println(cart);
    }

    @Test
    public void apiSearchProducts() throws IOException {
        FakeApiService fakeApiService = new FakeApi().createFakeApiService();
        Call<List<Product>> call = fakeApiService.getProductsOnSearch("");
        List<Product> products = call.execute().body();
        assertNotNull(products);
        System.out.println(new Gson().toJson(products));
    }
}