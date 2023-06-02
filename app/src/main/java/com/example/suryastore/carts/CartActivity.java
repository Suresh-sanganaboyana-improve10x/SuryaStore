package com.example.suryastore.carts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.suryastore.R;
import com.example.suryastore.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}