package com.example.foodapp.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.foodapp.R;

public class ThanhToanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Double tien = extras.getDouble("tien");

        }
    }
}