package com.example.foodapp.views;

import android.os.Bundle;
import android.view.MenuItem;
<<<<<<< HEAD:app/src/main/java/com/example/foodapp/views/HomeActivity.java
import android.view.View;
import android.widget.Toast;
=======
<<<<<<< HEAD
=======
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
>>>>>>> 5e7d9be45f47b375344a4c7f927226a882fcbb3a
>>>>>>> 4f74e166789148f70cb0973bc680fa633b23da79:app/src/main/java/com/example/foodapp/Home/HomeActivity.java

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodapp.R;
import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.fragment.FrmCart;
import com.example.foodapp.fragment.FrmHome;
import com.example.foodapp.fragment.FrmNotification;
import com.example.foodapp.fragment.FrmUser;
import com.example.foodapp.fragment.FrmWallet;
import com.example.foodapp.models.SanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView botNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
<<<<<<< HEAD:app/src/main/java/com/example/foodapp/views/HomeActivity.java
=======

<<<<<<< HEAD
=======
        getDataIntent();
>>>>>>> 5e7d9be45f47b375344a4c7f927226a882fcbb3a
>>>>>>> 4f74e166789148f70cb0973bc680fa633b23da79:app/src/main/java/com/example/foodapp/Home/HomeActivity.java
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