package com.example.foodapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {

    private static final String TAG = OTPActivity.class.getName();

    private EditText edtPhoneNumber_OTPActivity;
    private Button btnVerifyPhoneNumber_OTPActivity;
    private FirebaseAuth mAuth_OTPActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);

        setTitleToolBar();
        unitui();

        mAuth_OTPActivity = FirebaseAuth.getInstance();

        btnVerifyPhoneNumber_OTPActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPhoneNumber_OTPActivity = edtPhoneNumber_OTPActivity.getText().toString().trim();
                onClickVerifyPhoneNumber(strPhoneNumber_OTPActivity);
                
            }
        });
    }


    private void setTitleToolBar(){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("OTP Activity");
        }
    }

    private void unitui(){
        edtPhoneNumber_OTPActivity = findViewById(R.id.edt_phone_number_OTPActivity);
        btnVerifyPhoneNumber_OTPActivity = findViewById(R.id.btn_verify_phone_number_OTPActivity);

    }
    private void onClickVerifyPhoneNumber(String strPhoneNumber_otpActivity) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth_OTPActivity)
                        .setPhoneNumber(strPhoneNumber_otpActivity)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(OTPActivity.this,"Verification Failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verifycationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verifycationId, forceResendingToken);
                                goToVerifyOtpActivity(strPhoneNumber_otpActivity, verifycationId);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth_OTPActivity.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            //hiển thị thông tin cần hiện trong user
                            goToHomeActivity(user.getPhoneNumber());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(OTPActivity.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goToHomeActivity(String phoneNumber) {
        Intent intent_OTPActivity=new Intent(this, HomeActivity.class);
        intent_OTPActivity.putExtra("phone_number", phoneNumber);
        startActivity(intent_OTPActivity);
    }
    private void goToVerifyOtpActivity(String strPhoneNumber_otpActivity, String verifycationId) {
        Intent intent_OTPActivity=new Intent(this, VerifyOtpActivity.class);
        intent_OTPActivity.putExtra("phone_number", strPhoneNumber_otpActivity);
        intent_OTPActivity.putExtra("verifycation_id", verifycationId);
        startActivity(intent_OTPActivity);
    }
}