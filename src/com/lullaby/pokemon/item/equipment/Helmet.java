package com.lullaby.pokemon.item.equipment;

import com.lullaby.pokemon.Util.Tools;

/**
 * 头盔
 */
public class Helmet extends Equipment{

    public Helmet(int levelNumber) {
        super("头盔", levelNumber);
        this.attack = 0;
        this.defense = Tools.getRandom(20, 30, levelNumber);
        this.health = Tools.getRandom(200, 300, levelNumber);
    }
}
