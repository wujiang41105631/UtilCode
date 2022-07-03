package com.xcn.code.sort.algorthm;

import com.xcn.code.sort.AbstractSortAlgorithm;

/**
 * 冒泡排序
 *
 * @author: xupeng.guo
 * @date: 2019/3/21
 * @description
 */
public class MaoPaoSortAlgorithm extends AbstractSortAlgorithm {


    /**
     * 冒泡排序是最简单的排序之一了，其大体思想就是通过与相邻元素的比较和交换来把小的数交换到最前面。
     * 这个过程类似于水泡向上升一样，因此而得名。举个栗子，对5,3,8,6,4这个无序序列进行冒泡排序。
     * 首先从后向前冒泡，4和6比较，把4交换到前面，序列变成5,3,8,4,6。同理4和8交换，变成5,3,4,8,6,3和4无需交换。
     * 5和3交换，变成3,5,4,8,6,3.这样一次冒泡就完了，把最小的数3排到最前面了。
     * 对剩下的序列依次冒泡就会得到一个有序序列。冒泡排序的时间复杂度为O(n^2)
     *
     * @param datas
     * @return
     */
    @Override
    protected int[] process(int[] datas) {
//        int temp = 0;
//        for (int i = 0; i < datas.length - 1; i++) {
//            // 由于有了swapFlag 所以 最优时间复杂度为O(n)
////            boolean swapFlag = false;
//            for (int k = 0; k < datas.length - i - 1; k++) {
//                if (datas[k] > datas[k + 1]) {
//                    temp = datas[k + 1];
//                    datas[k + 1] = datas[k];
//                    datas[k] = temp;
////                    swapFlag = true;
//                }
//            }
////            if (swapFlag) {
////                return datas;
////            }
//        }

        return doProcess(datas);
    }

    private int[] doProcess(int[] datas) {
        for(int i = 0; i < datas.length; i++){
            for(int j = 0; j< datas.length-i-1;j++){
                if(datas[j] >= datas[j+1]){
                    int t = datas[j];
                    datas[j] = datas[j+1];
                    datas[j+1] = t;
                }
            }
        }
        return datas;
    }
}
