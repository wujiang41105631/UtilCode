package com.xcn.code.sort.algorthm;

import com.xcn.code.sort.AbstractSortAlgorithm;

/**
 * 快速排序
 * https://blog.csdn.net/adusts/article/details/80882649
 * @author: xupeng.guo
 * @date: 2019/3/22
 * @description
 */
public class KuaiSuSortAlgorithm extends AbstractSortAlgorithm {
    @Override
    protected int[] process(int[] datas) {
        quickSort_2(datas, 0, datas.length - 1);
        return datas;
    }


    public void quickSort_1(int[] data, int start, int end) {
        if (data == null || start < 0 || end > data.length - 1) {
            throw new IllegalArgumentException("Invalid Parameters");
        }
        if (start == end) return;
        int index = partition(data, start, end);
        if (index > start) {
            quickSort_1(data, start, index - 1);
        }
        if (index < end) {
            quickSort_1(data, index + 1, end);
        }
    }

    private int partition(int[] data, int start, int end) {
        int index = start + (int) (Math.random() * (end - start + 1));
        swap(data, index, end);
        int small = start - 1;
        for (index = start; index < end; index++) {
            if (data[index] < data[end]) {
                small++;
                if (small != index) {
                    swap(data, index, small);
                }
            }
        }
        swap(data, small + 1, end);
        return small + 1;
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }


    public void quickSort_2(int[] data, int start, int end) {
        if (data == null || start >= end) {
            return;
        }
        int leftOffset = start;
        int rightOffset = end;
        int pivotKey = data[start];
        while (leftOffset < rightOffset) {
            while (leftOffset < rightOffset && data[rightOffset] >= pivotKey) {
                rightOffset--;
            }
            if (leftOffset < rightOffset) {
                data[leftOffset++] = data[rightOffset];
            }
            while (leftOffset < rightOffset && data[leftOffset] <= pivotKey) {
                leftOffset++;
            }
            if (leftOffset < rightOffset) {
                data[rightOffset--] = data[leftOffset];
            }
        }
        data[leftOffset] = pivotKey;
        quickSort_2(data, start, leftOffset - 1);
        quickSort_2(data, leftOffset + 1, end);
    }
}
