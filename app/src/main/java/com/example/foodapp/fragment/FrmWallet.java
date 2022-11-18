package com.example.foodapp.fragment;

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

<<<<<<< HEAD:app/src/main/java/com/example/foodapp/fragment/FrmWallet.java
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrmWallet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrmWallet extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FrmWallet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrmWallet.
     */
    // TODO: Rename and change types and number of parameters
    public static FrmWallet newInstance(String param1, String param2) {
        FrmWallet fragment = new FrmWallet();
=======
public class FrmCart extends Fragment {
    private RecyclerView rcvCart;
    private CartAdapter cartAdapter;
    private ArrayList<Cart> listCart;

    public FrmCart() {

    }

    public static FrmCart newInstance(ArrayList<Cart> listCart) {

        FrmCart fragment = new FrmCart();
>>>>>>> 4f74e166789148f70cb0973bc680fa633b23da79:app/src/main/java/com/example/foodapp/Home/Frm/FrmCart.java
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
<<<<<<< HEAD:app/src/main/java/com/example/foodapp/fragment/FrmWallet.java
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frm_wallet, container, false);
=======
        return inflater.inflate(R.layout.fragment_frm_cart, container, false);
>>>>>>> 4f74e166789148f70cb0973bc680fa633b23da79:app/src/main/java/com/example/foodapp/Home/Frm/FrmCart.java
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