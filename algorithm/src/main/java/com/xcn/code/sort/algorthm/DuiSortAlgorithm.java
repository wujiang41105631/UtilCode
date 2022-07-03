package com.xcn.code.sort.algorthm;

import com.xcn.code.sort.AbstractSortAlgorithm;

/**
 * 堆排序
 * https://www.cnblogs.com/chengxiao/p/6129630.html
 * @author: xupeng.guo
 * @date: 2019/3/22
 * @description
 */
public class DuiSortAlgorithm extends AbstractSortAlgorithm {

    /**
     * 题目：
     * 1. 已知一个几乎有序的数组（几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不会超过K，并且k小对于数组来说是比较小的），请选择一个合适的策略排序。
     *    解法： 给定一个大小为K的小根堆，挨个放就好了
     * @param datas
     * @return
     */







    @Override
    protected int[] process(int[] datas) {
        //1.构建大顶堆
        for (int i = datas.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(datas, i, datas.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = datas.length - 1; j > 0; j--) {
            swap(datas, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(datas, 0, j);//重新对堆进行调整
        }
        return datas;
    }


    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
