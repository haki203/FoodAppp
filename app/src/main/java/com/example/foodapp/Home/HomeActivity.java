package com.example.foodapp.Home;

import android.os.Bundle;
import android.view.MenuItem;
<<<<<<< HEAD
=======
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
>>>>>>> 5e7d9be45f47b375344a4c7f927226a882fcbb3a

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

<<<<<<< HEAD
=======
        getDataIntent();
>>>>>>> 5e7d9be45f47b375344a4c7f927226a882fcbb3a
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

    private void getDataIntent(){
        String strPhoneNumber = getIntent().getStringExtra("phone_number");
        TextView tvUserInfor = findViewById(R.id.tv_user_inforHome);
        tvUserInfor.setText(strPhoneNumber);

    }

}