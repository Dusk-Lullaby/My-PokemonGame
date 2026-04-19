package com.lullaby.pokemon.item.equipment;

import com.lullaby.pokemon.Util.Tools;

/**
 * 护腿
 */
public class Leggings extends Equipment{

    public Leggings(int levelNumber) {
        super("头盔", levelNumber);
        this.attack = 0;
        this.defense = Tools.getRandom(30, 40, levelNumber);
        this.health = Tools.getRandom(150, 200, levelNumber);
    }
}
