package com.example.shoppingcart.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shoppingcart.R;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.prevOrders);

        linearLayout.setOnClickListener(this);

        return view;

//        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
