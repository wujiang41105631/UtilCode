package com.xcn.code.sort.algorthm;

/**
 * 插入排序
 * https://www.cnblogs.com/skywang12345/p/3596881.html
 * @author: xupeng.guo
 * @date: 2019/3/21
 * @description
 */
public class ChaRuSortAlgorithm extends AbstractSortAlgorithm {


    /**
     * 直接插入排序
     * <p>
     * 参数说明：
     * a -- 待排序的数组
     * n -- 数组的长度
     */
    @Override
    protected int[] process(int[] datas) {
        int i, j, k;
        int n = datas.length;
        for (i = 1; i < n; i++) {
            //为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
            for (j = i - 1; j >= 0; j--) {
                if (datas[j] < datas[i]) {
                    break;
                }

            }
            //如找到了一个合适的位置
            if (j != i - 1) {
                //将比a[i]大的数据向后移
                int temp = datas[i];
                for (k = i - 1; k > j; k--) {
                    datas[k + 1] = datas[k];
                }
                //将a[i]放到正确位置上
                datas[k + 1] = temp;
            }
        }
        return datas;
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
