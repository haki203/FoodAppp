package com.example.foodapp.Home.Frm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.foodapp.R;
import com.google.android.material.navigation.NavigationBarView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrmHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrmHome extends Fragment {

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_frm_home, container, false);
        return view;
    }
}