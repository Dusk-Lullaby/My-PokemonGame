package com.lullaby.pokemon.item.Monster;


import com.lullaby.pokemon.Util.Tools;

/**
 * 低级象牙猪
 */
public class Mamoswine extends Monster {
    public Mamoswine(int levelNumber) {
        super("象牙猪", levelNumber);
        this.attack = Tools.getRandom(45, 55, levelNumber);
        this.defense = Tools.getRandom(35, 45, levelNumber);
        this.health = Tools.getRandom(600, 800, levelNumber);
        this.currentHealth = health;
    }

    @Override
    public String getItemInformation() {
        return discovery ? "1" : "■";
    }
}
