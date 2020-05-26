package com.example.legacy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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

    Random random = new Random();
    int hp = 30;
    int hpMax = 30;
    int mana = 10;
    int manaMax = 10;
    int attack;
    int defense;
    int magicAttack;
    int magicDefend;
    //Momentum is a measurement of the flow of combat. Keep it up, and you'll have an easier time.
    public int momentum;
    // Level needs to get imported from save file. Work on that soon.
    int level;
    boolean combat = false;
    goblin goblin =new goblin();
    String message;

    public void updateText() {
        TextView filetest = findViewById(R.id.gametext);
        filetest.setText(goblin.intro);
    }

    public void startCombat() {
       if (combat == false) {
           momentum = 0;
           ProgressBar hpBar = findViewById(R.id.hpBar);
           hpBar.setMax(hpMax);
           hpBar.incrementProgressBy(hp);
           ProgressBar magicBar = findViewById(R.id.magicBar);
           magicBar.setMax(manaMax);
           magicBar.incrementProgressBy(mana);
           combat = true;
       }
    }

    public void attack(View view) {
        startCombat();
        int attackMod = attack + momentum + random.nextInt(6);
        momentum += 1;
        updateText();
        ProgressBar momentumBar = findViewById(R.id.momentumBar);
        momentumBar.incrementProgressBy(1);
    }

    public void defend(View view) {
        ProgressBar momentumBar = findViewById(R.id.momentumBar);
        momentumBar.incrementProgressBy(-1);
    }

    public void magic(View view) {

    }



}
