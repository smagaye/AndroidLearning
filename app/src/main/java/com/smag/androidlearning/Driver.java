package com.smag.androidlearning;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.smag.androidlearning.database.DatabaseService;

public class Driver extends AppCompatActivity {

    private Intent serviceIntent;
    private ServiceConnection serviceConnection;
    private DatabaseService service;
    private boolean isServiceBound;
    private TextView msgView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        msgView =(TextView)findViewById(R.id.idMsgView);
        serviceIntent = new Intent(getApplicationContext(),DatabaseService.class);
    }

    public void btnStart(View view){
        startService(serviceIntent);
        System.out.println("Service is stated !!!");
    }

    public void btnBindService(View view) {
        bindService();
        System.out.println("Service is Bind !!!");
    }

    public void btnUnbind(View view){
        unBindService();
        System.out.println("Service is unBind !!!");
    }

    public void btnStop(View view){
        stopService(serviceIntent);
        System.out.println("Service is stopped !!!");
    }

    public void btnCallServiceMethode(View view)  {
        callMethodService();
        System.out.println("Service ShowData() is called !!!");
    }

    private void bindService() {
        if(serviceConnection==null) {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    DatabaseService.DatabaseServiceBinder mbinder = (DatabaseService.DatabaseServiceBinder) iBinder;
                    service = mbinder.getService();
                    msgView.setText("Service Binded");
                    isServiceBound = true;
                }
                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    isServiceBound = false;
                    msgView.setText("Service Unbinded");
                }
            };
        }
        bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unBindService() {
        if(isServiceBound){
            unbindService(serviceConnection);
            isServiceBound = false;
            msgView.setText("Service Unbinded");
        }
    }

    private void callMethodService() {
        try {
            if (isServiceBound) {
                msgView.setText("Service call");
                service.showData();
            } else {
                msgView.setText("Service not bound");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
