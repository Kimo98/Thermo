package com.example.karimmahmoud.thermo;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class List2 extends AppCompatActivity {

String[] Languages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        ListView List= (ListView) findViewById(R.id.List);
        Resources res=getResources();
        Languages=res.getStringArray(R.array.Languages);
        List.setAdapter(new CustomAdapter(this));

    }
}
