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

public class register1 extends AppCompatActivity {

    EditText RUsername,REmail,RPassword;
    Button RRegisterBtn;
    TextView RLoginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        RUsername = findViewById(R.id.name);
        REmail = findViewById(R.id.email);
        RPassword = findViewById(R.id.Cpassword);
        RRegisterBtn = findViewById(R.id.register);
        RLoginBtn = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        RRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name = RUsername.getText().toString().trim();
            String email = REmail.getText().toString().trim();
            String password = RPassword.getText().toString().trim();

             if(TextUtils.isEmpty(name)){
                    REmail.setError("Username is required");
                    return;
             }

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

            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(register1.this, "User created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }else{
                        Toast.makeText(register1.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });


            }
        });

        RLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });


    }
}
