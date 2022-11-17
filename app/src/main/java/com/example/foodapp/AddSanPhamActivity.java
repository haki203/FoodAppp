package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.dao.changeIMG;
import com.example.foodapp.models.SanPham;

import java.io.ByteArrayOutputStream;

public class AddSanPhamActivity extends AppCompatActivity {
    ImageView ivHinh;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_san_pham);

        String name="Phô mai tuyết";
        String gia="10.000";
        Drawable img = getResources().getDrawable( R.drawable.phomai );
        changeIMG change= new changeIMG();
        String hinh=change.convertBitmapToString(change.drawableToBitmap(img));
        String id="004";
        String loai="Ăn vặt";
        String mota="Phô mai ngon mlem mlem";
        String tinhtrang ="Còn hàng";

        SanPham sp = new SanPham(name,loai,mota,tinhtrang,hinh,id,gia);
        SanPhamDAO dao = new SanPhamDAO(this);
        dao.addData(sp);
    }


}