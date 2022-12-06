package com.example.foodapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodapp.R;
import com.example.foodapp.SplashActivity.SplashActivity;

public class OpenningAppAnimation extends AppCompatActivity {
    private LottieAnimationView openningAppAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openning_app_animation);

        openningAppAnimation = findViewById(R.id.OpenningAppAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(i);
            }
        },5000);

    }
}