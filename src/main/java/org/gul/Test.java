package org.gul;

import java.util.*;
import java.util.stream.*;

public class Test {
    public static void main(String[] args) {
        String input = "Java is amazing";
        String result = sortWordsByLengthStable(input);
        System.out.println("Output: " + result); // Output: "is Java amazing"
    }

    public static String sortWordsByLengthStable(String sentence) {
        //write code here
        List<String> sortedString = Stream.of(sentence.split(" "))
                .sorted(
                        (String1,string2)->{
                            Integer s1 = String1.length();
                            Integer s2 = string2.length();
                            return s1.compareTo(s2);
                        }
                ).collect(Collectors.toList());

        String result = sortedString.stream().collect(Collectors.joining(" "));
        return result;
    }
}
