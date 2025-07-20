package org.gul;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test5 {


    public static void main(String[] args) {

        // 10,2,5,6,7,8,2,6,9,24,3,4
        // java,is,good,language
        // Java is good language


        int length = 5;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j  || j == length - i) {
                    System.out.print("*");
                }else{
                    System.out.print("-");
                }
            }

        }







    }
}
