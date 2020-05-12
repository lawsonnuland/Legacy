package com.example.legacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Formatter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGame(View view) {
        try {
            FileOutputStream newgame = openFileOutput("gamefile.txt", Context.MODE_PRIVATE);
            String gout ="You are reading data from the file bitch";
            newgame.write(gout.getBytes());
            newgame.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        Intent newgame = new Intent(this, newgamestart.class);
        startActivity(newgame);
    }

    public void aboutGame(View view) {
        Intent aboutthegame = new Intent(this, about_game.class);
        startActivity(aboutthegame);
    }

}
