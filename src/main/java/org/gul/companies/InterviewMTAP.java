package org.gul.companies;

public class InterviewMTAP {

    //isAnagram
    //cryptography
    //FetchFirstDigit

    public static void main(String[] args) {
        int num = 1234;
        findFirstDigit(num);
        findFirst2Digit(num);
        findFirst3Digit(num);

        findLastDigit(num);
        findLast2Digit(num);
        findLast3Digit(num);
    }


    private static void findFirstDigit(int num) {
        while (num > 10) {
            num = num / 10;
        }
        System.out.println("First digit of a number is: " + num);
    }

    private static void findFirst2Digit(int num) {
        while (num > 99) {
            num = num / 10;
        }
        System.out.println("First Two Digit of a number is: " + num);
    }

    private static void findFirst3Digit(int num) {
        while (num > 999) {
            num = num / 10;
        }
        System.out.println("First Three Digit of a number is: " + num);
    }

    private static void findLastDigit(int num) {
        int lastDigit = 0;
        while (num != 0) {
            lastDigit = num % 10;
            break;
        }
        System.out.println("Last digit of a number is: " + lastDigit);
    }

    private static void findLast2Digit(int num) {

        int lastDigit = 0;
        while (num != 0) {
            lastDigit = num % 100;
            break;
        }
        System.out.println("Last Two Digit of a number is: " + lastDigit);
    }

    private static void findLast3Digit(int num) {
        int lastDigit = 0;
        while (num != 0) {
            lastDigit = num % 1000;
            break;
        }
        System.out.println("Last Three Digit of a number is: " + lastDigit);
    }

}
