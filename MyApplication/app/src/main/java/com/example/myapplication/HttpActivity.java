package com.example.myapplication;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public class HttpActivity {
    NetworkService networkService;
    boolean mBound = false;


    protected ServiceConnection connection = new ServiceConnection() {
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
