package com.xcn.code.sort;

import java.util.Arrays;

/**
 * @author: xupeng.guo
 * @date: 2019/3/21
 * @description
 */
public abstract class AbstractSortAlgorithm implements SortAlgorithm {

    @Override
    public void sort(int[] datas) {
        System.out.println("Before sort : " + Arrays.toString(datas));
        int[] result = process(datas);
        System.out.println("After sort : " + Arrays.toString(result));
    }

    /**
     * 排序实际执行
     *
     * 假定在待排序的记录序列中，存在多个具有相同的关键字的记录，若经过排序，
     * 这些记录的相对次序保持不变，即在原序列中，r[i]=r[j]，且r[i]在r[j]之前，
     * 而在排序后的序列中，r[i]仍在r[j]之前，则称这种排序算法是稳定的;否则称为不稳定的。
     *
     * @param datas
     */
    protected abstract int[] process(int[] datas);

}
