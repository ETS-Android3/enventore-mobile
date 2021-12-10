package com.example.shoppingcart.views;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.shoppingcart.R;

public class ComplaintFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private static final String TAG = "ComplaintFragment";
    private NavController navController;
    private Button txtubmit;

    public ComplaintFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaint,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        txtubmit = (Button)view.findViewById(R.id.submitComplaint);

        String[] courses = { "Late Delivery", "Cold Meal",
                "Bad Smell", "Expired Items",
                "Stomach Ace"};

        Spinner spino = view.findViewById(R.id.coursesspinner);
        spino.setOnItemSelectedListener(this);

        ArrayAdapter ad
                = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,courses);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        spino.setAdapter(ad);

        txtubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        navController.navigate(R.id.action_complaintFragment_to_profileFragment);
                    }
                }, 1000);
                Toast.makeText(getContext(), "We have Recorded Your Complaint", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
