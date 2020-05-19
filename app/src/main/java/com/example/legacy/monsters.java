package com.example.legacy;

/*This is where all of the relevant monster data is stored. Its is primarily a way to get better
acquainted with abstract classes and their implementation.*/

abstract class monster {
    String intro;
    int hp;
    int attackPhysical;
    int attackMagic;
    int magicDefense;
    int physicalDefense;
    int goldDrop;
}

class goblin extends monster {
    String intro = "A little goblin stands before you, with an even littler knife.";
    int hp = 10;
    int attackPhysical = 3;
    int attackMagic = 2;
    int magicDefense = 2;
    int physicalDefense = 3;
    int goldDrop = 5;
}

class minotaur extends monster {
    String intro = "A large bullman, a minotaur, stands before you, holding 2 hatchets.";
    int hp = 20;
    int attackPhysical = 6;
    int attackMagic = 2;
    int magicDefense = 5;
    int physicalDefense = 5;
    int goldDrop = 15;
}

class skeleton extends monster {
    String intro = "At first you think a very thin man stands before you, but upon closer examination, its a skeleton!";
    int hp = 5;
    int attackPhysical = 7;
    int attackMagic = 2;
    int magicDefense = 2;
    int physicalDefense = 10;
    int goldDrop = 5;
}

class bandit extends monster {
    String intro = "Hey, is that one of your old friends from elementary school? It could be, but its probably just a regular bandit.";
    int hp = 20;
    int attackPhysical = 3;
    int attackMagic = 2;
    int magicDefense = 2;
    int physicalDefense = 3;
    int goldDrop = 5;
}