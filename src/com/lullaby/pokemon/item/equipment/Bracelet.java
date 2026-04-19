package com.lullaby.pokemon.item.equipment;

import com.lullaby.pokemon.Util.Tools;

/**
 * 手镯
 */
public class Bracelet extends Equipment{

    public Bracelet(int levelNumber) {
        super("手镯", levelNumber);
        this.attack = Tools.getRandom(20, 30, levelNumber);
        this.defense = Tools.getRandom(20, 30, levelNumber);
        this.health = Tools.getRandom(100, 200, levelNumber);
    }
}
