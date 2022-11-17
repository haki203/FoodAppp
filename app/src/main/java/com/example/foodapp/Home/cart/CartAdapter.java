package com.example.foodapp.Home.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewholder> {
    private List<Cart> list;
    private Context context;

    public CartAdapter (Context context) {
        this.context = context;
    }

    public void setData(List<Cart> list) {
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
    public void onBindViewHolder(@NonNull CartViewholder holder, int position) {
        Cart cart = list.get(position);
        if (cart != null) {
            holder.tvName.setText(cart.getNameProduct());
            holder.tvPrice.setText(cart.getPrice().toString());
            holder.tvAmout.setText(cart.getAmount());
            holder.imgProduct.setImageResource(cart.getPhoto());

            holder.btnTang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tvAmout.setText(cart.getAmount()+1);
                }
            });

            holder.btnGiam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tvAmout.setText(cart.getAmount()-1);
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
        private CircleImageView imgProduct;
        private Button btnTang, btnGiam;
        public CartViewholder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvAmout = itemView.findViewById(R.id.tv_amount);
            imgProduct = itemView.findViewById(R.id.img_product);
            btnTang = itemView.findViewById(R.id.btn_tang);
            btnGiam = itemView.findViewById(R.id.btn_giam);
        }
    }
}
