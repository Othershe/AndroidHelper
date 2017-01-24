package com.othershe.androidhelper.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class RandomUtil {

    /**
     * 返回[0.0, 1.0)随机浮点数
     *
     * @return
     */
    public static float getRandom() {
        return new Random().nextFloat();
    }

    /**
     * 返回[0, max)随机整数
     *
     * @param max
     * @return
     */
    public static int getRandom(int max) {
        return new Random().nextInt(max);
    }

    /**
     * 返回[min, max)随机整数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        }
        return min + getRandom(max - min);
    }

    /**
     * 返回[min, max)之间不重复的n（n <= max -min）个随机数
     *
     * @param min
     * @param max
     * @param n
     * @return
     */
    public static ArrayList<Integer> getRandom(int min, int max, int n) {
        if (min > max || n > (max - min)) {
            return null;
        }
        HashSet<Integer> set = new HashSet<>();

        while (set.size() != n) {
            int num = getRandom(min, max);
            set.add(num);
        }
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.shuffle(list);
        return list;
    }
}
