package com.lullaby.pokemon.level;

import com.lullaby.pokemon.Adventurer;
import com.lullaby.pokemon.DisplayItem;
import com.lullaby.pokemon.Util.Tools;
import com.lullaby.pokemon.item.Monster.CattleMonster;
import com.lullaby.pokemon.item.Monster.Mamoswine;
import com.lullaby.pokemon.item.Monster.Moltres;
import com.lullaby.pokemon.item.Monster.Ramoraid;
import com.lullaby.pokemon.item.Portal;
import com.lullaby.pokemon.item.Treasure;

public class LevelMap {
    /**
     * 当前关卡
     */
    private final int levelNum;
    /**
     * 地图上的物品 9 * 9
     */
    private final DisplayItem[][] items = new DisplayItem[9][9];
    /**
     * 冒险家的坐标
     */
    private int adventurerRow, adventurerCol;

    /**
     * 生成地图---> 宝箱：怪物：传送门 = 39：39：1
     * 第一个位置和第二个位置不能使用
     */
    public LevelMap(int levelNum) {
        this.levelNum = levelNum;
        generateMap();
    }

    public void generateMap() {
        if (this.levelNum == 1) {   // 如果是关卡1，第一个冒险家在第一个位置，第二个位置为象牙猪
            adventurerRow = 0; adventurerCol = 0;
            items[adventurerRow][adventurerCol] = new Adventurer();
            items[adventurerRow][adventurerCol + 1] = new Mamoswine(levelNum);
        } else {    // 其他关卡，第一个位置是返回上一关的传送门，第二个位置是冒险家
            adventurerRow = 0;
            adventurerCol = 1;
            items[adventurerRow][adventurerCol - 1] = new Portal(false);
            items[adventurerRow][adventurerCol] = new Adventurer();
        }
        // 生成的传送门数量
        int generatePortal = 0;
        // 生成的宝箱数量
        int generateTreasure = 0;
        // 生成的低级怪物数量
        int generateMonster1 = 0;
        // 生成的中级怪物数量
        int generateMonster2 = 0;
        // 生成的高级怪物数量
        int generateMonster3 = 0;
        // 生成的究极怪物数量
        int generateMonster4 = 0;

        while (generatePortal < 1 || generateTreasure < 39 ||
                generateMonster1 < 18 || generateMonster2 < 12 ||
                generateMonster3 < 6 || generateMonster4 < 3) {
            // 计算行列
            int position = Tools.getRandom(2, 81);
            int row = position / items.length;
            int col = position % items.length;
            if (items[row][col] != null) continue;
            int rate = Tools.getRandom(79);
            if (rate == 0) {    // 传送门
                if (generatePortal == 1) continue;
                items[row][col] = new Portal(true);
                generatePortal++;
            } else if (rate < 40) {   // 宝箱
                if (generateTreasure >= 39) continue;
                items[row][col] = new Treasure(levelNum);
                generateTreasure++;
            } else {    // 怪物
                int num = Tools.getRandom(39);
                if (num < 3) {  // 究极
                    if (generateMonster4 >= 3) continue;
                    items[row][col] = new Moltres(levelNum);
                    generateMonster4++;
                } else if (num < 9) {   // 高级
                    if (generateMonster3 >= 6) continue;
                    items[row][col] = new Ramoraid(levelNum);
                    generateMonster3++;
                } else if (num < 21) {  // 中级
                    if (generateMonster2 >= 12) continue;
                    items[row][col] = new CattleMonster(levelNum);
                    generateMonster2++;
                } else {    // 低级
                    if (generateMonster1 >= 18) continue;
                    items[row][col] = new Mamoswine(levelNum);
                    generateMonster1++;
                }
            }
        }
        System.out.println(generateTreasure + generatePortal + generateMonster1 + generateMonster2 + generateMonster3 + generateMonster4);
    }

    /**
     * 展示地图
     */
    public void show() {
        System.out.println("宠物小精灵" + levelNum + "关：");
        for (int i = 0; i < items.length; i++) {
            StringBuilder line1 = new StringBuilder();
            StringBuilder line2 = new StringBuilder();

            for (int j = 0; j < items[0].length; j++) {
                String info = " ";
                if (items[i][j] != null) {
                    info = items[i][j].getItemInformation();
                }

                // 第一行
                if (i == 0) {
                    if (j == 0) {   // 第一列
                        line1.append("┌───");
                        line2.append("│ ").append(info).append(" ");
                    } else if (j == items[0].length - 1) {  // 最后一列
                        line1.append("┬───┐");
                        line2.append("│ ").append(info).append(" │");
                    } else {
                        line1.append("┬───");
                        line2.append("│ ").append(info).append(" ");
                    }
                } else {
                    if (j == 0) {   //第一列
                        line1.append("├───");
                        line2.append("│ ").append(info).append(" ");
                    } else if (j == items[0].length - 1) {  // 最后一列
                        line1.append("┼───┤");
                        line2.append("│ ").append(info).append(" │");
                    } else {
                        line1.append("┼───");
                        line2.append("│ ").append(info).append(" ");
                    }
                }
            }
            System.out.println(line1);
            System.out.println(line2);
        }

        StringBuilder lastLine = new StringBuilder();
        for (int i = 0; i < items[0].length; i++) {
            if (i == 0) {   // 第一列
                lastLine.append("└───");
            } else if (i == items[0].length - 1) {  // 最后一列
                lastLine.append("┴───┘");
            } else {
                lastLine.append("┴───");
            }
        }
        System.out.println(lastLine);
    }

    public void move(char direction) {
        int oldRow = adventurerRow;
        int oldCol = adventurerCol;
        DisplayItem adventure = items[adventurerRow][adventurerCol];
        switch (direction) {
            case 'A':
                if (adventurerCol != 0) {
                    adventurerCol -= 1;
                    Tools.lazy(300L);
                } else {
                    System.err.println("非法移动");
                    return;
                }
                break;
            case 'W':
                if (adventurerRow != 0) {
                    adventurerRow -= 1;
                    Tools.lazy(300L);
                }else {
                    System.err.println("非法移动");
                    return;
                }
                break;
            case 'S' :
                if (adventurerRow != items.length - 1) {
                    adventurerRow += 1;
                    Tools.lazy(300L);
                }else {
                    System.err.println("非法移动");
                    return;
                }
                break;
            case 'D':
                if (adventurerCol != items[adventurerRow].length - 1) {
                    adventurerCol += 1;
                    Tools.lazy(300L);
                }else {
                    System.err.println("非法移动");
                    return;
                }
                break;
        }
        items[oldRow][oldCol] = null;
        items[adventurerRow][adventurerCol] = adventure;
    }

    /**
     * 获取移动方向物品信息
     * @param direction 移动方向
     * @return 物品
     */
    public DisplayItem discovery(char direction) {
        int targetRow = adventurerRow;
        int targetCol = adventurerCol;
        switch (direction) {
            case 'A':
                targetCol -= 1;
                break;
            case 'W':
                targetRow -= 1;
                break;
            case 'S':
                targetRow += 1;
                break;
            case 'D':
                targetCol += 1;
                break;
        }

        if (targetCol < 0 || targetCol >= items[0].length || targetRow < 0 || targetRow >= items.length) {
            return null;
        }

        return items[targetRow][targetCol];
    }

    /**
     * 添加冒险家
     * @param adventurer 冒险家
     */
    public void addAdventure(Adventurer adventurer) {
        adventurerRow = 0;
        if (this.levelNum == 1) {
            adventurerCol = 0;
        } else {
            adventurerCol = 1;
        }
        items[adventurerRow][adventurerCol] = adventurer;
    }
}
