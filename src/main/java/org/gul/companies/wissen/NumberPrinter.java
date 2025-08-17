package org.gul.companies.wissen;


import java.text.Collator;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.max;

/*print below patter
        5
        5 4
        5 4 3
        5 4 3 2
        5 4 3 2 1*/
public class NumberPrinter {
    public static void main(String[] args) throws InterruptedException {
        //print();
        ObjectLevelLocking o1 = new ObjectLevelLocking();
        ObjectLevelLocking o2 = new ObjectLevelLocking();

        ClassLevelLocking c1 = new ClassLevelLocking();
        ClassLevelLocking c2 = new ClassLevelLocking();

//        Runnable r1 = () -> o1.m1();
//        Runnable r2 = () -> o2.m1();
//        Thread t1 = new Thread(r1);
//        Thread t2 = new Thread(r1);
//        t1.start();
//        t2.start();

        //Since same object it accessing the lock it will wait
//        new Thread(() -> o1.m1()).start();
//        new Thread(() -> o1.m1()).start();

        // Since different objects are accessing the locks, it will be executed in parallel
//        new Thread(() -> o1.m1()).start();
//        new Thread(() -> o2.m1()).start();

//        new Thread(() -> c1.m1()).start();
//        new Thread(() -> c2.m1()).start();


//        Parent p = new Child();
//        //p.run();
//
//
//        List<Integer> list = Arrays.asList(1,2,4,5,6,7,8,9);
//        //(1,,,5,,7,,9);
//        //1+25+49+81
//        Integer reduce = list.stream().filter(e -> e % 2 != 0).map(e -> e * e).reduce(0, Integer::sum);
//        //System.out.println("reduce: "+reduce);
//
//        String longestEvenString = "HEllo my name is Gul MOhammed I am studying12 in BE, Computer Science Education";
//        String[] split = longestEvenString.split(" ");
//        String longestEvenString1 = Stream.of(split).filter(s -> s.length() % 2 == 0).max((s1, s2) -> {
//            Integer length1 = s1.length();
//            Integer length2 = s2.length();
//            return length1.compareTo(length2);
//        }).get();
//
//       // System.out.println("longestEvenString1: "+longestEvenString1);
//
//        String paranthesis = "{{[}{(){[()]}()]}}";
//        String[] paranthesiss = paranthesis.split("");
//        Stack<String> stack = new Stack<>();
//        for(String s : paranthesiss){
//            if(s.equals("{")||s.equals("[")||s.equals("(")){
//                stack.push(s);
//            }else {
//                String pop = stack.pop();
//            }
//        }
//        System.out.println("Stack is Empty: "+stack.isEmpty());


        String transform = "Hello World";
        Stream<String> split = Stream.of(transform.split(" "));
        split.forEach(s->{
            String unique = Stream.of(s.split(""))
                    .distinct().collect(Collectors.joining());

            String reversed = Stream.of(unique).map(StringBuilder::new)
                    .map(StringBuilder::reverse)
                    .map(StringBuilder::toString)
                    .collect(Collectors.joining());

            //System.out.print(reversed +" ");

           // split array int smaller arrays based on given size. int[] original =
            // {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; int splitSize = 3; /* expected Output [0, 1, 2] [3, 4, 5] [6, 7, 8] [9] *
            int[] original = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            int splitSize =3;
            int originalLength = original.length;
            int countInEachArray = originalLength / splitSize;
            int counter = 0;
            int remainingCountInLastArray = originalLength % splitSize;
            for (int i = 0; i < originalLength;) {
                counter++;
                int tempArray[] = new int[5];
                if(counter==countInEachArray){
                    tempArray = new int[remainingCountInLastArray];
                }else{
                    tempArray = new int[countInEachArray];
                }

                for (int j = 0; j < tempArray.length; j++) {
                    tempArray[j]=original[i];
                    i++;
                }
                System.out.println(Arrays.toString(tempArray));
            }


        });



        ShootingNTarget shootingNTarget = new ShootingNTarget();
        //int pointsObtained = shootingNTarget.pointsObtainedByShootingNTarget(2154);
        //System.out.println("pointsObtained: "+pointsObtained);
       // int L = Integer.highestOneBit(N);


    }

    private static void print() {
        for (int i = 5; i > 0; i--) {
            for (int j = 5; j >= i; j--) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }


    public int maxProfit(int[] prices) {

        int buy = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int sell = prices[i];
            if (sell > buy) {
                profit = Math.max(profit, sell - buy);
            } else {
                buy = sell;
            }
        }
        return profit;
    }

    //     int minNum = Integer.MAX_VALUE; // 1
    //     int minIndex = 0; // [1]
    //     int length = prices.length;
    //     int maxProfit = 0;

    //     for (int i = 0; i < length; i++) {
    //         if (prices[i] < minNum) {
    //             minNum = prices[i];
    //             minIndex = i;
    //         }
    //     }
    //     System.out.println("minNum & minIndx: "+minNum+" : "+minIndex);

    //     int maxNum = Integer.MIN_VALUE;
    //     for (int i = minIndex+1; i < length ; i++) {
    //         if (prices[i] > maxNum) {
    //             maxNum = prices[i];
    //         }
    //     }

    //     System.out.println("maxNum: "+maxNum);
    //     if(maxNum==Integer.MIN_VALUE){
    //         return 0;
    //     }

    //     maxProfit = maxNum - minNum;
    //     return maxProfit;

    // }


}

class ObjectLevelLocking {
    synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " : sleeping for 5 sec");
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " : Wake Up");

    }
}

class ClassLevelLocking {
    void m1() {
        System.out.println("Class Level");
        synchronized (ClassLevelLocking.class) {
        //synchronized (this) { // this again it will be object level locking
            System.out.println(Thread.currentThread().getName() + " : sleeping for 5 sec");
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " : Wake Up");
        }

    }
}

class  Parent {
    public void walk(){
        System.out.println("Parent Walk");
    }
    public void run(){
        System.out.println("Parent run");
        walk();
    }
}

class Child extends Parent{
    public void walk(){
        System.out.println("Child walks");
        super.walk();
    }

    public void run() {
        System.out.println("Child run");
        super.run();
    }
}

class ShootingNTarget {
    public int pointsObtainedByShootingNTarget(int n){

        int pointCollected = 0;
        int[] arr = new int[n];
        List<Integer> list = new ArrayList<>();



        for (int i = 0; i < n; i++) {
            arr[i]=i+1;
            list.add(i+1);
        }
        System.out.println(arr);
        System.out.println(list);

        List<Integer> mainList = new ArrayList<>();

        while (list.size()!=1){
            List<Integer> tempArray = new ArrayList<>();

            for (int i = 0; i < list.size(); i=i+2) {
                //pointCollected = pointCollected+ list.get(i);
                //list.remove(i);
                tempArray.add(list.get(i));
            }
            pointCollected =  pointCollected + tempArray.stream().reduce(0,Integer::sum);
            System.out.println("List: "+list);
            System.out.println("tempArray: "+tempArray);
            list.removeAll(tempArray);
            System.out.println("After removing tempArray From List, actual list: "+list);
            list = list.reversed();
            //Collections.reverse(list);
            System.out.println("Reversed original list: "+list);
        }


        return pointCollected;
    }
}
