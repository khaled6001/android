package com.example.myapplication;

import android.os.Bundle;

public interface ConnectivityChangeListener {
    void onCreate(Bundle savedInstanceState);

    void onConnectivityChange(boolean isConnected);
}
