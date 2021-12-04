package com.example.shoppingcart.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingcart.interfaces.RetrofitInterface;
import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.repositories.CartRepo;
import com.example.shoppingcart.repositories.FoodRepo;
import com.example.shoppingcart.repositories.ShopRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodViewModel extends ViewModel {

    FoodRepo foodRepo = new FoodRepo();
    CartRepo cartRepo = new CartRepo();

    MutableLiveData<Product> mutableProduct = new MutableLiveData<>();

    public LiveData<List<Product>> getProducts() {
        return foodRepo.getProducts();
    }

    public void setProduct(Product product) {
        mutableProduct.setValue(product);
    }

    public LiveData<Product> getProduct() {
        return mutableProduct;
    }

    public LiveData<List<CartItem>> getCart() {
        return cartRepo.getCart();
    }

    public boolean addItemToCart(Product product) {
        return cartRepo.addItemToCart(product);
    }

    public void removeItemFromCart(CartItem cartItem) {
        cartRepo.removeItemFromCart(cartItem);
    }

    public void changeQuantity(CartItem cartItem, int quantity) {
        cartRepo.changeQuantity(cartItem, quantity);
    }

    public LiveData<Double> getTotalPrice() {
        return cartRepo.getTotalPrice();
    }

    public void resetCart() {
        cartRepo.initCart();
    }
}
