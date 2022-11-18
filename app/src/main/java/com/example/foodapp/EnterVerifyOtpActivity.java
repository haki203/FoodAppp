package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.Home.HomeActivity;
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

public class EnterVerifyOtpActivity extends AppCompatActivity {

    private static final String TAG = EnterVerifyOtpActivity.class.getName();

    private EditText edtOtp;
    private Button btnSendOtpCode;
    private TextView tvSendOtpAgain;

    private FirebaseAuth mAuth;

    private String mPhoneNumber;
    private String mVerifycationId;

    private PhoneAuthProvider.ForceResendingToken mForceResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        getDataIntent();

        unitui();

        mAuth = FirebaseAuth.getInstance();

        btnSendOtpCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOtp = edtOtp.getText().toString().trim();
                onClickSendOtpCode(strOtp);
            }
        });
        tvSendOtpAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSendOtpAgain();
            }
        });
    }

    private void getDataIntent(){
        mPhoneNumber = getIntent().getStringExtra("phone_number");
        mVerifycationId = getIntent().getStringExtra("verifycation_id");
    }


    private void onClickSendOtpCode(String strOtp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerifycationId, strOtp);
        signInWithPhoneAuthCredential(credential);
    }
    private void onClickSendOtpAgain() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mPhoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setForceResendingToken(mForceResendingToken)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(EnterVerifyOtpActivity.this,
                                        "Verification Failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verifycationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verifycationId, forceResendingToken);
                                mVerifycationId = verifycationId;
                                mForceResendingToken = forceResendingToken;
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
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
                                Toast.makeText(EnterVerifyOtpActivity.this,
                                        "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    private void goToHomeActivity(String phoneNumber) {
        Intent intent=new Intent(this, HomeActivity.class);
        intent.putExtra("phone_number", phoneNumber);
        startActivity(intent);
    }

    private void unitui(){
        edtOtp = findViewById(R.id.edt_otp_verifyOtp);
        btnSendOtpCode = findViewById(R.id.btn_send_otp_code);
        tvSendOtpAgain = findViewById(R.id.tv_send_otp_again_verifyOtp);

    }
}