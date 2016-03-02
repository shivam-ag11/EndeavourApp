package com.kiet.ecell.endeavour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends Home1.PlaceholderFragment {

    EventListAdapter adapter;
    ListView lv;
    List<EventListElement> aa;
    ArrayAdapter<String> aa1;
    Spinner Spin;
    int height1;
    TextView txt11,txt21,txt31;

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        final RelativeLayout rl1= (RelativeLayout) getActivity().findViewById(R.id.rl1),rl2= (RelativeLayout) getActivity().findViewById(R.id.rl2),
                rl3= (RelativeLayout) getActivity().findViewById(R.id.rl3);
        final RelativeLayout rl11= (RelativeLayout) getActivity().findViewById(R.id.rl11),rl21= (RelativeLayout) getActivity().findViewById(R.id.rl21),
                rl31= (RelativeLayout) getActivity().findViewById(R.id.rl31);
        ImageView img1= (ImageView) getActivity().findViewById(R.id.img1),img2= (ImageView) getActivity().findViewById(R.id.img2),
                img3= (ImageView) getActivity().findViewById(R.id.img3);
        ImageView img11= (ImageView) getActivity().findViewById(R.id.img11),img21= (ImageView) getActivity().findViewById(R.id.img21),
                img31= (ImageView) getActivity().findViewById(R.id.img31);
        final RelativeLayout rl12= (RelativeLayout) getActivity().findViewById(R.id.rl12),rl22= (RelativeLayout) getActivity().findViewById(R.id.rl22),
                rl32= (RelativeLayout) getActivity().findViewById(R.id.rl32);
        ImageView img12= (ImageView) getActivity().findViewById(R.id.img12),img22= (ImageView) getActivity().findViewById(R.id.img22),
                img32= (ImageView) getActivity().findViewById(R.id.img32);
        txt11= (TextView) getActivity().findViewById(R.id.txt11);
        txt21= (TextView) getActivity().findViewById(R.id.txt21);
        txt31= (TextView) getActivity().findViewById(R.id.txt31);
        img1.getLayoutParams().height=width/2;
        img2.getLayoutParams().height=width/2;
        img3.getLayoutParams().height=width/2;
        rl11.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        height1 = rl11.getMeasuredHeight();
        Log.d("Height1",""+height1);
        rl1.getLayoutParams().height=height1;
        rl2.getLayoutParams().height=height1;
        rl3.getLayoutParams().height=height1;
        /*rl11.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                // gets called after layout has been done but before display.
                height1=rl11.getMeasuredHeight();
                Log.d("Height1",""+height1);
                if(height1>0)
                    rl11.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });*/

       // height1=rl1.getHeight();
        rl11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(),EventType.class);
                intent.putExtra("Type",1);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        rl21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(),EventType.class);
                intent.putExtra("Type",2);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        rl31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(),EventType.class);
                intent.putExtra("Type",3);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl11.setVisibility(View.INVISIBLE);
                rl12.setVisibility(View.VISIBLE);
                rl21.setVisibility(View.VISIBLE);
                rl22.setVisibility(View.INVISIBLE);
                rl31.setVisibility(View.VISIBLE);
                rl32.setVisibility(View.INVISIBLE);
                rl1.getLayoutParams().height=height1;
            }
        });
        img21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl21.setVisibility(View.INVISIBLE);
                rl22.setVisibility(View.VISIBLE);
                rl11.setVisibility(View.VISIBLE);
                rl12.setVisibility(View.INVISIBLE);
                rl31.setVisibility(View.VISIBLE);
                rl32.setVisibility(View.INVISIBLE);
                rl2.getLayoutParams().height=height1;
            }
        });
        img31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl31.setVisibility(View.INVISIBLE);
                rl32.setVisibility(View.VISIBLE);
                rl21.setVisibility(View.VISIBLE);
                rl22.setVisibility(View.INVISIBLE);
                rl11.setVisibility(View.VISIBLE);
                rl12.setVisibility(View.INVISIBLE);
                rl3.getLayoutParams().height=height1;
            }
        });
        img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl12.setVisibility(View.INVISIBLE);
                rl11.setVisibility(View.VISIBLE);
                rl1.getLayoutParams().height=height1;
            }
        });
        img22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl22.setVisibility(View.INVISIBLE);
                rl21.setVisibility(View.VISIBLE);
                rl2.getLayoutParams().height=height1;
            }
        });
        img32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl32.setVisibility(View.INVISIBLE);
                rl31.setVisibility(View.VISIBLE);
                rl3.getLayoutParams().height=height1;
            }
        });
        txt11.setText("A platform where the dreams of a starry eyed youth meet the opportunities that rarely knocks his door!\n" +
                "\n" +
                "The events under the ‘Corporate’ module are designed to test your leadership qualities, competitive and decision making skills in the real world situations using strategy and innovation as your tools.\n" +
                "\n" +
                "A crucial mix of theoretical knowledge and practical hands can lead you to victory. \n" +
                "The main aim is to give you a learning experience and at the same time have some fun.");
        txt21.setText("\"Any sufficiently advanced technology is indistinguishable from a magic\"\n" +
                "---- Arthur C. Clarke\n" +
                "\n" +
                "This module is a platform that provides the participants a chance to showcase their technique and emerge as the biggest technowit. It has various events under it which will put down one's technical skills to test.\n" +
                "\n" +
                "So if you find interest in this arena,you are at the right place. Unleash the technocrat in you.");
        txt31.setText("\"Winning is only half of it, having fun is the other half\"\n" +
                "\n" +
                "Overflow with gaming, entertainment and much more.It is a platform for both luck and wits, so get your brains rolling and push your luck to bluff your opposition.\n" +
                "\n" +
                "This \"FUN\" module is a platform which consists of various events that provides the participants to show their leadership,competitive and other gaming skills using various strategies as their rafts in the sea.");

        ScrollView scrollView= (ScrollView) getActivity().findViewById(R.id.scrollView),scrollView1= (ScrollView) getActivity().findViewById(R.id.scrollView1),
                scrollView2= (ScrollView) getActivity().findViewById(R.id.scrollView2),scrollView3= (ScrollView) getActivity().findViewById(R.id.scrollView3);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                getActivity().findViewById(R.id.scrollView1).getParent().requestDisallowInterceptTouchEvent(false);
                getActivity().findViewById(R.id.scrollView2).getParent().requestDisallowInterceptTouchEvent(false);
                getActivity().findViewById(R.id.scrollView3).getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        scrollView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        scrollView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        scrollView3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

}
