package com.example.foodapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.models.HoaDon;
import com.example.foodapp.models.SanPham;

import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter {
    ArrayList<String> list;
    Activity activity;
    public SearchAdapter(Activity activity , ArrayList<String> list){
        this.activity=activity;
        this.list=list;
    }
    @Override
    public int getCount() {
        return 0;
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
        view = inflater.inflate(R.layout.one_item_search, null);

        // Đặt chữ cho từng view trong danh sách.
        TextView name = (TextView) view.findViewById(R.id.txtNameSearch);



        name.setText(list.get(i)+"");



        // Trả về view kết quả.
        return view;
    }
}
