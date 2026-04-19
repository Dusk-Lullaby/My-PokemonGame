package com.lullaby.pokemon.item.equipment;

import com.lullaby.pokemon.Util.Tools;

/**
 * 武器
 */
public class Weapon extends Equipment{
    public Weapon(int levelNumber) {
        super("武器", levelNumber);
        this.attack = Tools.getRandom(100, 150, levelNumber);
        this.defense = 0;
        this.health = Tools.getRandom(250, 300, levelNumber);
    }
}