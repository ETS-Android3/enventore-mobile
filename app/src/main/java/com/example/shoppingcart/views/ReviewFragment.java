package com.example.shoppingcart.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.shoppingcart.databinding.FragmentReviewBinding;
import com.example.shoppingcart.models.Order;
import com.example.shoppingcart.viewmodels.OrdersViewModel;

public class ReviewFragment extends Fragment {

    FragmentReviewBinding fragmentReviewBinding;
    OrdersViewModel ordersViewModel;

    public ReviewFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentReviewBinding = FragmentReviewBinding.inflate(inflater, container, false);
        return fragmentReviewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ordersViewModel = new ViewModelProvider(requireActivity()).get(OrdersViewModel.class);
        fragmentReviewBinding.setOrderViewModel(ordersViewModel);

        System.out.println(ordersViewModel.getOrder().getValue());
    }
}

