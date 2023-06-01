package com.example.suryastore.products;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suryastore.databinding.ProductsItemBinding;

public class ProductsViewHolder extends RecyclerView.ViewHolder {

    public ProductsItemBinding binding;

    public ProductsViewHolder(ProductsItemBinding productsItemBinding  ) {
        super(productsItemBinding.getRoot());
        binding = productsItemBinding;
    }
}
