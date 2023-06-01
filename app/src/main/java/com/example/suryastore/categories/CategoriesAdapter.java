package com.example.suryastore.categories;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suryastore.OnItemActionListener;
import com.example.suryastore.databinding.CategoriesItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    public List<String> categories;
    public OnItemActionListener onItemActionListener;
    void setCategoriesData(List<String> categories) {
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
        String category = categories.get(position);
        holder.binding.categoriesTxt.setText(category);
        holder.binding.getRoot().setOnClickListener(v -> {
            onItemActionListener.onClick(category);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
