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
import android.widget.TextView;

import com.example.shoppingcart.R;
import com.example.shoppingcart.adapters.CartListAdapter;
import com.example.shoppingcart.databinding.FragmentCartBinding;
import com.example.shoppingcart.interfaces.RetrofitInterface;
import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.models.Order;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.viewmodels.FoodViewModel;
import com.example.shoppingcart.viewmodels.ShopViewModel;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG = "CartFragment";
    ShopViewModel shopViewModel;
    FoodViewModel foodViewModel;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.101:3001";

    private TextView username;
    private static String userId;

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

        username = (TextView)view.findViewById(R.id.txtName);
        MainActivity mainActivity = (MainActivity)getActivity();

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

                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                retrofitInterface = retrofit.create(RetrofitInterface.class);

                HashMap<String, List<CartItem>> map = new HashMap<>();

                map.put("orderList",cartListAdapter.getCurrentList());

                Call<Void> orderCall = retrofitInterface.executeOrder(map);

                orderCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        System.out.println("Success Order API Call");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println("Failed Order API Call");
                    }
                });

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