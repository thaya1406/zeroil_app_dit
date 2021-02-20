package com.app.zeroil;
// Go Learn Page

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    TextView contentTitle, contentDesc;
//    ImageView contentImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        contentTitle = findViewById(R.id.title_txt);
        contentDesc = findViewById(R.id.content_txt);
//        contentImg = findViewById(R.id.contentImage);

        Intent i = getIntent();
        String title = i.getStringExtra("title_txt");
        String content = i.getStringExtra("content_txt");
//        String image = i.getStringExtra("contentImage");

        // Set the appbar title as Story title
        getSupportActionBar().setTitle(title);

        // Set content of the story to textview
        contentTitle.setText(title);
        contentDesc.setText(content);
        contentDesc.setMovementMethod(new ScrollingMovementMethod());

        // Enable back button to main activity or Recyclerview
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}