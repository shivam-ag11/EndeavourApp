package com.kiet.ecell.endeavour;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class ImageViewActivity extends AppCompatActivity {

    SharedPreferences sp;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
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
        String image=getIntent().getExtras().getString("image");
        Log.d("image",image);
        ImageView img1= (ImageView) findViewById(R.id.img1);
        byte[] imageAsBytes = Base64.decode(image, Base64.DEFAULT);
        Bitmap bm= BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
        img1.getLayoutParams().height= (int) (bm.getHeight()*1.25);
        img1.getLayoutParams().width= (int) (bm.getWidth()*1.25);
        img1.setScaleType(ImageView.ScaleType.FIT_XY);
        img1.setImageBitmap(bm);
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
            Intent intent=new Intent(ImageViewActivity.this,Home1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
