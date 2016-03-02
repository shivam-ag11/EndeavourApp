package com.kiet.ecell.endeavour;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class EventType extends AppCompatActivity {

    EventListAdapter adapter;
    ListView lv;
    List<EventListElement> aa;
    SharedPreferences sp;
    int a4;
    int i=0;
    int Type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type);
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Endeavour");if(Integer.valueOf(Build.VERSION.SDK_INT)>=11) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        Constants co=new Constants();
        sp=getSharedPreferences(co.SharedPref,MODE_PRIVATE);
        lv= (ListView) findViewById(R.id.List);
        Type=getIntent().getExtras().getInt("Type",0);
        Log.d("Type",""+Type);
        aa=new ArrayList<EventListElement>();
        if(Type==1){
            aa.clear();
            EventListElement element=new EventListElement("Unknown","INNOPRENEUR","Let Your Imagination Run Wild","Price Worth: 1000/-",12);
            aa.add(element);
            element=new EventListElement("Unknown","SPUR OF THE MOMENT","Creativity is Intelligence Having Fun","Price Worth: 1000/-",10);
            aa.add(element);
            element=new EventListElement("Unknown","IDEASTORM","Ideas Worth Showing","Price Worth: 5000/-",1);
            aa.add(element);
            element=new EventListElement("Unknown","CASE CONNECT","Small Details Create The Big Pictures","Price Worth: 4000/-",6);
            aa.add(element);
            element=new EventListElement("Unknown","10 MINUTES","Connect The Dots","Price Worth: 4000/-",13);
            aa.add(element);
            element=new EventListElement("Unknown","B-NOESIS","Quest For The Sharpest","Price Worth: 3000/-",8);
            aa.add(element);
            element=new EventListElement("Unknown","MAESTRO","Discover Your M-Factor","Price Worth: 3500/-",4);
            aa.add(element);
            element=new EventListElement("Unknown","STRATEGIST","Be The Strategist","Price Worth: 3500/-",14);
            aa.add(element);
            Log.d("Type",""+Type);
        }
        else if(Type==2){
            aa.clear();
            EventListElement element=new EventListElement("Unknown","REMINISCENCE-THE AFTER-MOVIE","Light Camera Action","Unknown",16);
            aa.add(element);
            element=new EventListElement("Unknown","CAPTURED","Shades Of Endeavour","Unknown",17);
            aa.add(element);
            element=new EventListElement("Unknown","Back With a Bang!","The Treasure Hunt!!","Price Worth: 4000/-",18);
            aa.add(element);
            element=new EventListElement("Unknown","LAN GAMING","There ain't no party,like a LAN PARTY!!","Price Worth: 15000/-",15);
            aa.add(element);
            element=new EventListElement("Unknown","SocioSelfie","","Exciting Prizes and Goodies",19);
            aa.add(element);
        }
        else if(Type==3){
            aa.clear();
            EventListElement element=new EventListElement("Unknown","RoboSpate","Change is Inevitable","Price Worth: 2000/-",7);
            aa.add(element);
            element=new EventListElement("Unknown","Monster Arena","Bigger Battle, Greater Glory","Price Worth: 3000/-",5);
            aa.add(element);
            element=new EventListElement("Unknown","PROJECT EXPO","From Imagination to Reality","Price Worth: 5000/-",3);
            aa.add(element);
            element=new EventListElement("Unknown","CONSTRUCTO","Bridge the Gap","Price Worth: 3000/-",11);
            aa.add(element);
            element=new EventListElement("Unknown","LET US SEE","Only for Bookworms","Price Worth: 3000/-",9);
            aa.add(element);
            element=new EventListElement("Unknown","CODE WAR","Talk is Cheap, Show Me The Code","Price Worth: 4000/-",2);
            aa.add(element);
        }
        else if(Type==4){
            fillList();
        }
        else if(Type==5){
            fillList1();
        }
        Animation anim= AnimationUtils.loadAnimation(this, R.anim.list_animation);
        adapter=new EventListAdapter(getApplicationContext(),aa);
        lv.setAdapter(adapter);
        lv.setAnimation(anim);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(EventType.this,EventViewActivity.class);
                intent.putExtra("ID",aa.get(i).getID());
                intent.putExtra("Head",aa.get(i).getTitle());
                intent.putExtra("One", aa.get(i).getOneLine());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        Log.d("Type",""+Type);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Type==4)
        {
            fillList();
            adapter = new EventListAdapter(getApplicationContext(), aa);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(EventType.this, EventViewActivity.class);
                    intent.putExtra("ID", aa.get(i).getID());
                    intent.putExtra("Head", aa.get(i).getTitle());
                    intent.putExtra("One", aa.get(i).getOneLine());
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });
        }
        if(Type==5)
        {
            fillList1();
            adapter = new EventListAdapter(getApplicationContext(), aa);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(EventType.this, EventViewActivity.class);
                    intent.putExtra("ID", aa.get(i).getID());
                    intent.putExtra("Head", aa.get(i).getTitle());
                    intent.putExtra("One", aa.get(i).getOneLine());
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });
        }
    }
    private boolean chechNet() {
        ConnectivityManager cm =(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        RelativeLayout coordinatorLayout = (RelativeLayout) findViewById(R.id.rl);
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
    private void fillList() {

        aa.clear();
        EventListElement element;
        a4=0;
        if(sp.getInt(""+12,0)==10 || sp.getInt(""+12,0)==11) {
            element=new EventListElement("Unknown","INNOPRENEUR","Let Your Imagination Run Wild","Price Worth: 1000/-",12);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+10,0)==10 || sp.getInt(""+10,0)==11) {
            element=new EventListElement("Unknown","SPUR OF THE MOMENT","Creativity is Intelligence Having Fun","Price Worth: 1000/-",10);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+1,0)==10 || sp.getInt(""+1,0)==11) {
            element=new EventListElement("Unknown","IDEASTORM","Ideas Worth Showing","Price Worth: 5000/-",1);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+6,0)==10 || sp.getInt(""+6,0)==11) {
            element=new EventListElement("Unknown","CASE CONNECT","Small Details Create The Big Pictures","Price Worth: 4000/-",6);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+13,0)==10 || sp.getInt(""+13,0)==11) {
            element=new EventListElement("Unknown","10 MINUTES","Connect The Dots","Price Worth: 4000/-",13);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+8,0)==10 || sp.getInt(""+8,0)==11) {
            element=new EventListElement("Unknown","B-NOESIS","Quest For The Sharpest","Price Worth: 3000/-",8);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+4,0)==10 || sp.getInt(""+4,0)==11) {
            element=new EventListElement("Unknown","MAESTRO","Discover Your M-Factor","Price Worth: 3500/-",4);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+14,0)==10 || sp.getInt(""+14,0)==11) {
            element=new EventListElement("Unknown","STRATEGIST","Be The Strategist","Price Worth: 3500/-",14);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+16,0)==10 || sp.getInt(""+16,0)==11) {
            element=new EventListElement("Unknown","REMINISCENCE-THE AFTER-MOVIE","Light Camera Action","Unknown",16);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+17,0)==10 || sp.getInt(""+17,0)==11) {
            element=new EventListElement("Unknown","CAPTURED","Shades Of Endeavour","Unknown",17);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+18,0)==10 || sp.getInt(""+18,0)==11) {
            element=new EventListElement("Unknown","Back With a Bang!","The Treasure Hunt!!","Price Worth: 4000/-",18);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+15,0)==10 || sp.getInt(""+15,0)==11) {
            element=new EventListElement("Unknown","LAN GAMING","There ain't no party,like a LAN PARTY!!","Price Worth: 15000/-",15);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+19,0)==10 || sp.getInt(""+19,0)==11) {
            element=new EventListElement("Unknown","SocioSelfie","","Exciting Prizes and Goodies",19);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+7,0)==10 || sp.getInt(""+7,0)==11) {
            element = new EventListElement("Unknown", "RoboSpate", "Change is Inevitable", "Price Worth: 2000/-", 7);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+5,0)==10 || sp.getInt(""+5,0)==11) {
            element=new EventListElement("Unknown","Monster Arena","Bigger Battle, Greater Glory","Price Worth: 3000/-",5);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+3,0)==10 || sp.getInt(""+3,0)==11) {
            element=new EventListElement("Unknown","PROJECT EXPO","From Imagination to Reality","Price Worth: 5000/-",3);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+11,0)==10 || sp.getInt(""+11,0)==11) {
            element=new EventListElement("Unknown","CONSTRUCTO","Bridge the Gap","Price Worth: 3000/-",11);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+9,0)==10 || sp.getInt(""+9,0)==11) {
            element=new EventListElement("Unknown","LET US SEE","Only for Bookworms","Price Worth: 3000/-",9);
            aa.add(element);
            a4=1;
        }
        if(sp.getInt(""+2,0)==10 || sp.getInt(""+2,0)==11) {
            element=new EventListElement("Unknown","CODE WAR","Talk is Cheap, Show Me The Code","Price Worth: 4000/-",2);
            aa.add(element);
            a4=1;
        }
        if(a4==0)
        {
            findViewById(R.id.text_no).setVisibility(View.VISIBLE);
        }
    }
    private void fillList1() {
        if (chechNet() == true) {
            i=0;
            new HitJSPService(this, null, new TaskCompleted() {

                @Override
                public void onTaskCompleted(String result, int resultType) {
                    try {
                        JSONObject jo = new JSONObject(result);
                        JSONArray ja = jo.getJSONArray("result");
                        int len = ja.length();
                        Constants co=new Constants();
                        sp=getSharedPreferences(co.SharedPref,MODE_PRIVATE);
                        for (int i = 0; i < len; i++) {
                            JSONObject jo1 = ja.getJSONObject(i);
                            //Log.d("id",""+Integer.parseInt(jo1.getString("id").trim()));
                            Log.d("id",""+sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0));
                            if (sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0) == 0) {
                                Log.d("id",""+sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0));
                                sp.edit().putInt("" + Integer.parseInt(jo1.getString("id").trim()), 01).commit();
                                Log.d("id",""+sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0));
                            }
                            if (sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0) == 10) {
                                Log.d("id",""+sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0));
                                sp.edit().putInt("" + Integer.parseInt(jo1.getString("id").trim()), 11).commit();
                                Log.d("id",""+sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0));
                            }

                        }

                        aa.clear();
                        EventListElement element;
                        a4 = 0;
                        if (sp.getInt("" + 12, 0) == 01 || sp.getInt("" + 12, 0) == 11) {
                            element = new EventListElement("Unknown", "INNOPRENEUR", "Let Your Imagination Run Wild", "Price Worth: 1000/-", 12);
                            aa.add(element);
                            a4 = 1;
                            Log.d("succ",""+1);
                        }
                        if (sp.getInt("" + 10, 0) == 01 || sp.getInt("" + 10, 0) == 11) {
                            element = new EventListElement("Unknown", "SPUR OF THE MOMENT", "Creativity is Intelligence Having Fun", "Price Worth: 1000/-", 10);
                            aa.add(element);
                            a4 = 1;
                            Log.d("succ",""+2);
                        }
                        if (sp.getInt("" + 1, 0) == 01 || sp.getInt("" + 1, 0) == 11) {
                            element = new EventListElement("Unknown", "IDEASTORM", "Ideas Worth Showing", "Price Worth: 5000/-", 1);
                            aa.add(element);
                            a4 = 1;
                            Log.d("succ",""+3);
                        }
                        if (sp.getInt("" + 6, 0) == 01 || sp.getInt("" + 6, 0) == 11) {
                            element = new EventListElement("Unknown", "CASE CONNECT", "Small Details Create The Big Pictures", "Price Worth: 4000/-", 6);
                            aa.add(element);
                            a4 = 1;
                            Log.d("succ",""+4);
                        }
                        if (sp.getInt("" + 13, 0) == 01 || sp.getInt("" + 13, 0) == 11) {
                            element = new EventListElement("Unknown", "10 MINUTES", "Connect The Dots", "Price Worth: 4000/-", 13);
                            aa.add(element);
                            a4 = 1;
                            Log.d("succ",""+5);
                        }
                        if (sp.getInt("" + 8, 0) == 01 || sp.getInt("" + 8, 0) == 11) {
                            element = new EventListElement("Unknown", "B-NOESIS", "Quest For The Sharpest", "Price Worth: 3000/-", 8);
                            aa.add(element);
                            a4 = 1;
                            Log.d("succ",""+6);
                        }
                        if (sp.getInt("" + 4, 0) == 01 || sp.getInt("" + 4, 0) == 11) {
                            element = new EventListElement("Unknown", "MAESTRO", "Discover Your M-Factor", "Price Worth: 3500/-", 4);
                            aa.add(element);
                            a4 = 1;
                        }
                        if (sp.getInt("" + 14, 0) == 01 || sp.getInt("" + 14, 0) == 11) {
                            element = new EventListElement("Unknown", "STRATEGIST", "Be The Strategist", "Price Worth: 3500/-", 14);
                            aa.add(element);
                            a4 = 1;
                        }
                        if (sp.getInt("" + 16, 0) == 01 || sp.getInt("" + 16, 0) == 11) {
                            element = new EventListElement("Unknown", "REMINISCENCE-THE AFTER-MOVIE", "Light Camera Action", "Unknown", 16);
                            aa.add(element);
                            a4 = 1;
                        }
                        if (sp.getInt("" + 17, 0) == 01 || sp.getInt("" + 17, 0) == 11) {
                            element = new EventListElement("Unknown", "CAPTURED", "Shades Of Endeavour", "Unknown", 17);
                            aa.add(element);
                            a4 = 1;
                            Log.d("succ",""+10);
                        }
                        if (sp.getInt("" + 18, 0) == 01 || sp.getInt("" + 18, 0) == 11) {
                            element = new EventListElement("Unknown", "Back With a Bang!", "The Treasure Hunt!!", "Price Worth: 4000/-", 18);
                            aa.add(element);
                            a4 = 1;
                        }
                        if (sp.getInt("" + 15, 0) == 01 || sp.getInt("" + 15, 0) == 11) {
                            element = new EventListElement("Unknown", "LAN GAMING", "There ain't no party,like a LAN PARTY!!", "Price Worth: 15000/-", 15);
                            aa.add(element);
                            a4 = 1;
                        }
                        if(sp.getInt(""+19,0)==01 || sp.getInt(""+19,0)==11) {
                            element=new EventListElement("Unknown","SocioSelfie","","Exciting Prizes and Goodies",19);
                            aa.add(element);
                            a4=1;
                        }
                        if (sp.getInt("" + 7, 0) == 01 || sp.getInt("" + 7, 0) == 11) {
                            element = new EventListElement("Unknown", "RoboSpate", "Change is Inevitable", "Price Worth: 2000/-", 7);
                            aa.add(element);
                            a4 = 1;
                        }
                        if (sp.getInt("" + 5, 0) == 01 || sp.getInt("" + 5, 0) == 11) {
                            element = new EventListElement("Unknown", "Monster Arena", "Bigger Battle, Greater Glory", "Price Worth: 3000/-", 5);
                            aa.add(element);
                            a4 = 1;
                        }
                        if (sp.getInt("" + 3, 0) == 01 || sp.getInt("" + 3, 0) == 11) {
                            element = new EventListElement("Unknown", "PROJECT EXPO", "From Imagination To Reality", "Price Worth: 5000/-", 3);
                            aa.add(element);
                            a4 = 1;
                        }
                        if (sp.getInt("" + 11, 0) == 01 || sp.getInt("" + 11, 0) == 11) {
                            element = new EventListElement("Unknown", "CONSTRUCTO", "Bridge the Gap", "Price Worth: 3000/-", 11);
                            aa.add(element);
                            a4 = 1;
                        }
                        if (sp.getInt("" + 9, 0) == 01 || sp.getInt("" + 9, 0) == 11) {
                            element = new EventListElement("Unknown", "LET US SEE", "Only for Bookworms", "Price Worth: 3000/-", 9);
                            aa.add(element);
                            a4 = 1;
                        }
                        if (sp.getInt("" + 2, 0) == 01 || sp.getInt("" + 2, 0) == 11) {
                            element = new EventListElement("Unknown", "CODE WAR", "Talk is Cheap, Show Me The Code", "Price Worth: 4000/-", 2);
                            aa.add(element);
                            a4 = 1;
                        }
                        if (a4 == 0) {
                            findViewById(R.id.text_no1).setVisibility(View.VISIBLE);
                        }
                        adapter = new EventListAdapter(getApplicationContext(), aa);
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(EventType.this, EventViewActivity.class);
                                intent.putExtra("ID", aa.get(i).getID());
                                intent.putExtra("Head", aa.get(i).getTitle());
                                intent.putExtra("One", aa.get(i).getOneLine());
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            }});
                        i=1;
                    } catch (Exception e) {
                    }

                }
            }, "http://www.endeavourkiet.in/app/eventcheck.php?us_id=" + sp.getInt("Id", 0), 1).execute();
            i=1;
        }else{
            aa.clear();
            EventListElement element;
            a4 = 0;
            if (sp.getInt("" + 12, 0) == 01 || sp.getInt("" + 12, 0) == 11) {
                element = new EventListElement("Unknown", "INNOPRENEUR", "Let Your Imagination Run Wild", "Price Worth: 1000/-", 12);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 10, 0) == 01 || sp.getInt("" + 10, 0) == 11) {
                element = new EventListElement("Unknown", "SPUR OF THE MOMENT", "Creativity is Intelligence Having Fun", "Price Worth: 1000/-", 10);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 1, 0) == 01 || sp.getInt("" + 1, 0) == 11) {
                element = new EventListElement("Unknown", "IDEASTORM", "Ideas Worth Showing", "Price Worth: 5000/-", 1);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 6, 0) == 01 || sp.getInt("" + 6, 0) == 11) {
                element = new EventListElement("Unknown", "CASE CONNECT", "Small Details Create The Big Pictures", "Price Worth: 4000/-", 6);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 13, 0) == 01 || sp.getInt("" + 13, 0) == 11) {
                element = new EventListElement("Unknown", "10 MINUTES", "Connect The Dots", "Price Worth: 4000/-", 13);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 8, 0) == 01 || sp.getInt("" + 8, 0) == 11) {
                element = new EventListElement("Unknown", "B-NOESIS", "Quest For The Sharpest", "Price Worth: 3000/-", 8);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 4, 0) == 01 || sp.getInt("" + 4, 0) == 11) {
                element = new EventListElement("Unknown", "MAESTRO", "Discover Your M-Factor", "Price Worth: 3500/-", 4);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 14, 0) == 01 || sp.getInt("" + 14, 0) == 11) {
                element = new EventListElement("Unknown", "STRATEGIST", "Be The Strategist", "Price Worth: 3500/-",14);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 16, 0) == 01 || sp.getInt("" + 16, 0) == 11) {
                element = new EventListElement("Unknown", "REMINISCENCE-THE AFTER-MOVIE", "Light Camera Action", "Unknown", 16);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 17, 0) == 01 || sp.getInt("" + 17, 0) == 11) {
                element = new EventListElement("Unknown", "CAPTURED", "Shades Of Endeavour", "Unknown", 17);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 18, 0) == 01 || sp.getInt("" + 18, 0) == 11) {
                element = new EventListElement("Unknown", "BACK WITH A BANG!", "The Treasure Hunt", "Price Worth: 4000/-", 18);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 15, 0) == 01 || sp.getInt("" + 15, 0) == 11) {
                element = new EventListElement("Unknown", "LAN GAMING", "There ain't no party,like a LAN PARTY!!", "Price Worth: 15000/-", 15);
                aa.add(element);
                a4 = 1;
            }
            if(sp.getInt(""+19,0)==01 || sp.getInt(""+19,0)==11) {
                element=new EventListElement("Unknown","SocioSelfie","","Exciting Prizes and Goodies",19);
                aa.add(element);
                a4=1;
            }
            if (sp.getInt("" + 7, 0) == 01 || sp.getInt("" + 7, 0) == 11) {
                element = new EventListElement("Unknown", "ROBOSPATE", "Change is Inevitable", "Price Worth: 2000/-", 7);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 5, 0) == 01 || sp.getInt("" + 5, 0) == 11) {
                element = new EventListElement("Unknown", "Monster Arena", "Bigger Battle, Greater Glory", "Price Worth: 3000/-", 5);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 3, 0) == 01 || sp.getInt("" + 3, 0) == 11) {
                element = new EventListElement("Unknown", "PROJECT EXPO", "From Imagination To Reality", "Price Worth: 5000/-", 3);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 11, 0) == 01 || sp.getInt("" + 11, 0) == 11) {
                element = new EventListElement("Unknown", "CONSTRUCTO", "Bridge the Gap", "Price Worth: 3000/-", 11);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 9, 0) == 01 || sp.getInt("" + 9, 0) == 11) {
                element = new EventListElement("Unknown", "LET US SEE", "Only for Bookworms", "Price Worth: 3000/-", 9);
                aa.add(element);
                a4 = 1;
            }
            if (sp.getInt("" + 2, 0) == 01 || sp.getInt("" + 2, 0) == 11) {
                element = new EventListElement("Unknown", "CODE WAR", "Talk is Cheap, Show Me The Code", "Price Worth: 4000/-", 2);
                aa.add(element);
                a4 = 1;
            }
            if (a4 == 0) {
                findViewById(R.id.text_no1).setVisibility(View.VISIBLE);
            }
        }
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
            Intent intent=new Intent(EventType.this,Home1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
