package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
public class LoginActivity extends Home {

    public class HttpActivity {
        NetworkService networkService;
        boolean mBound = false;
        private ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName className,IBinder service) {
                NetworkService.LocalBinder binder= (NetworkService.LocalBinder) service;
                networkService = binder.getService();
                mBound = true;
            }
            @Override
            public void onServiceDisconnected(ComponentName arg0) {
                mBound = false;
            }
        };}
    @Override
    protected void onStart(SignUpActivity.HttpActivity connection) {
        super.onStart();
        Intent intent = new Intent(this, NetworkService.class);
        startService(intent);
        bindService(intent, (ServiceConnection) connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                validateCredentials(email, password);
            }
        });
    }
    @Override
    protected void onStop(SignUpActivity.HttpActivity connection){
        super.onStop();
        unbindService((ServiceConnection) connection);
        Intent intent = new Intent(this, NetworkService.class);
        stopService(intent);}

    private void validateCredentials(String email, String password) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.12/login.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("success")) {
                                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
// Error occurred, show error message
                            Toast.makeText(LoginActivity.this, "Error occurred: " + error.
                                    getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
// Set POST parameters
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };
// Add request to queue
            Volley.newRequestQueue(this).add(stringRequest);
        }

    }