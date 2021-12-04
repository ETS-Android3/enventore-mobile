package com.example.shoppingcart.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.databinding.FoodRowBinding;
import com.example.shoppingcart.databinding.OrderRowBinding;
import com.example.shoppingcart.models.Order;
import com.example.shoppingcart.models.Product;

public class OrdersListAdapter extends ListAdapter<Order, OrdersListAdapter.OrderViewHolder> {

    OrderInterface orderInterface;

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
        Order order = getItem(position);
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
