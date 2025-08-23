package org.gul.companies.wissen;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Practice {

    public static void main(String[] args) {


        //1. Given a 2D array, flatten it into a 1D array.
        flatten();
    }

    private static void flatten() {
        int[][] array2D = {
                {1, 2},
                {3, 4, 5},
                {6, 7, 8, 9}
        };

        int totalSize = 0 ;
        for (int[] vals : array2D) {
            totalSize += vals.length;
        }
        System.out.println("totalSize"+totalSize);



        int[] array = new int[totalSize];
        int index = 0;
        for (int[] rows : array2D) {
            for (int val : rows) {
                array[index] = val;
                index ++;
            }
        }
        System.out.println("array: "+ array);



        //Stream.of(array2D).flatMap(e-> Arrays.stream(e)).toList(); //Note: flatMap doesn't works
        Stream<int[]> stream = Arrays.stream(array2D);
        Stream<int[]> array2D1 = Stream.of(array2D);
        List<Integer> list1 = stream.flatMapToInt(e -> Arrays.stream(e)).boxed().toList();


        List.of(1, 2);
        List.of(3, 4, 5);
        List.of(6, 7, 8, 9);
        List<List<Integer>> lists = List.of(List.of(1, 2), List.of(3, 4, 5), List.of(6, 7, 8, 9));
        List<Integer> list = lists.stream().flatMap(e -> e.stream()).toList();
        System.out.println(list);









        //System.arraycopy();


    }
}
