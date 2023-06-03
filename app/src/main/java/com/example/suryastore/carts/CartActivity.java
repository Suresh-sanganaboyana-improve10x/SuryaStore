package com.example.suryastore.carts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.suryastore.BaseActivity;
import com.example.suryastore.R;
import com.example.suryastore.databinding.ActivityCartBinding;
import com.example.suryastore.model.Cart;
import com.example.suryastore.model.CartProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends BaseActivity {

    private ActivityCartBinding binding;
    private ArrayList<CartProduct> products = new ArrayList<>();
    private CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getCart();
        setupCartAdapter();
        setupCartRv();
    }

    public void getCart() {
        Call<Cart> call = fakeApiService.getCartProducts();
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Cart cartList = response.body();
                cartAdapter.setCarts(cartList.products);
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
    }

    public void setupCartAdapter() {
        cartAdapter = new CartAdapter();
        cartAdapter.setCarts(products);
    }

    public void setupCartRv() {
        binding.cartRv.setLayoutManager(new LinearLayoutManager(this));
        binding.cartRv.setAdapter(cartAdapter);
    }
}