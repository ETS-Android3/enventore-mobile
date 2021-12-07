package com.example.shoppingcart.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.interfaces.RetrofitInterface;
import com.example.shoppingcart.models.Order;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.viewmodels.OrdersViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.shoppingcart.views.MainActivity.userId;

public class OrdersRepo {

    OrdersViewModel ordersViewModel;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://3.144.145.92:3001";

    private MutableLiveData<List<Order>> mutableOrderList;

    public LiveData<List<Order>> getOrders() {
        if (mutableOrderList == null) {
            mutableOrderList = new MutableLiveData<>();
            loadOrders();
        }
        return mutableOrderList;
    }

    private void loadOrders() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String , String> map = new HashMap<>();
        map.put("userId" , userId);

        Call<List<Order>> orderCall = retrofitInterface.getMyOrders(map);
        orderCall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> orderList = response.body();
                List<Order> newOrders = new ArrayList<>();

                for(Order order : orderList){
                    newOrders.add(order);
                    System.out.println(order);
                }
                mutableOrderList.setValue(newOrders);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                System.out.println("failed");
            }
        });
    }
}
