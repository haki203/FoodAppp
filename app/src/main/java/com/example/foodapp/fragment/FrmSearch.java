package com.example.foodapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import static com.example.foodapp.views.LoginActivity.list;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.adapter.SearchAdapter;
import com.example.foodapp.models.SanPham;
import com.example.foodapp.views.SanPhamActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrmSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrmSearch extends Fragment {
    EditText edtSearch;
    ListView lvSearch;
    SanPham sp;
    public FrmSearch() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FrmSearch newInstance(String param1, String param2) {
        FrmSearch fragment = new FrmSearch();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
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
        return inflater.inflate(R.layout.fragment_frm_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // search

        lvSearch=getView().findViewById(R.id.lvSearch);
        edtSearch=getView().findViewById(R.id.edtSearch);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search =edtSearch.getText().toString();
                ArrayList<String> listSearch = new ArrayList<>();
                for(int i=0;i<list.size();i++){
                    try {
                        if(search.equalsIgnoreCase(list.get(i).getName().substring(0,search.length()))){
                            listSearch.add(list.get(i).getName());
                        }
                    }catch (Exception e){

                    }

                }
                ArrayAdapter adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,listSearch);
                lvSearch.setAdapter(adapter);
                lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String name = listSearch.get(position);
                        for(int i=0;i<list.size();i++){
                            if (name.equalsIgnoreCase(list.get(i).getName())){
                                sp = list.get(i);
                            }
                        }
                        Intent in = new Intent(getActivity().getApplicationContext(), SanPhamActivity.class);
                        in.putExtra("name",sp.getName());
                        in.putExtra("gia",Double.parseDouble(sp.getGia()));
                        in.putExtra("mota",sp.getMoTa());
                        in.putExtra("hinh",sp.getHinh());
                        in.putExtra("id",sp.getIdDB());
                        startActivity(in);
                    }
                });
//                if(edtSearch.getText().toString().isEmpty()){
//                    listSearch.clear();
//                    ArrayAdapter adapter1 = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,listSearch);
//                    lvSearch.setAdapter(adapter1);
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//search
    }
}