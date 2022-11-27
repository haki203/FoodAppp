package com.example.foodapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class VerifyOtpActivity extends AppCompatActivity {

    static final String TAG = VerifyOtpActivity.class.getName();

    private EditText edtOtp_VerifyOtpActivity;
    private Button btnSendOtpCode_VerifyOtpActivity;
    private TextView tvSendOtpAgain_VerifyOtpActivity;

    private FirebaseAuth mAuth_VerifyOtpActivity;

    private String mPhoneNumber_VerifyOtpActivity;
    private String mVerifycationId_VerifyOtpActivity;

    private PhoneAuthProvider.ForceResendingToken mForceResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        getDataIntent();
        setTitleToolBar();
        unitui();

        mAuth_VerifyOtpActivity = FirebaseAuth.getInstance();

        btnSendOtpCode_VerifyOtpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOtp_VerifyOtpActivity = edtOtp_VerifyOtpActivity.getText().toString().trim();
                onClickSendOtpCode(strOtp_VerifyOtpActivity);
            }
        });
        tvSendOtpAgain_VerifyOtpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSendOtpAgain();
            }
        });
    }

    private void getDataIntent(){
        mPhoneNumber_VerifyOtpActivity = getIntent().getStringExtra("phone_number");
        mVerifycationId_VerifyOtpActivity = getIntent().getStringExtra("verifycation_id");
    }

    private void setTitleToolBar(){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Verify Otp Activity");
        }
    }

    private void onClickSendOtpCode(String strOtp_verifyOtpActivity) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerifycationId_VerifyOtpActivity, strOtp_verifyOtpActivity);
        signInWithPhoneAuthCredential(credential);
    }
    private void onClickSendOtpAgain() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth_VerifyOtpActivity)
                        .setPhoneNumber(mPhoneNumber_VerifyOtpActivity)       // Phone number to verify
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
                                Toast.makeText(VerifyOtpActivity.this,"Verification Failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verifycationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verifycationId, forceResendingToken);
                                mVerifycationId_VerifyOtpActivity = verifycationId;
                                mForceResendingToken = forceResendingToken;
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth_VerifyOtpActivity.signInWithCredential(credential)
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
                                Toast.makeText(VerifyOtpActivity.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
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

    private void unitui(){
        edtOtp_VerifyOtpActivity = findViewById(R.id.edt_phone_number_OTPActivity);
        btnSendOtpCode_VerifyOtpActivity = findViewById(R.id.btn_verify_phone_number_OTPActivity);
        tvSendOtpAgain_VerifyOtpActivity = findViewById(R.id.tv_send_otp_again_verifyOtp);

    }
}