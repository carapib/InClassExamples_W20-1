package com.example.inclassexamples_w20;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class FragmentExample extends AppCompatActivity {

    public static final String ITEM_SELECTED = "ITEM";
    public static final String ITEM_POSITION = "POSITION";
    public static final String ITEM_ID = "ID";
    public static final int EMPTY_ACTIVITY = 345;

    ArrayAdapter<String> theAdapter;

    ArrayList<String> source = new ArrayList<>(Arrays.asList("One", "Two", "Three", "Four"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_example);

        ListView theList = (ListView) findViewById(R.id.theList);
        boolean isTablet = findViewById(R.id.fragmentLocation) != null; //check if the FrameLayout is loaded

        theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, source);
        theList.setAdapter(theAdapter);
        theList.setOnItemClickListener((list, item, position, id) -> {

            //Add fragment loading here from slide 14.

        });
    }
}
