package com.example.foodapp.fragment;

import static java.lang.String.format;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodapp.R;
import com.example.foodapp.adapter.CartAdapter;
import com.example.foodapp.models.Cart;
import com.example.foodapp.views.HomeActivity;
import com.example.foodapp.views.ThanhToanActivity;


import java.util.ArrayList;

public class FrmCart extends Fragment {
    private RecyclerView rcvCart;
    private CartAdapter cartAdapter;
    private ArrayList<Cart> listCart;
    private TextView tvTongGia;

    public FrmCart() {

    }

    public static FrmCart newInstance(ArrayList<Cart> listCart) {

        FrmCart fragment = new FrmCart();
        Bundle args = new Bundle();
        args.putSerializable("list_cart", listCart);
        fragment.setArguments(args);
        Log.d(">>>>>>>>>>>>>..TAG", "newInstance: "+listCart+"");
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTongGia = view.findViewById(R.id.tvTongGia);
        rcvCart = view.findViewById(R.id.rcv_cart);
        Button btnThanhToan=view.findViewById(R.id.btnThanhToan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double tien = Double.parseDouble(tvTongGia.getText().toString());
                Intent i = new Intent(getContext(), ThanhToanActivity.class);
                i.putExtra("tien",tien);
                i.putExtra("listCart",listCart);
                getActivity().startActivity(i);
                getActivity().finish();
            }
        });
        Button btnBack = view.findViewById(R.id.icon_left);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), HomeActivity.class);
                getActivity().startActivity(i);
                getActivity().finish();
            }
        });
        cartAdapter = new CartAdapter(getContext());

        Log.e("TAG", "onViewCreated: "+listCart );

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCart.setLayoutManager(linearLayoutManager);

        cartAdapter.setData(listCart);
        rcvCart.setAdapter(cartAdapter);

        Integer tongGia = 0;
        for (Cart cart : listCart) {
            tongGia = tongGia + Integer.valueOf((int) (cart.getPrice()*cart.getAmount()));
        }


        tvTongGia.setText(tongGia.toString());
    }
}