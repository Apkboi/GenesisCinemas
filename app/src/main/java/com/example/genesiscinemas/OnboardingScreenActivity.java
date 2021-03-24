package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;


public class OnboardingScreenActivity extends AppCompatActivity {

SharedPreferences sharedPreferences;
Boolean firstTime;
    LinearLayout dots;
//    addDots();
    TextView txtskip;
    TextView txtdot ;
    TextView txtdot2 ;
    TextView txtdot3;
    TextView txtdot4;
    ViewPager pager;
    final Animation fadeOutAnimation = new AlphaAnimation(1.0f,0.0f);
    final Animation fadeInAnimation = new AlphaAnimation(0.0f,1.0f);
    MaterialButton btn_login ;
    MaterialButton btn_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);

        sharedPreferences = getSharedPreferences("MyPreference",MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("firstTime",true)){
            startActivity(new Intent(OnboardingScreenActivity.this,LoginActivity.class));
            finish();
        }
//        addDots();


        fadeOutAnimation.setDuration(700);
        fadeOutAnimation.setFillAfter(true);
        fadeInAnimation.setDuration(700);
        fadeInAnimation.setFillAfter(true);

         btn_login = findViewById(R.id.btn_signin);
       btn_home = findViewById(R.id.btn_start);

        txtskip = findViewById(R.id.txt_skip);
       txtdot = findViewById(R.id.txtdot);
       txtdot2 = findViewById(R.id.txtdot2);
        txtdot3 = findViewById(R.id.txtdot3);
         txtdot4 = findViewById(R.id.txtdot4);
       pager = findViewById(R.id.pager);
       dots= findViewById(R.id.dots);
       txtdot.setText(Html.fromHtml("&#8226;"));
        txtdot2.setText(Html.fromHtml("&#8226;"));
        txtdot3.setText(Html.fromHtml("&#8226;"));
        txtdot4.setText(Html.fromHtml("&#8226;"));
        pager.addOnPageChangeListener(changeListener);
        txtskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                firstTime = false;
                editor.putBoolean("firstTime",firstTime);
                editor.apply();
                startActivity(new Intent(OnboardingScreenActivity.this,SigninActivity.class));

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
               firstTime = false;
                editor.putBoolean("firstTime",firstTime);
                editor.apply();
                startActivity(new Intent(OnboardingScreenActivity.this,LoginActivity.class));
           finish();
            }
        });


        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnboardingScreenActivity.this,AddCardActivity.class));
                finish();
            }
        });

        final SliderAdapter adapter = new SliderAdapter(this);
        pager.setAdapter(adapter);

    }

//    private void  addDots(){
//        dotings = new TextView[4];
////        dots.removeAllViews();
//        for (int i = 0; i <dotings.length; i++ ){
//            dotings[i] = new TextView(this);
//            dotings[i].setText(Html.fromHtml("&#8226;"));
//            dotings[i].setTextSize(30);
//
////            dots.addView(dotings[i]);
//        }


//    }


    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            final int count = position;
            if (count == 0){
                txtdot.setTextColor(getResources().getColor(R.color.materialblack));

            }else {
                    txtdot.setTextColor(getResources().getColor(R.color.dotscolor));
            }
            if (count == 1){
                txtdot2.setTextColor(getResources().getColor(R.color.materialblack));
            }else {
                txtdot2.setTextColor(getResources().getColor(R.color.dotscolor));
            }
            if (count == 2){
//                btn_login.setAnimation(fadeOutAnimation);
//                btn_home.setAnimation(fadeOutAnimation);
                btn_home.setVisibility(View.GONE);
                btn_login.setVisibility(View.GONE);
                txtdot3.setTextColor(getResources().getColor(R.color.materialblack));
            }else {
                txtdot3.setTextColor(getResources().getColor(R.color.dotscolor));
            }
            if (count == 3){
               btn_home.setVisibility(View.VISIBLE);
               btn_login.setVisibility(View.VISIBLE);
                txtskip.setVisibility(View.INVISIBLE);
                txtdot4.setTextColor(getResources().getColor(R.color.materialblack));
            }else {
                btn_home.setVisibility(View.INVISIBLE);
                btn_login.setVisibility(View.INVISIBLE);
                txtskip.setVisibility(View.VISIBLE);
                txtdot4.setTextColor(getResources().getColor(R.color.dotscolor));

            }




        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onPageSelected(int position) {
//            addDots(position);

position++;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };


}