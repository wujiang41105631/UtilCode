package com.xcn.code.sort.algorthm;

import com.xcn.code.sort.AbstractSortAlgorithm;

/**
 * 归并排序,时间复杂度= O(n*logN)
 * <p>
 * 左边排好序 + 右边排好序 + 左右merge
 * 主要适用： 某个数 左边别他小（大），右边比他大（小）然后在合并之后给个值。
 */
public class GuiBingSortAlgorithm extends AbstractSortAlgorithm {
    @Override
    protected int[] process(int[] datas) {
//        doProcess(datas, 0, datas.length - 1);
        doProcess(datas);
        return datas;
    }

    /**
     * 非递归方式
     *
     * @param datas
     */
    private void doProcess(int[] datas) {
        if (datas == null || datas.length == 0) {
            return;
        }
        int size = datas.length;
        int mergeSize = 1;// 表示半径。
        while (mergeSize < size) {
            int left = 0;
            while (left < size) {
                int middle = left + mergeSize - 1;
                if (middle >= size) {
                    break;
                }
                int right = Math.min(middle + mergeSize, size - 1);
                merge(datas, left, middle, right);
                left = right + 1;
            }
            // 下面的逻辑是为了防止 int 类型的 mergeSize * 2 以后 超过int的最大值
            if (mergeSize > size / 2) {
                break;
            }
            mergeSize <<= 1;
        }

    }

    private void doProcess(int[] datas, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        doProcess(datas, left, mid);
        doProcess(datas, mid + 1, right);
        merge(datas, left, mid, right);
    }

    private void merge(int[] datas, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            // // 此时注意： 如果左右两个数相等，则用左边的数
            help[i++] = datas[p1] <= datas[p2] ? datas[p1++] : datas[p2++];
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
    }


}
