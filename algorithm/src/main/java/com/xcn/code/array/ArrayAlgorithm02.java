package com.xcn.code.array;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 寻找数组中心下标
 * 给定一个整数数组：请编写一个返回数组中心下标的方法，使下标左边的数据之和等于右边数据之和
 */
public class ArrayAlgorithm02 {

    public static void main(String[] args) {
        LinkedBlockingQueue queue = new LinkedBlockingQueue<>();

        System.out.println(getCenterOffSet(new int []{1,7,3,6,5,6}));
    }

    private static int getCenterOffSet(int[] ints) {
        int sum = Arrays.stream(ints).sum();
        int total = 0;
        for (int i = 0;i<ints.length;i++) {
            total += ints[i];
            if(total == sum){
                return i;
            }
            sum -= ints[i];
        }
        return -1;
    }

}
