package com.app.zeroil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentDetails extends AppCompatActivity {

    ImageView contentImage;
    TextView pageContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_details);



        pageContent = findViewById(R.id.titleOfPage);
        Intent i = getIntent();
        String title = i.getStringExtra("titleOfPage");
        String content = i.getStringExtra("contentOfPage");

        // set the appbar title as Page title
        getSupportActionBar().setTitle(title);

        // set content of the page to textview
        pageContent.setText(title);
        pageContent.setMovementMethod(new ScrollingMovementMethod());
        pageContent.setText(content);
        pageContent.setMovementMethod(new ScrollingMovementMethod());

        //enable back button to main activity or recyclerview
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
