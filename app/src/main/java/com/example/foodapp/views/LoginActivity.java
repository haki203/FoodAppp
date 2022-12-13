package com.example.foodapp.views;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.dao.UserDAO;
import com.example.foodapp.fragment.FrmHome;
import com.example.foodapp.models.SanPham;
import com.example.foodapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    ImageButton btnBack;
    public  static  ArrayList<SanPham> list = new ArrayList<SanPham>();
    TextView tvError;
    ArrayList<User> listUser = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    User user= new User();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadData();
        edtUsername=findViewById(R.id.edtNumberLogin);
        edtPassword=findViewById(R.id.edtPasswordLogin);
        btnBack=findViewById(R.id.back);
        tvError=findViewById(R.id.tvError);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,WelcomeActivity.class);
                startActivity(i);
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user= (User) extras.getSerializable("user");
        }
        String phoneNumber = user.getSdt();
        String password = user.getPassword();
        if(phoneNumber!=null){
            edtUsername.setText(phoneNumber);
        }
        if(password!=null){
            edtPassword.setText(password);
        }

    }
    public void loadData() {

        list = new ArrayList<SanPham>();
        // getData from firebase
        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String id = document.getId();
                                String name = map.get("name").toString();
                                String loai = map.get("loai").toString();
                                String mota = map.get("mota").toString();
                                String tinhtrang = map.get("tinhtrang").toString();
                                String hinh = map.get("hinh").toString();
                                String gia = map.get("gia").toString();
                                list.add(new SanPham(name,loai,mota,tinhtrang,hinh,gia,id));
                            }
                        }

                    }
                });
    }
    public void Login(View view){
        String username=edtUsername.getText().toString();
        String password=edtPassword.getText().toString();

        UserDAO dao = new UserDAO(LoginActivity.this);
        Toast.makeText(LoginActivity.this,"Đang đăng nhập ...",Toast.LENGTH_SHORT).show();
        getListUser(username,password);

    }
    public void getListUser(String sdt,String pass){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String id = document.getId();
                                String name = map.get("name").toString();
                                String sdt = map.get("sdt").toString();
                                String password = map.get("password").toString();
                                String gmail = map.get("gmail").toString();
                                String diachi = map.get("diachi").toString();
                                Log.d("Tag",""+sdt+" & "+sdt);
                                Log.d("Tag",""+password+" & "+pass);
                                User user = new User(id,name,sdt,diachi,password,gmail);
                                listUser.add(user);

                            }
                            for(int i=0;i<listUser.size();i++){
                                if(sdt.equalsIgnoreCase(listUser.get(i).getSdt()) && pass.equalsIgnoreCase(listUser.get(i).getPassword())){
                                    Intent in = new Intent(LoginActivity.this,HomeActivity.class);
                                    in.putExtra("user",listUser.get(i));
                                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                                    startActivity(in);
                                    finish();
                                }


                            }
                            for(int i=0;i<listUser.size();i++){
                                if(sdt.equalsIgnoreCase(listUser.get(i).getSdt())==false && pass.equalsIgnoreCase(listUser.get(i).getPassword())==false){
                                    showDiaLog("Tài khoản hoặc mật khẩu không đúng ");
                                    break;
                                }


                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    public void ShowHidePasss(View view){

        if(view.getId()==R.id.show_pass_btnn){

            if(edtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_eye);

                //Show Password
                edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_eye);

                //Hide Password
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
    public void showDiaLog(String mess){
        new AlertDialog.Builder(this)
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