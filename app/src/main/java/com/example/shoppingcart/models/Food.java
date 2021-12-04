package com.example.shoppingcart.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

public class Food {
    private String dishId , storeId , dishName , category , hours , ingredients , dishPicture , description , genre;
    private double price;

    public Food(String dishId, String storeId, String dishName, String category, String hours, String ingredients, String dishPicture, String description, String genre, double price) {
        this.dishId = dishId;
        this.storeId = storeId;
        this.dishName = dishName;
        this.category = category;
        this.hours = hours;
        this.ingredients = ingredients;
        this.dishPicture = dishPicture;
        this.description = description;
        this.genre = genre;
        this.price = price;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDishPicture() {
        return dishPicture;
    }

    public void setDishPicture(String dishPicture) {
        this.dishPicture = dishPicture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                "dishId='" + dishId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", dishName='" + dishName + '\'' +
                ", category='" + category + '\'' +
                ", hours='" + hours + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", dishPicture='" + dishPicture + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0 &&
                dishId.equals(food.dishId) &&
                storeId.equals(food.storeId) &&
                dishName.equals(food.dishName) &&
                category.equals(food.category) &&
                hours.equals(food.hours) &&
                ingredients.equals(food.ingredients) &&
                dishPicture.equals(food.dishPicture) &&
                description.equals(food.description) &&
                genre.equals(food.genre);
    }

    public static DiffUtil.ItemCallback<Food> itemCallback = new DiffUtil.ItemCallback<Food>() {
        @Override
        public boolean areItemsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
            return oldItem.getDishId().equals(newItem.getDishId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Food oldItem, @NonNull Food newItem) {
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
