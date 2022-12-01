package com.example.foodapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.foodapp.R;
import com.example.foodapp.adapter.BannerAdapter;
import com.example.foodapp.dao.SanPhamDAO;
import com.example.foodapp.models.Banner;
import com.example.foodapp.models.Cart;
import com.example.foodapp.models.SanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class FrmHome extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private BannerAdapter bannerAdapter;
    private List<Banner> mListBanner;
    private Timer mTimer;

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

//        viewPager = findViewById(R.id.viewBaner);
        ViewPager viewPager = view.findViewById(R.id.viewBanner);

//        circleIndicator = findViewById(R.id.circle_indicator_banner);
        CircleIndicator circleIndicator = getView().findViewById(R.id.circle_indicator_banner);
        mListBanner = getListBanner();

        bannerAdapter = new BannerAdapter(getActivity().getApplicationContext(), getListBanner());
        viewPager.setAdapter(bannerAdapter);
        circleIndicator.setViewPager(viewPager);

        bannerAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoBannerSlideShow();



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

//code xu ly Banner
    private List<Banner> getListBanner(){
        List<Banner> list = new ArrayList<>();
        // muốn thay đổi phần add ảnh, xem https://www.youtube.com/watch?v=J1zCHTXjegI phút 14:20

        list.add(new Banner(R.drawable.foodbannerhome1));
        list.add(new Banner(R.drawable.foodbannerhome2));
        list.add(new Banner(R.drawable.foodbannerhome3));
        list.add(new Banner(R.drawable.foodbannerhome4));

        return list;
    }
    private void autoBannerSlideShow(){
        if (mListBanner == null || mListBanner.isEmpty() || viewPager == null){

            return;
        }
        //Init timer
        if (mTimer == null){
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListBanner.size()-1;
                        if (currentItem < totalItem){
                            currentItem ++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000); // 3000 == 3 giay se chuyen qua banner tiep theo

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }
}