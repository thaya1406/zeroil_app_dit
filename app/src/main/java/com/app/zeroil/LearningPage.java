package com.app.zeroil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class LearningPage extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learningpage);

        // get the list of page titles and contents in string array

        String[] lists = getResources().getStringArray(R.array.page_list);
        String[] contents = getResources().getStringArray(R.array.page_content);

        recyclerView = findViewById(R.id.pageListsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, lists, contents); //adapter takes 1 string array
        recyclerView.setAdapter(adapter);
    }
}