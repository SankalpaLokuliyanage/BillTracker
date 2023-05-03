package com.example.billtrackermobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.billtrackermobile.models.userModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button SignUp, DirectToLogin;
    EditText username, telephonenumber, email, password, conpass;

    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

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

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Please input your name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Please input your email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userNumber)) {
            Toast.makeText(this, "Please input your number for bill", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPass)) {
            Toast.makeText(this, "Please make a password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userConPass)) {
            Toast.makeText(this, "Please retype the password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPass.length() < 7) {
            Toast.makeText(this, "Your password must be more than 7 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPass != userConPass) {
            Toast.makeText(this, "Password and Confirm Password fields must be same!", Toast.LENGTH_SHORT).show();
            return;
        }


        //create user
        auth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    userModels usermodel = new userModels(userName, userEmail, userNumber, userPass);
                    String id = task.getResult().getUser().getUid();
                    database.getReference().child("users").child(id).setValue(usermodel);

                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}