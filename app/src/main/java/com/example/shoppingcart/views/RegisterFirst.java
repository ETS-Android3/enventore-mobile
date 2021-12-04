package com.example.shoppingcart.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.shoppingcart.R;

public class RegisterFirst extends Fragment implements View.OnClickListener {

    private static EditText Username , Email , Contact , Pass , ConfPass;

    public RegisterFirst() {
        // Required empty public constructor
    }

    public static RegisterFirst newInstance(String param1, String param2) {
        RegisterFirst fragment = new RegisterFirst();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_first, container, false);  //please check main fragment . this is same as main

//        firebaseAuth = FirebaseAuth.getInstance();

        Username = (EditText) view.findViewById(R.id.txtUsername);
        Email = (EditText) view.findViewById(R.id.txtemail);
        Contact = (EditText) view.findViewById(R.id.txtcontact);
        Pass = (EditText) view.findViewById(R.id.txtPassword);
        ConfPass = (EditText) view.findViewById(R.id.txtConfPassword);

        Button Regi = (Button) view.findViewById(R.id.regi);
        TextView goBack = (TextView) view.findViewById(R.id.loginbtn);

        Regi.setOnClickListener(this);
        goBack.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = this.getArguments();
        Fragment fragment = new Fragment();
        Bundle i = new Bundle();        //create bundle object
        switch (v.getId()) {
            case R.id.regi:
                String username = Username.getText().toString();
                String email = Email.getText().toString();
                String contactno = Contact.getText().toString();
                String password = Pass.getText().toString();
                String confpassword = ConfPass.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(TextUtils.isEmpty(username)){
                    Username.setError("Your Name is Required");
                    return;
                }else if (TextUtils.isEmpty(contactno)){
                    Contact.setError("Please enter your contact no");
                    return;
                }/*else if (email.matches(emailPattern)){
                    Email.setError("Please enter valid email address");
                    return;
                }*/else if (TextUtils.isEmpty(password) || password.length() < 6){
                    Pass.setError("Please enter valid password");
                    return;
                }else if (!password.equals(confpassword)){
                    Pass.setError("Passwords doesn't match");
                    return;
                }

                i.putString("username", username);
                i.putString("email", email);
                i.putString("contactno", contactno);
                i.putString("password", password);
                fragment = new RegisterSecond();
                fragment.setArguments(i);
                replaceFragment(fragment);      //replacing fragment
                break;
            case R.id.loginbtn:
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
                break;
        }
    }

    public void replaceFragment(Fragment nextFragment) {        //fragment replacement
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, nextFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goNext(){

        Bundle bundle = this.getArguments();
        Fragment fragment = new Fragment();
        Bundle i = new Bundle();


        fragment.setArguments(i);
    }
}
