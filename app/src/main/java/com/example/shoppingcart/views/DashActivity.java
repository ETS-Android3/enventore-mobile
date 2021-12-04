package com.example.shoppingcart.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.shoppingcart.R;
import com.example.shoppingcart.adapters.SliderAdapter;

public class DashActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button buttonPrevious, buttonNext;

    private  int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        mSlideViewPager = findViewById(R.id.slide_viewpager);
        mDotsLayout = findViewById(R.id.dots_layout);
        buttonNext = findViewById(R.id.btn_next);
        buttonPrevious = findViewById(R.id.btn_previous);

        if (isFirstTime()) {
            sliderAdapter = new SliderAdapter(this);

            mSlideViewPager.setAdapter(sliderAdapter);

            addDotsIndicator(0);

            mSlideViewPager.addOnPageChangeListener(viewListener);

            buttonNext.setOnClickListener(this);
            buttonPrevious.setOnClickListener(this);
        }else {
            startActivity(new Intent(DashActivity.this, MainActivity.class));
            finish();
        }
    }

    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotsLayout.removeAllViews(); //without this multiple number of dots will be created

        for (int i = 0; i< mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;")); //code for the dot icon like thing
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorAlmostWhite));

            mDotsLayout.addView(mDots[i]);
        }

        if (mDots.length>0){
            mDots[position] .setTextColor(getResources().getColor(R.color.White)); //setting currently selected dot to white
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);

            mCurrentPage = position;

            if (position == 0){//we are on first page
                buttonNext.setEnabled(true);
                buttonPrevious.setEnabled(false);
                buttonPrevious.setVisibility(View.INVISIBLE);

                buttonNext.setText("Next");
                buttonPrevious.setText("");
            } else if (position == mDots.length - 1){ //last page
                buttonNext.setEnabled(true);
                buttonPrevious.setEnabled(true);
                buttonPrevious.setVisibility(View.VISIBLE);

                buttonNext.setText("Finish");
                buttonPrevious.setText("Back");
            } else { //neither on first nor on last page
                buttonNext.setEnabled(true);
                buttonPrevious.setEnabled(true);
                buttonPrevious.setVisibility(View.VISIBLE);

                buttonNext.setText("Next");
                buttonPrevious.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                if (buttonNext.getText().toString().equalsIgnoreCase("next")){
                    mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                } else {
                    startActivity(new Intent(DashActivity.this, MainActivity.class));
                }
                break;
            case R.id.btn_previous:
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
                break;
            default: break;
        }
    }
}