package com.example.suryastore.carts;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suryastore.databinding.CartItemBinding;

public class CartViewHolder extends RecyclerView.ViewHolder {

    CartItemBinding binding;

    public CartViewHolder(CartItemBinding cartItemBinding) {
        super(cartItemBinding.getRoot());
        binding = cartItemBinding;
    }
}
