package com.example.foodapp.fragment;

<<<<<<< HEAD:app/src/main/java/com/example/foodapp/fragment/FrmCart.java
import android.annotation.SuppressLint;
import android.content.Intent;
=======
>>>>>>> 4f74e166789148f70cb0973bc680fa633b23da79:app/src/main/java/com/example/foodapp/Home/Frm/FrmHome.java
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD:app/src/main/java/com/example/foodapp/fragment/FrmCart.java
import android.widget.Button;
=======
>>>>>>> 4f74e166789148f70cb0973bc680fa633b23da79:app/src/main/java/com/example/foodapp/Home/Frm/FrmHome.java

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
<<<<<<< HEAD:app/src/main/java/com/example/foodapp/fragment/FrmCart.java
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.CartAdapter;
import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.models.SanPham;
import com.example.foodapp.views.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
=======

import com.example.foodapp.R;
import com.google.android.material.navigation.NavigationBarView;
>>>>>>> 4f74e166789148f70cb0973bc680fa633b23da79:app/src/main/java/com/example/foodapp/Home/Frm/FrmHome.java


<<<<<<< HEAD:app/src/main/java/com/example/foodapp/fragment/FrmCart.java
public class FrmCart extends Fragment {
    RecyclerView recyclerView;
    ArrayList<SanPham> list;
    BottomNavigationView navigationView ;
=======

public class FrmHome extends Fragment {

>>>>>>> 4f74e166789148f70cb0973bc680fa633b23da79:app/src/main/java/com/example/foodapp/Home/Frm/FrmHome.java
    // TODO: Rename and change types and number of parameters
    public static FrmHome newInstance(String param1, String param2) {
        FrmHome fragment = new FrmHome();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
<<<<<<< HEAD:app/src/main/java/com/example/foodapp/fragment/FrmCart.java
        SanPhamDAO dao = new SanPhamDAO(getContext());
        list = new ArrayList<SanPham>();
        try {
            list=dao.getGioHang();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rcvGioHang);
        setAdapter();

        Button btnBack = view.findViewById(R.id.icon_left);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), HomeActivity.class);
                getActivity().startActivity(i);
                getActivity().finish();
            }
        });
        Button btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView=view.findViewById(R.id.rcvGioHang);
                setAdapter();

            }
        });

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_frm_cart, container, false);
        return view;
    }
    private void ReplaceFrm(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_home, fragment);
        fragmentTransaction.commit();
    }
    private void setAdapter(){
        CartAdapter adapter = new CartAdapter(list,getActivity().getApplicationContext());
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
=======
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_frm_home, container, false);
        return view;
    }
>>>>>>> 4f74e166789148f70cb0973bc680fa633b23da79:app/src/main/java/com/example/foodapp/Home/Frm/FrmHome.java
}