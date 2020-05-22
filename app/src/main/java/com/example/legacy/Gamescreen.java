package com.example.legacy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

/* This is going to be the primary screen for gameplay. Read through for additional comments where
 appropriate.  */
public class Gamescreen extends AppCompatActivity {
    Random random = new Random();
    int hp;
    int hpMax;
    int attack;
    int defense;
    int magicAttack;
    int magicDefend;
    //Momentum is a measurement of the flow of combat. Keep it up, and you'll have an easier time.
    int momentum;
    // Level needs to get imported from save file. Work on that soon.
    int level;
    goblin goblin =new goblin();
    TextView filetest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);
        String playername = "butts";
        int defense = 2;
        level = 1;
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

    public void updateText() {

    }

    public void startCombat() {
        momentum = 0;
        if (level ==1) {
            filetest.setText(goblin.intro);
        }
    }

    public void attack(View view) {
        filetest.setText("boobs");
        int attackMod = attack + momentum + random.nextInt(6);
        momentum += 1;
    }

    public void defend(View view) {

    }

    public void magic(View view) {

    }



}
