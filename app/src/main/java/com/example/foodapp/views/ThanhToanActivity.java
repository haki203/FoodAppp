package com.example.foodapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.adapter.ThanhToanAdapter;
import com.example.foodapp.models.Cart;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ThanhToanActivity extends AppCompatActivity {
    EditText maGiamGia,diaChi,loiNhan;
    RadioButton rbCod;
    Button btnThanhToan;
    ListView lvDonHang;
    TextView tvTien;
    ArrayList<Cart> listCart;
    ThanhToanAdapter adapter = new ThanhToanAdapter();
    Double tien;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tien = extras.getDouble("tien");
            listCart = (ArrayList<Cart>) extras.get("listCart");
        }
        lvDonHang=findViewById(R.id.lvDonHang);
        maGiamGia=findViewById(R.id.edtCodeSale);
        diaChi=findViewById(R.id.edtDiachi);
        loiNhan=findViewById(R.id.edtMess);
        rbCod=findViewById(R.id.rbCod);
        tvTien= findViewById(R.id.tvTien);

        loadData();

    }
    public void loadData(){
        ThanhToanAdapter adapter = new ThanhToanAdapter(ThanhToanActivity.this,listCart);
        lvDonHang.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        tvTien.setText(tien+"");
    }
    public void onClDelete(ArrayList<Cart> list){
        Double tongGia = 0.0;
        for (Cart cart : list) {
            tongGia = tongGia +  (cart.getPrice()*cart.getAmount());
        }
        tvTien.setText(""+tongGia);
    }

}