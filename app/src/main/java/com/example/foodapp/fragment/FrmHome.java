package com.example.foodapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodapp.R;
import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.models.Cart;
import com.example.foodapp.models.SanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


public class FrmHome extends Fragment {

    ArrayList<SanPham> list;
    BottomNavigationView navigationView ;
    // TODO: Rename and change types and number of parameters
    public static FrmHome newInstance(ArrayList<SanPham> list) {
        FrmHome fragment = new FrmHome();
        Bundle args = new Bundle();
        args.putSerializable("list",list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list = (ArrayList<SanPham>) getArguments().getSerializable("list");
            Log.d("list size home:",""+list.size());
            ArrayList<SanPham> listSPA = new ArrayList<>();
            for(int i=0;i<=list.size()-1;i++){
                if(list.get(i).getLoai().equalsIgnoreCase("Ăn vặt")){
                    listSPA.add(list.get(i));
                    Log.d("listSPA size home:",""+listSPA.size());
                }
            }
            ReplaceFrm(DoAnFragment.newInstance(listSPA));
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navigationView.setSelectedItemId(R.id.do_an_vat);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.do_an_vat:
                        Log.d("list size home:",""+list.size());
                        ArrayList<SanPham> listSPA = new ArrayList<>();
                        for(int i=0;i<=list.size()-1;i++){
                            if(list.get(i).getLoai().equalsIgnoreCase("Ăn vặt")){
                                listSPA.add(list.get(i));
                                Log.d("listSPA size home:",""+listSPA.size());
                            }
                        }
                        ReplaceFrm(DoAnFragment.newInstance(listSPA));
                        break;
                    case R.id.com:
                        ArrayList<SanPham> listSPC = new ArrayList<>();
                        for(int i=0;i<=list.size()-1;i++){
                            if(list.get(i).getLoai().equalsIgnoreCase("Cơm")){
                                listSPC.add(list.get(i));
                            }
                        }
                        ReplaceFrm(DoAnFragment.newInstance(listSPC));
                        break;
                    case R.id.tra_sua:
                        ArrayList<SanPham> listSPT = new ArrayList<>();
                        for(int i=0;i<=list.size()-1;i++){
                            if(list.get(i).getLoai().equalsIgnoreCase("Trà Sữa")){
                                listSPT.add(list.get(i));
                            }
                        }
                        ReplaceFrm(DoAnFragment.newInstance(listSPT));                        break;
                    case R.id.banh:
                        ArrayList<SanPham> listSPB = new ArrayList<>();
                        for(int i=0;i<=list.size()-1;i++){
                            if(list.get(i).getLoai().equalsIgnoreCase("Bánh")){
                                listSPB.add(list.get(i));
                            }
                        }
                        ReplaceFrm(DoAnFragment.newInstance(listSPB));
                        break;
                }
                return true;
            }
        });
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_frm_home, container, false);
        navigationView=view.findViewById(R.id.bottom_navigation_home);

        return view;
    }
    private void ReplaceFrm(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_home, fragment);
        fragmentTransaction.commit();
    }
}