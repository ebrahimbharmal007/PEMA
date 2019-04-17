package com.example.pema;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Form extends AppCompatActivity {

    EditText txtEmaillogin,txtPasswordlogin;
    Button btn_login;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        txtEmaillogin = (EditText)findViewById(R.id.txtEmaillogin);
        txtPasswordlogin = (EditText)findViewById(R.id.txtPasswordlogin);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmaillogin.getText().toString().trim();
                String password = txtPasswordlogin.getText().toString().trim();
                firebaseAuth = FirebaseAuth.getInstance();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Login_Form.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(Login_Form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6)
                {
                    Toast.makeText(Login_Form.this, "Password too Short", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_Form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    openMainActivity();
                                } else {
                                    Toast.makeText(Login_Form.this, "Login Failed or User Not Found", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });


            }
        });
    }

    public void openMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void btn_signup_form(View view) {
        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }
}
