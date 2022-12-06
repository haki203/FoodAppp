package com.example.foodapp.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import static com.example.foodapp.views.LoginActivity.list;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.fragment.FrmCart;
import com.example.foodapp.fragment.FrmHome;
import com.example.foodapp.fragment.FrmSearch;
import com.example.foodapp.fragment.FrmUser;
import com.example.foodapp.models.Cart;
import com.example.foodapp.models.SanPham;
import com.example.foodapp.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity  {
    private BottomNavigationView botNav;
    private ArrayList<Cart> listCart;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static User user;
    FloatingActionButton btnCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        botNav = findViewById(R.id.bottom_nav);
        FloatingActionButton fl = findViewById(R.id.fl_btn);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user= (User) extras.getSerializable("user");
        }
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.frLayout, FrmHome.newInstance(list))
                .commit();
        btnCart = findViewById(R.id.fl_btn);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataCart();
            }
        });

        botNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.frLayout, FrmHome.newInstance(list))
                                .commit();
                        break;
                    case R.id.action_search:
                        ReplaceFrm(new FrmSearch());
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

    public void loadDataCart() {

        db.collection("giohang")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        listCart = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String nameProduct = map.get("nameProduct").toString();
                                String amount =  map.get("amount").toString();
                                String price =  map.get("price").toString();
                                String photo =  map.get("photo").toString();
                                String id = map.get("id").toString();
                                String idUser = map.get("idUser").toString();
                                Cart cart = new Cart(photo, nameProduct, Double.valueOf(price), Integer.valueOf(amount),id,idUser);
                                cart.setId(document.getId());
                                listCart.add(cart);
                                Log.e(">>>>>>>>>.TAG", "onComplete: "+listCart+"");
                            }
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .setReorderingAllowed(true)
                                    .replace(R.id.frLayout, FrmCart.newInstance(listCart))
                                    .commit();
                        }
                    }
                });
    }

    public void onClickDelete(Cart cart) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

        builder.setTitle("Xác nhận xóa !");
        builder.setMessage("Bạn chắc chắn muốn xóa?");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(">>>>>>>>>TAG", "onClick: "+cart.getId());
                db.collection("giohang")
                        .document(cart.getId())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(HomeActivity.this, "Xoá thành công "+cart.getId(),
                                        Toast.LENGTH_LONG).show();
                                loadDataCart();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(HomeActivity.this, "Xoá không thành công",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




    public void loadImageURL(String url, CircleImageView circleImageView) {
        Glide.with(this).load(url).into(circleImageView);
    }
}