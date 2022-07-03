package com.xcn.code.array;

/**
 * 返回 数据长度
 */
public class ArrayAlgorithm01 {

    public static void main(String[] args) {
        System.out.println(getArraySize(new int []{1,2,2,3,4,5,5,7,8,8}));
    }

    /**
     * 双指针法返回数组长度
     * @param ints
     * @return
     */
    private static int getArraySize(int[] ints) {
        if(ints == null){
            return 0;
        }

        int result = 0;
        for(int j = 1; j<ints.length;j++){
           if(ints[j]!=ints[result]){
                result ++;
                ints[result] = ints[j];
           }
        }
        return result+1;
    }
}
