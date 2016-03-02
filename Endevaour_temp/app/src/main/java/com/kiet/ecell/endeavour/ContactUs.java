package com.kiet.ecell.endeavour;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


public class ContactUs extends Home1.PlaceholderFragment {

    EditText ed3,ed4,ed5;
    TextInputLayout inputLayoutEmail,inputLayoutQuestion,inputLayoutSubject;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button b= (Button) getActivity().findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed3.getText().toString().trim().equals("")|| ed4.getText().toString().trim().equals("") || ed4.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Fill all the enteries to submit your query",Toast.LENGTH_SHORT).show();
                }
                else {
                    new HitJSPService(getActivity(), null, new TaskCompleted() {

                        @Override
                        public void onTaskCompleted(String result, int resultType) {
                            try {
                                if (result.toString().trim().equals("Success")) {
                                    new AlertDialog.Builder(getActivity())
                                            .setTitle("Query Submitted")
                                            .setMessage("Your query is submitted we will contact you soon")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            })
                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                            .show();
                                    ed3.setText("");
                                    ed4.setText("");
                                    ed5.setText("");
                                } else {
                                    Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                // TODO: handle exception
                                Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, "http://www.endeavourkiet.in/app/send.php?sub=" + ed3.getText().toString().trim() + "&from=" + ed4.getText().toString().trim() + "&msg=From%20-%20" + ed4.getText().toString().trim() + "%20:-%20%20%20%20" + ed5.getText().toString().trim(), 1).execute();

                }
            }
        });
        inputLayoutEmail = (TextInputLayout) getActivity().findViewById(R.id.input_layout_email);
                inputLayoutQuestion = (TextInputLayout) getActivity().findViewById(R.id.input_layout_ques);
                inputLayoutSubject = (TextInputLayout) getActivity().findViewById(R.id.input_layout_subject);
        ed3= (EditText) getActivity().findViewById(R.id.editText3);
        ed4=(EditText) getActivity().findViewById(R.id.editText4);
        ed5=(EditText) getActivity().findViewById(R.id.editText5);
        ed3.addTextChangedListener(new MyTextWatcher(ed3));
        ed4.addTextChangedListener(new MyTextWatcher(ed4));
        ed5.addTextChangedListener(new MyTextWatcher(ed5));
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_contact_us,container,false);
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
                case R.id.editText3:
                    validateSubject();
                    break;
                case R.id.editText4:
                    validateEmail();
                    break;
                case R.id.editText5:
                    validateQuery();
                    break;
            }
        }


        private boolean validateEmail() {
            String email = ed4.getText().toString().trim();

            if (email.isEmpty() || !isValidEmail(email)) {
                inputLayoutEmail.setError("Enter Valid Email-id");
                requestFocus(ed4);
                return false;
            } else {
                inputLayoutEmail.setErrorEnabled(false);
            }

            return true;
        }

        private boolean validateSubject() {
            if (ed3.getText().toString().trim().isEmpty()) {
                inputLayoutSubject.setError("Subject cannot be empty");
                requestFocus(ed3);
                return false;
            } else {
                inputLayoutSubject.setErrorEnabled(false);
            }

            return true;
        }
        private boolean validateQuery() {
            if (ed5.getText().toString().trim().isEmpty()) {
                inputLayoutQuestion.setError("Subject cannot be empty");
                requestFocus(ed5);
                return false;
            } else {
                inputLayoutQuestion.setErrorEnabled(false);
            }

            return true;
        }
        private  boolean isValidEmail(String email) {
            return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

        private void requestFocus(View view) {
            if (view.requestFocus()) {
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
    }
}
