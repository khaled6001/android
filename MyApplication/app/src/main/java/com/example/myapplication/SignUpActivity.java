package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;import com.android.volley.RequestQueue;
import com.android.volley.Response;import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

import android.content.ServiceConnection;

public class SignUpActivity extends HomeActivity {

    protected void onStart(HttpActivity connection) {
        super.onStart();
        Intent intent = new Intent(this, NetworkService.class);
        startService(intent);
        bindService(intent, (ServiceConnection) connection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        }


    @Override
    protected void onStop(HttpActivity connection){
        super.onStop();
        unbindService((ServiceConnection) connection);
        Intent intent = new Intent(this, NetworkService.class);
        stopService(intent);}

    private void signUp() {
        String first_name = firstNameEditText.getText().toString();
        String family_nane = fanilyNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String address = addressEditText.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.12/signup.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(SignUpActivity.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignUpActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("first_name", first_name);
                params.put("family_name", family_nane);
                params.put("email", email);
                params.put("password", password);
                params.put("age", age);
                params.put("address", address);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
//