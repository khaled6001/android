package com.example.myapplication;

import java.util.Map;

public abstract class HttpActivity extends AppCompatActivity implements
        ConnectivityChangeListener {// Some attribute..
    NetworkService networkService;
    boolean mBound = false;
    private NetworkChangeReceiver networkReceiver;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkReceiver = NetworkChangeReceiver.getInstance(this);
        registerReceiver(networkReceiver, filter);
    }
    @Override
    public void onConnectivityChange(boolean isConnected) {
        if (isConnected) {
// do something when the conenctivity is restored
        } else {
// do something when the connectivity is lost
        }
    }
    protected void send(Map<String, String> params) {
        if(!networkReceiver.isConnected()) {
            Toast.makeText(HttpActivity.this,
                    getResources().getString(R.string.
                            verify_your_Internet), Toast.LENGTH_SHORT).show();
            return;}
// More code..
    }
    @Override
    public void onDestroy() {
        if (networkReceiver != null) {
            unregisterReceiver(networkReceiver);
            networkReceiver = null;
        }
        super.onDestroy();
    }
// More methods..
}