package com.lullaby.pokemon.Util;

import com.lullaby.pokemon.item.HP;
import com.lullaby.pokemon.item.Item;
import com.lullaby.pokemon.item.equipment.*;
import com.lullaby.pokemon.item.pokemon.Bikachu;
import com.lullaby.pokemon.item.pokemon.Bulbasaur;
import com.lullaby.pokemon.item.pokemon.Charmander;
import com.lullaby.pokemon.item.pokemon.Jolteon;

import java.util.Random;
import java.util.Scanner;

/**
 * 工具类
 */
public class Tools {
    /**
     * 随机数
     */
    private static final Random RANDOM = new Random();
    /**
     * 从控制台获取输入
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * 延迟给定时间
     * @param times 时间
     */
    public static void lazy(long times) {
        try {
            Thread.sleep(times);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取大写字符
     * @return 字符
     */
    public static char getUpperChar() {
        while (true) {
            String input = SCANNER.next();
            if (input.length() != 1) {
                System.out.println("请重新输入字符");
            } else {
                char res = input.charAt(0);
                if ((res >= 'a' && res <= 'z') || (res >= 'A' && res <= 'Z')) {
                    return Character.toUpperCase(res);
                } else {
                    System.out.println("请重新输入字符");
                }
            }
        }
    }

    /**
     * 从控制台获取给定范围内的数字
     * @param min 最小值
     * @param max 最大值
     * @return 获取的数字
     */
    public static int getInputNumber(int min, int max) {
        while (true) {
            if (SCANNER.hasNextInt()) {
                int num = SCANNER.nextInt();
                if (num >= min && num <= max) {
                    return num;
                } else {
                    System.out.println("输入错误，请输入" + min + "~" + max + "之间的整数");
                }
            } else {
                System.out.println("输入错误，请输入" + min + "~" + max + "之间的整数");
                SCANNER.next();
            }
        }
    }

    /**
     * 获取随机值
     * @param min 最小值
     * @param max 最大值
     * @param levelNum 关卡编号
     * @return 随机数
     */
    public static int getRandom(int min, int max, int levelNum) {
        return RANDOM.nextInt(min, max) * levelNum;
    }

    /**
     * 获取随机数
     * @param min 最小值
     * @param max 最大值
     * @return 随机数
     */
    public static int getRandom(int min, int max) {
        return getRandom(min, max, 1);
    }

    /**
     * 获取随机数
     * @param max 最大值
     * @return 随机数
     */
    public static int getRandom(int max) {
        return getRandom(0, max);
    }

    /**
     * 获取随机物品
     * @param levelNumber 关卡编号
     * @return 获得的物品
     */
    public static Item getRandomItem(int levelNumber) {
        // 取[0, 10)之间的随机数
        int num = Tools.getRandom(10);
        if (num == 0) { // 获得宠物小精灵，初级：中级：高级：究极 = 80:15:4:1
            int rate = Tools.getRandom(100);
            if (rate == 0) {    // 究极小精灵
                return new Bikachu();
            }
            if (rate <= 4) {    // 高级
                return new Charmander();
            }
            if (rate <= 20) {   // 中级
                return new Jolteon();
            }
            return new Bulbasaur(); // 初级
        } else if (num <= 3) {  // 获得装备
            // 武器：项链：戒指：手镯：头盔：铠甲：护腿：鞋子 = 3：5：8：8：19：19：19：19
            int rate = Tools.getRandom(100);
            if (rate < 3) { // 武器
                return new Weapon(levelNumber);
            }
            if (rate < 8) { // 项链
                return new Necklace(levelNumber);
            }
            if (rate < 16) {// 戒指
                return new Ring(levelNumber);
            }
            if (rate < 24) {// 手镯
                return new Bracelet(levelNumber);
            }
            if (rate < 43) {// 头盔
                return new Helmet(levelNumber);
            }
            if (rate < 62) {// 铠甲
                return new Armor(levelNumber);
            }
            if (rate < 81) {// 护腿
                return new Leggings(levelNumber);
            }
            else return new Shoe(levelNumber);  // 鞋子
        } else {    // 获得药品
            return new HP(levelNumber, 10);
        }
    }
}
