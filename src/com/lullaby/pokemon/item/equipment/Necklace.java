package com.lullaby.pokemon.item.equipment;

import com.lullaby.pokemon.Util.Tools;

/**
 * 项链
 */
public class Necklace extends Equipment{

    public Necklace(int levelNumber) {
        super("项链", levelNumber);
        this.attack = 0;
        this.defense = Tools.getRandom(25, 35, levelNumber);
        this.health = Tools.getRandom(120, 180, levelNumber);
    }
}
