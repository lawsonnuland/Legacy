package com.example.legacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
        String playername = "null";
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
        //This is the method for loading the players level.
        try {
            FileInputStream lvl = openFileInput("playerstats.txt");
            InputStreamReader levelScan = new InputStreamReader(lvl);
            Scanner lvlScan = new Scanner(levelScan);
            String level = lvlScan.nextLine();
            player.level = Integer.parseInt(level);
        } catch (FileNotFoundException e) {
            filetest.setText("level not working");
        }

        filetest.setText("Welcome to the arena " + playername + ".");
        Button button1 = findViewById(R.id.button5);
        button1.setText("Begin");

    }

    Random random = new Random();
    boolean combat = false;
    boolean defending = false;
    boolean alive = true;
    monster Enemy;
    String message;
    Player player = new Player();

    public class Player {
        //The oh so important player class and associated stats.
        public int hp = 30;
        public int hpMax = 30;
        public int mana = 10;
        public int manaMax = 10;
        public int attack = 5;
        public int defense = 2;
        public int magicAttack = 7;
        public int magicDefend;
        public int momentum;
        int level = 1;
    }

    public void updateText() {
        //A function to update text after every round.
        TextView filetest = findViewById(R.id.gametext);
        if (player.hp > 1) {
            if (defending) {
                player.hp -= Enemy.attackPhysical;
                player.hp += player.defense;
                ProgressBar hpBar = findViewById(R.id.hpBar);
                hpBar.incrementProgressBy(-Enemy.attackPhysical);
                hpBar.incrementProgressBy(player.defense);
            } else {
                player.hp -= Enemy.attackPhysical;
                ProgressBar hpBar = findViewById(R.id.hpBar);
                hpBar.incrementProgressBy(-Enemy.attackPhysical);
            }

            filetest.setText(Enemy.intro);
            filetest.append(message);
            filetest.append(" The enemy has " + Enemy.hp + " hp remaining!");
        } else {
            ProgressBar hpBar = findViewById(R.id.hpBar);
            hpBar.setProgress(0);
            filetest.setText("You are dead. Maybe start a new game? All buttons will now take you to the first screen.");
            alive = false;
        }
    }

    public void startCombat() {
        //A function that is called whenever combat is started.
        if (combat == false) {
           player.momentum = 0;
           player.mana = 10;
           player.hp = player.hpMax;
           ProgressBar hpBar = findViewById(R.id.hpBar);
           hpBar.setMax(player.hpMax);
           hpBar.incrementProgressBy(player.hp);
           ProgressBar magicBar = findViewById(R.id.magicBar);
           magicBar.setMax(player.manaMax);
           magicBar.incrementProgressBy(player.mana);
           if (player.level == 1) {
                Enemy =new goblin();
           } else if (player.level == 2) {
                Enemy = new minotaur();
           } else if (player.level == 3) {
                Enemy = new skeleton();
           } else if (player.level== 4) {
               Enemy = new bandit();
           } else {
               message = " Too high or too low";
           }
           Button button1 = findViewById(R.id.button5);
           button1.setText("Attack");
           combat = true;
       }
    }

    public void endCombat() {
        //A function that is called every time a combat is won.
        TextView filetest = findViewById(R.id.gametext);
        player.level += 1;
        if (player.level <= 4) {
            filetest.setText("Your enemy lies defeated before you. You level up and your stats are increased!");
            ProgressBar momentumBar = findViewById(R.id.momentumBar);
            momentumBar.incrementProgressBy(-player.momentum);
            ProgressBar hpBar = findViewById(R.id.hpBar);
            hpBar.setProgress(player.hpMax);
            try {
                FileOutputStream savestats = openFileOutput("playerstats.txt", Context.MODE_PRIVATE);
                String playerLevel = "" + player.level + "";
                savestats.write(playerLevel.getBytes());
                savestats.close();
            } catch (Exception e) {
                System.out.println("Error");
            }
            combat = false;
            Button button1 = findViewById(R.id.button5);
            button1.setText("Begin");
        } else {
            filetest.setText("You've made your way through the arena! You're free! Press quit to go back to the Main Menu and start again.");
        }
    }

    public void attack(View view) {
        //Attack function.
        if (alive) {
            if (!combat) {
                startCombat();
                message = "";
                updateText();
                ProgressBar hpBar = findViewById(R.id.hpBar);
                hpBar.setProgress(player.hpMax);
                player.hp = player.hpMax;
            } else {
                defending = false;
                message = " You swing your blade!";
                int attackMod = player.attack + player.momentum + random.nextInt(6);
                Enemy.hp -= (attackMod - Enemy.physicalDefense);
                if (Enemy.hp > 0) {
                    player.momentum += 1;
                    updateText();
                    ProgressBar momentumBar = findViewById(R.id.momentumBar);
                    momentumBar.incrementProgressBy(1);
                } else {
                    endCombat();
                }
            }
        } else {
            Intent quitgame = new Intent(this, MainActivity.class );
            startActivity(quitgame);
        }
    }

    public void defend(View view) {
        //Defending is not that useful, but it still exists. This is a function for it.
        if (alive) {
            message = " You defend!";
            defending = true;
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
        } else {
            Intent quitgame = new Intent(this, MainActivity.class );
            startActivity(quitgame);
        }
    }

    public void magic(View view) {
        //Cast magic to injure your enemies.
        if (alive) {
            defending = false;
            if (player.mana >= 2) {
                player.mana -= 2;
                Enemy.hp -= (player.magicAttack - Enemy.magicDefense);
                message = " You cast a small ball of light at your enemy!";
                ProgressBar manaBar = findViewById(R.id.magicBar);
                manaBar.incrementProgressBy(-2);
                if (Enemy.hp > 0) {
                    player.momentum += 1;
                    updateText();
                    ProgressBar momentumBar = findViewById(R.id.momentumBar);
                    momentumBar.incrementProgressBy(1);
                } else {
                    endCombat();
                }
            } else {
                message = " You don't have enough mana!";
            }
            updateText();
        } else {
            Intent quitgame = new Intent(this, MainActivity.class );
            startActivity(quitgame);
        }
    }

    public void quit(View view) {
        Intent quitgame = new Intent(this, MainActivity.class );
        startActivity(quitgame);
    }


}
