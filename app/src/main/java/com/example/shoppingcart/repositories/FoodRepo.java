package com.example.shoppingcart.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.interfaces.RetrofitInterface;
import com.example.shoppingcart.models.LoginResult;
import com.example.shoppingcart.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodRepo {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://3.144.145.92:3001";

    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts() {
        if (mutableProductList == null) {
            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<List<Product>> productCall = retrofitInterface.getAllFoods();
        productCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
                List<Product> newProducts = new ArrayList<>();

                for(Product product : productList){
                    newProducts.add(product);
                }
                mutableProductList.setValue(newProducts);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                System.out.println("failed");
            }
        });

//        List<Product> productList = new ArrayList<>();
//
//
//
//        productList.add(new Product(UUID.randomUUID().toString(), "Kottu", 320, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg" ));
//        productList.add(new Product(UUID.randomUUID().toString(), "Fried Rice", 420, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "Buriyani", 540, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "String Hoppers", 110, false, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "Cheese Kottu", 360, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "Nasigoreng", 720, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "Cutlets", 200, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "Rice and Curry", 160, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "Devilled Chicken", 380, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "Hot Cuttlefish", 460, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        mutableProductList.setValue(productList);
    }
}
