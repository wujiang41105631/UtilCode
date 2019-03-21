package com.xcn.code.sort.algorthm;

/**
 * @author: xupeng.guo
 * @date: 2019/3/21
 * @description
 */
public class XuanZeSortAlgorithm extends com.xcn.code.sort.algorthm.AbstractSortAlgorithm {
    @Override
    protected int[] process(int[] datas) {
        for (int i = 0; i < datas.length; i++) {
            int offset = i;
            for (int k = offset + 1; k < datas.length; k++) {
                if (datas[offset] > datas[k]) {
                    offset = k;
                }
            }
            if (offset != i) {
                int minvalue = datas[offset];
                datas[offset] = datas[i];
                datas[i] = minvalue;
            }
        }
        return datas;
    }
}
