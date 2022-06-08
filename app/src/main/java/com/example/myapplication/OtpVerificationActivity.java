package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

public class OtpVerificationActivity extends AppCompatActivity {
    private EditText et1, et2, et3, et4, et5, et6;
    private TextView resend;
    private Button verify;
    private TextView mobile;
    private String verificationId;
    private ProgressBar progressVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        et1 = findViewById(R.id.etC1);
        et2 = findViewById(R.id.etC2);
        et3 = findViewById(R.id.etC3);
        et4 = findViewById(R.id.etC4);
        et5 = findViewById(R.id.etC5);
        et6 = findViewById(R.id.etC6);
        progressVerify = findViewById(R.id.progressBarVerify);
        resend = findViewById(R.id.tvResendBtn);
        verify = findViewById(R.id.btnVerify);
        mobile = findViewById(R.id.tvMobile);
        editTextInput(et1, et2, et3, et4, et5, et6);
        mobile.setText(String.format(
                "+91-%s", getIntent().getStringExtra("phone")
        ));

        verificationId = getIntent().getStringExtra("verificationId");

        resend.setOnClickListener(v -> Toast.makeText(OtpVerificationActivity.this, "OTP Send Successfully.", Toast.LENGTH_SHORT).show());

        verify.setOnClickListener(v -> {
            progressVerify.setVisibility(View.VISIBLE);
            verify.setVisibility(View.INVISIBLE);
            if (et1.getText().toString().trim().isEmpty() ||
                    et2.getText().toString().trim().isEmpty() ||
                    et3.getText().toString().trim().isEmpty() ||
                    et4.getText().toString().trim().isEmpty() ||
                    et5.getText().toString().trim().isEmpty() ||
                    et6.getText().toString().trim().isEmpty()) {
                Toast.makeText(OtpVerificationActivity.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
            } else {
                if (verificationId != null) {
                    String code = et1.getText().toString().trim() +
                            et2.getText().toString().trim() +
                            et3.getText().toString().trim() +
                            et4.getText().toString().trim() +
                            et5.getText().toString().trim() +
                            et6.getText().toString().trim();

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                    FirebaseAuth
                            .getInstance()
                            .signInWithCredential(credential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressVerify.setVisibility(View.VISIBLE);
                                        verify.setVisibility(View.INVISIBLE);
                                        Toast.makeText(OtpVerificationActivity.this, "Welcome...", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(OtpVerificationActivity.this, TransactionActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    } else {
                                        progressVerify.setVisibility(View.GONE);
                                        verify.setVisibility(View.VISIBLE);
                                        Toast.makeText(OtpVerificationActivity.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void editTextInput(EditText et1, EditText et2, EditText et3, EditText et4, EditText et5, EditText et6) {
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et1.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}