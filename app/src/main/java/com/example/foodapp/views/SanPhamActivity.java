package com.example.foodapp.views;

import static com.example.foodapp.views.HomeActivity.user;

import androidx.appcompat.app.AppCompatActivity;
import java.io.Serializable;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.models.Cart;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SanPhamActivity extends AppCompatActivity {
    Button btnBack,btnTang,btnGiam;
    TextView txtGia,txtName,txtMota,txtAmount;
    Double gia;
    Integer amount;
    String mota,name,hinh,id;
    Button btnAddCart;
    ImageView ivHinh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        btnBack=findViewById(R.id.btnBack);
        btnTang=findViewById(R.id.btnTang);
        btnGiam=findViewById(R.id.btnGiam);
        txtGia=findViewById(R.id.txtGia);
        ivHinh=findViewById(R.id.ivHinhSP);
        txtAmount=findViewById(R.id.txtAmount);
        btnAddCart=findViewById(R.id.btnAdd_Cart);
        txtName=findViewById(R.id.txtTenSP);
        txtMota=findViewById(R.id.txtMota);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name= (String) extras.getSerializable("name");
            gia= (Double) extras.getSerializable("gia");
            mota= (String) extras.getSerializable("mota");
            hinh= (String) extras.getSerializable("hinh");
            id= (String) extras.getSerializable("id");

        }
        txtName.setText(""+name);
        txtGia.setText(""+gia);
        txtMota.setText(""+mota);
        loadImageURL(hinh,ivHinh);
        amount=1;
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart(hinh,name,gia,amount,id,user.getId());
                SanPhamDAO dao = new SanPhamDAO(SanPhamActivity.this);
                dao.addGioHang(cart);
                Toast.makeText(SanPhamActivity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SanPhamActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAmount.setText(Integer.parseInt(txtAmount.getText().toString())+1);
            }
        });
        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(txtAmount.getText().toString())>0){
                    txtAmount.setText(Integer.parseInt(txtAmount.getText().toString())-1);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SanPhamActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    public void loadImageURL(String url, ImageView imageView) {
        Glide.with(this).load(url).into(imageView);
    }
}