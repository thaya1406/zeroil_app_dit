package com.app.zeroil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {

    Button btnrecycle, btngolearn , btnprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btnrecycle = (Button) findViewById(R.id.GoRecyle);
        btngolearn = (Button) findViewById(R.id.GoLearn);
        btnprofile = (Button) findViewById(R.id.Goprofile);


        btnrecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), DropOff.class);
                   startActivity(intent);

            }

        });



        btngolearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), LearningPage.class);
                   startActivity(intent);

            }

        });



        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(getApplicationContext(), UserProfile.class);
                  startActivity(intent);

            }

        });



    }

}