package com.lullaby.pokemon.item;

/**
 * 恢复药品
 */
public class HP extends Item{
    /**
     * 药品数量
     */
    private int count;

    public HP(int count, int levelNumber) {
        super("恢复药剂", levelNumber);
        this.count = count;
    }

    @Override
    public String getItemInformation() {
        return name;
    }

    /**
     * 使用药品
     * @return 回复的血量
     */
    public int use() {
        this.count--;
        return this.levelNumber * 100;
    }

    /**
     * 是否可以被销毁
     * @return 是/否
     */
    public boolean isDestroy() {
        return count == 0;
    }

    /**
     * 药品堆叠
     * @param other 其他药品
     */
    public void addHP(HP other) {
        if (this.getLevelNumber() == other.getLevelNumber())
            this.count += other.count;
    }
}
