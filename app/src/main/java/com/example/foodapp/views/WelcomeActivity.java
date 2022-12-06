package com.example.foodapp.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
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

    GoogleSignInClient gsc=null;
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

        sib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleIntent = gsc.getSignInIntent();
                googleLauncher.launch(googleIntent);
            }
        });

//end google
    }
    public void onSignIn(View view){
        Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(i);
    }
    public void onSignUp(View view){
        Intent i = new Intent(WelcomeActivity.this, RegisterActivity.class);
        startActivity(i);
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
                    String name =account.getDisplayName();

                    Log.d("Tag"," Emaill: "+email);

                    // ktra email neu co r thi thoi , neu ch co thi tao moi email do
//                    UserDAO userDAO = new UserDAO(WelcomeActivity.this);
//                    userDAO.login(email);
                    if(email.isEmpty()==false){
                        Intent i = new Intent(WelcomeActivity.this,RegisterActivity.class);
                        i.putExtra("email",email);
                        i.putExtra("name",name);
                        startActivity(i);
                    }
                }
            }
    );
}