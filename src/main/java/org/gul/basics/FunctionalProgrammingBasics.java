package org.gul.basics;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class FunctionalProgrammingBasics {

    public static void main(String[] args) {

    //Functional Interface

        //Predicate
        Predicate<Integer> isSelfMultiplication25 = i -> i * i == 25;
        isSelfMultiplication25.test(5);
        //Function
        Function<Integer, Integer> returnSelfMul = i -> i * i;
        returnSelfMul.apply(5);
        //Consumer
        Consumer<Integer> consumeSelfAndMulAndDoNotReturn = i -> System.out.println(i * i);
        consumeSelfAndMulAndDoNotReturn.accept(5);
        //Supply
        Supplier<Integer> returnSomeValueWithoutTakingAnyInput = () -> 5 * 5;
        returnSomeValueWithoutTakingAnyInput.get();


        //1. BiPredicate
        BiPredicate<Integer, Integer> isSumOfTwoGreaterThanGivenNUmber = (i, j) -> i + j >= 5;
        boolean test = isSumOfTwoGreaterThanGivenNUmber.test(3, 4);
        System.out.println(test);

        //2. BiFunction //If we wanted to return the result and do some operation on returned result then we go for function
        BiFunction<Integer, Integer, Integer> returnSumOfTwo = (i, j) -> i * j;
        Integer apply = returnSumOfTwo.apply(5, 6);
        System.out.println(apply);

        //3.BiConsumer //If we don't need to return the result  then we go for consumer
        BiConsumer<Integer, Integer> consumeToPerform = (i, j) -> System.out.println(i + j);
        consumeToPerform.accept(5, 6);

    //Streams
        //Creating List of Integers, Longs, Doubles
        List<Integer> integerList = IntStream.rangeClosed(1, 10).boxed().toList();
        List<Long> longList = LongStream.range(1, 11).boxed().toList();

        //Make all the Long value as Double just by appending .0 in Long value
        List<Double> doubleList = LongStream.range(1, 11).asDoubleStream().boxed().toList();

        //DoubleStream doesn't have range() & rangeClosed() method but it has generate() and iterate() same as Stream
        //Make sure to use limit while using these methods as they will generate infinite stream
        List<Double> doubleListUsingGenerate = DoubleStream.generate(()-> 1.0).limit(10).boxed().toList();
        List<Double> doubleListUsingIterate = DoubleStream.iterate(1, d -> d + d).limit(10).boxed().toList();

        //For Double to generate, Random class has provided the methods
        //doubles(long streamSize, double randomNumberOrigin, double randomNumberBound)
        List<Double> doubleListUsingRandom = new Random().doubles(3,1,10).boxed().toList();

        System.out.println("integerList: " +integerList + "\n" + "longList: " +longList + "\n" + "doubleList: " +doubleList + "\n"
                + "doubleListUsingRandom: " +doubleListUsingRandom + "\n"  + "doubleListUsingGenerate: " +doubleListUsingGenerate+ "\n"
                + "doubleListUsingIterate: " +doubleListUsingIterate);

        //<<================================================================================>>

        //Configuring of stream -> filter, map

        //filter
        Stream<Integer> integerStream = integerList.stream().filter(i -> i % 2 == 0);
        integerStream.forEach(i -> System.out.print(i+" "));
        System.out.println();
        //or
        integerList.stream().filter(i -> i % 2 == 0).forEach(i-> System.out.print(i+" "));
        System.out.println();

        //map
        integerList.stream().map(i->i*2).forEach(i-> System.out.print(i+" "));
        System.out.println();


        //Processing of Streams -> collect(), count(), sorted(), min(), max(), forEach(), of()

        //--------//
        //Collect//
        //-------//
        List<Integer> integerListGreaterThan50 = integerList.stream().filter(i -> i > 50).collect(Collectors.toList());

        // Accumulate names into a List-->
        // List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());

        // Accumulate names into a TreeSet-->
        // Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));

        // Convert elements to strings and concatenate them, separated by commas-->
        //List<String> things
        // String joined = things.stream().map(Object::toString).collect(Collectors.joining(", "));

        // Compute sum of salaries of employees-->
        // int total = employees.stream().collect(Collectors.summingInt(Employee::getSalary));

        // Group employees by department-->
        // Map<Department, List<Employee>> byDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        // Compute sum of salaries by department-->
        // Map<Department, Integer> totalByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(Employee::getSalary)));

        // Partition students into passing and failing-->
        // Map<Boolean, List<Student>> passingFailing = students.stream().collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));
        Map<Boolean, List<Integer>> evenOddMap = integerList.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println("evenOddMap: "+evenOddMap);
        List<Integer> even = evenOddMap.get(true);
        List<Integer> odd = evenOddMap.get(false);
        System.out.println("Even::: " + even);
        System.out.println("Odd::: " + odd);

        //------//
        //Count//
        //-----//
        long count = integerList.stream().filter(i -> i % 10 == 0).count();
        long directCount = integerList.stream().count();//can be used directly as well on stream or after configuring

        //------//
        //Sort//
        //-----//
        List<Integer> naturalSorted = integerList.stream().sorted().toList(); //can be used directly as well on stream or after configuring
        List<Integer> naturalSortedUsingComparator = integerList.stream().sorted((i1,i2)->i1.compareTo(i2)).toList();
        List<Integer> descendingSorted = integerList.stream().sorted((i1, i2) -> i2.compareTo(i1)).toList();
        System.out.println("naturalSorted: " +naturalSorted + "\n" + "naturalSortedUsingComparator: " +naturalSortedUsingComparator
                + "\n" + "descendingSorted: " +descendingSorted + "\n");


        //--------//
        //Min,Max//
        //-------//
        Integer  min = integerList.stream().min(((i1, i2) -> i1.compareTo(i2))).get();
        System.out.println("Min-->"+min);

        Integer  max = integerList.stream().max(((i1, i2) -> i1.compareTo(i2))).get();
        System.out.println("max-->"+max);

        //--------//
        //toArray//
        //-------//
        Object[] array = integerList.stream().toArray();


        //----//
        //of//
        //---//
        Stream<Integer> intList = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<String> stringList = Stream.of("Hello", "Hi", "How");

        //--------------------------------------------------------------------------------------------------------------//

        //----//
        //peek//
        //---//
        List<Integer> list = integerList.stream().sorted().filter(e -> e > 10 && e < 40).sorted(Comparator.reverseOrder()).peek(System.out::print).toList();


        //----//
        //reduce//
        //---//
        integerList.stream().filter(e -> e > 10 && e < 40).reduce((a, b) -> a + b).ifPresent(System.out::println);
        integerList.stream().filter(e -> e > 10 && e < 40).reduce((a, b) -> Math.max(a,b)).ifPresent(System.out::println);
        integerList.stream().filter(e -> e > 10 && e < 40).reduce((a, b) -> Math.min(a,b)).ifPresent(System.out::println);
        integerList.stream().filter(e -> e > 10 && e < 40).reduce(Integer::sum).ifPresent(System.out::println);
        integerList.stream().filter(e -> e > 10 && e < 40).reduce(Integer::max).ifPresent(System.out::println);
        integerList.stream().filter(e -> e > 10 && e < 40).reduce(Integer::min).ifPresent(System.out::println);


        //----------------------------------Short-circuit-operators------------------------------------------------------------//

        //----//
        //limit//
        //---//
        integerList.stream().filter(e -> e > 10 && e < 40).limit(5).toList();

        //----//
        //findFirst//
        //---// Always returns the same first element even in parallel stream
        Integer i = integerList.stream().filter(e -> e > 5 && e < 40).findFirst().get();
        integerList.stream().filter(e -> e > 5 && e < 40).findFirst().ifPresent(System.out::println);
        integerList.stream().parallel().filter(e -> e > 5 && e < 40).findFirst().ifPresent(System.out::println);

        //----//
        //findAny//
        //---// can return any element in parallel stream
        integerList.stream().filter(e -> e > 5 && e < 40).findAny().ifPresent(System.out::println);
        integerList.stream().parallel().filter(e -> e > 5 && e < 40).findAny().ifPresent(System.out::println);

        //----//
        //anyMatch//
        //---// same as java || (OR operations)
        integerList.stream().filter(e -> e > 5 && e < 40).findAny().isPresent(); // This both can be replaced
        integerList.stream().filter(e -> e > 5 && e < 40).findFirst().isPresent(); // with anyMatch,  i.e. if there is filter and findFirst then anymatch can be used
        boolean anyMatch = integerList.stream().anyMatch(e -> e > 5 && e < 40);

        //----//
        //allMatch//
        //---// same as java && (AND operations) -> All should Match, if any doesn't  match it short circuits
        boolean b = integerList.stream().filter(e -> e > 9 && e < 40).allMatch(e -> e > 10);

        //----//
        //noneMatch//
        //---//
        boolean b1 = integerList.stream().filter(e -> e > 9 && e < 40).noneMatch(e -> e > 10);



        //Parallel Stream






        // More Details -> Collectors Methods, Comparators Methods
    }

}
