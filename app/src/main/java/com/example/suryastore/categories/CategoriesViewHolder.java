package com.example.suryastore.categories;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suryastore.databinding.CategoriesItemBinding;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    // TODO : use default
    CategoriesItemBinding binding;

    public CategoriesViewHolder(CategoriesItemBinding categoriesItemBinding) {
        super(categoriesItemBinding.getRoot());
        binding = categoriesItemBinding;
    }
}
