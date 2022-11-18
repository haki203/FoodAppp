package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataIntent();

    }
    public void register(View view){
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    private void getDataIntent(){
        String strPhoneNumber = getIntent().getStringExtra("phone_number");

        TextView tvUserInfor = findViewById(R.id.tv_user_inforMain);
        tvUserInfor.setText(strPhoneNumber);

    }

}