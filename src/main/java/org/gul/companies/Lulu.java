package org.gul.companies;

import java.util.HashMap;
import java.util.Map;

public class Lulu {

    public static void main(String[] args) {


        //The coin denominations available for a currency are 1,2,5,10,50,100.
        //For a given amount, find the denomination which will have the least number of coins.
        //For example 183 -> 100x1, 50x1, 10x3, 2x1, 1x1

        int givenNumber = 183;
        int coins[] = new int[]{1, 2, 5, 10, 50, 100};
        int i = coins.length;
        Map<Integer, Integer> coinsMap = new HashMap<>();
        while (givenNumber != 0) {
            int fre = givenNumber / coins[i - 1];
            coinsMap.put(coins[i - 1], fre);
            givenNumber = givenNumber % coins[i - 1];
            i--;
        }
        System.out.println(coinsMap);
    }


}

