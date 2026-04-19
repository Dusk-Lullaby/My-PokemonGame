package com.lullaby.pokemon.level;

public class Level {
    /**
     * 关卡编号
     */
    private int currentLever;
    /**
     * 上一关
     */
    private Level prevLever;
    /**
     * 下一关
     */
    private Level nextLevel;
    /**
     * 关卡地图
     */
    private final LevelMap map;

    public Level(Level prevLever, int currentLever, Level nextLevel) {
        this.currentLever = currentLever;
        this.prevLever = prevLever;
        this.nextLevel = nextLevel;
        this.map = new LevelMap(currentLever);
    }

    public int getCurrentLever() {
        return currentLever;
    }

    public Level getPrevLever() {
        return prevLever;
    }


    public Level getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Level nextLevel) {
        this.nextLevel = nextLevel;
    }

    public LevelMap getMap() {
        return map;
    }
}
