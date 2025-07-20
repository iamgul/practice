package org.gul.companies;

import java.util.Arrays;

public class Amadeus {


    public static void main(String[] args) {

        String s = "tttss12yyt13hh60ii09";
        int length = s.length();

        char[] ch = s.toCharArray();
        int sum = 0;
        String num = "";

        for (int i = 0; i < ch.length; i++) {
            if (Character.isDigit(ch[i])) {
                num = num + ch[i];
            } else {
                if (!num.isEmpty()) {
                    int StringToNum = Integer.parseInt(num);
                    System.out.println(StringToNum);
                    sum = sum + StringToNum;
                }
                num = "";
            }
        }
        if (!num.isEmpty()) {
            sum = sum + Integer.parseInt(num);
        }
        System.out.println(sum);



/*        public class SumOfNumbersInString {
            public static void main(String[] args) {
                String input = "ase12df15skn03";
                System.out.println(findSum(input));
            }

            public static int findSum(String str) {
                int sum = 0;
                String temp = "";

                for (int i = 0; i < str.length(); i++) {
                    char ch = str.charAt(i);
                    if (Character.isDigit(ch)) {
                        temp += ch;
                    } else {
                        if (!temp.isEmpty()) {
                            sum += Integer.parseInt(temp);
                            temp = "";
                        }
                    }
                }
                if (!temp.isEmpty()) {
                    sum += Integer.parseInt(temp);
                }
                return sum;
            }
        }*/


        //Anagram
        //keep , peek


//        String s1 = "keep";
//        String s2 = "peek";
//        boolean isAngm =  isAnagrm(s1,s2);
//        System.out.println(isAngm);


    }

    private static boolean isAnagram(String s1, String s2) {
        boolean isAnagram = false;

        if (s1.length() != s2.length()) {
            return false;
        }

        char[] charArray1 = s1.toCharArray();
        char[] charArray2 = s2.toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);


        for (int i = 0; i < charArray1.length; i++) {
            if(charArray1[i]!=charArray2[i]){
                isAnagram=false;
                break;
            }
        }
        return isAnagram;


    }
}