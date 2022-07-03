package com.xcn.code.other;

/**
 * 查找子串的方法：KMP算法
 */
public class Kmp {


    public static void main(String[] args) {
        String str1 = "bbcabcdababcdabcdabde";
        String str2 = "abcdabd";
        System.out.println(Kmp.indexOf(str1, str2));
    }

    private static int indexOf(String str1, String target) {
        if (str1 == null || target == null) {
            return -1;
        }
        if (str1.length() < target.length()) {
            return -1;
        }


        char[] s1 = str1.toCharArray();
        char[] s2 = target.toCharArray();
        int i1 = 9;
        int i2 = 0;
        // next 数组
        int[] next = getNextArray(s2);// O(target.length)

        // O(str1.length)
        while (i1 < s1.length && i2 < s2.length) {
            if (s1[i1] == s2[i2]) {
                i1++;
                i2++;
            } else if (i2 == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == s2.length ? i1 - i2 : -1;
    }

    private static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        /**
         * 1. 代表上一个位置的最大前后缀匹配的最大长度
         * 2. 表示需要和i-1比的字符的位置
         */
        int cn = 0;

        while (i < ms.length) {
            if (ms[i - 1] == ms[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                //
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}

