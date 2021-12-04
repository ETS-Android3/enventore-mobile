package com.example.shoppingcart.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.shoppingcart.R;
import com.example.shoppingcart.adapters.CartListAdapter;
import com.example.shoppingcart.databinding.FragmentCartBinding;
import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.viewmodels.FoodViewModel;
import com.example.shoppingcart.viewmodels.ShopViewModel;

import java.util.List;

public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG = "CartFragment";
    ShopViewModel shopViewModel;
    FoodViewModel foodViewModel;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        final CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        foodViewModel = new ViewModelProvider(requireActivity()).get(FoodViewModel.class);

//        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
//            @Override
//            public void onChanged(List<CartItem> cartItems) {
//                cartListAdapter.submitList(cartItems);
//                fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size() > 0);
//            }
//        });

        foodViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);
                fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size() > 0);
            }
        });

//        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
//            @Override
//            public void onChanged(Double aDouble) {
//                fragmentCartBinding.subTotal.setText("Rs " + aDouble.toString());
//                double totalCost = aDouble + 120;
//                fragmentCartBinding.orderTotalTextView.setText("Rs " + totalCost);
//            }
//        });

        foodViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCartBinding.subTotal.setText("Rs " + aDouble.toString());
                double totalCost = aDouble + 120;
                fragmentCartBinding.orderTotalTextView.setText("Rs " + totalCost);
            }
        });

        fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("xxxxxxxxxxxxxxxxxx");
                System.out.println(cartListAdapter.getCurrentList());
                navController.navigate(R.id.action_cartFragment_to_orderFragment);
            }
        });

    }

    @Override
    public void deleteItem(CartItem cartItem) {
//        shopViewModel.removeItemFromCart(cartItem);
        foodViewModel.removeItemFromCart(cartItem);
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
//        shopViewModel.changeQuantity(cartItem, quantity);
        foodViewModel.changeQuantity(cartItem, quantity);
    }
}