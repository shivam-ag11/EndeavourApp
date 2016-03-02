package com.kiet.ecell.endeavour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 01/10/2016.
 */
public class DrawerListAdapter extends BaseAdapter {
    final Context context;
    final List<String> listElement;

    public DrawerListAdapter(Context context,List<String> listElement)
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
        final String entry = listElement.get(position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.drawerlistitem, null);
        TextView tvHead1 = (TextView) convertView.findViewById(R.id.txt_Head1);
        TextView tvHead2 = (TextView) convertView.findViewById(R.id.txt_Head2);
        TextView tvHead3 = (TextView) convertView.findViewById(R.id.txt_Head3);
        TextView tvHead4 = (TextView) convertView.findViewById(R.id.txt_Head4);
        TextView tvHead5 = (TextView) convertView.findViewById(R.id.txt_Head5);
        TextView tvHead6 = (TextView) convertView.findViewById(R.id.txt_Head6);
        TextView tvHead7 = (TextView) convertView.findViewById(R.id.txt_Head7);
        TextView tvHead8 = (TextView) convertView.findViewById(R.id.txt_Head8);
        if(entry.trim().equals("Home")) {
            tvHead1.setVisibility(View.VISIBLE);
        }
        else if(entry.trim().equals("Events")) {
            tvHead2.setVisibility(View.VISIBLE);
        }
        else if(entry.trim().equals("Schedule")) {
            tvHead3.setVisibility(View.VISIBLE);
        }
        else if(entry.trim().equals("Sponsors")) {
            tvHead4.setVisibility(View.VISIBLE);
        }
        else if(entry.trim().equals("Facebook")) {
            tvHead5.setVisibility(View.VISIBLE);
        }
        else if(entry.trim().equals("Contact Us")) {
            tvHead6.setVisibility(View.VISIBLE);
        }
        else if(entry.trim().equals("Logout")) {
            tvHead7.setVisibility(View.VISIBLE);
        }
        else if(entry.trim().equals("Registered Events")) {
            tvHead8.setVisibility(View.VISIBLE);
        }
        //convertView.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
              //  if(entry.trim().equals("Facebook")){
                //    Intent intent = new Intent();
                  //  intent.setAction(Intent.ACTION_VIEW);
                    //intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    //intent.setData(Uri.parse("http://facebook.com/endeavourkiet/"));
                    //context.startActivity(intent);
               // }
          //  }
       // });
        return convertView;
    }
}
