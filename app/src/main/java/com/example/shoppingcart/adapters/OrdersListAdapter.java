package com.example.shoppingcart.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shoppingcart.databinding.OrderRowBinding;
import com.example.shoppingcart.models.Order;
import com.example.shoppingcart.views.ReviewFragment;

public class OrdersListAdapter extends ListAdapter<Order, OrdersListAdapter.OrderViewHolder> {

    OrderInterface orderInterface;
    Context context;

    public OrdersListAdapter(OrdersListAdapter.OrderInterface orderInterface) {
        super(Order.itemCallback);
        this.orderInterface = orderInterface;
    }

    @NonNull
    @Override
    public OrdersListAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        OrderRowBinding orderRowBinding = OrderRowBinding.inflate(layoutInflater, parent, false);
        orderRowBinding.setOrderInterface(orderInterface);
        return new OrdersListAdapter.OrderViewHolder(orderRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersListAdapter.OrderViewHolder holder, int position) {
        final Order order = getItem(position);
        holder.orderRowBinding.setOrder(order);
        holder.orderRowBinding.executePendingBindings();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        OrderRowBinding orderRowBinding;

        public OrderViewHolder(OrderRowBinding binding) {
            super(binding.getRoot());
            this.orderRowBinding = binding;
        }
    }

    public interface OrderInterface {
        void addItem(Order order);
        void onItemClick(Order order);
    }

}
