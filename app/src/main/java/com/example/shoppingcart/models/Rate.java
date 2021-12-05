package com.example.shoppingcart.models;

import java.util.Objects;

public class Rate {
    String orderId , userId , dishId;
    int rating;

    public Rate(String orderId, String userId, String dishId, int rating) {
        this.orderId = orderId;
        this.userId = userId;
        this.dishId = dishId;
        this.rating = rating;
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

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", dishId='" + dishId + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return rating == rate.rating &&
                orderId.equals(rate.orderId) &&
                userId.equals(rate.userId) &&
                dishId.equals(rate.dishId);
    }
}
