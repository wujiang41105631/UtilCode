package com.xcn.code.sort.algorthm;

import com.xcn.code.sort.SortAlgorithm;

/**
 * @author: xupeng.guo
 * @date: 2019/3/21
 * @description
 */
public class Main {

    public static void main(String[] args) {

        int[] data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10};
        System.out.println("MaoPaoSortAlgorithm--------------------------------------");
        SortAlgorithm sortAlgorithm = new MaoPaoSortAlgorithm();
        sortAlgorithm.sort(data);

        System.out.println("ChaRuSortAlgorithm--------------------------------------");
//        data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10};
        data = new int[]{12, 10, 11, 7, 6, 5, 4, 3, 2, 1};
        SortAlgorithm chaRuAlgorithm = new ChaRuSortAlgorithm();
        chaRuAlgorithm.sort(data);

        System.out.println("XuanZeSortAlgorithm--------------------------------------");
//        data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10};
        data = new int[]{12, 10, 11, 7, 6, 5, 4, 3, 2, 1};
        SortAlgorithm xuanZeAlgorithm = new XuanZeSortAlgorithm();
        xuanZeAlgorithm.sort(data);
    }
}
