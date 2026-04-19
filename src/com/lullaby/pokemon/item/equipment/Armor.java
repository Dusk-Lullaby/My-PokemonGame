package com.lullaby.pokemon.item.equipment;

import com.lullaby.pokemon.Util.Tools;

/**
 * 铠甲
 */
public class Armor extends Equipment{

    public Armor(int levelNumber) {
        super("铠甲", levelNumber);
        this.attack = 0;
        this.defense = Tools.getRandom(40, 60, levelNumber);
        this.health = Tools.getRandom(200, 250, levelNumber);
    }
}
