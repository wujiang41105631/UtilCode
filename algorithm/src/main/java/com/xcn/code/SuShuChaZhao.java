package com.xcn.code;

/**
 * 素数统计。
 */
public class SuShuChaZhao {

    public static void main(String[] args) {
        System.out.println(SuShuChaZhao.search(100));
    }

    /**
     * 埃筛法
     * @param base
     * @return
     */
    private static int search(int base) {
        boolean[] prime = new boolean[base]; // false 表示素数
        int count = 0;
        for (int i = 2; i < base; i++) {
            if (!prime[i]) {
                count++;
                for (int j = i * i; j<base;j += i){
//                for (int j = 2 * i; j<base;j += i){  会有重复计算的逻辑
                    prime[j] = true;
                }
            }
        }
        return count;
    }
}
