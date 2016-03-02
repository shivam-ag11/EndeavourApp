package com.kiet.ecell.endeavour;

import android.app.ActionBar;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 01/06/2016.
 */
public class EventListAdapter extends BaseAdapter {
    final Context context;
    final List<EventListElement> listElement;

    public EventListAdapter(Context context,List<EventListElement> listElement)
    {
        this.context=context;
        this.listElement=listElement;
    }

    @Override
    public int getCount() {
        return listElement.size();
    }

    @Override
    public Object getItem(int i) {
        return listElement.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventListElement entry = listElement.get(position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.eventlistrow, null);
        TextView tvHead = (TextView) convertView.findViewById(R.id.text_Head);
        tvHead.setText(entry.getTitle());
        TextView tvOneLine = (TextView) convertView.findViewById(R.id.text_OneLine);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.text_Price);
        tvOneLine.setText(entry.getOneLine());
        tvPrice.setText(entry.getPlace());
        if(entry.getID()==0)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.getLayoutParams().width=0;
            RelativeLayout rl1= (RelativeLayout) convertView.findViewById(R.id.rl1);
            rl1.setPadding(0,0,0,0);
            tvPrice.setVisibility(View.INVISIBLE);
        }
        if(entry.getID()==12)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.innolist);
        }
        else if(entry.getID()==10)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.spurofmomentlist);
        }
        else if(entry.getID()==1)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.ideastormlist);
        }
        else if(entry.getID()==6)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.casestudylist);
        }
        else if(entry.getID()==13)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.mins10list);
        }
        else if(entry.getID()==8)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.bnoesislist);
        }
        else if(entry.getID()==4)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.maestrolist);
        }
        else if(entry.getID()==14)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.stretegistlist);
        }
        else if(entry.getID()==16)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.reminiscenelist);
            tvPrice.setVisibility(View.INVISIBLE);
        }
        else if(entry.getID()==17)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.photographylist);
            tvPrice.setVisibility(View.INVISIBLE);
        }
        else if(entry.getID()==18)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.treasurehuntlist);
        }
        else if(entry.getID()==15)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.langaminglist);
        }
        else if(entry.getID()==19)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.selfielist);
        }
        else if(entry.getID()==7)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.botmobileslist);
        }
        else if(entry.getID()==5)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.monsterarenalist);
        }
        else if(entry.getID()==3)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.projectexpolist);
        }
        else if(entry.getID()==11)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.contructolist);
        }
        else if(entry.getID()==9)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.letusseelist);
        }
        else if(entry.getID()==2)
        {
            ImageView img= (ImageView) convertView.findViewById(R.id.img1);
            img.setImageResource(R.drawable.codewarlist);
        }
        return convertView;
    }
}
