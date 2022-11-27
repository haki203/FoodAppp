package com.example.foodapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodapp.R;
import com.example.foodapp.models.Cart;
import com.example.foodapp.views.HomeActivity;
import com.example.foodapp.views.ThanhToanActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class ThanhToanAdapter extends BaseAdapter {
    ArrayList<Cart> list;
    Activity activity;
    public ThanhToanAdapter(Activity activity , ArrayList<Cart> list){
        this.activity=activity;
        this.list=list;
    }
    public ThanhToanAdapter(){

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
        view = inflater.inflate(R.layout.item_thanh_toan, null);

        // Đặt chữ cho từng view trong danh sách.
        TextView tvAmount = (TextView) view.findViewById(R.id.tvAmount);
        TextView tvName = (TextView) view.findViewById(R.id.tvTenSP);
        TextView tvGia = (TextView) view.findViewById(R.id.tvGia);
        ImageButton btnDelete=view.findViewById(R.id.btn_deleteTT);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Xác nhận xóa !");
                builder.setMessage("Bạn chắc chắn muốn xóa?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(activity.getApplicationContext(),"Xóa "+list.get(i).getNameProduct()+" thành công",Toast.LENGTH_SHORT).show();
                        list.remove(i);
                        ((ThanhToanActivity)activity).onClDelete(list);
                        tvAmount.setText(list.get(i).getAmount()+"");
                        tvName.setText(list.get(i).getNameProduct());
                        tvGia.setText(list.get(i).getPrice()+" vnd");
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        tvAmount.setText(list.get(i).getAmount()+"");
        tvName.setText(list.get(i).getNameProduct());
        tvGia.setText(list.get(i).getPrice()+" vnd");

        // Trả về view kết quả.
        return view;
    }
}
