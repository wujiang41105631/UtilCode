package com.xcn.code.sort;

import com.xcn.code.sort.algorthm.KuaiSuSortAlgorithm;

import java.util.Arrays;

/**
 * @author: xupeng.guo
 * @date: 2019/3/21
 * @description
 */
public class Main {
    public static void main(String[] args) {

        int[] a = new int[]{1,2,3};
        int[] b = new int[]{1,2,3};
        System.out.println(Arrays.toString(mergeAndSort(a,b)));
    }

    public static int[] mergeAndSort(int[] a, int[] b){
        if(a == null && b == null){
            return null;
        }
        if(a == null && b!=null){
            return b;
        }
        if(a != null && b == null){
            return a;
        }
        int[] result = new int[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        Arrays.sort(result);
        return result;
    }

    public static void main1(String[] args) {

        int[] data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10};
//        System.out.println("MaoPaoSortAlgorithm---------------------------------------");
//        SortAlgorithm sortAlgorithm = new MaoPaoSortAlgorithm();
//        sortAlgorithm.sort(data);
//
//        System.out.println("ChaRuSortAlgorithm----------------------------------------");
//        data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10};
//        SortAlgorithm chaRuAlgorithm = new ChaRuSortAlgorithm();
//        chaRuAlgorithm.sort(data);
//
//        System.out.println("XuanZeSortAlgorithm---------------------------------------");
//        data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10};
//        SortAlgorithm xuanZeAlgorithm = new XuanZeSortAlgorithm();
//        xuanZeAlgorithm.sort(data);
//
        System.out.println("KuaiSuSortAlgorithm---------------------------------------");
        data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10};
        SortAlgorithm kuaiSuAlgorithm = new KuaiSuSortAlgorithm();
        kuaiSuAlgorithm.sort(data);
//
//        System.out.println("DuiSortAlgorithm---------------------------------------");
//        data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10, 11};
//        SortAlgorithm duiSuAlgorithm = new DuiSortAlgorithm();
//        duiSuAlgorithm.sort(data);

//        System.out.println("GuiBingAlgorithm---------------------------------------");
//        data = new int[]{1, 15, 2, 25, 1, 3, 5, 6, 10, 11};
//        SortAlgorithm guibingAlgorithm = new GuiBingSortAlgorithm();
//        guibingAlgorithm.sort(data);
    }
}
