package com.example.shoppingcart.responses;

import com.example.shoppingcart.models.Product;

import java.util.List;

public class FetchDishResponse {

    List<Product> productList;
    String error;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
