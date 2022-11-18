package com.example.foodapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.dao.changeIMG;
import com.example.foodapp.models.SanPham;

import java.util.ArrayList;

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
        changeIMG change = new changeIMG();
        holder.hinh.setImageBitmap(change.convertStringToBitmap(sp.getHinh()));

        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get ID sp
                String name = list.get(position).getName();
                Toast.makeText(v.getContext(),  " | " + " Name "+name, Toast.LENGTH_SHORT).show();

            }
        });
        holder.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // get ID sp
//                String id = list.get(position).getId();
//                Toast.makeText(v.getContext(),  " | " + " Added "+id, Toast.LENGTH_SHORT).show();
                SanPhamDAO dao = new SanPhamDAO(mContext.getApplicationContext());
                dao.addGioHang(list.get(position));
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


}