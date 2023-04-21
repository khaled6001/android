package com.example.myapplication;

import android.content.Intent;
import android.content.IntentFilter;

public class NetworkService {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkReceiver = NetworkChangeReceiver.getIstance(new ConnectivityChangeListener() {@Override
            public void onConnectivityChange(boolean isConnected) {
// do something when the connectivity changes
                                                                   }
                                                                   });
        registerReceiver(networkReceiver, filter);
        return START_STICKY;
    }
}
