package com.example.suryastore.categories;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.suryastore.BaseActivity;
import com.example.suryastore.Constants;
import com.example.suryastore.R;
import com.example.suryastore.apichangeclasses.Category;
import com.example.suryastore.carts.CartActivity;
import com.example.suryastore.databinding.ActivityCategoriesBinding;
import com.example.suryastore.products.ProductsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends BaseActivity {

    private ActivityCategoriesBinding binding;
    private ArrayList<Category> categories = new ArrayList<>();
    private CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Categories");
        fetchCategories();
        setupCategoriesAdapter();
        setupCategoriesRv();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart_icon) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void fetchCategories() {
        setupProgressBarVisible();
        Call<List<Category>> call = fakeApiService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                setupProgressBarGone();
                List<Category> categories1 = response.body();
                categoriesAdapter.setCategoriesData(categories1);
                showToast("Successfully fetch categories");
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                setupProgressBarGone();
                showToast("Failed to fetch categories");
            }
        });
    }

    public void setupCategoriesAdapter() {
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setCategoriesData(categories);
        categoriesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onClick(int categoryId) {
                Intent intent = new Intent(CategoriesActivity.this, ProductsActivity.class);
                intent.putExtra(Constants.KEY_CATEGORY, categoryId);
                startActivity(intent);
            }
        });
    }
    public void setupCategoriesRv() {
        binding.categoriesRv.setLayoutManager(new LinearLayoutManager(this));
        binding.categoriesRv.setAdapter(categoriesAdapter);
    }

    private void setupProgressBarVisible() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void setupProgressBarGone() {
        binding.progressBar.setVisibility(View.GONE);
    }
}