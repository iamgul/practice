package org.gul.companies;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        //Q1. Majority Elements -> if number of same elements in array is half of size of array. then its major element
        int[] array = {1,2,3,4,6,1,2,1,8,1,1,1};
        //getMajorEle(array);

        //Q2 Find Duplicates in String
        String s = "interview";
        //getDuplicateElements(s);
        getDuplicateElementsUsingJava8(s);


    }


    private static void getMajorEle(int[] array) {
        Map<Integer, Long> collect = Arrays.stream(array).boxed().collect(Collectors.groupingBy(element -> element, Collectors.counting()));
        System.out.println(collect);
        Optional<Integer> first = collect.entrySet().stream().filter(e -> e.getValue() >= array.length / 2).map(e -> e.getKey()).findFirst();
        first.ifPresent(System.out::println);
    }

    private static void getDuplicateElements(String s) {
        char[] charArray = s.toCharArray();
        int length = charArray.length;
        for (int i = 0; i <length ; i++) {
            for (int j = i+1; j < length; j++) {
                if(charArray[i]==charArray[j]){
                    System.out.println(charArray[i]);
                }
            }
        }
    }

    private static void getDuplicateElementsUsingJava8(String s) {
        char[] charArray = s.toCharArray();
        IntStream chars = s.chars();
        Stream<Character> characterStream = chars.mapToObj(c -> (char) c);
        Map<Character, Long> collect3 = characterStream.collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        List<Character> list1 = collect3.entrySet().stream().filter(e -> e.getValue() > 1).map(e -> e.getKey()).toList();
        System.out.println(list1);
//        Map<Integer, Long> collect2 = chars.boxed().collect(Collectors.groupingBy(c -> c, Collectors.counting()));
//        Set<Map.Entry<Integer, Long>> entries = collect2.entrySet();
//        List<Integer> list = entries.stream().filter(e -> e.getValue() > 1).map(e -> e.getKey()).toList();
//        System.out.println(list);
//
//        Map<char[], Long> collect1 = Stream.of(s.toCharArray()).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
//        System.out.println(collect1);
//
//        Map<String, Long> collect = Stream.of(s).collect(Collectors.groupingBy(e -> e, Collectors.counting()));
//        System.out.println(collect);
//        List<String> list = collect.entrySet().stream().filter(e -> e.getValue() > 1).map(e -> e.getKey()).toList();
//        System.out.println(list);
    }

}