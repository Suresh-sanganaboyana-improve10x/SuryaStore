package com.example.suryastore.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.CursorTreeAdapter;

import com.example.suryastore.BaseActivity;
import com.example.suryastore.OnItemActionListener;
import com.example.suryastore.R;
import com.example.suryastore.databinding.ActivityCategoriesBinding;
import com.example.suryastore.network.FakeApi;
import com.example.suryastore.network.FakeApiService;
import com.example.suryastore.products.ProductsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends BaseActivity {

    public ActivityCategoriesBinding binding;
    public ArrayList<String> categories = new ArrayList<>();
    public CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Categories");
        fetchCategories();
        setCategoriesAdapter();
        setCategoriesRv();
    }

    public void fetchCategories() {
        Call<List<String>> call = fakeApiService.getCategories();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> categoryList = response.body();
                categoriesAdapter.setCategoriesData(categoryList);
                showToast("Successfully fetch categories");
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                showToast("Failed to fetch categories");
            }
        });
    }

    public void setCategoriesAdapter() {
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setCategoriesData(categories);
        categoriesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onClick(String categoryName) {
                Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
                intent.putExtra("category", categoryName);
                startActivity(intent);
            }
        });
    }

    public void setCategoriesRv() {
        binding.categoriesRv.setLayoutManager(new LinearLayoutManager(this));
        binding.categoriesRv.setAdapter(categoriesAdapter);
    }

}