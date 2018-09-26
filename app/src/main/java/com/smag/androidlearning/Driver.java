package com.smag.androidlearning;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smag.androidlearning.service.DatabaseService;
import com.smag.androidlearning.service.ServiceNotification;

import java.io.Serializable;

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
        if(!startNotification())
          {
            setContentView(R.layout.activity_driver);
            serviceIntent = new Intent(getApplicationContext(),DatabaseService.class);
            new ThreadApp().start();
        } else{
           onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        unBindService();
        super.onBackPressed();
    }

    private boolean startNotification() {
        if(getIntent().getBooleanExtra("boot", false)) {
            startService(new Intent(getApplicationContext(), ServiceNotification.class));
            return true;
        }
        return false;
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
            bindService(serviceIntent,serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    private void unBindService() {
        if(isServiceBound){
            unbindService(serviceConnection);
            isServiceBound = false;
        }
    }

    class ThreadApp extends Thread{
        @Override
        public void run() {
            try {
                initComponent();
                bindService();
                sleep(3000);
                if(isServiceBound){
                    // Transfert des donnees vers AppViewContainer
                    Intent intent =new Intent(getApplicationContext(),AppViewContainer.class);
                    intent.putExtra("listThemesTrans", (Serializable) service.getAllTheme());
                    intent.putExtra("listCoursTrans", (Serializable) service.getAllCours());
                    intent.putExtra("listExercicesTrans", (Serializable) service.getAllExercices());
                    startActivity(intent);
                    unBindService();
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
