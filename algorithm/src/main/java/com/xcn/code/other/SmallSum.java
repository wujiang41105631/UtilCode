package com.xcn.code.other;

/**
 * 求小和，使用归并排序的思想
 */
public class SmallSum {


    protected void process(int[] datas) {
        
    }


    private int doProcess(int[] datas, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        int t = doProcess(datas, left, mid);
        int t1 = doProcess(datas, mid + 1, right);
        int t2 = merge(datas, left, mid, right);
        return t + t1 + t2;
    }

    private int merge(int[] datas, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= right) {
            res += datas[p1] < datas[p2] ? (right - p2 + 1) * datas[p1] : 0;
            // 此时注意,merge和归并排序唯一的区别： 如果左右两个数相等，则用右边的数
            help[i++] = datas[p1] < datas[p2] ? datas[p1++] : datas[p2++];

        }

        while (p2 <= right) {
            help[i++] = datas[p2++];
        }

        while (p1 <= mid) {
            help[i++] = datas[p1++];
        }

        for (i = 0; i < help.length; i++) {
            datas[left + i] = help[i];
        }
        return res;
    }
}
