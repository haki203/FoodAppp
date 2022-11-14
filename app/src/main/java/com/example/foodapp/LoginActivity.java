package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodapp.dao.UserDAO;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    TextView tvError;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername=findViewById(R.id.edtNumberLogin);
        edtPassword=findViewById(R.id.edtPasswordLogin);
        tvError=findViewById(R.id.tvError);
    }
    public void Login(View view){
        String username=edtUsername.getText().toString();
        String password=edtPassword.getText().toString();

        UserDAO dao = new UserDAO(LoginActivity.this);
        dao.loginEmail(username,password);
    }
}