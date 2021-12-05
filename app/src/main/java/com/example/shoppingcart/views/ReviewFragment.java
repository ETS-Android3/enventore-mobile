package com.example.shoppingcart.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shoppingcart.R;
import com.example.shoppingcart.databinding.FragmentReviewBinding;
import com.example.shoppingcart.interfaces.RetrofitInterface;
import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.models.Order;
import com.example.shoppingcart.models.Rate;
import com.example.shoppingcart.viewmodels.OrdersViewModel;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewFragment extends Fragment {

    FragmentReviewBinding fragmentReviewBinding;
    OrdersViewModel ordersViewModel;
    NavController navController;
    private RatingBar ratingBar;
    private Button submitBtn;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.101:3001";

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

        navController = Navigation.findNavController(view);

        ratingBar = (RatingBar)view.findViewById(R.id.ratingBar);
        submitBtn = (Button)view.findViewById(R.id.submitReview);

        ordersViewModel = new ViewModelProvider(requireActivity()).get(OrdersViewModel.class);
        fragmentReviewBinding.setOrderViewModel(ordersViewModel);

        System.out.println(ordersViewModel.getOrder().getValue().getDishName());

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rating = (int) ratingBar.getRating();
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Rate rate = new Rate(ordersViewModel.getOrder().getValue().getOrderId(),
                        ordersViewModel.getOrder().getValue().getUserId()
                        ,ordersViewModel.getOrder().getValue().getDishId()
                        ,rating);


                retrofitInterface = retrofit.create(RetrofitInterface.class);
                HashMap<String, Rate> map = new HashMap<>();
                map.put("review",rate);
                Call<Rate> rateCall = retrofitInterface.executeRate(map);

                rateCall.enqueue(new Callback<Rate>() {
                    @Override
                    public void onResponse(Call<Rate> call, Response<Rate> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getContext(),"Review Added Successfull",Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.action_reviewFragment_to_ordersFragment);
                        }

                    }

                    @Override
                    public void onFailure(Call<Rate> call, Throwable t) {
                        System.out.println("Failed Review API Call");
                    }
                });


            }
        });

        Order neworder = ordersViewModel.getOrder().getValue();

    }
}

