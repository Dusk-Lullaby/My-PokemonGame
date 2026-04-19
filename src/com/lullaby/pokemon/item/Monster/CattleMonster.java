package com.lullaby.pokemon.item.Monster;

import com.lullaby.pokemon.Util.Tools;

/**
 * 中级牛魔怪
 */
public class CattleMonster extends Monster {
    public CattleMonster(int levelNumber) {
        super("牛魔怪", levelNumber);
        this.attack = Tools.getRandom(50, 60, levelNumber);
        this.defense = Tools.getRandom(40, 50, levelNumber);
        this.health = Tools.getRandom(700, 900, levelNumber);
        this.currentHealth = health;
    }

    @Override
    public String getItemInformation() {
        return discovery ? "2" : "■";
    }
}
