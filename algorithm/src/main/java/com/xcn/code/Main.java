package com.xcn.code;

/**
 * @description:
 * @author: xupeng.guo
 * @create: 2020-07-08 14:57
 **/
public class Main {


    public static String addString(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return null;
        } else if (s1 == null && s2 != null) {
            return s2;
        } else if (s2 == null && s1 != null) {
            return s1;
        } else {
            char[] s1CharArray = s1.toCharArray();
            char[] s2CharArray = s2.toCharArray();
            int s1Offset = s1CharArray.length - 1;
            int s2Offset = s2CharArray.length - 1;
            int upper = 0;
            boolean s1Stop = true;
            String result = "";
            for (; s1Offset > -1; s1Offset--) {
                if (s2Offset > -1) {
                    int r = (s1CharArray[s1Offset] - '0') + (s2CharArray[s2Offset] - '0') + upper;
                    if (r >= 10) {
                        upper = 1;
                        result = (r - 10) + result;
                    } else {
                        upper = 0;
                        result = r + result;
                    }
                    s2Offset--;
                } else {
                    break;
                }
            }
            if (s1Offset > -1) {
                s1Offset++;
                result = getPrefix(s1, upper, s1Offset) + result;
            } else if (s2Offset > -1) {
                s2Offset++;
                result = getPrefix(s2, upper, s2Offset) + result;
            } else {
                String prefix = upper > 0 ? "1" : "";
                return prefix + result;
            }
            return result;
        }
    }

    public static String getPrefix(String s, int upper, int offset) {
        String subString = s.substring(0, offset);
        if (upper == 0) {
            return subString;
        } else {
            return addString(subString, "" + upper);
        }
    }

    public static void main(String[] args) {
        System.out.println(Main.addString(null, null));
        System.out.println(Main.addString(null, "123"));
        System.out.println(Main.addString("123", null));
        System.out.println(Main.addString("123", "123"));
        System.out.println(Main.addString("1234", "123"));
        System.out.println(Main.addString("1234", "12345"));
    }
}
