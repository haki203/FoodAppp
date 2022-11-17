package com.example.foodapp.Home.Frm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodapp.R;
import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.fragment.ComFragment;
import com.example.foodapp.fragment.DoAnFragment;
import com.example.foodapp.models.SanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrmHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrmHome extends Fragment {
    private ScrollView scrollView;

    private Button buttonScrollUp;
    private Button buttonScrollDown;
    ArrayList<SanPham> list;
    BottomNavigationView navigationView ;
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
        SanPhamDAO dao = new SanPhamDAO(getContext());
        list = new ArrayList<SanPham>();
        try {
            list=dao.getData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("list sp:",""+list.size());

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ReplaceFrm(DoAnFragment.newInstance(list));
        navigationView.setSelectedItemId(R.id.do_an_vat);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.do_an_vat:
                        ReplaceFrm(DoAnFragment.newInstance(list));
                        break;
                    case R.id.com:
                        ReplaceFrm(new ComFragment());
                        break;
//                    case R.id.action_bell:
//                        ReplaceFrm(new FrmNotification());
//                        break;
//                    case R.id.action_user:
//                        ReplaceFrm(new FrmUser());
//                        break;
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