package com.xcn.code.sort.algorthm;

import com.xcn.code.sort.AbstractSortAlgorithm;

/**
 * 插入排序
 * https://www.cnblogs.com/skywang12345/p/3596881.html
 * @author: xupeng.guo
 * @date: 2019/3/21
 * @description
 */
public class ChaRuSortAlgorithm extends AbstractSortAlgorithm {


    /**
     * 插入排序不是通过交换位置而是通过比较找到合适的位置插入元素来达到排序的目的的。
     * 相信大家都有过打扑克牌的经历，特别是牌数较大的。在分牌时可能要整理自己的牌，牌多的时候怎么整理呢？
     * 就是拿到一张牌，找到一个合适的位置插入。这个原理其实和插入排序是一样的。
     * 举个栗子，对5,3,8,6,4这个无序序列进行简单插入排序，首先假设第一个数的位置时正确的，想一下在拿到第一张牌的时候，
     * 没必要整理。然后3要插到5前面，把5后移一位，变成3,5,8,6,4.想一下整理牌的时候应该也是这样吧。
     * 然后8不用动，6插在8前面，8后移一位，4插在5前面，从5开始都向后移一位。
     * 注意在插入一个数的时候要保证这个数前面的数已经有序。简单插入排序的时间复杂度也是O(n^2)。
     * @param arr
     * @return
     */
    @Override
    protected int[] process(int[] arr) {
            int t;
            for (int i = 1; i < arr.length; i++) {
                int j = i - 1;
                t = arr[i];
                while (j >= 0 && t < arr[j]) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = t;
            }
        return arr;
    }

//    @Override
//    protected int[] process(int[] datas) {
//        int[] newDatas = new int[datas.length];
//
//        for (int i = 0; i < datas.length; i++) {
//            int v = newDatas[i] = datas[i];
//            // 此处从后向前走,比较绕
//            for (int j = i; j > 0; j--) {
//                if (v >= newDatas[j - 1]) {
//                    for (int k = i; k >= j; k--) {
//                        newDatas[k] = newDatas[k - 1];
//                    }
//                    newDatas[j] = v;
//                    break;
//                }
//            }
//        }
//        return newDatas;
//    }
//
//    protected int[] otherProcess(int[] data) {
//        int[] na = new int[data.length];
//        for (int i = 0; i < data.length; i++) {
//            int temp = na[i] = data[i];
//            //此处从前向后走,比较简单
//            for (int offset = 0; offset < i; offset++) {
//                if (na[offset] > temp) {
//                    for (int mvOffset = i; mvOffset > offset; mvOffset--) {
//                        na[mvOffset] = na[mvOffset - 1];
//                    }
//                    na[offset] = temp;
//                    break;
//                }
//            }
//        }
//        return na;
//    }

    protected int[] otherProcess(int[] datas) {
        int offset = 0;
        int moveOffset = 0;
        int tempv = 0;
        for (int i = 1; i < datas.length; i++) {
            for (offset = i - 1; offset >= 0; offset--) {
                if (datas[offset] < datas[i]) {
                    break;
                }
            }
            if ((offset + 1) != i) {
                tempv = datas[i];
                for (moveOffset = i; moveOffset > offset + 1; moveOffset--) {
                    datas[moveOffset] = datas[moveOffset - 1];
                }
                datas[offset + 1] = tempv;
            }
        }
        return datas;
    }
}
