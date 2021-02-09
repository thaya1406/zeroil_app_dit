package com.app.zeroil;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class DropOff extends AppCompatActivity{

    Intent intent;
    ListView dropofflist;
    CustomAdapter customAdapter;
    ArrayList<DropOffModel> arrayList;
    DropOffModel dm;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_off);


        dropofflist= (ListView) findViewById(R.id.dropofflistt) ;
        dbHelper = new DBHelper(this);

        arrayList = new ArrayList<>();
        arrayList =dbHelper.GetDropOffLocation();

        customAdapter=new CustomAdapter(this,arrayList);

        dropofflist.setAdapter(customAdapter);
        dropofflist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dm = customAdapter.getItem(position);
                Intent intent=new Intent(DropOff.this,DropOffDetails.class);
               intent.putExtra("name",dm.getName());
                intent.putExtra("email",dm.getEmail());
                intent.putExtra("contact",dm.getContact());
                intent.putExtra("address",dm.getAddress());
                intent.putExtra("lat",dm.getLat());
                intent.putExtra("longi",dm.getLongi());


                startActivity(intent);


            }
        });

    }
}
