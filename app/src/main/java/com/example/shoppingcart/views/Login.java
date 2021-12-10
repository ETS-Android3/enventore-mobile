package com.example.shoppingcart.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingcart.R;
import com.example.shoppingcart.interfaces.RetrofitInterface;
import com.example.shoppingcart.models.LoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private Button mLogin;
    private EditText mEmail , mPassword;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://3.144.145.92:3001";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLogin = (Button) findViewById(R.id.login);
        mEmail = (EditText) findViewById(R.id.txtUsername);       //initializing components
        mPassword = (EditText) findViewById(R.id.txtPassword);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        mLogin.setOnClickListener(new View.OnClickListener() {      //when user clicks login btn
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Login Button Clicked", Toast.LENGTH_SHORT).show();
                final String email = mEmail.getText().toString();       //getting user inputs
                final String password = mPassword.getText().toString();
                HashMap<String , String> map = new HashMap<>();
                map.put("email" , email);
                map.put("password" , password);
                Call<LoginResult> call = retrofitInterface.executeLogin(map);
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if (response.code() == 200) {
                            LoginResult result = response.body();
                            System.out.println(response.body().toString());
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("userId" , result.getUserId());
                            intent.putExtra("name",result.getName());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void goRegister(View view) {
        startActivity(new Intent(Login.this, Registration.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        return;
    }
}