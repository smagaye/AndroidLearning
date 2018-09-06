package com.smag.androidlearning;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.smag.androidlearning.helper.XmlManager;

import java.io.IOException;

public class Driver extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            XmlManager.startDatabaseConfiguration(getAssets().open(getResources().getString(R.string.datafile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
