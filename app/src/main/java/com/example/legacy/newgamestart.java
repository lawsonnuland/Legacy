package com.example.legacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class newgamestart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgamestart);


    }

    public void pname(View view) {
        //This reads the input of the players chosen name, and saves it to a file.
        EditText editText = (EditText) findViewById(R.id.inputname);
        String playername = editText.getText().toString();
        try {
            FileOutputStream newgame = openFileOutput("gamefile.txt", Context.MODE_PRIVATE);

            newgame.write(playername.getBytes());
            newgame.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

        Intent startgame = new Intent(this, Gamescreen.class);
        startActivity(startgame);

    }
}
