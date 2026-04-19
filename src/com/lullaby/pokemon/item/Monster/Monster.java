package com.lullaby.pokemon.item.Monster;

import com.lullaby.pokemon.Util.Tools;
import com.lullaby.pokemon.item.Item;
import com.lullaby.pokemon.item.pokemon.Pokemon;

/**
 * 怪物
 */
public abstract class Monster extends Item {
    /**
     * 攻击力
     */
    protected int attack;
    /**
     * 防御力
     */
    protected int defense;
    /**
     * 健康值
     */
    protected int health;
    /**
     * 当前血量
     */
    protected int currentHealth;

    public Monster(String name, int levelNumber) {
        super(name, levelNumber);
    }

    /**
     * 攻击宠物小精灵
     * @param pokemon 宠物小精灵
     */
    public void attackPokemon(Pokemon pokemon) {
        int minusHealth = this.attack * this.attack / pokemon.getDefense();
        if (minusHealth == 0) minusHealth = 1;  // 如果伤害为0，则将伤害修改为1
        else if (minusHealth > pokemon.getCurrentHealth()) {  // 如果伤害高于当前宠物小精灵血量，修改伤害
            minusHealth = pokemon.getCurrentHealth();
        }
        int resultHealth = pokemon.getCurrentHealth() - minusHealth;
        pokemon.setCurrentHealth(resultHealth);
        System.err.println(this.name + "对" + pokemon.getName() + "发动攻击，造成了" + minusHealth + "伤害");
    }

    /**
     * 掉落物品
     * @param levelNumber 关卡编号
     * @return 掉落的物品
     */
    public Item drop(int levelNumber) {
        return Tools.getRandomItem(levelNumber);
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}
