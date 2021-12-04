package com.example.shoppingcart.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppingcart.databinding.FragmentFoodDetailBinding;
import com.example.shoppingcart.databinding.FragmentProductDetailBinding;
import com.example.shoppingcart.viewmodels.FoodViewModel;
import com.example.shoppingcart.viewmodels.ShopViewModel;

public class FoodDetailFragment extends Fragment {

    FragmentFoodDetailBinding fragmentFoodDetailBinding;
    FoodViewModel foodViewModel;

    public FoodDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentFoodDetailBinding = FragmentFoodDetailBinding.inflate(inflater, container, false);
        return fragmentFoodDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodViewModel = new ViewModelProvider(requireActivity()).get(FoodViewModel.class);
        fragmentFoodDetailBinding.setFoodViewModel(foodViewModel);
    }
}