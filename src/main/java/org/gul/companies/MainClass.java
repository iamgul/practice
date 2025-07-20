package org.gul.companies;

public class MainClass {

    //Given String s = "I Work At Accolite Digital"
    // Convert the lowercase
    // character to uppercase and vice-versaOutput - i wORK aT aCCOLITE dIGITAL


    public static void main(String[] args) {
        String s = "I Work At Accolite Digital";
        int capa = 'A'; //65
        int capz = 'Z'; //90
        int smalla = 'a'; //97
        int smallz = 'z'; //122

        System.out.println(capa);
        System.out.println(capz);
        System.out.println(smalla);
        System.out.println(smallz);


        char[] charArray = s.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            if (charArray[i] >= 'A' && charArray[i] <= 'Z') {
                //means its capital -> convert it to small
                int i1 = i + ('Z' - 'A');
                char c =  (char) i1;
                System.out.println(c);
            }else if(charArray[i]>=97 && charArray[i]<=122){
                //means it small -> convert it to caps
                int i1 = i - (97 - 65);
                char c = (char)i1;
                System.out.println(c);
            }
            }
//        for (char c : charArray){
//            System.out.println(c);
//        }
//        String s1 = String.valueOf(charArray);
//        System.out.println(s1);
        }


    }
