package com.example.legacy;

public class goblin extends monster {
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

/* if (level < 2) {
    Goblin goblin = new goblin();
} else if (level > 2 && level < 4) {
    Skeleton skeleton = new Skeleton();
} else if (level > 4) {
    Minotaur minotaur = new minotaur();
}


 */