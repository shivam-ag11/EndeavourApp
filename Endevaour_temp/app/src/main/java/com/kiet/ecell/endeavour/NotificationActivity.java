package com.kiet.ecell.endeavour;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NotificationActivity extends ActionBarActivity {

    EventListAdapter adapter;
    ListView lv;
    List<EventListElement> aa;
    SharedPreferences sp;
    int a4;
    int i=0;
    int Type;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Endeavour");if(Integer.valueOf(Build.VERSION.SDK_INT)>=11) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        db=openOrCreateDatabase("Endeavour_2k161", Context.MODE_PRIVATE, null);
        Constants co=new Constants();
        sp=getSharedPreferences(co.SharedPref,MODE_PRIVATE);
        sp.edit().putInt("notification1", sp.getInt("notification",3)).commit();
        lv= (ListView) findViewById(R.id.List);
        aa=new ArrayList<EventListElement>();
        RelativeLayout rl= (RelativeLayout) findViewById(R.id.rl);
        if(chechNet()==true)
        {
            getdata();
        }else{
            aa.clear();
            EventListElement element;
            Cursor c=db.rawQuery("select * from Notification", null);
            while(c.moveToNext())
            {
                element=new EventListElement("Unknown",c.getString(0),c.getString(1),c.getString(2),0);
                aa.add(element);
            }
            adapter=new EventListAdapter(getApplicationContext(),aa);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String image = aa.get(position).getPlace();
                    if (image.trim().equals("") || image.trim().equals("false")) {
                    } else {
                        Intent i = new Intent(NotificationActivity.this, ImageViewActivity.class);
                        i.putExtra("image", image);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }
            });
        }
    }

    private void getdata() {
        new HitJSPService(this,null,new TaskCompleted() {

            @Override
            public void onTaskCompleted(String result,int resultType) {
                EventListElement element;
                aa.clear();
                Cursor c=db.rawQuery("select * from Notification", null);
                while(c.moveToNext())
                {
                    element=new EventListElement("Unknown",c.getString(0),c.getString(1),c.getString(2),0);
                    aa.add(0,element);
                }
                try {
                    JSONObject jo=new JSONObject(result);
                    JSONArray ja=jo.getJSONArray("result");
                    int len=ja.length();
                    for(int i=0;i<len;i++) {
                        JSONObject jo1 = ja.getJSONObject(i);
                        element = new EventListElement("Unknown", jo1.getString("name"), jo1.getString("detail"), jo1.getString("image"), 0);
                        if (jo1.getString("save").trim().equals("1")) {
                            db.execSQL("insert into Notification values ('" + jo1.getString("name") + "','" + jo1.getString("detail") + "','" + jo1.getString("image") + "');");
                        }
                        aa.add(0,element);
                        //aa.add(element);
                        sp.edit().putInt("notification", jo1.getInt("id")).commit();
                        sp.edit().putInt("notification1", jo1.getInt("id")).commit();
                    }
                } catch (Exception e) {
                }
                adapter=new EventListAdapter(getApplicationContext(),aa);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String image = aa.get(position).getPlace();
                        if (image.trim().equals("") || image.trim().equals("false")) {
                        } else {
                            Intent i = new Intent(NotificationActivity.this, ImageViewActivity.class);
                            i.putExtra("image", image);
                            startActivity(i);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    }
                });
            }
        },"http://www.endeavourkiet.in/app/notification.php?id="+sp.getInt("notification",2)+"&user="+sp.getInt("Id",0),1).execute();
    //},"http://10.0.2.2:8080/PhpProject2/notification.php?id="+sp.getInt("notification",1),1).execute();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                return true;
        }
        if(item.getItemId() == R.id.action_menu){
            Intent intent=new Intent(NotificationActivity.this,Home1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean chechNet() {
        ConnectivityManager cm =(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        RelativeLayout coordinatorLayout = (RelativeLayout) findViewById(R.id.rl);
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
