package org.gul.basics;

import java.util.*;

public class BasicRaghuSir {

    public static void main(String[] args) {

        //1.Write a program to display sum of 1 to N numbers?
        displaySum();
        //2.Write a program to check if given number is EVEN or ODD?
        checkEvenOrOdd();
        //3.Write a program to display PRIME NUMBERS from 1 to n?
        displayPrimeNumbers(); //A prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself. In other words, a prime number is only divisible by 1 and itself. Ex: 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, ...
        //4.Write a program to find SUM OF PRIME numbers?
        sumOfPrime();
        //5.Write a program to display MULTIPLICATION table?
        multiplicationTable();
        //6.Write a program to display MULTIPLICATION table's?
        multiplicationTables();
        //7.Write program to display PERFECT_NUMBERS from 1 to n?
        displayPerfectNumbers();//If sum of all positive divisors of number(excluding itself) equals to number than its PERFECT_NUMBER. Ex: 6 is a perfect number because its divisors (1,2,3) sums up to 6.
        //8.Write a program to display PALINDROME_NUMBERS from 1 to n?
        displayPalindromicNumbers();
        //9.Write a program to display the FACTORIAL of a given numbers from 1 to n?
        displayFactorialOfNum();//Factorial of a number is the product of all positive integers from 1 up to that number. It is denoted by the symbol n!.
        //10.Write program to check if the given number is STRONG_NUMBER?
        displayStrongNumbers();//If sum of factorial of each digit of number is same as number itself, then it's a STRONG_NUMBER. Ex: 145-> fact of 1,4,5 are 1, 24 & 120 respectively and sum of 1,24,120 is 145 which is same as num
        //11.Write a program to display the FIBONACCI_NUMBERS from 1 to n?
        displayFibonacciNumbers();//The Fibonacci numbers are a sequence of numbers where each number is the sum of the two preceding ones, starting from 0 and 1. Ex: 0,1,1,2,3,5,8,13,21,34,55...
        //12.Write a program to REVERSE the number?
        reverseNumber();
        //13.Write a program to display GCD of two numbers?
        gcdOfTwoNumbs();//Greatest Common Divisor is the largest number that can divide both numbers without leaving a remainder.
        //14.Write a program to check the given number is PRIME PALINDROME or not?
        displayPrimePalindromicNumbers();
        //15.Write a Program to check if the given number is ARMSTRONG or not?
        isArmStrongNumber();
        //16.Write a program to Swap two numbers without using 3rd variable?
        swap2NumbersWithoutUsing3rdVariable();

        //////////////////////////
        /////////Strings//////////
        //////////////////////////

        //17.DisplayAnagram
        displayAnagram();
        //18.WAP to convert String into Array
        toCharArray("STRING");
        //19.WAP to reverse the String without reversing the number and their position
        reverseStringMaintainingNumPosition();




    }

    private static void reverseStringMaintainingNumPosition() {

        String s = "abc123def45";
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length;) {
            for (int j = 0; j < charArray.length; ) {
                if(Character.isAlphabetic(charArray[j])){
                    while (Character.isAlphabetic(charArray[j])){
                        j++;
                    }
                    char[] chars = Arrays.copyOfRange(charArray, i, j);

                    printChar("before reversing chars",chars);
                    chars = revArray(chars);
                    printChar("after reversing chars",chars);

                    printChar("before charArray",charArray);
                    System.arraycopy(chars,0,charArray,i,chars.length);
                    printChar("After charArray",charArray);
                    i=j;
                }else{
                    i++;
                    j++;
                }
            }
        }
        String s1 = new String(charArray);
        printChar("final output :",s1);
    }

    private static void reverseStringMaintainingNumPosition1() {

        String s = "abc123def45";
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length;) {
            for (int j = 0; j < charArray.length; ) {
                if(Character.isAlphabetic(charArray[j])){
                    while (Character.isAlphabetic(charArray[j])){
                        j++;
                    }
                    char[] chars = Arrays.copyOfRange(charArray, i, j);

                    printChar("before reversing chars",chars);
                    chars = revArray(chars);
                    printChar("after reversing chars",chars);

                    printChar("before charArray",charArray);
                    System.arraycopy(chars,0,charArray,i,chars.length);
                    printChar("After charArray",charArray);
                    i=j;
                }else{
                    i++;
                    j++;
                }
            }
        }
        String s1 = new String(charArray);
        printChar("final output :",s1);
    }

    private static char[] revArray(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            for (int j = i+1; j < chars.length; j++) {
                char temp = chars[i];
                chars[i]=chars[j];
                chars[j]=temp;
            }
        }
        return chars;
    }

    private static void printChar(String s, char[] chars) {
        System.out.println("\n"+s);
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }
    }
    private static void printChar(String s, String string) {
        System.out.print(s);
        System.out.print(string);
    }


    private static String reverseString(String s) {
        System.out.println("Original String" + s);
        if (s.length() == 1)
            return s;
        String reverse = "";
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            reverse = charArray[i] + reverse;
        }
        return reverse;
    }


    //================  ================  ================
    private static void displaySum() {
        int n = 10;
        int sum = 0;
        for (int i = 1; i <=n ; i++) {
            sum=sum+i;
        }
        System.out.println(sum);
    }

    //================  ================  ================
    private static void checkEvenOrOdd() {
        int n = 10;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                System.out.println(i + " is EVEN");
            } else {
                System.out.println(i + " ODD");
            }
        }
    }
    //================  ================  ================
    private static void displayPrimeNumbers() {
        int n = 100;
        for (int i = 1; i <= n ; i++) {
            boolean isPrime =  isPrimeNumber(i);
            if (isPrime){
                System.out.println(i + " is Prime Number");
            }
        }
    }

    private static boolean isPrimeNumber(int num) {
        if (num < 2) {
            return false; // All numbers lower than 2 is not prime numbers
        }
        //Why Do We Only Check Up to Math.sqrt(num) ? If n is divisible by a number greater than Math.sqrt(num), then it must also be divisible by a smaller number (a factor less than Math.sqrt(num)). This property allows us to reduce the range of numbers we need to check.
        //Math.sqrt(10)=3.16 so we check divisibility by integers up to 3 (i.e., 2 and 3).
        //This also satisfies 2 & 3 as prime number as Math.sqrt(2) is 1.41 , which is smaller than the loop condition and hence it does not enter the loop and directly return true.
        for (int i = 2; i <= Math.sqrt(num); i++) { //Note that <= is used since Math.sqrt(9) is 3 and and we need to check till 3, hence = sign is imp.
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    //================  ================  ================
    private static void sumOfPrime() {
        int n = 10;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            boolean primeNumber = isPrimeNumber(i);
            if (primeNumber) {
                sum = sum + i;
            }
        }
        System.out.println("The sum of prime numbers is: " + sum);
    }
    //================  ================  ================
    private static void multiplicationTable() {
        int num = 11;
        for (int i = 1; i <= 10; i++) {
            System.out.println(num + "*" + i + " = " + num * i);
        }
    }
    //================  ================  ================
    private static void multiplicationTables() {
        int range = 5; // Number of tables
        int limit = 10;
        for (int j = 1; j < limit; j++) {
            for (int i = 1; i <= range; i++) {
                System.out.print(i + "*" + j + "=" + i * j + "\t"); // This will print in order of [1*1=1	2*1=2	3*1=3	4*1=4	5*1=5] and inner loop ends a line will be added and continue... that why we are  1*1, 2*1, 3*1, instead 1*1, 1*2, 1*3, because once new line is added we cant come to same line for tab and hence tab is performed first and then new line
            }
            System.out.println();
        }
    }
    //================  ================  ================
    private static void displayPerfectNumbers() {
        int n = 100;
        int sum = 0;
        for (int i = 1; i < n; i++) {
            boolean isPerfect = isPerfectNumber(i);
            if (isPerfect) {
                System.out.println(i + " : is Perfect Number");
            }
        }
    }

    private static boolean isPerfectNumber(int num) {
        //find half of number, we will divide the given num from 1 to till half of num.
        //if we find any proper divisor, we will sum all of them & if sum == num, then its PN
        //The only proper divisor of 1 is none (it has no divisors other than itself), Hence it's not PN
        int sum = 0;
        boolean isPerfectNum = false;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sum = sum + i;
            }
        }
        if (sum == num) {
            isPerfectNum = true;
        }
        return isPerfectNum;
        //This if condition and flag isPerfectNum can be avoided using one line: return num == sum;
    }
    //================  ================  ================
    private static void displayPalindromicNumbers() {
        int n = 121;
        for (int i = 1; i <=n ; i++) {
            boolean isPalindrome = isPalindromeNumber(i);
            if(isPalindrome){
                System.out.println(i + " : is Palindrome Number");
            }
        }
    }

    private static boolean isPalindromeNumber(int num) {
        int originalNum = num;
        int reversedNum = 0;
        while (num != 0) {
            int lastDigit = num % 10;
            reversedNum = reversedNum * 10 + lastDigit;
            num = num / 10;
        }
        return originalNum == reversedNum;
    }
    //================  ================  ================
    private static void displayFactorialOfNum() {
        int range = 100;
        for (int i = 1; i <= 10 ; i++) {
            int factorialOfNum = findFactorialOfNum(i);
            System.out.println("Factorial of given number: " + i + " is :" + factorialOfNum);
        }
    }

    private static int findFactorialOfNum(int num) {
        int sum = 1;
        for (int i = 1; i <= num; i++) {
            sum = sum * i;
        }
        return sum;
    }
    //================  ================  ================
    private static void displayStrongNumbers() {
        int n = 100_00;
        for (int i = 1; i <= n; i++) {
            boolean isStrongNum = isStrongNumber(i);
            if (isStrongNum) {
                System.out.println(i + " : is Strong Number");
            }
        }
    }

    private static boolean isStrongNumber(int originalNum) {
        int temp = originalNum;
        int sum = 0;
        while (temp != 0) {
            int lastDigit = temp % 10;
            int factorialNum = findFactorialOfNum(lastDigit);
            sum = sum + factorialNum;
            temp = temp / 10;
        }
        return originalNum == sum;
    }
    //================  ================  ================
    private static void displayFibonacciNumbers() {
        int range = 100;
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        for (int i = 1; i <= 100; i++) {
            boolean isFibonacci = isFibonacciNumber(i, list);
            //printFibonacciNumberWithoutUsingList();
        }
        System.out.println("Fibonacci numbers are:");
        System.out.println(list);
    }

    private static boolean isFibonacciNumber(int num, List<Integer> list) {
        if (list.get(list.size() - 1) + list.get(list.size() - 2) == num) {
            list.add(num);
            return true;
        }
        return false;
    }

    private static void printFibonacciNumberWithoutUsingList() {
        int range = 100;
        int a = 0;
        int b = 1;
        int c = 0;
        System.out.println("Fibonacci Numbers without using List are:: ");
        System.out.println(a);
        System.out.println(b);
        for (; c <= range; c++) {
            c = a + b;
            System.out.println(c);
            a = b;
            b = c;
        }
    }
    //================  ================  ================
    private static void reverseNumber() {
        int givenNum = 157;
        int reversedNum = 0;
        while (givenNum != 0) {
            int lastDigit = givenNum % 10; // 7
            reversedNum = reversedNum * 10 + lastDigit;
            givenNum = givenNum / 10;
        }
        System.out.println("The reversed number of a given number is:" + reversedNum);
    }
    //================  ================  ================
    private static void gcdOfTwoNumbs() {
        //The Euclidean Algorithm is a mathematical method for finding the GCD of two numbers. The algorithm is based on the principle: The GCD of two numbers
        //does not change if the larger number is replaced by its remainder when divided by the smaller number.
        //In the Euclidean algorithm, we swap the smaller number into the larger number's position, and the remainder takes the smaller number's position.
        //This does not violate the principle stated above but rather implements it iteratively.

        /* In the Euclidean algorithm:
        1. Initially, 𝑎 is the larger number, and 𝑏 is the smaller number.
        2. We calculate 𝑎 mod 𝑏, which is the remainder.
        3. Then: a is replaced with 𝑏 (the smaller number) & 𝑏 is replaced with 𝑎 mod 𝑏 (the remainder).
        4. This iterative process continues until 𝑏 = 0 and the GCD is found in 𝑎.*/

        int a = 12;
        int b = 18;

        findGCDUsingEuclideanAlg(a, b);
        findGCDUsingFactorization(a, b);
    }

    private static void findGCDUsingEuclideanAlg(int a, int b) {
        if (b > a) { //Making sure `a` is always greater
            int temp = a;
            a = b;
            b = temp;
        }
        while (b != 0) {
            int reminder = a % b;
            a = b;
            b = reminder;
        }
        System.out.println("The GCD of two given number using Euclidean Algorithm is  :: " + a);
    }

    private static void findGCDUsingFactorization(int a, int b) {
        //Factors a = 2 * 2 * 3
        //Factors b = 2 * 3 * 3
        //GCD = Multiply Common Element = 2 * 3
        List<Integer> listA = factorsOfNum(a);
        System.out.println("ListA" + listA);

        List<Integer> listB = factorsOfNum(b);
        System.out.println("ListB" + listB);

        List<Integer> commonElement = new LinkedList<>();
        for (Integer as : listA) {
            if (listB.contains(as)) {
                commonElement.add(as);
                listB.remove(as);//To handle duplicates
            }
        }
        System.out.println("commonElement" + commonElement);

        int sum = 1;
        for (int ce : commonElement) {
            sum = sum * ce;
        }
        System.out.println("The GCD of two given number using Factorization is :: " + sum);
    }

    private static List<Integer> factorsOfNum(int num) {
        //Divisor of a = find divisors of a and put in List
        List<Integer> list = new LinkedList<>();
        for (int i = 2; i <= num; i++) {
            while (num % i == 0) {
                list.add(i);
                num = num / i;
            }
        }
        return list;
    }
    //This can also be achieved by finding all the divisors of a given number and finding the highest common element in both the number
    //ex: 18 -> 1,2,3,6,9,18 , 36-> 1,2,3,4,6,9,12,18,36 -> In both 18 is common and Highest, so 18 would be the GCD
    public static List<Integer> divisors(int num) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }
    //================  ================  ================
    private static void displayPrimePalindromicNumbers() {
        int range = 1000;
        for (int i = 1; i <= range; i++) {
            boolean isPrimePalindromic = isPrimePalindrome(i);
            if (isPrimePalindromic) {
                System.out.println("The given number is PrimePalindrome: " + i);
            }
        }
    }

    private static boolean isPrimePalindrome(int num) {
        boolean isPalindromic = false;
        boolean isPrime = isPrimeNumber(num);
        if (isPrime) {
            isPalindromic = isPalindromeNumber(num);
        }
        return isPalindromic;
    }
    //================  ================  ================
    private static void isArmStrongNumber() {
        int num = 371;
        int temp = num;
        int getNumOfDigit = getNumOfDigit(num);
        double result = 0;
        while (num != 0) {
            int lastDigit = num % 10;
            result = result + pow(lastDigit, getNumOfDigit);
            num = num / 10;
        }
        if (temp == result) {
            System.out.println("The given number is ArmStrongNumber");
        }
    }

    private static double pow(int n, int p) {
        int pow = 1;
        while (p != 0) {
            pow = pow * n;
            p--;
        }
        return pow;
    }

    private static int getNumOfDigit(int num) {
        int numOfDigit = 0;
        while (num != 0) {
            numOfDigit++;
            num = num / 10;
        }
        return numOfDigit;
    }
    //================  ================  ================
    private static void swap2NumbersWithoutUsing3rdVariable() {
        int a = 20;
        int b = 30;
        System.out.println("Before swapping--> a:" + a + " b:" + b);
        a = a + b; // 20 + 30 = 50 = a
        b = a - b; // 50 - 30 = 20 = b
        a = a - b; // 50 - 20 = 30 = a
        System.out.println("After swapping--> a:" + a + " b:" + b);
    }
    //================  ================  ================
    private static void displayAnagram() {
        String first = "Mother in law";
        String second = "Hitler woman";

        //17.1.Write a program to find weather a string is ANAGRAM or not?
        boolean isAnagram = isAnagram(first, second);
        System.out.println("isAnagram: " + isAnagram);

        //17.2.Write a program to find weather a string is ANAGRAM without using inbuilt functions?
        boolean isAnagramWithoutUsingInbuiltFunctions = isAnagramWithoutUsingInbuiltFunctions(first, second);
        System.out.println("isAnagramWithoutUsingInbuiltFunctions :" + isAnagramWithoutUsingInbuiltFunctions);
    }

    private static boolean isAnagram(String first, String second) {
        boolean isAnagram = true;

        first = first.replaceAll(" ", "");
        second = second.replaceAll(" ", "");

        if (first.length() != second.length()) {
            return false;
        }

        first = first.toLowerCase();
        second = second.toLowerCase();

        char[] firstCharArray = first.toCharArray();
        char[] secondCharArray = second.toCharArray();

        Arrays.sort(firstCharArray);
        Arrays.sort(secondCharArray);

        for (int i = 0; i < firstCharArray.length; i++) {
            if (firstCharArray[i] != secondCharArray[i]) {
                isAnagram = false;
                break;
            }
        }
        return isAnagram;
    }

    private static boolean isAnagramWithoutUsingInbuiltFunctions(String first, String second) {
        boolean isAnagram = true;

        first = removeSpace(first);
        second = removeSpace(second);

        first = toLowerCase(first);
        second = toLowerCase(second);

        char[] firstCharArray = sort(first);
        char[] secondCharArray = sort(second);

        for (int i = 0; i < firstCharArray.length; i++) {
            if (firstCharArray[i] != secondCharArray[i]) {
                isAnagram = false;
                break;
            }
        }
        return isAnagram;
    }

    private static String removeSpace(String value) {
        char[] charArray = value.toCharArray();
        String stringWithoutSpace = "";
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] != ' ') {
                stringWithoutSpace = stringWithoutSpace + charArray[i];
            }
        }
        return stringWithoutSpace;
    }

    private static String toLowerCase(String value) {
        char[] charArray = value.toCharArray();
        String lowerCase = "";
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                lowerCase =  Character.isUpperCase(charArray[i]) ? String.valueOf(Character.toLowerCase(charArray[i])):lowerCase + charArray[i];

                lowerCase = lowerCase + (char) (charArray[i] + 32);
            } else {
                lowerCase = lowerCase + charArray[i];
            }
        }
        return lowerCase;
    }

    private static char[] sort(String first) {
        char[] charArray = first.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            for (int j = i + 1; j < charArray.length; j++) {
                if (charArray[i] > charArray[j]) {
                    char temp = charArray[i];
                    charArray[i] = charArray[j];
                    charArray[j] = temp;
                }
            }
        }
        return charArray;
    }
    //================  ================  ================
    private static void toCharArray(String first) {
        char[] arr = new char[first.length()];
        for (int i = 0; i < first.length(); i++) {
            arr[i] = first.charAt(i);
        }
        System.out.println("char Array");
        for (char c : arr){
            System.out.print(c);
        }
    }
    //================  ================  ================






}
