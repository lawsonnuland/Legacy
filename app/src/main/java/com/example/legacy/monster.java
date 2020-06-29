package com.example.legacy;

/*This is where all of the relevant monster data is stored. Its is primarily a way to get better
acquainted with abstract classes and their implementation.*/

public abstract class monster {
    String intro;
    int hp;
    int attackPhysical;
    int attackMagic;
    int magicDefense;
    int physicalDefense;
    int goldDrop;
}

class goblin extends monster {
    public goblin() {
        intro = "A little goblin stands before you, with an even littler knife.";
        hp = 10;
        attackPhysical = 3;
        attackMagic = 2;
        magicDefense = 2;
        physicalDefense = 3;
        goldDrop = 5;
    }
}

class minotaur extends monster {
    public minotaur() {
        intro = "A large bullman, a minotaur, stands before you, holding 2 hatchets.";
        hp = 20;
        attackPhysical = 6;
        attackMagic = 2;
        magicDefense = 5;
        physicalDefense = 2;
        goldDrop = 15;
    }
}

class skeleton extends monster {
    public skeleton() {
        intro = "At first you think a very thin man stands before you, but upon closer examination, its a skeleton!";
        hp = 5;
        attackPhysical = 3;
        attackMagic = 2;
        magicDefense = 2;
        physicalDefense = 7;
        goldDrop = 5;
    }
}

class bandit extends monster {
    public bandit() {
        intro = "Hey, is that one of your old friends from elementary school? It could be, but its probably just a regular bandit.";
        hp = 20;
        attackPhysical = 3;
        attackMagic = 2;
        magicDefense = 2;
        physicalDefense = 3;
        goldDrop = 5;
    }
}