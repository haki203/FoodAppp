package com.example.foodapp.views;

import static com.example.foodapp.views.HomeActivity.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.foodapp.R;
import com.example.foodapp.adapter.HoaDonAdapter;
import com.example.foodapp.models.HoaDon;
import com.example.foodapp.models.SanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class HoaDonActivity extends AppCompatActivity {
    ArrayList<HoaDon> list;
    ListView listView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        ImageButton btnBack = findViewById(R.id.hdBack);
        listView = findViewById(R.id.lvHoaDon);

        list = new ArrayList<>();
        // getData from firebase
        db.collection("hoadon")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String nameUser = map.get("tenNguoiDung").toString();
                                Log.d("Name:",nameUser);
                                String nameSP = map.get("name").toString();
                                String idUser = map.get("idUser").toString();
                                String idHD = map.get("maHoaDon").toString();
                                String price = map.get("price").toString();
                                String thoigian = map.get("thoigian").toString();
                                String diachi=map.get("diachi").toString();
                                HoaDon hd = new HoaDon(nameSP,nameUser,thoigian,diachi,idHD,idUser,price);
                                list.add(hd);
                                Log.d("List add thanh cong",hd.getNameUser());
                            }
                            setData(list);
                        }
                    }

                });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HoaDonActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });



    }
    public void setData(ArrayList<HoaDon> listt){
        ArrayList<HoaDon> listHD=new ArrayList<>();
        for(int i=0;i<listt.size();i++){
            Log.d("id:",user.getId()+" & "+listt.get(i).getIdUser());
            if(listt.get(i).getIdUser().equals(user.getId())){
                listHD.add(listt.get(i));
            }
        }
        HoaDonAdapter adapter = new HoaDonAdapter(HoaDonActivity.this,listHD);
        listView.setAdapter(adapter);
    }
}