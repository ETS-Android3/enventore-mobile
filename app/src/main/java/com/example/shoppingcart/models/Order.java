package com.example.shoppingcart.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class Order {
    private String userId , storeId , dishId , dishName;
    private double price;

    public Order(String userId, String storeId, String dishId, String dishName, double price) {
        this.userId = userId;
        this.storeId = storeId;
        this.dishId = dishId;
        this.dishName = dishName;
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId='" + userId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", dishId='" + dishId + '\'' +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.getPrice(), getPrice()) == 0 &&
                getDishId().equals(order.getDishId()) &&
                getDishName().equals(order.getDishName()) &&
                getStoreId().equals(order.getStoreId()) &&
                getUserId().equals(order.getUserId());
    }

    public static DiffUtil.ItemCallback<Order> itemCallback = new DiffUtil.ItemCallback<Order>() {
        @Override
        public boolean areItemsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            return oldItem.getDishId().equals(newItem.getDishId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            return oldItem.equals(newItem);
        }
    };
}
