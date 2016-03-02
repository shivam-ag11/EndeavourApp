package com.kiet.ecell.endeavour;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;


public class Home1 extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public Context getContext() {
        return getApplication();
    }
    private int home;
    SharedPreferences sp;
    private int position;
    SQLiteDatabase db;

    private CharSequence mTitle;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if(mNavigationDrawerFragment.isDrawerOpen())
            {
                mNavigationDrawerFragment.mDrawerLayout.closeDrawer(GravityCompat.START);
                return Boolean.parseBoolean(null);
            }
            if(home==0) {
                new AlertDialog.Builder(Home1.this)
                        .setMessage("Exit Endeavour App")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Home1.this.finish();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                        .show();
            }
            else
            {
                mNavigationDrawerFragment.selectItem(0);
            }
            }
        return Boolean.parseBoolean(null);
    }
    private boolean chechNet() {
        ConnectivityManager cm =(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        RelativeLayout coordinatorLayout = (RelativeLayout) findViewById(R.id.rl);
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        Constants co=new Constants();
        sp=getSharedPreferences(co.SharedPref,MODE_PRIVATE);
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if(Integer.valueOf(Build.VERSION.SDK_INT)>=11) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        home=0;
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = "Endeavour";
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        mNavigationDrawerFragment.mDrawerLayout.closeDrawer(GravityCompat.START);
        db=openOrCreateDatabase("Endeavour_2k161", Context.MODE_PRIVATE,null);
        if (chechNet() == true) {
            new HitJSPService(null, null, new TaskCompleted() {

                @Override
                public void onTaskCompleted(String result, int resultType) {
                    try {
                        Log.d("notification","abcd");
                        JSONObject jo = new JSONObject(result);
                        Log.d("notification","abcd");
                        JSONArray ja = jo.getJSONArray("result");
                        Log.d("notification","abcd");
                        int len = ja.length();
                        Log.d("notification","abcd");
                        for (int i = 0; i < len; i++) {
                            Log.d("notification","abcd");
                            JSONObject jo1 = ja.getJSONObject(i);
                            if (jo1.getString("save").trim().equals("1")) {
                                db.execSQL("insert into Notification values ('" + jo1.getString("name") + "','" + jo1.getString("detail") + "','" + jo1.getString("image") + "');");
                            }
                            Log.d("notification","abcd");
                            //aa.add(element);
                            Log.d("notification",""+jo1.getInt("id"));
                            sp.edit().putInt("notification", jo1.getInt("id")).commit();
                        }
                    } catch (Exception e) {
                    }
                }
            }, "http://www.endeavourkiet.in/app/notification.php?id=" + sp.getInt("notification", 2) + "&user=" + sp.getInt("Id", 0), 1).execute();
        }
        }

    @Override
    public void onNavigationDrawerItemSelected(final int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Constants co=new Constants();
        sp=getSharedPreferences(co.SharedPref,MODE_PRIVATE);
        ft.replace(R.id.container, PlaceholderFragment.newInstance(position + 1,sp));
        Log.d("ABC", "" + FragmentManager.BackStackEntry.class);
        ft.commit();
        this.position=position;
        if(position==0)
        {
            home=0;
        }
        else
        {
            home=1;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.event_schedule1, menu);
            restoreActionBar();
            if(sp.getInt("notification1",0)!=sp.getInt("notification",0))
            {
                menu.add("Notification").setIcon(R.drawable.newnotification).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(Home1.this, NotificationActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        return false;
                    }
                }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            }
            else
            {
                Log.d("notification",""+sp.getInt("notification",0));
                Log.d("notification1",""+sp.getInt("notification1",0));
                menu.add("Notification").setIcon(R.drawable.reminders).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent=new Intent(Home1.this,NotificationActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        return false;
                    }
                }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.ic_marked){
            Intent intent=new Intent(Home1.this,EventType.class);
            intent.putExtra("Type",4);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        static Context context ;
        static int a=1;

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            context=getActivity().getApplicationContext();
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber,SharedPreferences sp) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            if(sp.getBoolean("isTrue",false)) {
                if (sectionNumber == 1) {
                    fragment = new LoggedInFragment();
                    a = 1;
                }
                if (sectionNumber == 2) {
                    fragment = new EventFragment();
                    a = 2;
                }
                if (sectionNumber == 4) {
                    fragment = new ScheduleFragment();
                    a = 3;
                }
                if (sectionNumber == 5) {
                    fragment = new sponsers();
                    a = 4;
                }
                if (sectionNumber == 6) {
                    fragment = new AboutUsFrag();
                    a = 5;
                }
                if (sectionNumber == 7) {
                    fragment = new ContactUs();
                    a = 6;
                }
                if (sectionNumber == 3) {
                    fragment = new LoggedInFragment();
                    a = 1;
                    Intent intent=new Intent(context,EventType.class);
                    intent.putExtra("Type",5);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if (sectionNumber == 8) {
                    sp.edit().putBoolean("isTrue",false).commit();
                    sp.edit().putInt("Id",0).commit();
                    for(int i=1;i<=19;i++) {
                        if (sp.getInt("" + i, 0) == 01)
                            sp.edit().putInt("" + i, 0).commit();
                        else if (sp.getInt("" + i, 0) == 11)
                            sp.edit().putInt("" + i, 10).commit();
                    }
                    Intent intent=new Intent(context,Home1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }else {
                if (sectionNumber == 1) {
                    fragment = new reg_activity();
                    a = 1;
                }
                if (sectionNumber == 2) {
                    fragment = new EventFragment();
                    a = 2;
                }
                if (sectionNumber == 3) {
                    fragment = new ScheduleFragment();
                    a = 3;
                }
                if (sectionNumber == 4) {
                    fragment = new sponsers();
                    a = 4;
                }
                if (sectionNumber == 5) {
                    fragment = new AboutUsFrag();
                    a = 5;
                }
                if (sectionNumber == 6) {
                    fragment = new ContactUs();
                    a = 6;
                }
            }
            return fragment;
        }

        public PlaceholderFragment() {
        }

       @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
          View rootView = inflater.inflate(R.layout.fragment_home1, container, false);
            return rootView;
        }

       // @Override
       // public void onAttach(Activity activity) {
       //     super.onAttach(activity);
       //     ((EventSchedule1) activity).onSectionAttached(
        //            getArguments().getInt(ARG_SECTION_NUMBER));
        //}
    }

}
