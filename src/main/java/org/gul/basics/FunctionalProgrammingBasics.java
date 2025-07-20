package org.gul.basics;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        List<Integer> integerList = IntStream.rangeClosed(1, 100).boxed().toList();

        //Configuring of stream -> filter, map

        //filter
        Stream<Integer> integerStream = integerList.stream().filter(i -> i % 2 == 0);
        integerStream.forEach(i -> System.out.println(i));
        //or
        integerList.stream().filter(i -> i % 2 == 0).forEach(i-> System.out.println(i));

        //map
        integerList.stream().map(i->i*2).forEach(i-> System.out.println(i));


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
        // String joined = things.stream().map(Object::toString).collect(Collectors.joining(", "));

        // Compute sum of salaries of employees-->
        // int total = employees.stream().collect(Collectors.summingInt(Employee::getSalary));

        // Group employees by department-->
        // Map<Department, List<Employee>> byDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        // Compute sum of salaries by department-->
        // Map<Department, Integer> totalByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(Employee::getSalary)));

        // Partition students into passing and failing-->
        // Map<Boolean, List<Student>> passingFailing = students.stream().collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));
        Map<Boolean, List<Integer>> collect = integerList.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
        List<Integer> even = collect.get(true);
        List<Integer> odd = collect.get(false);
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
        Stream<Integer> naturalSorted = integerList.stream().sorted(); //can be used directly as well on stream or after configuring
        Stream<Integer> naturalSortedUsingComparator = integerList.stream().sorted((i1,i2)->i1.compareTo(i2));
        Stream<Integer> descendingSorted = integerList.stream().sorted((i1, i2) -> i2.compareTo(i1));

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
        //List<Integer> list = integerList.stream().sorted().filter(e -> e > 10 && e < 40).sorted(Comparator.reverseOrder()).peek(System.out::println).toList();


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
        Integer i = integerList.stream().filter(e -> e > 10 && e < 40).findFirst().get();
        integerList.stream().parallel().filter(e -> e > 10 && e < 40).findFirst().ifPresent(System.out::println);

        //----//
        //findAny//
        //---// can return the anyt element in parallel stream
        integerList.stream().parallel().filter(e -> e > 10 && e < 40).findAny().ifPresent(System.out::println);

        //----//
        //anyMatch//
        //---// same as java || (OR operations)
        integerList.stream().filter(e -> e > 10 && e < 40).findAny().isPresent(); // This both can be replaced
        integerList.stream().filter(e -> e > 10 && e < 40).findFirst().isPresent(); // with anyMatch,  i.e. if there is filter and findFirst then anymatch can be used
        boolean anyMatch = integerList.stream().anyMatch(e -> e > 10 && e < 40);

        //----//
        //allMatch//
        //---// same as java && (AND operations) -> All should Match, if any doesn't  match it short circuits
        boolean b = integerList.stream().filter(e -> e > 9 && e < 40).allMatch(e -> e > 10);

        //----//
        //noneMatch//
        //---//
        boolean b1 = integerList.stream().filter(e -> e > 9 && e < 40).noneMatch(e -> e > 10);


        //Count number od occurrence of words in given String
        String inputString = "welcome to the code decode and code decode welcome you";
        Stream.of(inputString).collect(Collectors.groupingBy(s-> s)).entrySet().forEach(e-> {
            System.out.println(e.getKey());
            System.out.println(e.getValue());
        });


        //Parallel Stream






        // More Details -> Collectors Methods, Comparators Methods
    }

}
