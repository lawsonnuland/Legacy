package com.example.legacy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* This is going to be the primary screen for gameplay. Read through for additional comments where
 appropriate.  */
public class Gamescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);
        String playername = "butts";

        TextView filetest = findViewById(R.id.gametext);
        //This is the method for reading the players name from the saved file.
        try {
            FileInputStream fin = openFileInput("gamefile.txt");
            InputStreamReader readfile = new InputStreamReader(fin);
            Scanner sc = new Scanner(readfile);
            StringBuffer sb =new StringBuffer();

            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }
            playername = sb.toString();
            filetest.setText(playername);
            readfile.close();

        } catch (FileNotFoundException e) {
            filetest.setText("Gump bump");
        } catch (IOException e) {
            e.printStackTrace();
        }
        filetest.setText("Welcome to the arena " + playername + ".");

    }


}
