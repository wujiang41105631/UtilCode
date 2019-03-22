package com.xcn.code.sort.algorthm;

/**
 * 冒泡排序
 *
 * @author: xupeng.guo
 * @date: 2019/3/21
 * @description
 */
public class MaoPaoSortAlgorithm extends AbstractSortAlgorithm {


    @Override
    protected int[] process(int[] datas) {
        int temp = 0;
        for (int i = 0; i < datas.length-1; i++) {
            for (int k = 0; k < datas.length - i - 1; k++) {
                if (datas[k] > datas[k + 1]) {
                    temp = datas[k + 1];
                    datas[k + 1] = datas[k];
                    datas[k] = temp;
                }
            }
        }
        return datas;
    }
}
