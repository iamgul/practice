package org.gul.companies;

import java.util.Comparator;
import java.util.List;

public class New {
/*    Input: abcdef, bhd

    abcdef    a,b,c,d,ab,aef,ce,bd,cef,acdf,etc
    bhd       b,h,d,bh,hd,bd,bhd

    b,d,bd

            bd

    output: 2 */

    public static void main(String[] args) {

        String s1 = "abcdef";
        String s2 = "bhd";

        List<String> c1 = subSequence(s1);
        List<String> c2 =  subSequence(s2);

        List<String> common = findCommon(c1,c2);

        Integer length = common.stream().map(s -> s.length()).sorted(Comparator.reverseOrder()).toList().get(0);
        System.out.println(length);
    }

    private static List<String> findCommon(List<String> c1, List<String> c2) {
        c1.retainAll(c2);
        return c1;
    }

    private static List<String> subSequence(String s1) {

        int length = s1.length();


        return null;

    }


}

















