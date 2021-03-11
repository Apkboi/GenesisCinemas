package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView pop = findViewById(R.id.pop);
        final TextView txt = findViewById(R.id.title);
        final TextView txttag = findViewById(R.id.txtGenesis);


        final Animation fadeOutAnimation = new AlphaAnimation(1.0f,0.0f);
        final Animation fadeInAnimation = new AlphaAnimation(0.0f,1.0f);
        fadeOutAnimation.setDuration(700);
        fadeOutAnimation.setFillAfter(true);
        fadeInAnimation.setDuration(700);
        fadeInAnimation.setFillAfter(true);
        pop.startAnimation(fadeInAnimation);
        txt.startAnimation(fadeInAnimation);

        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(MainActivity.this,OnboardingScreenActivity.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        CountDownTimer timer = new CountDownTimer(2000,30) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                pop.startAnimation(fadeOutAnimation);
                txt.startAnimation(fadeOutAnimation);
                txttag.startAnimation(fadeOutAnimation);

            }
        }.start();
    }
}