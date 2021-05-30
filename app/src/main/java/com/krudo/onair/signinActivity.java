package com.krudo.onair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signinActivity extends AppCompatActivity {
    EditText emailtext,passwordtext;
    Button loginbtn,signupbtn;
    FirebaseAuth mauth;
    ProgressDialog diaglogbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mauth = FirebaseAuth.getInstance();
        emailtext = findViewById(R.id.etEmail);
        passwordtext = findViewById(R.id.etPass);
        loginbtn = findViewById(R.id.loginbtn);
        signupbtn = findViewById(R.id.signUpbutton);
        diaglogbox = new ProgressDialog(this);
        diaglogbox.setMessage("Please wait");

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaglogbox.show();
                String email , password;
                email = emailtext.getText().toString();
                password = passwordtext.getText().toString();

                mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        diaglogbox.dismiss();
                        if(task.isSuccessful()){
                            startActivity(new Intent(signinActivity.this,DashboardActivity.class));

                        }else {
                            Toast.makeText(signinActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signinActivity.this,signupActivity.class));
            }
        });


    }
}