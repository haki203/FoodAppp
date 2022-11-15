package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitleToolBar();
    }
    public void register(View view){
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    //hàm này chỉ thể hiện tên activity thôi, ko có liên quan tới chức năng code đâu 😘
    private void setTitleToolBar(){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Main Activity");
        }
    }
}