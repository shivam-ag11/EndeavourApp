package com.kiet.ecell.endeavour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;


public class activity_splash extends Activity {

    SQLiteDatabase db;
    private static int SPLASH_TIME_OUT=1800;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        Constants co=new Constants();
        sp=getSharedPreferences(co.SharedPref,MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(activity_splash.this, Home1.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);if(Integer.valueOf(Build.VERSION.SDK_INT)>=11) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        db=openOrCreateDatabase("Endeavour_2k161", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Event(ID int(2));");
        db.execSQL("CREATE TABLE IF NOT EXISTS Notification(Name varchar,detail varchar,image varchar);");
        Cursor cursor2 = db.rawQuery("SELECT count(*) FROM Notification",null);
        cursor2.moveToFirst();
        if (cursor2.getInt(0) == 0) {
            db.execSQL("insert into Notification values('Welcome','Thank You for downloading Endeavour App','');");
        }
        if(sp.getInt("notification",0)<3){

            sp.edit().putInt("notification",3).commit();
            sp.edit().putInt("notification1",3).commit();
        }
    }


}
