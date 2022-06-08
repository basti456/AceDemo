package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private Button sendButton;
    private EditText phone;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sendButton=findViewById(R.id.btnSend);
        phone=findViewById(R.id.etPhone);
        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        sendButton.setOnClickListener(v -> {
            if (phone.getText().toString().trim().isEmpty()) {
                Toast.makeText(SignInActivity.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
            } else if (phone.getText().toString().trim().length() != 10) {
                Toast.makeText(SignInActivity.this, "Type valid Phone Number", Toast.LENGTH_SHORT).show();
            } else {
                otpSend(progressBar,sendButton);
            }
        });
    }

    private void otpSend(ProgressBar progressBar, Button sendButton) {
        sendButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                progressBar.setVisibility(View.GONE);
                sendButton.setVisibility(View.VISIBLE);
                Toast.makeText(SignInActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                progressBar.setVisibility(View.GONE);
                sendButton.setVisibility(View.VISIBLE);
                Toast.makeText(SignInActivity.this, "OTP is successfully send.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignInActivity.this, OtpVerificationActivity.class);
                intent.putExtra("phone", phone.getText().toString().trim());
                intent.putExtra("verificationId", verificationId);
                startActivity(intent);
            }

        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + phone.getText().toString().trim())
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

}