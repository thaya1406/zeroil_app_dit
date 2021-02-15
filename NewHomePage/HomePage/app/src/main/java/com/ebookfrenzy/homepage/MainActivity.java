package com.ebookfrenzy.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ebookfrenzy.homepage.R;

public class MainActivity extends AppCompatActivity {

    Button btnrecycle, btngolearn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnrecycle = (Button) findViewById(R.id.GoRecyle);
        btngolearn = (Button) findViewById(R.id.GoLearn);


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
    }

}
