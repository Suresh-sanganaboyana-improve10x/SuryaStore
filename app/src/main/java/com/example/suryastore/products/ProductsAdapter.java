package com.example.suryastore.products;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suryastore.databinding.ProductsItemBinding;
import com.example.suryastore.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsViewHolder> {

    public List<Product> products;

    void setProductsData(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductsItemBinding binding = ProductsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        ProductsViewHolder productsViewHolder = new ProductsViewHolder(binding);
        return productsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Product product = products.get(position);
        holder.binding.setProduct(product);
        holder.binding.productsRatingBar.setRating(product.rating.getRate());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
