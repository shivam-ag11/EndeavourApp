package com.kiet.ecell.endeavour;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class register extends AppCompatActivity {

    private EditText inputName, inputEmail, inputPassword, inputPhone, inputCollege, inputYear, inputBranch, inputBrand;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword, inputLayoutPhone, inputLayoutCollege, inputLayoutYear
            , inputLayoutBranch, inputLayoutBrand;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Endeavour");
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_pass);
        inputLayoutPhone=(TextInputLayout) findViewById(R.id.input_layout_phone);
        inputLayoutCollege=(TextInputLayout) findViewById(R.id.input_layout_college);
        inputLayoutYear=(TextInputLayout) findViewById(R.id.input_layout_year);
        inputLayoutBranch=(TextInputLayout) findViewById(R.id.input_layout_branch);
        inputLayoutBrand=(TextInputLayout) findViewById(R.id.input_layout_brand);
        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_pass);
        inputPhone=(EditText) findViewById(R.id.input_phone);
        inputCollege=(EditText) findViewById(R.id.input_college);
        inputYear=(EditText) findViewById(R.id.input_year);
        inputBranch=(EditText) findViewById(R.id.input_branch);
        inputBrand=(EditText) findViewById(R.id.input_brand);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));
        inputCollege.addTextChangedListener(new MyTextWatcher(inputCollege));
        inputYear.addTextChangedListener(new MyTextWatcher(inputYear));
        inputBranch.addTextChangedListener(new MyTextWatcher(inputBranch));
        inputBrand.addTextChangedListener(new MyTextWatcher(inputBrand));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
            Intent intent=new Intent(register.this,Home1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_pass:
                    validatePassword();
                    break;
                case R.id.input_phone:
                    validatePhone();
                    break;
                case R.id.input_college:
                    validateCollge();
                    break;
                case R.id.input_year:
                    validateYear();
                    break;
                case R.id.input_branch:
                    validateBranch();
                    break;
                case R.id.input_brand:
                    validateBrand();
                    break;
            }
        }
    }

    private boolean validateYear() {
        if (inputYear.getText().toString().trim().isEmpty() || Integer.parseInt(inputYear.getText().toString().trim()) > 4
                || Integer.parseInt(inputYear.getText().toString().trim()) < 1) {
            inputLayoutYear.setError("Enter Year 1,2,3 or 4");
            requestFocus(inputYear);
            return false;
        } else {
            inputLayoutYear.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateBrand() {
        return true;
    }

    private boolean validateBranch() {
        if (inputBranch.getText().toString().trim().isEmpty()) {
            inputLayoutBranch.setError("Enter Branch");
            requestFocus(inputBranch);
            return false;
        } else {
            inputLayoutBranch.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateCollge() {
        if (inputCollege.getText().toString().trim().isEmpty()) {
            inputLayoutCollege.setError("Enter College");
            requestFocus(inputCollege);
            return false;
        } else {
            inputLayoutCollege.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validatePhone() {
        if (inputPhone.getText().toString().trim().isEmpty() || inputPhone.getText().toString().trim().length()!=10) {
            inputLayoutPhone.setError("Enter Valid Phone No.");
            requestFocus(inputPhone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError("Enter Name");
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("Enter Valid Email-id");
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()||inputPassword.getText().toString().trim().length()<6) {
            inputLayoutPassword.setError("Enter atleast 6 charcter Password");
            requestFocus(inputPassword);
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
}
