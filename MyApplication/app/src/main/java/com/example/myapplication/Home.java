package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public abstract class Home extends AppCompatActivity {
    protected EditText emailEditText;
    protected EditText firstNameEditText;
    protected EditText fanilyNameEditText;
    protected EditText ageEditText;
    protected EditText addressEditText;
    protected Button signUpButton;
    protected EditText passwordEditText;
    protected Button loginButton;

    protected abstract void onStart(SignUpActivity.HttpActivity connection);

    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        firstNameEditText = findViewById(R.id.first_name);
        fanilyNameEditText = findViewById(R.id.family_name);
        emailEditText = findViewById(R.id.email);
        ageEditText = findViewById(R.id.age);
        passwordEditText = findViewById(R.id.password);
        addressEditText = findViewById(R.id.address);
        signUpButton = findViewById(R.id.signup_button);
        loginButton = findViewById(R.id.login_button);


    };

    protected abstract void onStop(SignUpActivity.HttpActivity connection);
}



