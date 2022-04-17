package com.elallouch.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    EditText etName, etEmail, etPassword;
    FirebaseAuth fAuth;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), login.class));
            finish();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(TextUtils.isEmpty(name))
                {
                    etName.setError("Name is required.");
                }
                if(TextUtils.isEmpty(email))
                {
                    etEmail.setError("Email is required.");
                }
                if(TextUtils.isEmpty(password))
                {
                    etPassword.setError("Password is required.");
                }
                if(password.length() < 6)
                {
                    etPassword.setError("Password must bt >= 6 characters");
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(register.this,login.class));
                    }
                });

            }
        });

    }
}
