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
<<<<<<< HEAD:app/src/main/java/com/example/foodapp/adapter/CartAdapter.java
    private ArrayList<SanPham> list;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;
    public CartAdapter(ArrayList<SanPham> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }
    public void setData(ArrayList<SanPham> list ) {
=======
    private List<Cart> list;
    private Context context;

    public CartAdapter (Context context) {
        this.context = context;
    }

    public void setData(List<Cart> list) {
>>>>>>> 4f74e166789148f70cb0973bc680fa633b23da79:app/src/main/java/com/example/foodapp/Home/cart/CartAdapter.java
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
<<<<<<< HEAD:app/src/main/java/com/example/foodapp/adapter/CartAdapter.java
    public void onBindViewHolder(@NonNull CartViewholder holder, @SuppressLint("RecyclerView") int position) {
        SanPham sp ;
        sp = list.get(position);
        if (sp != null) {
            holder.tvName.setText(sp.getName());
            holder.tvPrice.setText(sp.getGia());
            holder.tvAmout.setText(sp.getSoLuong());
            changeIMG change = new changeIMG();
            holder.imgProduct.setImageBitmap(change.convertStringToBitmap(sp.getHinh()));
=======
    public void onBindViewHolder(@NonNull CartViewholder holder, int position) {
        Cart cart = list.get(position);
        if (cart != null) {
            holder.tvName.setText(cart.getNameProduct());
            holder.tvPrice.setText(cart.getPrice().toString());
            holder.tvAmout.setText(cart.getAmount());
            holder.imgProduct.setImageResource(cart.getPhoto());
>>>>>>> 4f74e166789148f70cb0973bc680fa633b23da79:app/src/main/java/com/example/foodapp/Home/cart/CartAdapter.java

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
