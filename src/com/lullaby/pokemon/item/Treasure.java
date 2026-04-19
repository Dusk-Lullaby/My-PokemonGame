package com.lullaby.pokemon.item;

import com.lullaby.pokemon.Util.Tools;

/**
 * 宝箱
 */
public class Treasure extends Item{

    public Treasure(int levelNumber) {
        super("宝箱", levelNumber);
    }

    @Override
    public String getItemInformation() {
        return discovery ? "¤" : "■";
    }

    /**
     * 打开宝箱
     * @param levelNum 关卡编号
     * @return 获得的物品
     */
    public Item open(int levelNum) {
        return Tools.getRandomItem(levelNum);
    }
}
