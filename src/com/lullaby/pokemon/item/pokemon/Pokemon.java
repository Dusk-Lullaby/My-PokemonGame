package com.lullaby.pokemon.item.pokemon;

import com.lullaby.pokemon.item.Item;
import com.lullaby.pokemon.item.Monster.Monster;
import com.lullaby.pokemon.item.equipment.*;

/**
 * 宠物小精灵
 */
public abstract class Pokemon extends Item {
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
    /**
     * 星级
     */
    protected int star;
    /**
     * 宠物小精灵可以穿戴8件装备，默认没穿戴装备
     * 穿戴顺序：头盔、铠甲、护腿、鞋子、武器、项链、戒指、手镯
     */
    private final Equipment[] equipments = new Equipment[8];

    public Pokemon(String name) {
        super(name);
    }

    @Override
    public String getItemInformation() {
        return name + ": 攻击=" + getAttack() + " 防御=" + getDefense() + " 生命=" + getCurrentHealth();
    }

    /**
     * 与其他精灵融合
     * @param other 其他小精灵
     */
    public void merge(Pokemon other) {
        if (star == 10) {
            System.out.println("星级已满，无法融合");
            return;
        }
        this.attack += (other.attack >> 1);
        this.defense += (other.defense >> 1);
        this.health += (other.health >> 1);
        this.star++;
    }

    /**
     * 更换装备
     * @param newEquipment 新装备
     * @return 换下来的装备
     */
    public Equipment changeEquipment(Equipment newEquipment) {
        Equipment old = null;
        int index = -1;
        if (newEquipment instanceof Helmet) {   // 头盔
            index = 0;
        } else if (newEquipment instanceof Armor) { // 铠甲
            index = 1;
        }else if (newEquipment instanceof Leggings) { // 护腿
            index = 2;
        }else if (newEquipment instanceof Shoe) { // 鞋子
            index = 3;
        }else if (newEquipment instanceof Weapon) { // 武器
            index = 4;
        }else if (newEquipment instanceof Necklace) { // 项链
            index = 5;
        }else if (newEquipment instanceof Ring) { // 戒指
            index = 6;
        }else if (newEquipment instanceof Bracelet) { // 手镯
            index = 7;
        }

        old = equipments[index];
        if (old == null) {  // old为null，说明原本无装备可以直接穿
            equipments[index] = newEquipment;
        } else {    // 原本就有装备，需要进行比较
            if (old.isBetter(newEquipment)) {   // 旧装备更好
                old = newEquipment;
            } else {
                equipments[index] = newEquipment;
            }
        }
        return old;
    }

    /**
     * 获取攻击值
     * @return 攻击值
     */
    public int getAttack() {
        int totalAttack = this.attack;
        for (Equipment equipment : equipments) {
            if (equipment == null) continue;
            totalAttack += equipment.getAttack();
        }
        return totalAttack;
    }

    /**
     * 获取防御值
     * @return 防御值
     */
    public int getDefense() {
        int totalDefense = this.defense;
        for (Equipment equipment : equipments) {
            if (equipment == null) continue;
            totalDefense += equipment.getDefense();
        }
        return totalDefense;
    }

    /**
     * 获取当前生命值
     * @return 当前生命值
     */
    public int getCurrentHealth() {
        int totalHealth = 0;
        for (Equipment equipment : equipments) {
            if (equipment == null) continue;
            totalHealth += equipment.getHealth();
        }
        return totalHealth + currentHealth;
    }

    /**
     * 攻击怪物
     * @param monster 怪物
     */
    public void attackMonster(Monster monster) {
        int minusHealth = this.attack * this.attack / monster.getDefense();
        if (minusHealth == 0) minusHealth = 1;  // 如果伤害为0，则将伤害修改为1
        else if (minusHealth > monster.getCurrentHealth()) {  // 如果伤害高于当前怪物血量，修改伤害
            minusHealth = monster.getCurrentHealth();
        }
        int resultHealth = monster.getCurrentHealth() - minusHealth;
        monster.setCurrentHealth(resultHealth);
        System.out.println(this.name + "对" + monster.getName() + "发动攻击，造成了" + minusHealth + "伤害");
    }

    /**
     * 计算生命百分比
     * @return 比例
     */
    public double getHealthPercent() {
        return 1.0 * currentHealth / health;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

}
