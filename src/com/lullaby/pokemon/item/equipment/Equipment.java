package com.lullaby.pokemon.item.equipment;

import com.lullaby.pokemon.item.Item;

/**
 * 装备
 */
public abstract class Equipment extends Item {
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

    public Equipment(String name, int levelNumber) {
        super(name, levelNumber);
    }

    @Override
    public String getItemInformation() {
        return name + ": 攻击=" + attack + " 防御=" + defense + " 生命=" + health;
    }

    /**
     * 与其他装备进行比较
     * @param other 其他装备
     * @return 该装备是否更好
     */
    public boolean isBetter(Equipment other) {
        // 必须是同类型的装备
        if (this.getClass() != other.getClass()) {
            return false;
        }

        int total1 = this.attack + this.defense + this.health >> 1;
        int total2 = other.attack + other.defense + other.health >> 1;
        return total1 > total2;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }
}
