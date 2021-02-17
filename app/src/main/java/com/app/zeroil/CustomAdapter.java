package com.app.zeroil;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<DropOffModel> Item;
    TextView name ,address ,email,contact,lat,longi;

//Constructor
    public CustomAdapter (Activity activity,ArrayList<DropOffModel> Items){
        this.activity=activity;
        this.Item=Items;
    }
    @Override
    public int getCount() {
        return Item.size();
    }

    @Override
    public DropOffModel getItem(int position) {
        return Item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if ( inflater == null ){
            inflater=(LayoutInflater) activity.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        }

        if ( convertView == null ){
            convertView= inflater.inflate (R.layout.list_row,null);
        }

        name = (TextView) convertView.findViewById(R.id.name);
        address = (TextView) convertView.findViewById(R.id.address);
        email = (TextView) convertView.findViewById(R.id.email);
        contact = (TextView) convertView.findViewById(R.id.contact);



        DropOffModel DO = Item.get(position);
        name.setText(DO.getName());
        address.setText(DO.getAddress());
        email.setText(DO.getEmail());
        contact.setText(DO.getContact());



        return convertView;
    }
}