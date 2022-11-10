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
        if (user == null) {
            // Add a new document with a generated ID
            db.collection("users")
                    .add(item)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }

        return false;
    }


    public boolean checkLogin(String taikhoan){
        ArrayList<User> list = new ArrayList<>();
        // getData from firebase
        db.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String username = map.get("username").toString();
                                list.add(new User(username));
                            }
                        } else {

                        }
                    }
                });
        for(int i=0;i<=list.size();i++){
            if(list.get(i).getUsername().equalsIgnoreCase(taikhoan)){
                return false;
            }
        }
        return true;
    }
    public boolean loginGoogle(String taikhoan){
// Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("username", "Ada");

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
//    public User login(String email, String password){
//        User appUser = null;
//        SQLiteDatabase db = database.getReadableDatabase();
//        String sql = "SELECT ID, EMAIL, PASSWORD, ROLE FROM USERS WHERE EMAIL = ? ";
//        Cursor cursor = db.rawQuery(sql, new String[] {email});
//
//        try {
//            //co van de
//
//            if (cursor.getCount() == 0){
//                Log.d(">>>>>>TAG", "rong roi");
//            }
//
//            if (cursor.moveToFirst()){
//                while (!cursor.isAfterLast()){
//                    Integer _id = cursor.getInt(0);
//                    String _email = cursor.getString(1);
//                    String _password =cursor.getString(2);
//                    Integer _role = cursor.getInt(3);
//                    if(!_password.equals(password)) break;
//
//                    appUser = new AppUser(_id,_role, _email,null);
//                    cursor.moveToNext();
//                }
//            }
//        } catch (Exception e){
//            Log.d(">>>>>>>>>>>>TAG",e.getMessage());
//        } finally {
//            if (cursor != null && !cursor.isClosed()) cursor.close();
//        }
//
//        return appUser;
//    }
}
