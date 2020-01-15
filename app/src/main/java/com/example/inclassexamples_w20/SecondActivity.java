package com.example.inclassexamples_w20;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        Intent dataSent = getIntent();
        String nameSent = dataSent.getStringExtra("name");
        int ageSent = dataSent.getIntExtra("age", 0);
        String typed = dataSent.getStringExtra("typed");

        Button previousButton = findViewById(R.id.previousPageButton);
        previousButton.setOnClickListener(click ->
                finish()
        );
    }

}
