package com.example.foodapp.views;

import static com.example.foodapp.views.HomeActivity.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.adapter.ThanhToanAdapter;
import com.example.foodapp.dao.ThanhToanDAO;
import com.example.foodapp.models.Cart;
import com.example.foodapp.models.HDCT;
import com.example.foodapp.models.HoaDon;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ThanhToanActivity extends AppCompatActivity {
    EditText maGiamGia,diaChi,loiNhan;
    RadioButton rbCod;
    Button btnThanhToan;
    ListView lvDonHang;
    TextView tvTien;
    ArrayList<Cart> listCart;
    ThanhToanAdapter adapter = new ThanhToanAdapter();
    Double tien;
    ThanhToanDAO dao = new ThanhToanDAO(ThanhToanActivity.this);
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
        diaChi.setText(user.getName() +"\n"+user.getSdt()+"\n"+ user.getDiaChi());

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
    public void onClickThanhToan(View view){

        Toast.makeText(ThanhToanActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
        String tien = tvTien.getText().toString();
        String tenSP="";
        Date date = new Date();

        for (Cart cart:listCart) {
            Log.d("Thanh toan","xoa "+cart.getId()+" thanh cong");
            tenSP +=cart.getNameProduct()+" x"+cart.getAmount()+"\n";
            dao.deleteData(cart.getId());
        }
        HoaDon hd = new HoaDon(tenSP,user.getName(),date.toString(),user.getDiaChi(),findNumber(user.getId()+date),user.getId(),tien);
        dao.upHDCT(hd);
        Intent i = new Intent(ThanhToanActivity.this,HomeActivity.class);
        startActivity(i);

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
}