package com.example.foodapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodapp.R;
import com.example.foodapp.models.Cart;
import com.example.foodapp.views.HomeActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewholder> {
    private List<Cart> list;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context context;
    private Map<String, Object> item = new HashMap<>();

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
            holder.tvAmout.setText(cart.getAmount().toString());
            holder.tvAmout.setText(String.valueOf(cart.getAmount()));

            ((HomeActivity)context).loadImageURL(cart.getPhoto(), holder.imgProduct);

            holder.btnTang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.put("amount", String.valueOf(cart.getAmount()+1));
                    item.put("nameProduct", cart.getNameProduct());
                    item.put("price", String.valueOf(cart.getPrice()));
                    item.put("photo", String.valueOf(cart.getPhoto()));
                    item.put("id",cart.getId());
                    item.put("idUser",cart.getIdUser());

                    db.collection("giohang").document(cart.getId())
                            .set(item)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    ((HomeActivity)context).loadDataCart();
                                    Toast.makeText(context, "cập nhật thành công", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });
                    Log.d("TAG", "onClick: "+cart.getAmount());
                }
            });

            holder.btnGiam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.put("amount", String.valueOf(cart.getAmount()-1));
                    item.put("nameProduct", cart.getNameProduct());
                    item.put("price", String.valueOf(cart.getPrice()));
                    item.put("photo", String.valueOf(cart.getPhoto()));
                    item.put("id",cart.getId());
                    item.put("idUser",cart.getIdUser());

                    db.collection("giohang").document(cart.getId())
                            .set(item)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    ((HomeActivity)context).loadDataCart();
                                    Toast.makeText(context, "cập nhật thành công", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });
                    Log.d("TAG", "onClick: "+cart.getAmount());
                }
            });

            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((HomeActivity)context).onClickDelete(cart);
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
        private Button btnTang, btnGiam, btnDelete;
        public CartViewholder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvAmout = itemView.findViewById(R.id.tv_amount);
            imgProduct = itemView.findViewById(R.id.img_product);
            btnTang = itemView.findViewById(R.id.btn_tang);
            btnGiam = itemView.findViewById(R.id.btn_giam);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

}
