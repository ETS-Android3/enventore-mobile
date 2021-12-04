package com.example.shoppingcart.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.interfaces.RetrofitInterface;
import com.example.shoppingcart.models.Order;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Retrofit;

public class OrdersRepo {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.101:3001";

    private MutableLiveData<List<Order>> mutableOrderList;

    public LiveData<List<Order>> getOrders() {
        if (mutableOrderList == null) {
            mutableOrderList = new MutableLiveData<>();
            loadOrders();
        }
        return mutableOrderList;
    }

    private void loadOrders() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("XXXXXXXX", "oooooooo", "lollollol", "Kottuu" , 320));
        orderList.add(new Order("10256251", "oooooooo", "hello cucci", "Cheese" , 320));
        orderList.add(new Order("15842569", "oooooooo", "lollollol", "String Hoppers" , 320));
        orderList.add(new Order("XXXXXXXX", "oooooooo", "lollollol", "Fried RIce" , 320));
        orderList.add(new Order("XXXXXXXX", "oooooooo", "lollollol", "Banana" , 320));
        mutableOrderList.setValue(orderList);

    }
}
