package com.example.foodapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.models.Cart;
import com.example.foodapp.models.HoaDon;
import com.example.foodapp.views.ThanhToanActivity;

import java.util.ArrayList;

public class HoaDonAdapter extends BaseAdapter {
    ArrayList<HoaDon> list;
    Activity activity;
    public HoaDonAdapter(Activity activity , ArrayList<HoaDon> list){
        this.activity=activity;
        this.list=list;
    }
    public HoaDonAdapter(){

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();

        // Đổ dữ liệu vào biến View, view này chính là những gì nằm trong item_name.xml
        view = inflater.inflate(R.layout.one_item_lv_hoa_don, null);

        // Đặt chữ cho từng view trong danh sách.
        TextView tvMaHD = (TextView) view.findViewById(R.id.itIDHoaDon);
        TextView tvNameUser = (TextView) view.findViewById(R.id.itNameUser);
        TextView tvNameSP = (TextView) view.findViewById(R.id.itNameSP);
        TextView tvDiaChi = (TextView) view.findViewById(R.id.itDiaChi);
        TextView tvGia = (TextView) view.findViewById(R.id.itGiaTien);
        TextView tvThoiGian = (TextView) view.findViewById(R.id.itTime);


        tvMaHD.setText(list.get(i).getIdHD()+"");
        tvNameUser.setText(list.get(i).getNameUser()+"");
        tvNameSP.setText(list.get(i).getNameSP()+"");
        tvDiaChi.setText(list.get(i).getDiaChi()+"");
        tvGia.setText(list.get(i).getPrice()+" VND");
        tvThoiGian.setText(list.get(i).getTime()+"");


        // Trả về view kết quả.
        return view;
    }
}
