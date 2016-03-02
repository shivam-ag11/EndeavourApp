package com.kiet.ecell.endeavour;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;



public class LoginActivity extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1;
    SharedPreferences sp;
    CheckBox cb;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Endeavour");if(Integer.valueOf(Build.VERSION.SDK_INT)>=11) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        ed1= (EditText) findViewById(R.id.input_email);
        ed2= (EditText) findViewById(R.id.input_pass);
        Constants co=new Constants();
        sp=getSharedPreferences(co.SharedPref,MODE_PRIVATE);
        b1= (Button) findViewById(R.id.button1);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_pass);
        ed1.addTextChangedListener(new MyTextWatcher(ed1));
        ed2.addTextChangedListener(new MyTextWatcher(ed2));
        cb= (CheckBox) findViewById(R.id.checkBox1);
        cb.setOnCheckedChangeListener(new  CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if (!isChecked) {
                    // show password
                    ed2.setTransformationMethod(PasswordTransformationMethod
                            .getInstance());
                } else {
                    // hide password
                    ed2.setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chechNet()==true) {
                    authenticate();
                }else{
                    Toast.makeText(getApplicationContext(),"No Internet Access",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
public void authenticate()
{
    try {
        new HitJSPService(this, null, new TaskCompleted() {

            @Override
            public void onTaskCompleted(String result, int resultType) {
                try {
                    JSONObject jo = new JSONObject(result);
                    JSONArray ja = jo.getJSONArray("result");
                    JSONObject jo1 = ja.getJSONObject(0);
                    sp.edit().putString("Name", jo1.getString("name")).commit();
                    sp.edit().putInt("Id", Integer.parseInt(jo1.getString("id"))).commit();
                    sp.edit().putBoolean("isTrue", true).commit();
                    new HitJSPService(LoginActivity.this, null, new TaskCompleted() {

                        @Override
                        public void onTaskCompleted(String result, int resultType) {
                            try {
                                JSONObject jo = new JSONObject(result);
                                JSONArray ja = jo.getJSONArray("result");
                                int len = ja.length();
                                Constants co = new Constants();
                                sp = getSharedPreferences(co.SharedPref, MODE_PRIVATE);
                                for (int i = 0; i < len; i++) {
                                    JSONObject jo1 = ja.getJSONObject(i);
                                    //Log.d("id",""+Integer.parseInt(jo1.getString("id").trim()));
                                    Log.d("id", "" + sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0));
                                    if (sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0) == 0) {
                                        Log.d("id", "" + sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0));
                                        sp.edit().putInt("" + Integer.parseInt(jo1.getString("id").trim()), 01).commit();
                                        Log.d("id", "" + sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0));
                                    }
                                    if (sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0) == 10) {
                                        Log.d("id", "" + sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0));
                                        sp.edit().putInt("" + Integer.parseInt(jo1.getString("id").trim()), 11).commit();
                                        Log.d("id", "" + sp.getInt("" + Integer.parseInt(jo1.getString("id").trim()), 0));
                                    }

                                }
                                Intent in = new Intent(LoginActivity.this, Home1.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(in);
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Intent in = new Intent(LoginActivity.this, Home1.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(in);
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, "http://www.endeavourkiet.in/app/eventcheck.php?us_id=" + sp.getInt("Id", 0), 1).execute();
                } catch (Exception e) {
                    // TODO: handle exception
                    Toast.makeText(getApplicationContext(), "Email and Code do not match", Toast.LENGTH_SHORT).show();
                }

            }
        }, "http://www.endeavourkiet.in/app/login.php?user=" + ed1.getText().toString().trim() + "&pass=" + ed2.getText().toString().trim(), 1).execute();
    }catch (Exception e){ Toast.makeText(getApplicationContext(), "Invalid character found", Toast.LENGTH_SHORT).show();}
}
    private boolean chechNet() {
        ConnectivityManager cm =(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        RelativeLayout coordinatorLayout = (RelativeLayout) findViewById(R.id.rl);
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
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
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                return true;
        }
        if(item.getItemId() == R.id.action_menu){
            Intent intent=new Intent(LoginActivity.this,Home1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
           this.finish();
           overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return Boolean.parseBoolean(null);
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_pass:
                    validatePassword();
                    break;
            }
        }
    }

    private boolean validateEmail() {
        String email = ed1.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("Enter Valid Email-id");
            requestFocus(ed1);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (ed2.getText().toString().trim().isEmpty()||ed2.getText().toString().trim().length()<6) {
            inputLayoutPassword.setError("Enter atleast 6 charcter Password");
            requestFocus(ed2);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
