package com.example.foodapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.DoAnAdapter;
import com.example.foodapp.models.SanPham;

import java.util.ArrayList;


public class DoAnFragment extends Fragment {
    RecyclerView recyclerView;

    public DoAnFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DoAnFragment newInstance(ArrayList<SanPham> list) {
        DoAnFragment fragment = new DoAnFragment();
        Bundle args = new Bundle();
        args.putSerializable("list",list);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.listFood);
        ArrayList<SanPham> list= (ArrayList<SanPham>) getArguments().getSerializable("list");
        Log.d("TAg list size:",list.toString());
        DoAnAdapter adapter = new DoAnAdapter(list,getActivity().getApplicationContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_food, container, false);

        return view;
    }
}