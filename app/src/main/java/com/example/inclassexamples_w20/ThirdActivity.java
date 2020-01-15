package com.example.inclassexamples_w20;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_third);
        Button previousButton = findViewById(R.id.previousPageButton);
        previousButton.setOnClickListener(click -> {
            //when you click the button, we want to send information back to the previous page

            Intent dataBack = new Intent();
            dataBack.putExtra("Day","Thursday");
            dataBack.putExtra("Year", 2020);

            //send the intent back to the previous page. Check onActivityResult() in FirstActivity
            setResult(50, dataBack);

            //now go back to previous page.
            finish();
            }
        );

        EditText edit = findViewById(R.id.editText);
    }

}
