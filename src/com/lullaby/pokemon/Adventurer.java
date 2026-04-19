package com.lullaby.pokemon;

import com.lullaby.pokemon.Util.Tools;
import com.lullaby.pokemon.item.HP;
import com.lullaby.pokemon.item.Item;
import com.lullaby.pokemon.item.Monster.Monster;
import com.lullaby.pokemon.item.Portal;
import com.lullaby.pokemon.item.Treasure;
import com.lullaby.pokemon.item.equipment.Equipment;
import com.lullaby.pokemon.item.pokemon.Bulbasaur;
import com.lullaby.pokemon.item.pokemon.Pokemon;
import com.lullaby.pokemon.level.Level;

import java.util.ArrayList;
import java.util.List;

public class Adventurer implements DisplayItem{

    private final List<HP> medicinePackage = new ArrayList<>();

    private final List<Equipment> equipmentPackage = new ArrayList<>();

    private final List<Pokemon> pokemonPackage = new ArrayList<>();

    private Level currentLevel;

    public Adventurer() {
        medicinePackage.add(new HP(10, 1));
        pokemonPackage.add(new Bulbasaur());
    }

    public void start() {
        currentLevel  = new Level(null, 1, null);
        while (true) {
            currentLevel.getMap().show();
            System.out.println("请选择移动方向：W(上）、A（左）、S（下）、D（右）、E（退出）");
            char direction = Tools.getUpperChar();
            if (direction == 'E') { // 退出游戏
                System.out.println("是否确定退出?     Y/N");
                if (Tools.getUpperChar() == 'Y') {
                    System.out.println("感谢使用宠物小精灵");
                    System.exit(0);
                }
            } else {
                if (discovery(direction) == null) { // 移动失败
                    move(direction);
                } else {    // 发现物品
                    Item discoveryItem = discovery(direction);
                    discoveryItem.setDiscovery(true);
                    currentLevel.getMap().show();
                    if (discoveryItem instanceof Portal) {  // 传送门
                        System.out.println("发现传送门，是否通过？  Y/N");
                        char pass = Tools.getUpperChar();
                        if (pass == 'Y') {  // 通过
                            if (((Portal) discoveryItem).isNext()) {    // 通往下一关的传送门
                                if (this.currentLevel.getNextLevel() == null) { // 下一关为空
                                    Level nextLevel = new Level(currentLevel, currentLevel.getCurrentLever(), null);
                                    currentLevel.setNextLevel(nextLevel);
                                }
                                currentLevel = currentLevel.getNextLevel();
                                currentLevel.getNextLevel().getMap().addAdventure(this);
                            } else {    // 通往上一关
                                if (currentLevel.getPrevLever() == null) {
                                    System.out.println("非法操作");
                                } else {
                                    this.currentLevel = currentLevel.getPrevLever();
                                    currentLevel.getPrevLever().getMap().addAdventure(this);
                                }
                            }
                        }
                    } else if (discoveryItem instanceof Treasure) { //宝箱
                        processTreasure((Treasure) discoveryItem, direction);
                    } else if (discoveryItem instanceof Monster){    // 怪物
                        processMonster((Monster) discoveryItem, direction);
                    } else {
                        move(direction);
                    }
                }
            }
        }
    }

    public void processMonster(Monster monster, char direction) {
        System.out.println("发现怪物" + monster.getName() + ", 是否清除？  Y/N");
        if (Tools.getUpperChar() == 'Y') {  // 清除
            System.out.println("请选择出战宠物小精灵");
            for (int i = 0; i < pokemonPackage.size(); i++) {
                System.out.println((i + 1) + " " + pokemonPackage.get(i).getItemInformation());
            }
            int num = Tools.getInputNumber(1, pokemonPackage.size());
            Pokemon pokemon = pokemonPackage.get(num - 1);
            while (monster.getCurrentHealth() != 0 && pokemon.getCurrentHealth() != 0) {
                Tools.lazy(300L);
                if (pokemon.getCurrentHealth() > 0)
                    pokemon.attackMonster(monster);
                Tools.lazy(300L);
                if (monster.getCurrentHealth() > 0)
                    monster.attackPokemon(pokemon);
                Tools.lazy(300L);
                if (pokemon.getHealthPercent() < 0.5) { // 生命值低于50%
                    System.out.println("当前生命值过低，是否使用恢复药剂？   Y/N");
                    if (Tools.getUpperChar() == 'Y') {  // 使用
                          if (medicinePackage.isEmpty()) {
                              System.out.println("当前无可用药品");
                          } else {
                              useHP(pokemon);
                          }
                    }
                }
            }
            if (pokemon.getCurrentHealth() == 0) {  // 宠物小精灵死亡
                System.out.println("宠物小精灵" + pokemon.getName() + "死亡");
                pokemonPackage.remove(pokemon);
                monster.setCurrentHealth(monster.getCurrentHealth());
            } else {    // 怪物死亡
                System.out.println("怪物" + monster.getName() + "死亡");
                Item dropItem = monster.drop(currentLevel.getCurrentLever());
                processItem(dropItem);
                move(direction);
            }
        }
    }

    /**
     * 处理宝箱
     * @param treasure 宝箱
     */
    public void processTreasure(Treasure treasure, char direction) {
        System.out.println("发现宝箱，是否打开？   Y/N");
        char open = Tools.getUpperChar();
        if (open == 'Y') {  // 打开宝箱
            // 获取物品
            Item openItem = treasure.open(currentLevel.getCurrentLever());
            // 处理物品
            processItem(openItem);
            // 移动
            move(direction);
        }
    }

    /**
     * 处理物品
     * @param item 物品
     */
    private void processItem(Item item) {
        System.out.println("获得" + item.getName());
        if (item instanceof HP) {   // 药品
            for (int i = 0; i < medicinePackage.size(); i++) {
                // 如果背包中已经有同类药品
                if (medicinePackage.get(i).getLevelNumber() == item.getLevelNumber()) {
                    medicinePackage.get(i).addHP((HP)item);
                    item = null;
                    break;
                }
            }
            // 没有同类药品
            if (item != null) {
                medicinePackage.add((HP)item);
            }
        } else if (item instanceof Equipment) { // 装备
            System.out.println("发现新的装备是否给宠物小精灵更换？   Y/N");
            Equipment old = null;
            Equipment change = (Equipment) item;
            if (Tools.getUpperChar() == 'Y') {  // 更换
                for (Pokemon pokemon : pokemonPackage) {
                    old = pokemon.changeEquipment(change);
                    if (old == null) break;
                }
                if (old != null) equipmentPackage.add(old);
            }
            else {  // 不更换
                equipmentPackage.add(change);
            }
        } else if (item instanceof Pokemon){    // 宠物小精灵
            Pokemon newPokemon = (Pokemon) item;
            for (Pokemon pokemon : pokemonPackage) {
                if (pokemon.getClass() == newPokemon.getClass()) {  // 相同类型就升星
                    pokemon.merge(newPokemon);
                    newPokemon = null;
                    break;
                }
            }
            if (newPokemon != null) {
                // 给新宠物穿装备
                Equipment old = null;
                for (Equipment equipment : equipmentPackage) {
                    old = newPokemon.changeEquipment(equipment);
                    if (old == null) {  // 旧装备为空，穿上新装备
                        equipmentPackage.remove(equipment);
                    } else {    // 旧装备非空
                        equipmentPackage.remove(equipment);
                        equipmentPackage.add(old);
                    }
                }
                pokemonPackage.add(newPokemon);
            }
        }
    }

    /**
     * 发现物品
     * @param direction 方向
     * @return 发现的物品
     */
    public Item discovery(char direction) {
        return (Item) currentLevel.getMap().discovery(direction);
    }

    /**
     * 使用药品
     */
    public void useHP(Pokemon pokemon) {
        HP hp = medicinePackage.get(medicinePackage.size() - 1);
        int recover = hp.use();
        pokemon.setCurrentHealth(pokemon.getCurrentHealth() + recover);
        if (hp.isDestroy()) {
            medicinePackage.remove(hp);
        }
    }

    /**
     * 移动
     * @param direction 方向
     */
    public void move(char direction) {
        this.currentLevel.getMap().move(direction);
    }

    @Override
    public String getItemInformation() {
        return "@";
    }
}
