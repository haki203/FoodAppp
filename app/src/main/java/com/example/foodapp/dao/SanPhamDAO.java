package com.example.foodapp.dao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodapp.models.Cart;
import com.example.foodapp.models.SanPham;
import com.example.foodapp.models.User;
import com.example.foodapp.views.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanPhamDAO {
    Context c;
    public SanPhamDAO(Context c){
        this.c=c;
    }
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    User user = null;

    public ArrayList<SanPham> getData() throws InterruptedException {
        ArrayList<SanPham> list = new ArrayList<SanPham>();
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
                                Log.d("id product:",""+id);
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

        return list;
    }
//    public ArrayList<SanPham> getGioHang() throws InterruptedException {
//        ArrayList<SanPham> list = new ArrayList<SanPham>();
//        // getData from firebase
//        db.collection("giohang")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Map<String, Object> map = document.getData();
//                                String id = map.get("id").toString();
//                                String name = map.get("name").toString();
//                                String loai = map.get("loai").toString();
//                                String mota = map.get("mota").toString();
//                                String tinhtrang = map.get("tinhtrang").toString();
//                                String hinh = map.get("hinh").toString();
//                                String gia = map.get("gia").toString();
//                                String sl = map.get("soluong").toString();
//                                String idDB= document.getId();
//                                list.add(new SanPham(name,loai,mota,tinhtrang,hinh,id,gia,sl,idDB));
//                            }
//                        }
//                    }
//                });
//        return list;
//    }

//    public void addData(SanPham sp){
//        // Create a new user with a first and last name
//        Map<String, Object> product = new HashMap<>();
//
//        product.put("name",sp.getName() );
//        product.put("gia",sp.getGia() );
//        product.put("loai",sp.getLoai() );
//        product.put("id",sp.getIdDB() );
//        product.put("hinh",sp.getHinh() );
//        product.put("tinhtrang",sp.getTinhTrang() );
//        product.put("mota",sp.getMoTa() );
//
//// Add a new document with a generated ID
//        db.collection("products")
//                .add(product)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("TAG", "Error adding document", e);
//                    }
//                });
//    }
    public void addGioHang(Cart cart){
        // Create a new user with a first and last name
        Map<String, Object> product = new HashMap<>();

        product.put("nameProduct",cart.getNameProduct() );
        product.put("price",cart.getPrice() );
        product.put("id",cart.getId() );
        product.put("photo",cart.getPhoto() );
        product.put("amount",1 );

// Add a new document with a generated ID
        db.collection("giohang")
                .add(product)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(c.getApplicationContext(),    "Thêm "+cart.getNameProduct()+" vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                        Toast.makeText(c.getApplicationContext()," Thêm  vào giỏ thất bại ", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

