package com.example.billtrackermobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button SignUp, DirectToLogin;
    EditText username, telephonenumber, email, password, conpass;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        SignUp = findViewById(R.id.signUpText);
        DirectToLogin = findViewById(R.id.signUpDirect);
        username = findViewById(R.id.signup_name);
        telephonenumber = findViewById(R.id.signup_mobile);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        conpass = findViewById(R.id.signup_Conpassword);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });



        findViewById(R.id.signUpDirect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private void createUser() {
        String userName = username.getText().toString();
        String userEmail = email.getText().toString();
        String userNumber = telephonenumber.getText().toString();
        String userPass = password.getText().toString();
        String userConPass = conpass.getText().toString();

        


        //create user
        auth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}