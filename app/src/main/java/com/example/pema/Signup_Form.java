package com.example.pema;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Form extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btn_register;
    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);

        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        btn_register = (Button)findViewById(R.id.btn_Resgiter);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Signup_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Signup_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6)
                {
                    Toast.makeText(Signup_Form.this, "Password too Short", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);


                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(View.GONE);


                                if (task.isSuccessful()) {
                                   startActivity(new Intent(getApplicationContext(),Login_Form.class));
                                    Toast.makeText(Signup_Form.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                } else {

                                    Toast.makeText(Signup_Form.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });










            }
        });


    }



}
