package com.example.shoppingcart.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.interfaces.RetrofitInterface;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.models.Rate;
import com.example.shoppingcart.views.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopRepo {

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

        String userId = MainActivity.userId;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("userId",userId);

        final Call<List<Product>> productRecCall = retrofitInterface.getRecProducts(map);
        productRecCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
                List<Product> newProducts = new ArrayList<>();

                for(Product product : productList){
                    newProducts.add(product);
                    System.out.println("xoxoxoxo");
                    System.out.println(product);
                }
                mutableProductList.setValue(newProducts);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                System.out.println("failed");
            }
        });
//        List<Product> productList = new ArrayList<>();
//        productList.add(new Product(UUID.randomUUID().toString(), "iMac 21", 1299, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg" ));
//        productList.add(new Product(UUID.randomUUID().toString(), "iPad Air", 799, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "iPad Pro", 999, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 11", 699, false, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 11 Pro", 999, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 11 Pro", 1099, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "iPhone SE", 399, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "MacBook Air", 999, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "MacBook Pro 13", 1299, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        productList.add(new Product(UUID.randomUUID().toString(), "MacBook Pro 16", 2399, true, "https://www.theflavorbender.com/wp-content/uploads/2018/03/Chicken-Kottu-Roti-The-Flavor-Bender-Featured-Image-SQ-8.jpg"));
//        mutableProductList.setValue(productList);
    }
}
