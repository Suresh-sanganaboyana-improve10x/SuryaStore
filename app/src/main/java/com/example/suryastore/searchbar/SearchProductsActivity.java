package com.example.suryastore.searchbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.suryastore.BaseActivity;
import com.example.suryastore.R;
import com.example.suryastore.apichangeclasses.Category;
import com.example.suryastore.categories.CategoriesAdapter;
import com.example.suryastore.databinding.ActivitySearchProductsBinding;
import com.example.suryastore.model.Product;
import com.example.suryastore.products.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductsActivity extends BaseActivity {

    private ActivitySearchProductsBinding binding;
    private ArrayList<Product> categories = new ArrayList<>();
    private ProductsAdapter productsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Search products");
        setupSearchProductsAdapter();
        setupSearchProductsRv();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_products_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search_ico);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchProducts(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.app_bar_search_ico) {
            Toast.makeText(this, "Dhobbey", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getSearchProducts(String s) {
        Call<List<Product>> call = fakeApiService.getProductsOnSearch(s);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
                productsAdapter.setProductsData(productList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }

    public void setupSearchProductsAdapter() {
        productsAdapter = new ProductsAdapter();
        productsAdapter.setProductsData(categories);
    }

    public void setupSearchProductsRv() {
        binding.searchProductsRv.setLayoutManager(new GridLayoutManager(this, 2));
        binding.searchProductsRv.setAdapter(productsAdapter);
    }
}