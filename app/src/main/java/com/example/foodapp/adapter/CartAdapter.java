package com.example.foodapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.dao.changeIMG;
import com.example.foodapp.models.SanPham;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewholder> {
    private ArrayList<SanPham> list;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;
    public CartAdapter(ArrayList<SanPham> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }
    public void setData(ArrayList<SanPham> list ) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);

        return new CartViewholder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull CartViewholder holder, @SuppressLint("RecyclerView") int position) {
        SanPham sp ;
        sp = list.get(position);
        if (sp != null) {
            holder.tvName.setText(sp.getName());
            holder.tvPrice.setText(sp.getGia());
            holder.tvAmout.setText(sp.getSoLuong());
            changeIMG change = new changeIMG();
            holder.imgProduct.setImageBitmap(change.convertStringToBitmap(sp.getHinh()));

            holder.btnTang.setOnClickListener(new View.OnClickListener() {
                int i =Integer.parseInt(sp.getSoLuong().toString());
                @Override
                public void onClick(View v) {
                    SanPham sp = new SanPham();
                    sp = list.get(position);
                    i++;
                    sp.setSoLuong(""+i);
                    holder.tvAmout.setText(""+i);
                }
            });

            holder.btnGiam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SanPham sp = new SanPham();
                    sp = list.get(position);
                    if(Integer.parseInt(holder.tvAmout.getText().toString())>1){
                        holder.tvAmout.setText(""+(Integer.parseInt(holder.tvAmout.getText().toString())-1));
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class CartViewholder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPrice, tvAmout;
        private ImageView imgProduct;
        private Button btnTang, btnGiam,btnBack;
        public CartViewholder(@NonNull View itemView) {
            super(itemView);
            btnBack=itemView.findViewById(R.id.icon_left);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvAmout = itemView.findViewById(R.id.tv_amount);
            imgProduct = itemView.findViewById(R.id.img_product);
            btnTang = itemView.findViewById(R.id.btn_tang);
            btnGiam = itemView.findViewById(R.id.btn_giam);
        }
    }
}
