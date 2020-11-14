package com.example.joke_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText REmail,RPassword;
    Button RLoginBtn;
    TextView RCreateAccountBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        REmail = findViewById(R.id.email);
        RPassword = findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        RLoginBtn = findViewById(R.id.login);
        RCreateAccountBtn = findViewById(R.id.createText);

        RLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = REmail.getText().toString().trim();
                String password = RPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    REmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    RPassword.setError("password is required");
                    return;
                }
                if(password.length() < 6) {
                    RPassword.setError("Password must be 6 characters");
                    return;

                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        RCreateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register1.class));
            }
        });
    }
}
