package com.example.suryastore.searchbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.suryastore.R;
import com.example.suryastore.databinding.ActivitySearchProductsBinding;

public class SearchProductsActivity extends AppCompatActivity {

    private ActivitySearchProductsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}