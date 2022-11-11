package com.example.foodapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.foodapp.Home.HomeActivity;
import com.example.foodapp.dao.UserDAO;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class WelcomeActivity extends AppCompatActivity {
    //login gg
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // dang  nhap goggle
        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(WelcomeActivity.this,gso);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        SignInButton sib = findViewById(R.id.login_google_button);
//ktra co login hay chua
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(WelcomeActivity.this);
        if(account!= null){
            // go home
            Intent i = new Intent(WelcomeActivity.this,HomeActivity.class);
            startActivity(i);
            finish();
        }

        sib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleIntent = gsc.getSignInIntent();
                googleLauncher.launch(googleIntent);
            }
        });

//end google
    }

    // nhan kq tra ve tu gg signin
    ActivityResultLauncher<Intent> googleLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();

                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
////////////
                    GoogleSignInAccount account = null;
                    try {
                        account = task.getResult(ApiException.class);
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
                    String email = account.getEmail();
                    // ktra email neu co r thi thoi , neu ch co thi tao moi email do

                    UserDAO userDAO = new UserDAO(WelcomeActivity.this);
                    if(userDAO.loginGoogle(email)==false){
                        Toast.makeText(WelcomeActivity.this, "Email "+email+" dang ky thang cong", Toast.LENGTH_SHORT).show();
                        // chuyen qua man hinh home
                        Intent i = new Intent(WelcomeActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toast.makeText(WelcomeActivity.this, "Email "+email+" dang nhap thang cong", Toast.LENGTH_SHORT).show();
                        // chuyen qua man hinh home
                        Intent i = new Intent(WelcomeActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    }
//                    if(userDAO.checkLogin(email)==false){
//                        Log.d("Tag: ", "email "+email +" da co roi");
//                    }else{
//                        userDAO.loginGoogle(email);
//                    }


//                    /////////
//                    try {
//
//                        Log.d("Tag","onAcResult "+email);
//
//                        // chuyen qua man hinh home
//                        Intent i = new Intent(WelcomeActivity.this, HomeActivity.class);
//                        startActivity(i);
//                        finish();
//                    }catch (Exception e){
//
//                    }

                }
            }
    );
}