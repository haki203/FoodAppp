package com.example.foodapp.views;

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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.dao.UserDAO;
import com.example.foodapp.models.User;

public class RegisterActivity extends AppCompatActivity {
    EditText edtName,edtSDT,edtGmail,pass,newPass,cfPass,edtDiachi;
    ImageButton btnBack;
    Button btnSave;
    String sdt,password,name,gmail,diachi;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtName=findViewById(R.id.edtName);
        edtSDT=findViewById(R.id.edtNumber);
        edtGmail=findViewById(R.id.edtGmail);
        pass=findViewById(R.id.edtPassword);
        cfPass=findViewById(R.id.edtCFPassword);
        btnBack=findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,WelcomeActivity.class);
                startActivity(i);
            }
        });
        edtDiachi=findViewById(R.id.edtDiachi);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gmail=extras.getString("email");
            name=extras.getString("name");
            edtGmail.setText(gmail);
            edtName.setText(name);
        }
    }
    public void onRegister(View view){
         sdt =edtSDT.getText().toString();
         password = pass.getText().toString();
         name=edtName.getText().toString();
         gmail = edtGmail.getText().toString();
         diachi = edtDiachi.getText().toString();

        if(sdt.equals("")){
            showDiaLog("Số điện thoại không được để trống");
            edtSDT.requestFocus();
        }
        else if(sdt.length()<10 || sdt.length()>11){
            showDiaLog("Số điện thoại không đúng định dạng");
            edtSDT.requestFocus();
        }
        else if(password.equals("")){
            showDiaLog("Mật khẩu không được để trống");
            pass.requestFocus();
        }
        else if(password.length()<5){
            showDiaLog("Mật khẩu phải nhiều hơn 4 ký tự");
            pass.requestFocus();
        }
        else if(name.length()<8){
            showDiaLog("Vui lòng nhập họ và tên");
            edtName.requestFocus();
        }
        else if(gmail.length()<0){
            showDiaLog("Vui lòng nhập Gmail");
            edtGmail.requestFocus();
        }
        else if(diachi.length()<0){
            showDiaLog("Vui lòng nhập địa chỉ");
            edtDiachi.requestFocus();
        }

        else {
            Toast.makeText(RegisterActivity.this, "Đang kiểm tra thông tin ...", Toast.LENGTH_SHORT).show();
            User user = new User(name,sdt,diachi,password,gmail);
            UserDAO dao = new UserDAO(RegisterActivity.this);
            dao.register(user);
        }

    }
    public void showDiaLog(String mess){
        new AlertDialog.Builder(RegisterActivity.this)
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
    public void ShowHidePass1(View view){

        if(view.getId()==R.id.show_pass_btn1){

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