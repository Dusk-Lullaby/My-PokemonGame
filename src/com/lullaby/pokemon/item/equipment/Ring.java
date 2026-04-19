package com.lullaby.pokemon.item.equipment;

import com.lullaby.pokemon.Util.Tools;

/**
 * 戒指
 */
public class Ring extends Equipment{

    public Ring(int levelNumber) {
        super("戒指", levelNumber);
        this.attack = Tools.getRandom(20, 30, levelNumber);
        this.defense = Tools.getRandom(20, 30, levelNumber);
        this.health = Tools.getRandom(100, 200, levelNumber);
    }
}
