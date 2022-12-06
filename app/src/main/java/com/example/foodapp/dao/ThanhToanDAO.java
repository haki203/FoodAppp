package com.example.foodapp.dao;

import static com.example.foodapp.views.HomeActivity.user;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodapp.models.HDCT;
import com.example.foodapp.models.HoaDon;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ThanhToanDAO {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context c;
    public  ThanhToanDAO(Context c){
        this.c=c;
    }
    public void deleteData(String id){
        db.collection("giohang").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error deleting document", e);
                    }
                });
    }
    public void upHDCT(HoaDon hd){
        Map<String, Object> product = new HashMap<>();

        product.put("name",hd.getNameSP());
        product.put("maHoaDon",hd.getIdHD());
        product.put("price",hd.getPrice());
        product.put("idUser",hd.getIdUser());
        product.put("diachi",hd.getDiaChi());
        product.put("thoigian",hd.getTime());
        product.put("tenNguoiDung",hd.getNameUser());

// Add a new document with a generated ID
        db.collection("hoadon")
                .add(product)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());}
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);}
                });

    }

}
