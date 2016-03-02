package com.kiet.ecell.endeavour;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link LoggedInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoggedInFragment extends Home1.PlaceholderFragment {

    SharedPreferences sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logged_in, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Constants co=new Constants();
        sp=getActivity().getSharedPreferences(co.SharedPref,getActivity().MODE_PRIVATE);
        TextView tv= (TextView) getActivity().findViewById(R.id.user);
        tv.setText(sp.getString("Name",null));
    }
}
