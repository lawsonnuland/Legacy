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
    //Momentum is a measurement of the flow of combat. Keep it up, and you'll have an easier time.
    // Level needs to get imported from save file. Work on that soon.
    int level;
    boolean combat = false;
    goblin goblin =new goblin();
    String message;
    Player player = new Player();

    public class Player {
        public int hp = 30;
        public int hpMax = 30;
        public int mana = 10;
        public int manaMax = 10;
        public int attack;
        public int defense;
        public int magicAttack;
        public int magicDefend;
        public int momentum;
    }

    public void updateText() {
        TextView filetest = findViewById(R.id.gametext);
        filetest.setText(goblin.intro);
        filetest.append(message);
    }

    public void startCombat() {
       if (combat == false) {
           player.momentum = 0;
           player.mana = 10;
           ProgressBar hpBar = findViewById(R.id.hpBar);
           hpBar.setMax(player.hpMax);
           hpBar.incrementProgressBy(player.hp);
           ProgressBar magicBar = findViewById(R.id.magicBar);
           magicBar.setMax(player.manaMax);
           magicBar.incrementProgressBy(player.mana);
           combat = true;
       }
    }

    public void attack(View view) {
        startCombat();
        message = " You swing your blade!";
        int attackMod = player.attack + player.momentum + random.nextInt(6);
        player.momentum += 1;
        updateText();
        ProgressBar momentumBar = findViewById(R.id.momentumBar);
        momentumBar.incrementProgressBy(1);
    }

    public void defend(View view) {
        message = " You defend!";
        ProgressBar momentumBar = findViewById(R.id.momentumBar);
        momentumBar.incrementProgressBy(-1);
        if (player.momentum >= 1) {
            player.momentum -= 1;
        }
        if (player.mana < 10) {
            ProgressBar manaBar = findViewById(R.id.magicBar);
            manaBar.incrementProgressBy(1);
            player.mana += 1;
        }
        updateText();
    }

    public void magic(View view) {
        if (player.mana >= 2) {
            player.mana -= 2;
            message = " You cast a small ball of light at your enemy!";
            ProgressBar manaBar = findViewById(R.id.magicBar);
            manaBar.incrementProgressBy(-2);
        } else {
            message = " You don't have enough mana!";
        }
        updateText();
    }

}
