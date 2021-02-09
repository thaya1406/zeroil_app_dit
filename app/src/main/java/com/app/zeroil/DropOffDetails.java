package com.app.zeroil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class DropOffDetails extends AppCompatActivity implements OnMapReadyCallback  {

    Intent intent;
    TextView name ,address ,email,contact,lat,longi;
    String s_name,s_email,s_add,s_contact,s_lat,s_longi;
    Double lattt,longii;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_off_details);

        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        email = (TextView)findViewById(R.id.email);
        contact = (TextView) findViewById(R.id.contact);
        lat = (TextView) findViewById(R.id.lat);
        longi = (TextView) findViewById(R.id.longi);

        intent =getIntent();
        if(intent != null)
        {
            s_name = intent.getStringExtra("name");
            s_email = intent.getStringExtra("email");
            s_add = intent.getStringExtra("address");
            s_contact = intent.getStringExtra("contact");
            s_lat =intent.getStringExtra("lat");
            s_longi =intent.getStringExtra("longi");


        }

        lattt = Double.parseDouble(s_lat);
        longii = Double.parseDouble(s_longi);

        name.setText(s_name);
        address.setText(s_add);
        email.setText(s_email);
        contact.setText(s_contact);


        SupportMapFragment supportMapFragment =(SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng add = new LatLng(lattt,longii);

        googleMap.addMarker(new MarkerOptions()
                .position(add)
                .title("LinkedIn")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        UiSettings mUiSettings = googleMap.getUiSettings();
         mUiSettings.setZoomControlsEnabled(true);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lattt, longii), 10));

    }
}