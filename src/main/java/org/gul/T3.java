package org.gul;

public class T3 {

/*    Given two strings s1 and s2, write a snippet to check whether s2 is a rotation

    of s1. Strings may contain duplicates. Examples:


    Input : s1 = "ABCD", s2 = "CDAB" Output : True

    String s1 is rotation of s2.

    Input : s1 = "ABAD", s2 = "ADAB" Output : True

    Input : s1 = ABCD, and s2 = ACBD Output : False*/


    public static void main(String[] args) {
        
        String s1 = "ABCD";
        String s2 = "CDAB"; //BCDA
        boolean isRotation = isRotation(s1,s2);
        
        
    }

    private static boolean isRotation(String s1, String s2) {
        boolean isRotation = false;
        if(s1.length()!=s2.length()){
            return isRotation;
        }

        char[] s1c = s1.toCharArray();
        char[] s2c = s2.toCharArray();

        for (int i = 0; i < s1c.length; i++) {
            int indexOfS2 = s2.indexOf(s1c[i]);
            String substring = s2.substring(indexOfS2);

        }
        


        return isRotation;
    }


}
