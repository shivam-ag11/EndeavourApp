package com.kiet.ecell.endeavour;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class reg_activity extends Home1.PlaceholderFragment {
    Button b1,b2;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        b1 = (Button) getActivity().findViewById(R.id.button);
        b2 = (Button) getActivity().findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                String scheme = "http://www.endeavourkiet.in/frontend/web/index.php?r=user%2Fcreate";
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme));
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_reg,container,false);
    }
}
