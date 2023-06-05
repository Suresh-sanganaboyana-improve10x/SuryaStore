package com.example.suryastore.products;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suryastore.databinding.ProductsItemBinding;
import com.example.suryastore.model.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsViewHolder> {

    // TODO : private
    public List<Product> products;
    private OnItemActionListener onItemActionListener;

    public void setProductsData(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    // TODO : you can use default
    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO : xml should be product_item
        ProductsItemBinding binding = ProductsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        // TODO : ProductViewHolder, change object as well
        ProductsViewHolder productsViewHolder = new ProductsViewHolder(binding);
        return productsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Product product = products.get(position);
        holder.binding.setProduct(product);
        holder.binding.getRoot().setOnClickListener(v -> {
            onItemActionListener.onClick(product.getId());
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
