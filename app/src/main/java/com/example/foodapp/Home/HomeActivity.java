package com.example.foodapp.Home;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodapp.Home.Frm.FrmHome;
import com.example.foodapp.Home.Frm.FrmNotification;
import com.example.foodapp.Home.Frm.FrmUser;
import com.example.foodapp.Home.Frm.FrmWallet;
import com.example.foodapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView botNav;


    public static final int SCROLL_DELTA = 15; // Pixel.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        botNav = findViewById(R.id.bottom_nav);
        ReplaceFrm(new FrmHome());
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