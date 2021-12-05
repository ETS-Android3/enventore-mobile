package com.example.shoppingcart.interfaces;

import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.models.LoginResult;
import com.example.shoppingcart.models.Order;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.models.Rate;
import com.example.shoppingcart.models.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/loginapp")
    Call<LoginResult> executeLogin(@Body HashMap<String,String> map);

    @POST("/register")
    Call<User> executeRegister(@Body HashMap<String,String> map);

    @GET("/loadFoods")
    Call<List<Product>> getAllFoods();

    @POST("/buyfood")
    Call<Product> executePurchase(@Body HashMap<String,String> map);

    @POST("/order")
    Call<Void> executeOrder(@Body HashMap<String,List<CartItem>> map);

    @POST("/rateFood")
    Call<Rate> executeRate(@Body HashMap<String,Rate> map);

    @POST("/getOrders")
    Call<List<Order>> getMyOrders(@Body HashMap<String,String> map);
}
