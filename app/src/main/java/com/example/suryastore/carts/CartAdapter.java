package com.example.suryastore.carts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suryastore.databinding.CartItemBinding;
import com.example.suryastore.model.Cart;
import com.example.suryastore.model.CartProduct;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<CartProduct> cartProducts;

    void setCarts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemBinding binding = CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(binding);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartProduct cartProduct = cartProducts.get(position);
        holder.binding.productIdTxt.setText(String.valueOf(cartProduct.getProductId()));
        holder.binding.quantityTxt.setText(String.valueOf(cartProduct.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }
}
