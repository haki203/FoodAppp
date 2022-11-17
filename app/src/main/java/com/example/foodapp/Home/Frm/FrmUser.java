package com.example.foodapp.Home.Frm;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodapp.R;
import com.example.foodapp.RegisterActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class FrmUser extends Fragment{
    ImageView logout;
    GoogleSignInClient gsc;
    GoogleSignInAccount account;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(getActivity().getApplicationContext(), gso);
        account = GoogleSignIn.getLastSignedInAccount(getActivity().getApplicationContext());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_frm_user, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("LOGIN_STATUS",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("isLoggedIn");
                editor.remove("id");
                editor.remove("role");
                editor.remove("email");
                editor.apply();

                //google
                if(account != null){
                    gsc.signOut()
                            .addOnCompleteListener(getActivity(),
                                    new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Intent homeIntent = new Intent(getActivity().getApplicationContext(), RegisterActivity.class);
                                            startActivity(homeIntent);

                                        }
                                    });
                }else{
                    Intent homeIntent = new Intent(getActivity().getApplicationContext(), RegisterActivity.class);
                    startActivity(homeIntent);
                    getActivity().finish();
                }
            }
        });
    }


}