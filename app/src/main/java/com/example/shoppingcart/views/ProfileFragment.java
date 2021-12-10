package com.example.shoppingcart.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shoppingcart.R;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private NavController navController;
    TextView username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.prevOrders);
        LinearLayout linearLayout1 = (LinearLayout)view.findViewById(R.id.logOut);
        LinearLayout complaints = (LinearLayout)view.findViewById(R.id.complaints);
        LinearLayout payments = (LinearLayout)view.findViewById(R.id.payments);
        LinearLayout promotions = (LinearLayout)view.findViewById(R.id.promotions);

        linearLayout1.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        payments.setOnClickListener(this);
        promotions.setOnClickListener(this);
        complaints.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = (TextView)view.findViewById(R.id.name);
        MainActivity mainActivity = (MainActivity)getActivity();
        username.setText(mainActivity.mName);
        navController = Navigation.findNavController(view);
    }

    public ProfileFragment(){

    }

    @Override
    public void onClick(View view) {
        Fragment fragment = new Fragment();
        switch (view.getId()){
            case R.id.prevOrders:
                navController.navigate(R.id.action_profileFragment_to_ordersFragment);
                break;
            case R.id.payments:
                navController.navigate(R.id.action_profileFragment_to_paymentFragment);
                break;
            case R.id.promotions:
                navController.navigate(R.id.action_profileFragment_to_promotionFragment);
                break;
            case R.id.complaints:
                navController.navigate(R.id.action_profileFragment_to_complaintFragment);
                break;
            case R.id.logOut:
                Intent intent = new Intent(getContext(),Login.class);
                MainActivity.mName=null;
                MainActivity.userId=null;
                startActivity(intent);
        }
    }

    public void replaceFragment(Fragment nextFragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container,nextFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goOrders(){
        Bundle bundle = this.getArguments();
        Fragment fragment = new Fragment();
        Bundle i = new Bundle();
        fragment.setArguments(i);
    }
}
