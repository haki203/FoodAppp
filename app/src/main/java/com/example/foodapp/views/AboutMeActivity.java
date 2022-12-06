package com.example.foodapp.views;

import static com.example.foodapp.views.HomeActivity.user;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.dao.UserDAO;
import com.example.foodapp.models.Cart;

import java.util.ArrayList;

public class AboutMeActivity extends AppCompatActivity {
    EditText edtName,edtSDT,edtDiaChi,edtGmail,edtPass,edtNewPass,edtCFNewPass;
    ImageButton btnBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        edtName = findViewById(R.id.edtName);
        edtSDT = findViewById(R.id.edtSDT);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtGmail = findViewById(R.id.edtGmail);
        edtPass = findViewById(R.id.et_matKhauHienTai);
        edtNewPass = findViewById(R.id.et_matKhauMoi);
        edtCFNewPass = findViewById(R.id.edtCFPassword);
        btnBack = findViewById(R.id.btnBack);
        edtName.setText(user.getName());
        edtSDT.setText(user.getSdt());
        edtDiaChi.setText(user.getDiaChi());
        edtPass.setText(user.getPassword());
        edtGmail.setText(user.getDiaChi());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AboutMeActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });

    }
    public void onClickSave(View view){
        String name =edtName.getText().toString();
        String sdt = edtSDT.getText().toString();
        String gmail = edtGmail.getText().toString();
        String password = edtPass.getText().toString();
        String Npassword = edtNewPass.getText().toString();
        String CNpassword = edtCFNewPass.getText().toString();

        String diachi = edtDiaChi.getText().toString();
        if(name.equals("")){
            showDiaLog("Tên không được để trống");
            edtName.requestFocus();
        }
        else if(sdt.equals("")){
            showDiaLog("SDT không được để trống");
            edtSDT.requestFocus();
        }
        else if(gmail.equals("")){
            showDiaLog("Gmail không được để trống");
            edtGmail.requestFocus();
        }
        else if(password.equals("")){
            showDiaLog("Password không được để trống");
            edtPass.requestFocus();
        }
        else if(diachi.equals("")){
            showDiaLog("Địa chỉ không được để trống");
            edtDiaChi.requestFocus();
        }
        else if(Npassword.length()>0){
            if((password.equals(Npassword))){
                showDiaLog("Mật khẩu mới trùng với mật khẩu cũ");
                edtNewPass.requestFocus();
            }
            else if(Npassword.length()<5){
                showDiaLog("Password phải nhiều hơn 5 ký tự");
                edtNewPass.requestFocus();
            }
            else if(Npassword.equals(CNpassword)==false){
                showDiaLog("Password không trùng khớp");
                edtCFNewPass.requestFocus();
            }
        }
        else {
            showDiaLog("Lưu cài đặt thành công");

        }


    }
    public void ShowHide(View view){
        if(view.getId()==R.id.show_pass_btn){
            if(edtPass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_eye);
                //Show Password
                edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_eye);
                //Hide Password
                edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
    public void ShowHide1(View view){
        if(view.getId()==R.id.show_pass_btn1){
            if(edtPass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_eye);
                //Show Password
                edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_eye);
                //Hide Password
                edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
    public void ShowHide2(View view){
        if(view.getId()==R.id.show_pass_btn2){
            if(edtPass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_eye);
                //Show Password
                edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_eye);
                //Hide Password
                edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
    public void showDiaLog(String mess){
         new AlertDialog.Builder(AboutMeActivity.this)
                .setTitle("Thông báo!")
                .setMessage(mess)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}