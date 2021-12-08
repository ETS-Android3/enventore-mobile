package com.example.shoppingcart.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.shoppingcart.R;
import com.example.shoppingcart.interfaces.RetrofitInterface;
import com.example.shoppingcart.models.User;
import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterSecond extends Fragment implements View.OnClickListener {

    RadioGroup status_radio , status_health ;

    CheckBox st_spicy , st_bitter , st_salty , st_savoury , st_sweet;

    TextView mtlogin, mcancelbut , setCond , setDief;
    Button Register;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://3.144.145.92:3001";

    DatePickerDialog.OnDateSetListener mDateSetListener;

    public RegisterSecond() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register_second, container, false);  //please check main fragment . this is same as main
        Button Next = (Button) view.findViewById(R.id.register);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        setCond = (TextView) view.findViewById(R.id.setCondition);
        setDief = (TextView) view.findViewById(R.id.setDiet);

        status_radio = (RadioGroup)view.findViewById(R.id.status);
        status_health = (RadioGroup)view.findViewById(R.id.condition);

        st_spicy = (CheckBox) view.findViewById(R.id.pref_spicy);
        st_bitter = (CheckBox) view.findViewById(R.id.pref_bitter);
        st_salty = (CheckBox) view.findViewById(R.id.pref_salty);
        st_savoury = (CheckBox) view.findViewById(R.id.pref_savoury);
        st_sweet = (CheckBox) view.findViewById(R.id.pref_sweet);

        setDief.setText("Non-Vegitarian");
        setCond.setText("No");

        status_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) view.findViewById(checkedId);
                String status = checkedRadioButton.getText().toString();
                String diet = "";
                switch(checkedId) {
                    case R.id.sta_vegi:
                        diet = status;
                        setDief.setText(diet);
                        break;
                    case R.id.sta_veg:
                        diet = status;
                        setDief.setText(diet);
                        break;
                    case R.id.sta_non_veg:
                        diet = status;
                        setDief.setText(diet);
                        break;
                }
            }
        });

        status_health.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) view.findViewById(checkedId);
                String condition = checkedRadioButton.getText().toString();
                String cond = "";
                switch(checkedId) {
                    case R.id.cond_yes:
                        cond = condition;
                        setCond.setText(cond);
                        break;
                    case R.id.cond_no:
                        cond = condition;
                        setCond.setText(cond);
                        break;
                }
            }
        });

        Next.setOnClickListener(this);      //button onclicklisteners

        return view;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = this.getArguments();
        String username = bundle.getString("username");
        String email = bundle.getString("email");
        String contactno = bundle.getString("contactno");
        String password = bundle.getString("password");
        String dietary = setDief.getText().toString();
        String phycond = setCond.getText().toString();
        switch (v.getId()) {
            case R.id.register:
                HashMap<String , String> map = new HashMap<>();
                map.put("name" , username);
                map.put("email" , email);
                map.put("contactNo" , contactno);
                map.put("password" , password);
                map.put("dietary" , dietary);
                map.put("physicalCondition" , phycond);
                Call<User> call = retrofitInterface.executeRegister(map);
                call.enqueue(new Callback<User>(){
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.code() == 200) {
                            User result = response.body();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Gson gson = new Gson();
                            String userdata = gson.toJson(result);
                            intent.putExtra("userId",response.body().getUserId());
                            intent.putExtra("name",response.body().getName());
                            startActivity(intent);
                            ((Activity) getActivity()).overridePendingTransition(0, 0);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });




                break;
        }
    }
}
