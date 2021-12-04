package com.example.shoppingcart.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {

    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts() {
        if (mutableProductList == null) {
            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts() {
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
