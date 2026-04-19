package com.lullaby.pokemon.item.Monster;

import com.lullaby.pokemon.Util.Tools;

/**
 * 高级怪物铁炮鱼
 */
public class Ramoraid extends Monster {
    public Ramoraid(int levelNumber) {
        super("铁炮鱼", levelNumber);
        this.attack = Tools.getRandom(60, 70, levelNumber);
        this.defense = Tools.getRandom(50, 60, levelNumber);
        this.health = Tools.getRandom(900, 1100, levelNumber);
        this.currentHealth = health;
    }

    @Override
    public String getItemInformation() {
        return discovery ? "3" : "■";
    }
}
