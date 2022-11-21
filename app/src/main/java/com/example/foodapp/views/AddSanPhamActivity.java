package com.example.foodapp.views;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.dao.changeIMG;
import com.example.foodapp.models.SanPham;

public class AddSanPhamActivity extends AppCompatActivity {
    ImageView ivHinh;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_san_pham);

        String name="Bánh trung thu";
        String gia="15.000";
        Drawable img = getResources().getDrawable( R.drawable.banhtrungthu );
        changeIMG change= new changeIMG();
        String hinh =change.convertBitmapToString(change.drawableToBitmap(img));
        String id="016";
        String loai="Bánh";
        String mota="bánh trung thu cho trẻ em";
        String tinhtrang ="Còn hàng";

        SanPham sp = new SanPham(name,loai,mota,tinhtrang,hinh,id,gia);
        SanPhamDAO dao = new SanPhamDAO(this);
        dao.addData(sp);
    }


}