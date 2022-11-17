package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodapp.Home.HomeActivity;
import com.example.foodapp.dao.UserDAO;

public class RegisterActivity extends AppCompatActivity {
    EditText sdt,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sdt=findViewById(R.id.edtNumber);
        pass=findViewById(R.id.edtPassword);

    }
    public void onRegister(View view){
        String number =sdt.getText().toString();
        String password = pass.getText().toString();
        if(number.equals("")){
            Toast.makeText(this,"Sdt ko dc trong",Toast.LENGTH_SHORT).show();
            sdt.requestFocus();
        }
        else if(number.length()<10 || number.length()>11){
            Toast.makeText(this,"Sdt ko dung dinh dang",Toast.LENGTH_SHORT).show();
            sdt.requestFocus();
        }
        else if(password.length()<6){
            Toast.makeText(this,"Password nhieu hon 6 ky tu",Toast.LENGTH_SHORT).show();
            sdt.requestFocus();
        }
        else if(password.equals("")){
            Toast.makeText(this,"Password ko dc trong",Toast.LENGTH_SHORT).show();
            pass.requestFocus();
        }
        else {
            UserDAO dao = new UserDAO(RegisterActivity.this);
            dao.checkRegister(number,password);
        }

    }
    public void ShowHidePass(View view){

        if(view.getId()==R.id.show_pass_btn){

            if(pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_eye);

                //Show Password
                pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_eye);

                //Hide Password
                pass.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
}