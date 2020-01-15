package com.example.inclassexamples_w20;


import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button previousButton = findViewById(R.id.previousPageButton);
        previousButton.setOnClickListener(click ->
                finish()
        );

    }

}
