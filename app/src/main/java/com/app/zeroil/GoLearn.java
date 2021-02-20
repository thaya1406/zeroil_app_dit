package com.app.zeroil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.ActivityOptions;
import android.os.Handler;
import android.util.Pair;
public class GoLearn extends AppCompatActivity {


    // Go Learn Page
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_main);
        // Go Learn Page
        String[] titles = getResources().getStringArray(R.array.page_title_list);
        String[] content = getResources().getStringArray(R.array.description);
//        int[] images = {R.drawable.used_oil, R.drawable.used_oil2, R.drawable.used_oil3, R.drawable.used_oil4, R.drawable.used_oil5, R.drawable.used_oil6};

        // Lookup the recyclerview in activity layout
        recyclerView = findViewById(R.id.recyclerView);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Create adapter passing in the sample user data
        adapter = new Adapter(this, titles, content);
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);

    }
}


