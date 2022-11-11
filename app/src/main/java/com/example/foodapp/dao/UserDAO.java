package com.example.foodapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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
    User user = null;
    public Boolean register(String username, String password) {
        // Create a new user with a first and last name
        Map<String, Object> item = new HashMap<>();
        item.put("username", username);
        item.put("password", password);

        item.put("name","null" );
        item.put("ngaysinh","null" );
        item.put("diachi","null" );
        item.put("id",username);
        if (user == null) {
            // Add a new document with a generated ID
            db.collection("users")
                    .add(item)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(c.getApplicationContext(), "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }

        return true;
    }
    boolean check=false;

    public boolean checkLogin(String taikhoan){
        // getData from firebase
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String username = map.get("username").toString();
                                if(username.equalsIgnoreCase(taikhoan)){
                                    check=true;
                                }
                                Log.d("Tag checklogin",""+ username);
                            }
                        } else {

                        }
                    }
                });
        if(check==true){
            return  true;
        }
        return false;
    }
    public String findNumber(String sample) {
            char[] chars = sample.toCharArray();
            StringBuilder sb = new StringBuilder();
            for(char c : chars){
                if(Character.isDigit(c)){
                    sb.append(c);
                }
            }
            return sb.toString();

    }
    public boolean loginGoogle(String taikhoan){
        // check taikhoan nay da co chua
        if(checkLogin(taikhoan)==true){
            return false;
        }
// Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();

        user.put("username",taikhoan );
        user.put("password","null" );
        user.put("name","null" );
        user.put("ngaysinh","null" );
        user.put("diachi","null" );
        String s1=taikhoan.substring(0,4);
        String s2=findNumber(taikhoan);
        user.put("id",s1+s2);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });
        return true;
    }

}
