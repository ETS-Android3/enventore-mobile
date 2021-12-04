package com.example.shoppingcart.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingcart.databinding.FoodRowBinding;
import com.example.shoppingcart.interfaces.RetrofitInterface;
import com.example.shoppingcart.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodListAdapter extends ListAdapter<Product, FoodListAdapter.FoodViewHolder> {

    FoodInterface foodInterface;

    public FoodListAdapter(FoodInterface foodInterface) {
        super(Product.itemCallback);
        this.foodInterface = foodInterface;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FoodRowBinding foodRowBinding = FoodRowBinding.inflate(layoutInflater, parent, false);
        foodRowBinding.setFoodInterface(foodInterface);
        return new FoodViewHolder(foodRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Product product = getItem(position);
        holder.foodRowBinding.setProduct(product);
        holder.foodRowBinding.executePendingBindings();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {

        FoodRowBinding foodRowBinding;

        public FoodViewHolder(FoodRowBinding binding) {
            super(binding.getRoot());
            this.foodRowBinding = binding;
        }
    }

    public interface FoodInterface {
        void addItem(Product product);
        void onItemClick(Product product);
    }
}
