package com.smag.androidlearning;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Exercice;
import com.smag.androidlearning.beans.Ressourcedescription;
import com.smag.androidlearning.beans.Theme;
import com.smag.androidlearning.database.DatabaseFactory;
import com.smag.androidlearning.helper.XmlManager;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class Driver extends AppCompatActivity {

    DatabaseFactory databaseFactory;
    private static String MsgLog ="MsgConsole";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
