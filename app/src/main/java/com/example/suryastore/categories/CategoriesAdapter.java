package com.example.suryastore.categories;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suryastore.apichangeclasses.Category;
import com.example.suryastore.databinding.CategoriesItemBinding;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private List<Category> categories;
    private OnItemActionListener onItemActionListener;

    void setCategoriesData(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoriesItemBinding binding = CategoriesItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(binding);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.binding.categoriesTxt.setText(category.getName());
        holder.binding.getRoot().setOnClickListener(v -> {
            onItemActionListener.onClick(category.getId());
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
