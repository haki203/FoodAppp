package com.example.foodapp.fragment;

<<<<<<< HEAD
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
=======
import static java.lang.String.format;

>>>>>>> 376da4fbab864aa673499e34803aeeabd871e953
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.Button;
import android.widget.Toast;
=======
import android.widget.TextView;
>>>>>>> 376da4fbab864aa673499e34803aeeabd871e953

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
<<<<<<< HEAD
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Abstract.DeleteItemCart;
import com.example.foodapp.Inferface.CartInterface;
import com.example.foodapp.R;
import com.example.foodapp.adapter.CartAdapter;
import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.models.SanPham;
import com.example.foodapp.views.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
=======
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodapp.R;
import com.example.foodapp.models.Cart;
import com.example.suppermarket.Home.cart.CartAdapter;

>>>>>>> 376da4fbab864aa673499e34803aeeabd871e953

import java.util.ArrayList;
import java.util.Map;

<<<<<<< HEAD

public class FrmCart extends Fragment implements CartInterface {
    RecyclerView recyclerView;
    ArrayList<SanPham> list;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    BottomNavigationView navigationView ;
    // TODO: Rename and change types and number of parameters
    public static FrmHome newInstance(String param1, String param2) {
        FrmHome fragment = new FrmHome();
=======
public class FrmCart extends Fragment {
    private RecyclerView rcvCart;
    private CartAdapter cartAdapter;
    private ArrayList<Cart> listCart;
    private TextView tvTongGia;

    public FrmCart() {

    }

    public static FrmCart newInstance(ArrayList<Cart> listCart) {

        FrmCart fragment = new FrmCart();
>>>>>>> 376da4fbab864aa673499e34803aeeabd871e953
        Bundle args = new Bundle();
        args.putSerializable("list_cart", listCart);
        fragment.setArguments(args);
        Log.d(">>>>>>>>>>>>>..TAG", "newInstance: "+listCart+"");
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
            SanPhamDAO dao = new SanPhamDAO(getContext());
            list = new ArrayList<SanPham>();
            try {
                list=dao.getGioHang();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
=======
        if (getArguments() != null) {
            listCart = (ArrayList<Cart>) getArguments().getSerializable("list_cart");
            Log.d("TAG", "onCreate: "+ listCart);
        }
        Log.d("TAG", "onCreate: "+getArguments());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frm_cart, container, false);
>>>>>>> 376da4fbab864aa673499e34803aeeabd871e953
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
<<<<<<< HEAD
        recyclerView=view.findViewById(R.id.rcvGioHang);
        Button btnBack = view.findViewById(R.id.icon_left);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), HomeActivity.class);
                getActivity().startActivity(i);
                getActivity().finish();
            }
        });

        Button btnShowCart = view.findViewById(R.id.btn);
        btnShowCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAdapter();
            }
        });
        setAdapter();
        enableSwipeToDeleteAndUndo();
    }
=======
        tvTongGia = view.findViewById(R.id.tvTongGia);
        rcvCart = view.findViewById(R.id.rcv_cart);
        cartAdapter = new CartAdapter(getContext());

        Log.e("TAG", "onViewCreated: "+listCart );

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCart.setLayoutManager(linearLayoutManager);

        cartAdapter.setData(listCart);
        rcvCart.setAdapter(cartAdapter);
>>>>>>> 376da4fbab864aa673499e34803aeeabd871e953

        Integer tongGia = 0;
        for (Cart cart : listCart) {
            tongGia = tongGia + Integer.valueOf((int) (cart.getPrice()*cart.getAmount()));
        }

<<<<<<< HEAD
        View view=inflater.inflate(R.layout.fragment_frm_cart, container, false);
        return view;
    }
    private void setAdapter(){
        CartAdapter adapter = new CartAdapter(list,getActivity().getApplicationContext());
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
=======

        tvTongGia.setText(tongGia.toString() + "VND");
>>>>>>> 376da4fbab864aa673499e34803aeeabd871e953
    }

    private void enableSwipeToDeleteAndUndo() {
        DeleteItemCart swipeToDeleteCallback = new DeleteItemCart(getActivity().getApplicationContext()) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return super.onMove(recyclerView, viewHolder, viewHolder1);
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                SanPham sp  = list.get(viewHolder.getPosition());
//                final int position = viewHolder.getAdapterPosition();
//                final String item = mAdapter.getData().get(position);


                SanPhamDAO dao = new SanPhamDAO(getContext());
                onDeleteCart(sp);
                delCart(viewHolder.getPosition());
//                mAdapter.removeItem(position);
                readData();

            }

        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }
    public void delCart(int i){
        list.remove(i);
        Log.d("TAg list","list="+list.size());
        Log.d("TAg del","i="+i);
        setAdapter();
    }
    public void onDeleteCart(SanPham sp) {
        new AlertDialog.Builder(getContext())
                .setTitle("Xác nhận")
                .setMessage("Xoá không phục hồi được!!")
                .setNegativeButton("Huỷ", null)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.collection("giohang")
                                .document(sp.getIdDB())
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getActivity().getApplicationContext(), "Xoá thành công",
                                                Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity().getApplicationContext(), "Xoá không thành công",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                })
                .show();
    }
    private void readData(){
        ArrayList<SanPham> list = new ArrayList<>();

        // fill data to fragment
        db.collection("giohang")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String id = map.get("id").toString();
                                String name = map.get("name").toString();
                                String loai = map.get("loai").toString();
                                String mota = map.get("mota").toString();
                                String tinhtrang = map.get("tinhtrang").toString();
                                String hinh = map.get("hinh").toString();
                                String gia = map.get("gia").toString();
                                String sl = map.get("soluong").toString();
                                String idDB= document.getId();
                                list.add(new SanPham(name,loai,mota,tinhtrang,hinh,id,gia,sl,idDB));
                            }
                            setAdapter();
                        } else {

                        }
                    }
                });

    }
}