package com.example.shoppingcart.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("dishId")
    private String id;

    @SerializedName("dishName")
    private String name;

    @SerializedName("price")
    private double price;

    private boolean isAvailable;

    @SerializedName("dishPicture")
    private String imageUrl;

    @SerializedName("description")
    private String description;

    @SerializedName("storeId")
    private String storeId;

    @SerializedName("genre")
    private String genre;

    public Product(String id, String name, double price, boolean isAvailable, String imageUrl, String description, String storeId, String genre) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
        this.imageUrl = imageUrl;
        this.description = description;
        this.storeId = storeId;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoreId(){
        return storeId;
    }

    public void setStoreId(){
        this.storeId = storeId;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(){
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", storeId='" + storeId + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                isAvailable() == product.isAvailable() &&
                getId().equals(product.getId()) &&
                getName().equals(product.getName()) &&
                getImageUrl().equals(product.getImageUrl()) &&
                getDescription().equals(product.getDescription()) &&
                getStoreId().equals(product.getDescription()) &&
                getGenre().equals(product.getGenre());
    }

    public static DiffUtil.ItemCallback<Product> itemCallback = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:productImage")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView)
                .load(imageUrl)
                .fitCenter()
                .into(imageView);
    }
}
