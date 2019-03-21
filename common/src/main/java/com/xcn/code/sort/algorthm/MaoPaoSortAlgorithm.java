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
        for (int i = 0; i < datas.length; i++) {
            for (int k = i + 1; k < datas.length; k++) {
                if (datas[i] > datas[k]) {
                    temp = datas[i];
                    datas[i] = datas[k];
                    datas[k] = temp;
                }
            }
        }
        return datas;
    }
}
