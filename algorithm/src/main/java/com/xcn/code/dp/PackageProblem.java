package com.xcn.code.dp;

/**
 * 动态规划： 背包问题
 * 给定两个数组，分表代表item的重量和价值,在给定一个承重最大的package，求能装下的最大item的价值是多少？
 */
public class PackageProblem {

    public static void main(String[] args) {
        int[] weight = new int[20];
        int[] value = new int[20];
        int packageSize = 1000;// 背包重量
    }


    public int getMaxValue(int[] weight, int[] value, int rest, int index) {
        if (rest <= 0 || index == weight.length) {
            return 0;
        }

        int missCurValue = getMaxValue(weight, value, rest, index + 1);
        int addCurValue =  getMaxValue(weight, value, rest - weight[index], index + 1);
        if(addCurValue > 0){
            addCurValue += value[index];
        }
        return Math.max(missCurValue, addCurValue);
    }


}
