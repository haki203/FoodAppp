package com.example.foodapp.dao;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.foodapp.models.SanPham;
import com.example.foodapp.models.User;
import com.example.foodapp.views.AboutMeActivity;
import com.example.foodapp.views.CartActivity;
import com.example.foodapp.views.HomeActivity;
import com.example.foodapp.views.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    Context c;
    public UserDAO(Context c){
        this.c=c;
    }
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void upFirebase(User user) {
        // Create a new user with a first and last name
        Map<String, Object> item = new HashMap<>();
        item.put("sdt", user.getSdt());
        item.put("password", user.getPassword());
        item.put("name",user.getName() );
        item.put("gmail",user.getGmail() );
        item.put("diachi",user.getDiaChi() );
            // Add a new document with a generated ID
            db.collection("users")
                    .add(item)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("Tag","Up firebase thanh cong");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Tag","Up firebase ko thanh cong");

                        }
                    });

    }

    public User login(String sdtt,String passwordd ){
        // dang nhap bang google
        // getData from firebase
        User user = new User();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String sdt = map.get("sdt").toString();
                                String pass = map.get("password").toString();
                                if(sdt.equalsIgnoreCase(sdtt) && pass.equalsIgnoreCase(passwordd)){
                                    Intent i = new Intent(c.getApplicationContext(), HomeActivity.class);
                                    i.putExtra("user",getUser(sdtt,passwordd));
                                    c.startActivity(i);
                                }
                                else{
                                    showDiaLog("Đăng nhập thất bại, tài khoản hoặc mật khẩu không đúng");
                                }
                            }
                        } else {

                        }
                    }
                });
        return user;
    }

    public User getUser(String sdtt,String passwordd){
        ArrayList<User> listUser = new ArrayList<User>();
        User user = new User();
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
                                Log.d("name :",""+name);
                                String sdt = map.get("sdt").toString();
                                String gmail = map.get("gmail").toString();
                                String diachi = map.get("diachi").toString();
                                String password = map.get("password").toString();
                                listUser.add(new User(id,name,sdt,diachi,password,gmail));
                                Log.d("ListUser :",listUser.size()+"");
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

        for (int i=0;i<=listUser.size();i++){
            if (sdtt.equalsIgnoreCase(listUser.get(i).getSdt()) && passwordd.equalsIgnoreCase(listUser.get(i).getPassword())){
                user = listUser.get(i);
            }
        }
        Log.d("Login success ",":"+user.getSdt());
        return user;
    }

    public void register(User user){

        // getData from firebase
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String sdt = map.get("sdt").toString();
                                String name = map.get("name").toString();
                                String password = map.get("password").toString();

                                if(name.equalsIgnoreCase(user.getName()) ||sdt.equals(user.getSdt()) ){
                                    showDiaLog("Tài khoản này đã tồn tại,Đăng nhập ngay");
                                    Intent i = new Intent(c.getApplicationContext(),LoginActivity.class);
                                    i.putExtra("user",user);
                                    c.startActivity(i);

                                }
                                else{
                                    upFirebase(user);
                                    Intent i = new Intent(c.getApplicationContext(), LoginActivity.class);
                                    i.putExtra("user",user);
                                    c.startActivity(i);
                                }
                            }
                        }
                    }
                });
    }


    public void showDiaLog(String mess){
        new AlertDialog.Builder(c)
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
