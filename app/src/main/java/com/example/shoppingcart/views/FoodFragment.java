package com.example.shoppingcart.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.shoppingcart.R;
import com.example.shoppingcart.adapters.FoodListAdapter;
import com.example.shoppingcart.databinding.FragmentFoodBinding;
import com.example.shoppingcart.interfaces.RetrofitInterface;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.responses.FetchDishResponse;
import com.example.shoppingcart.viewmodels.FoodViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class FoodFragment extends Fragment implements FoodListAdapter.FoodInterface{

    private static final String TAG = "FoodFragment";
    FragmentFoodBinding fragmentFoodBinding;
    private FoodListAdapter foodListAdapter;
    private FoodViewModel foodViewModel;
    private NavController navController;

    private TextView username;
    private static String userId;

    public FoodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFoodBinding = FragmentFoodBinding.inflate(inflater, container, false);
        return fragmentFoodBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = (TextView)view.findViewById(R.id.txtName);
        MainActivity mainActivity = (MainActivity)getActivity();

        username.setText("Hi Welcome\n" + mainActivity.mName);

        foodListAdapter = new FoodListAdapter(this);
        fragmentFoodBinding.foodRecyclerView.setAdapter(foodListAdapter);
        fragmentFoodBinding.foodRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        fragmentFoodBinding.foodRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));

        foodViewModel = new ViewModelProvider(requireActivity()).get(FoodViewModel.class);
        foodViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                foodListAdapter.submitList(products);
            }
        });

        navController = Navigation.findNavController(view);
    }

    @Override
    public void addItem(Product product) {
        System.out.println("OOOOO");
        boolean isAdded = foodViewModel.addItemToCart(product);
        if (isAdded) {
            Snackbar.make(requireView(), product.getName() + " added to cart.", Snackbar.LENGTH_LONG)
                    .setAction("Checkout", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            navController.navigate(R.id.action_foodFragment_to_cartFragment);
                        }
                    })
                    .show();
        } else {
            Snackbar.make(requireView(), "Already have the max quantity in cart.", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onItemClick(Product product) {
        foodViewModel.setProduct(product);
        navController.navigate(R.id.action_foodFragment_to_foodDetailFragment);
    }
}
