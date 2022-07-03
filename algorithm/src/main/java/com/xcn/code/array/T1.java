package com.xcn.code.array;

/**
 *
 */
public class T1 {

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,5,13};
        getData(arr);
//        getData(1);
    }

    /**
     * 提取出最右侧的1
     *
     * @param data
     */
    public static void getData1(int data) {
        System.out.println(data & ((~data) + 1));
    }


    /**
     * 提取出最右侧的1的一个应用: 给定一个数字，找出这个数字的二进制位上有几个1；
     *
     * @param data
     */
    public static void getData(int data) {
        int count = 0;

        while (data != 0) {
            // 找到最右侧的1
            int rightOne = data & ((~data) + 1);
            count++;
            // 把最右侧的1抹掉, 不用减法的原因是 data可能为负数
            data ^= rightOne;
        }
        System.out.println(count);
    }

    /**
     * 提取出最右侧的1的一个应用: 数组中有两个数出现1次,找出那个数
     *
     * @param arr
     */
    public static void getData(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOne = eor & (~eor + 1);// 提取数中最右侧的1
        int a = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) { // 此处是为了 取出 2个数中的一个数
                a ^= arr[i];
            }
        }
        int b = a ^ eor;
        System.out.println(a + "，" + b);
    }
}
