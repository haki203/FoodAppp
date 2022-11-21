package com.example.foodapp.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodapp.Inferface.CartInterface;
import com.example.foodapp.R;
import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.fragment.FrmCart;
import com.example.foodapp.fragment.FrmHome;
import com.example.foodapp.fragment.FrmNotification;
import com.example.foodapp.fragment.FrmUser;
import com.example.foodapp.fragment.FrmWallet;
import com.example.foodapp.models.SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity  {
    private BottomNavigationView botNav;


    public static final int SCROLL_DELTA = 15; // Pixel.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        botNav = findViewById(R.id.bottom_nav);
        ReplaceFrm(new FrmHome());
        FloatingActionButton fl = findViewById(R.id.fl_btn);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPhamDAO dao = new SanPhamDAO(HomeActivity.this);
                try {
                    ArrayList<SanPham> listGioHang = dao.getGioHang();
                    ReplaceFrm(new FrmCart());
                } catch (InterruptedException e) {
                    Toast.makeText(HomeActivity.this,"Lỗi khi lấy thông tin giỏ hàng",Toast.LENGTH_SHORT).show();

                }

            }
        });
        botNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        ReplaceFrm(new FrmHome());
                        break;
                    case R.id.action_wallet:
                        ReplaceFrm(new FrmWallet());
                        break;
                    case R.id.action_bell:
                        ReplaceFrm(new FrmNotification());
                        break;
                    case R.id.action_user:
                        ReplaceFrm(new FrmUser());
                        break;
                }
                return true;
            }
        });
    }

    private void ReplaceFrm(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frLayout, fragment);
        fragmentTransaction.commit();
    }


}