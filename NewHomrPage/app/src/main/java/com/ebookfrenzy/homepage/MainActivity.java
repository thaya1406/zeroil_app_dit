package com.ebookfrenzy.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnrecycle, btngolearn;
    ImageButton btn_profile, btn_home, btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnrecycle = (Button) findViewById(R.id.GoRecyle);
        btngolearn = (Button) findViewById(R.id.GoLearn);
        btn_profile = (ImageButton) findViewById(R.id.profile_button);
        btn_home = (ImageButton) findViewById(R.id.home_button);
        btn_logout = (ImageButton) findViewById(R.id.logout_button);


        btnrecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//Leave the event handling as it need to be merged at the end
                //Intent intent  = new Intent(getApplicationContext(), SideNavigation.class);
                //   startActivity(intent);

            }

        });



        btngolearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//Leave the event handling as it need to be merged at the end
                //Intent intent  = new Intent(getApplicationContext(), SideNavigation.class);
                //   startActivity(intent);

            }

        });

        btn_profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, profile_page.class);
            startActivity(intent);
            }

        });

        btn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"You're already at the homepage", Toast.LENGTH_LONG).show();
            }

        });

        btn_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, logout_page.class);
                startActivity(intent);
            }

        });


    }
}
