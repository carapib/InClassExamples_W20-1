package com.example.inclassexamples_w20;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView loads objects onto the screen.
        // Before this function, the screen is empty.
        setContentView(R.layout.activity_main);

        Intent goToSecond = new Intent(this, SecondActivity.class);
        Button secondButton = findViewById(R.id.buttonToSecond);
        secondButton.setOnClickListener( click -> startActivity( goToSecond ));

        EditText editText = findViewById(R.id.inputText);

        Intent goToThird = new Intent(this, ThirdActivity.class);
            goToThird.putExtra("name", "Eric");
            goToThird.putExtra("age", 20);
            goToThird.putExtra("typed", editText.getText().toString());

        Button thirdButton = findViewById(R.id.buttonToThird);
        thirdButton.setOnClickListener( click -> startActivityForResult(goToThird, 20 ));


        Button sharedPrefsSave = findViewById(R.id.saveButton);
        sharedPrefsSave.setOnClickListener( click -> saveSharedPrefs(editText.getText().toString()) );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void saveSharedPrefs(String stringToSave)
    {
        SharedPreferences prefs = getSharedPreferences("FileName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("TypedText", stringToSave);
        editor.commit();
    }
}
