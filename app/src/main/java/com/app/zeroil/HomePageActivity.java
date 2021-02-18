package com.app.zeroil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomePageActivity extends AppCompatActivity {

    Button btnrecycle, btngolearn ;
    ImageButton btn_profile,btn_home,btn_logout;
    SharedPreferences preff;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btnrecycle = (Button) findViewById(R.id.GoRecyle);
        btngolearn = (Button) findViewById(R.id.GoLearn);
        btn_profile = (ImageButton) findViewById(R.id.profile_button);
        btn_logout = (ImageButton) findViewById(R.id.logout_button);

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



        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  = new Intent(getApplicationContext(), UserProfile.class);
                  startActivity(intent);

            }

        });




        btn_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                SharedPreferences settings = context.getSharedPreferences("username", Context.MODE_PRIVATE);
                settings.edit().clear().commit();

                Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

    }

}