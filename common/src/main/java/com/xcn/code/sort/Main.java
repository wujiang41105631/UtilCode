package com.xcn.code.sort;

import com.xcn.code.sort.algorthm.ChaRuSortAlgorithm;
import com.xcn.code.sort.algorthm.MaoPaoSortAlgorithm;
import com.xcn.code.sort.algorthm.SortAlgorithm;
import com.xcn.code.sort.algorthm.XuanZeSortAlgorithm;

/**
 * @author: xupeng.guo
 * @date: 2019/3/21
 * @description
 */
public class Main {

    public static void main(String[] args) {

        int[] data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10};
        System.out.println("MaoPaoSortAlgorithm---------------------------------------");
        SortAlgorithm sortAlgorithm = new MaoPaoSortAlgorithm();
        sortAlgorithm.sort(data);

        System.out.println("ChaRuSortAlgorithm----------------------------------------");
        data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10};
        SortAlgorithm chaRuAlgorithm = new ChaRuSortAlgorithm();
        chaRuAlgorithm.sort(data);

        System.out.println("XuanZeSortAlgorithm---------------------------------------");
        data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10};
        SortAlgorithm xuanZeAlgorithm = new XuanZeSortAlgorithm();
        xuanZeAlgorithm.sort(data);
    }
}
