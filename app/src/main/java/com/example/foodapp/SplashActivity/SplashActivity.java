package com.example.foodapp.SplashActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class SplashActivity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private CircleIndicator3 mCircleIndicator3;
    private List<Photo> mListPhoto;
    private TextView tvSkip, tvNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mViewPager2 = findViewById(R.id.viewPager2);
        mCircleIndicator3 = findViewById(R.id.circle3);
        tvNext = findViewById(R.id.tv_next);
        tvSkip = findViewById(R.id.tv_skip);


        mListPhoto = getListPhoto();

        PhotoViewPager2Adapter photoAdapter = new PhotoViewPager2Adapter(mListPhoto);
        mViewPager2.setAdapter(photoAdapter);

        mCircleIndicator3.setViewPager(mViewPager2);

        mViewPager2.setPageTransformer(new DepthPageTransformer());

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Next();
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Skip();
            }
        });
    }

    private List<Photo> getListPhoto() {
        List<Photo> listPhoto = new ArrayList<>();
        listPhoto.add(new Photo(R.drawable.img));
        listPhoto.add(new Photo(R.drawable.img_1));
        listPhoto.add(new Photo(R.drawable.img_2));
        listPhoto.add(new Photo(R.drawable.img_3));

        return listPhoto;
    }

    private void Next() {
        if (mViewPager2.getCurrentItem() == mListPhoto.size()-1) {
        } else {
            mViewPager2.setCurrentItem(mViewPager2.getCurrentItem()+1);
        }
    }

    private void Skip() {
        mViewPager2.setCurrentItem(mListPhoto.size()-1);
    }
}