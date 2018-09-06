package com.smag.androidlearning;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.smag.androidlearning.database.DatabaseFactory;
import com.smag.androidlearning.helper.XmlManager;

public class Driver extends Activity {

    DatabaseFactory databaseFactory;
    private static String MsgLog ="MsgConsole";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            XmlManager.startDatabaseConfiguration(getAssets().open(getResources().getString(R.string.datafile)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
