package com.example.shoppingcart.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Order {
    private String orderId , userId , storeId , dishId , dishName , dishPicture;
    private int quantity;
    private boolean rated;

    public Order(String orderId , String userId, String storeId, String dishId, String dishName, String dishPicture, int quantity, boolean rated) {
        this.orderId = orderId;
        this.userId = userId;
        this.storeId = storeId;
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishPicture = dishPicture;
        this.quantity = quantity;
        this.rated = rated;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getDishPicture() {
        return dishPicture;
    }

    public void setDishPicture(String dishPicture) {
        this.dishPicture = dishPicture;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                quantity == order.quantity &&
                rated == order.rated &&
                userId.equals(order.userId) &&
                storeId.equals(order.storeId) &&
                dishId.equals(order.dishId) &&
                dishName.equals(order.dishName) &&
                dishPicture.equals(order.dishPicture);
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
