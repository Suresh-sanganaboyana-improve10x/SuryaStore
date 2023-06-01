package com.example.suryastore.products;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.suryastore.R;
import com.example.suryastore.databinding.ActivityProductsBinding;
import com.example.suryastore.model.Product;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    private ArrayList<Product> products = new ArrayList<>();
    private ActivityProductsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}