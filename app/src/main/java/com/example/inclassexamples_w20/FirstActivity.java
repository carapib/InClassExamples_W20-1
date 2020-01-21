package com.example.inclassexamples_w20;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView loads objects onto the screen.
        // Before this function, the screen is empty.
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("FileName", Context.MODE_PRIVATE);
        String savedString = prefs.getString("ReserveName", "Enter something");
        EditText typeField = findViewById(R.id.inputText);
        typeField.setText(savedString);

        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener( bt -> saveSharedPrefs( typeField.getText().toString()) );
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
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ReserveName", stringToSave);
        editor.commit();
    }
}
