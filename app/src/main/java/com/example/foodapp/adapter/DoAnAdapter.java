package com.example.foodapp.adapter;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import static com.example.foodapp.views.HomeActivity.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.models.Cart;
import com.example.foodapp.models.SanPham;
import com.example.foodapp.views.RegisterActivity;
import com.example.foodapp.views.SanPhamActivity;

import java.util.ArrayList;
import java.io.Serializable;
public class DoAnAdapter extends RecyclerView.Adapter<DoAnAdapter.ViewHolder> {
    //Dữ liệu hiện thị là danh sách sinh viên

    private ArrayList<SanPham> list;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    public DoAnAdapter(ArrayList<SanPham> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View studentView =
                inflater.inflate(R.layout.custom_lv_doan_frm, parent, false);

        ViewHolder viewHolder = new ViewHolder(studentView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SanPham sp = (SanPham) list.get(position);

        holder.name.setText(sp.getName());
        holder.gia.setText(sp.getGia()+"");
        loadImageURL(sp.getHinh(),holder.hinh);
        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double gia = Double.valueOf(list.get(position).getGia());
                // get ID sp
                Intent intent = new Intent(mContext.getApplicationContext(), SanPhamActivity.class);
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("gia",gia);
                intent.putExtra("mota",list.get(position).getMoTa());
                intent.putExtra("hinh",list.get(position).getHinh());
                intent.putExtra("id",list.get(position).getIdDB());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });
        holder.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // get ID sp
//                String id = list.get(position).getId();
//                Toast.makeText(v.getContext(),  " | " + " Added "+id, Toast.LENGTH_SHORT).show();
                SanPhamDAO dao = new SanPhamDAO(mContext.getApplicationContext());
                Cart cart = new Cart(
                        list.get(position).getHinh(),
                        list.get(position).getName(),
                        Double.parseDouble(list.get(position).getGia()),
                        1,
                        sp.getIdDB(),user.getId());
                dao.addGioHang(cart);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public TextView name;
        public TextView gia;
        public ImageView hinh;
        public Button btnAddCart;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            name = itemView.findViewById(R.id.txtTenSP);
            gia = itemView.findViewById(R.id.txtGia);
            btnAddCart = itemView.findViewById(R.id.btnAddCart);
            hinh=itemView.findViewById(R.id.ivHinh);

//            //Xử lý khi nút add gio hang được bấm
//            btnAddCart.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Toast.makeText(view.getContext(),name.getText() +" | " + " Demo function", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
    public void loadImageURL(String url, ImageView circleImageView) {
        Glide.with(mContext.getApplicationContext()).load(url).into(circleImageView);
    }

}