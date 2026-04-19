package com.lullaby.pokemon.item.Monster;

import com.lullaby.pokemon.Util.Tools;


/**
 * 究极怪物火焰鸟
 */
public class Moltres extends Monster {
    public Moltres(int levelNumber) {
        super("火焰鸟", levelNumber);
        this.attack = Tools.getRandom(80, 100, levelNumber);
        this.defense = Tools.getRandom(70, 90, levelNumber);
        this.health = Tools.getRandom(1400, 1800, levelNumber);
        this.currentHealth = health;
    }

    @Override
    public String getItemInformation() {
        return discovery ? "4" : "■";
    }
}

