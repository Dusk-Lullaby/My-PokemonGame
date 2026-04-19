package com.lullaby.pokemon.item.equipment;

import com.lullaby.pokemon.Util.Tools;

/**
 * 鞋子
 */
public class Shoe extends Equipment{

    public Shoe(int levelNumber) {
        super("鞋子", levelNumber);
        this.attack = 0;
        this.defense = Tools.getRandom(10, 20, levelNumber);
        this.health = Tools.getRandom(80, 100, levelNumber);
    }
}
