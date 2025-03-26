package com.xcn.code.sort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
    public static void main(String[] args) {
        System.out.println(4&7);
    }

//    public static int getData(String data){
//        if(data == null || data.equals("")){
//            return -1;
//        }
//        int[] arr = new int[26];
//        char[] charArr = data.toCharArray();
//        for(int i = 0;i<charArr.length;i++){
//            char c = charArr[i];
//            char t = c;
//            if(c >= 'A' && c <= 'Z'){
//                t = (char)(c + 32);
//            }
//            arr[ t-'a']++;
//        }
//        int minIndex = Integer.MAX_VALUE;
//        for(int i = 0; i<arr.length;i++){
//            if(arr[i] == 1){
//                char t = (char)(i+'a');
//                int tmpIndex = data.indexOf(t);
//                if(tmpIndex < 0){
//                    tmpIndex = data.indexOf((int)(t-32));
//                }
//                if(tmpIndex < minIndex){
//                    minIndex = tmpIndex;
//                }
//            }
//        }
//        return minIndex == Integer.MAX_VALUE?-1:minIndex;
//    }
}