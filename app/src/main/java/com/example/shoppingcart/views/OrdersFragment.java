package com.example.shoppingcart.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.shoppingcart.R;
import com.example.shoppingcart.adapters.OrdersListAdapter;
import com.example.shoppingcart.databinding.FragmentOrdersBinding;
import com.example.shoppingcart.models.Order;
import com.example.shoppingcart.viewmodels.OrdersViewModel;

import java.util.List;

public class OrdersFragment extends Fragment implements OrdersListAdapter.OrderInterface {

    private static final String TAG = "OrdersFragment";
    FragmentOrdersBinding fragmentOrdersBinding;
    private OrdersListAdapter ordersListAdapter;
    private OrdersViewModel ordersViewModel;
    private NavController navController;

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentOrdersBinding = FragmentOrdersBinding.inflate(inflater, container, false);
        return fragmentOrdersBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ordersListAdapter = new OrdersListAdapter(this);
        fragmentOrdersBinding.ordersRecyclerView.setAdapter(ordersListAdapter);
        fragmentOrdersBinding.ordersRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        fragmentOrdersBinding.ordersRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));

        ordersViewModel = new ViewModelProvider(requireActivity()).get(OrdersViewModel.class);
        ordersViewModel.getOrders().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                ordersListAdapter.submitList(orders);
            }
        });
        navController = Navigation.findNavController(view);
    }

    @Override
    public void addItem(Order order) {

    }

    @Override
    public void onItemClick(Order order) {
        ordersViewModel.setOrder(order);
        navController.navigate(R.id.action_ordersFragment_to_reviewFragment);
    }
}
