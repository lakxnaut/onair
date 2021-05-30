package com.krudo.onair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signupActivity extends AppCompatActivity {
    FirebaseAuth mauth;
    FirebaseFirestore database;
    EditText emailtext,passwordtext,nametext;
    Button loginbtn,signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mauth = FirebaseAuth.getInstance();
        nametext = findViewById(R.id.etname);
        emailtext = findViewById(R.id.etEmail);
        passwordtext = findViewById(R.id.etPass);
        loginbtn = findViewById(R.id.loginbtn);
        database = FirebaseFirestore.getInstance();
        signupbtn = findViewById(R.id.signUpbutton);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,email,pass;

                name = nametext.getText().toString();
                email = emailtext.getText().toString();
                pass = passwordtext.getText().toString();
                user user = new user();
                user.setName(name);
                user.setPass(pass);
                user.setEmail(email);

                mauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            database.collection("users").document().set(user);
                            startActivity(new Intent(signupActivity.this,signinActivity.class));
                        }
                        else {
                            Toast.makeText(signupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



                
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signupActivity.this,signinActivity.class));
            }
        });




    }
}