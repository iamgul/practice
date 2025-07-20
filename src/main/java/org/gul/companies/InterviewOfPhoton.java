package org.gul.companies;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InterviewOfPhoton {

/*   You are given a string s, which contains stars *.
    In one operation, you can:
    Choose a star in s.
    Remove the closest non-star character to its left, as well as remove the star itself.
    Return the string after all stars have been removed.

    Note:· The input will be generated such that the operation is always possible.· It can be shown that the resulting string will always be unique.
    Example 1:
    Input: s = "leet**cod*e"
    Output: "lecoe"
    Explanation: Performing the removals from left to right: - The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e". - The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e". - The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe". There are no more stars, so we return "lecoe".

    Example 2:
    Input: s = "erase*****"
    Output: ""
    Explanation: The entire string is removed, so we return an empty string.has context menu*/

    public static void main(String[] args) {
        String s = "erase*****";

        List<Character> list = new LinkedList<>();

        char[] charArray = s.toCharArray();
        int size = charArray.length;
        for (int i = 0; i < size; i++) {
            list.add(charArray[i]);
        }
        System.out.println(list);


        while (list.contains('*')) {
            int i = list.indexOf('*');
            System.out.println("index of * :"+ i);
            list.set(i, 'X');
            list.set(i - 1, 'X');
        }
        List list2 = Arrays.asList('X');
        list.removeAll(list2);
        System.out.println("========="+list);


    }
}