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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.smag.androidlearning.service.DatabaseService;

public class Driver extends AppCompatActivity {

    private Intent serviceIntent;
    private ServiceConnection serviceConnection;
    private DatabaseService service;
    private boolean isServiceBound;

    private TextView txtname;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        serviceIntent = new Intent(getApplicationContext(),DatabaseService.class);
        bindService();
        new ThreadApp().start();
    }


    private void bindService() {
        if(serviceConnection==null) {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    DatabaseService.DatabaseServiceBinder mbinder = (DatabaseService.DatabaseServiceBinder) iBinder;
                    service = mbinder.getService();

                    isServiceBound = true;
                }
                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    isServiceBound = false;

                }
            };
        }
        bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unBindService() {
        if(isServiceBound){
            unbindService(serviceConnection);
            isServiceBound = false;

        }
    }

    private void callMethodService() {
        try {
            if (isServiceBound) {
                service.showData();
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ThreadApp extends Thread{
        @Override
        public void run() {
            try {
                initComponent();
                sleep(3000);
                if(isServiceBound){
                    startActivity(new Intent(getApplicationContext(),AppViewContainer.class));
                    finish();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initComponent() {
        txtname =(TextView) findViewById(R.id.txt_id);
        image = (ImageView) findViewById(R.id.img_id);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_res);
        txtname.setAnimation(animation);
        image.setAnimation(animation);
    }

}
