package com.example.foodapp.Home.Frm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Home.cart.Cart;
import com.example.foodapp.Home.cart.CartAdapter;
import com.example.foodapp.R;

import java.util.ArrayList;

public class FrmCart extends Fragment {
    private RecyclerView rcvCart;
    private CartAdapter cartAdapter;
    private ArrayList<Cart> listCart;

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frm_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvCart = view.findViewById(R.id.rcv_cart);
        cartAdapter = new CartAdapter(getContext());

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvCart.setLayoutManager(linearLayoutManager);

        cartAdapter.setData(listCart);
        rcvCart.setAdapter(cartAdapter);

    }
}