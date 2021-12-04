package com.example.shoppingcart.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingcart.models.Order;
import com.example.shoppingcart.repositories.OrdersRepo;

import java.util.List;

public class OrdersViewModel extends ViewModel {

    OrdersRepo ordersRepo = new OrdersRepo();

    MutableLiveData<Order> orders = new MutableLiveData<>();

    public LiveData<List<Order>> getOrders() {
        return ordersRepo.getOrders();
    }

    public void setOrder(Order order) {
        orders.setValue(order);
    }

    public LiveData<Order> getOrder() {
        return orders;
    }


}
