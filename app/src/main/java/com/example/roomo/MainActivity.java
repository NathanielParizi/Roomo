package com.example.roomo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomo.Data.DatabaseHandler;
import com.example.roomo.Model.Animals;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Button button;
    public static final String PREFS_NAME = "myPrefsFile";
    private EditText editText;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        txt = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);

        DatabaseHandler db = new DatabaseHandler(this);


        //Insert Contacts

        Log.d("INSERT", "inserting ... ");
        db.addAnimal(new Animals("Viper", "Reptile"));
        db.addAnimal(new Animals("Zebra", "Mammel"));
        db.addAnimal(new Animals("Shark", "Fish"));
        db.addAnimal(new Animals("Elephant", "Mammal"));


        //Get them
        Log.d("READ", "Reading...");
        List<Animals> animalsList = db.getAllAnimals();

        for (Animals c : animalsList) {

            String str = String.valueOf(c.getId()) + " " + c.getName() + " " + c.getSpeices();
            Log.d("GETALL", str);

        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("hello", editText.getText().toString());
                editor.apply();
                txt.setText(editText.getText().toString());

            }
        });

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);

        if (prefs.contains("hello")) {

            String message = prefs.getString("hello", "empty");
            txt.setText(message);

        }


    }


}
