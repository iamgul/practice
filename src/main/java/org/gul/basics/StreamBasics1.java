package org.gul.basics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBasics1 {


    public static void main(String[] args) {


        //Q1. Occurrence each character in a given String
        String sentence = "My name is Gul Mohammed And I Live in Bangalore, and my hobby is playing";
        String[] array = sentence.split("");

        //Stream<String> streamOfString = Stream.of(array); -> can also be used
        //instead of Arrays.stream(array)
        Map<String, List<String>> collect = Arrays.stream(array).collect(Collectors.groupingBy(e -> e));
        //1st Approach
        collect.forEach((k, v) -> {
            System.out.print(" " + k + "=" + v.size());
        });
        System.out.println();

        //2nd Approach
        Map<String, List<String>> map = Arrays.stream(array).collect(Collectors.groupingBy(e -> e));
        Map<String, Integer> mapOfCharsAndCount2ndApproach = map.entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey(), e -> e.getValue().size())
        );
        System.out.println("mapOfCharsAndCount2ndApproach: "+mapOfCharsAndCount2ndApproach);


        //3rd Approach //Best Approach
        Map<String, Long> mapOfCharsAndCount3rdApproach = Arrays.stream(array).collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println("mapOfCharsAndCount3rdApproach : "+mapOfCharsAndCount3rdApproach);

        //4th Approach
        IntStream chars = sentence.chars();
        Stream<Character> characterStream = chars.mapToObj(c -> (char) c);
        Map<Character, Long> mapOfCharsAndCount4thApproach = characterStream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("mapOfCharsAndCount4thApproach : "+ mapOfCharsAndCount4thApproach);

        //Approach 5 to ignore spaces and case sensitivity
                                                  //.replaceAll(" ", "");
        Map<Character, Long> mapOfCharsAndCountIgnoringCaseSensitivityAndSpace5thApproach = sentence.replace(" ", "").toLowerCase()
                .chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("mapOfCharsAndCountIgnoringCaseSensitivityAndSpace5thApproach : "+mapOfCharsAndCountIgnoringCaseSensitivityAndSpace5thApproach);

        //<<==================================================================================================================>>

        //Q2. Occurrence of each Word in a sentence
        String[] splitArrayBasedOnSpace = sentence.split(" ");
        Stream<String> stream = Arrays.stream(splitArrayBasedOnSpace);
        Map<String, Long> collect5 = stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("collect5: " + collect5);

        //Count number of occurrence of words in given String
        String inputString = "welcome to the code decode and code decode welcome you";
        String[] split = inputString.split(" ");
        Stream<String> streamOfString = Stream.of(split);
        //Here this will not preserve the order of insertion as internally it uses HasMap to store
        Map<String, Long> mapOfStringAndCountWithoutInsertionOrderMaintained = streamOfString.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println("mapOfStringAndCountWithoutInsertionOrderMaintained : " + mapOfStringAndCountWithoutInsertionOrderMaintained);

        //However we can pass supplier for Storing in LinkedHashMap to maintain insertion order
        Map<String, Long> mapOfStringAndCountWithInsertionPreserved = Stream.of(split).collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()));
        System.out.println("mapOfStringAndCountWithInsertionPreserved : " + mapOfStringAndCountWithInsertionPreserved);

        //If we want to sort based on alphabetical order
        Map<String, Long> mapOfStringAndCountSortedBasedOnAlphabeticalOrder = Stream.of(split).collect(Collectors.groupingBy(s -> s, TreeMap::new, Collectors.counting()));
        System.out.println("mapOfStringAndCountSortedBasedOnAlphabeticalOrder : " + mapOfStringAndCountSortedBasedOnAlphabeticalOrder);

        //If we want to sort on reverse order //mapOfStringAndCountSortedBasedOnReverseOrder
        Map<String, Long> mapOfStringAndCountSortedBasedOnReverseOrder = Stream.of(split).collect(
                Collectors.groupingBy(
                        s -> s,
                        //This below line will result in no suitable method found for groupingBy(...) is not applicable (cannot infer type-variable(s)
                        //This is caused by Java not being able to infer types properly when you use a lambda like () -> new TreeMap<>(Comparator.reverseOrder())
                        //This often happens when the map supplier lambda is too complex, and Java can't deduce the generic types.
                        //To fix this, Explicitly define the types
                        (Supplier<Map<String, Long>>) () -> new TreeMap<>(Comparator.reverseOrder()),
                        Collectors.counting()
                ));
        System.out.println("mapOfStringAndCountSortedBasedOnReverseOrder : " + mapOfStringAndCountSortedBasedOnReverseOrder);

        //Alternative way
        Map<String, Long> treeMap = new TreeMap<>(Comparator.reverseOrder());
        Map<String, Long> mapOfWordsAndTheirCount = Stream.of(split).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        treeMap.putAll(mapOfWordsAndTheirCount);
        System.out.println("mapOfWordsAndTheirCountSortedBasedOnReverseOrder: "+treeMap);

        //<<==================================================================================================================>>

        //Q3. First non-repeating character in String
        // [Note: We are using Linked hashMap because groupingBy internally uses HashMap we don't pass the supplier and
        // HashMap will not preserve the order in which they are processed, and we may get any character that is non-repeating
        // but not the 1st, hence we are passing supplier as LinkedHashMap explicitly.
        String repeated = "zzzzooemiaabbccdeeacm";
        Map<String, Long> mapOfRepeat = Arrays.stream(repeated.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,  Collectors.counting()));
        String FirstNonRepeatingCharacter = mapOfRepeat.entrySet().stream().filter(e -> e.getValue() < 2).map(Map.Entry::getKey).findFirst().orElse("Not Available");
        System.out.println("First non-repeating character in String :"+FirstNonRepeatingCharacter);

        //<<==================================================================================================================>>

        //Q4. Find duplicate characters and their counts.
        Map<String, Long> duplicateCharsAndTheirCounts = Arrays.stream(repeated.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        System.out.println("duplicateCharsAndTheirCounts : " + duplicateCharsAndTheirCounts);

        //Find duplicate characters and their counts maintaining insertion order
        Map<String, Long> duplicateCharsAndTheirCountsMaintainingTheirInsertionOrder = Arrays.stream(repeated.split(""))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                )).entrySet().stream().filter(e -> e.getValue() > 1)
                //.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
                //.peek(e -> System.out.print(e + " "))
                //If we want to preserve the insertion order, we have to use LinkedHashMap::new in groupBy() as well as in toMap(),
                //because both internally uses HashMap, and it will interfere the order if we don't use in any one of them.
                //However if we want to sort the output then probably we should use TreeMap::new in toMap() only and not vice-versa(i.e. not in toMap() but in groupBy())
                //because if we use in groupBy(), it will be overridden by toMap(), and hence order of sort can be changed,
                //so for sorting we need to mention TreeMap::new in toMap()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue(),
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
        System.out.println("duplicateCharsAndTheirCountsMaintainingTheirInsertionOrder: " + duplicateCharsAndTheirCountsMaintainingTheirInsertionOrder);
        //groupBy() and toMap() both these method takes Supplier map function which tells in which type of Map to collect in.
        //groupBy() takes it as 2nd Parameter and toMap() takes it as 4th parameter, but for both it has same functionality
        //to use Supplier<M> in toMap(), we need to use 4 argument method of toMap(), where we need to pass 3rd argument as Merge Function i.e. BinaryOperator
        //Which merges two values as discussed somewhere

        //Find duplicate characters and their counts by sorting in alphabetical order
        Map<String, Long> duplicateCharsAndTheirCountsAndNaturalSorting = Stream.of(repeated.split(""))
                .collect(Collectors.groupingBy(
                        s -> s,
                        Collectors.counting()
                )).entrySet().stream().filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue(),
                        (v1, v2) -> v1,
                        TreeMap::new

                ));
        System.out.println("duplicateCharsAndTheirCountsAndNaturalSorting : " + duplicateCharsAndTheirCountsAndNaturalSorting);

        //Find duplicate characters and their counts by sorting in reverse order
        Map<String, Long> duplicateCharsAndTheirCountsAndReverseSorting = new TreeMap<>(Comparator.reverseOrder());
        duplicateCharsAndTheirCountsAndReverseSorting.putAll(duplicateCharsAndTheirCountsAndNaturalSorting);
        System.out.println("duplicateCharsAndTheirCountsAndReverseSorting : " + duplicateCharsAndTheirCountsAndReverseSorting);

        //<<==================================================================================================================>>

        //Q5. Find 1st duplicate characters and their count.
        Map.Entry<String, Long> firstDuplicateCharacter = Arrays.stream(repeated.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1).findFirst().get();

        System.out.println("firstDuplicateCharacter "+firstDuplicateCharacter);

        //<<==================================================================================================================>>

        //Q6. Find nth Highest / nth Lowest number
        //1st approach
        int[] intArray = {6,1,3,5,8,2,9,7,3};
        List<Integer> collect11 = Arrays.stream(intArray).distinct().sorted().boxed().toList();
        Integer secondHighest = collect11.get(collect11.size() - 2);
        Integer thirdHighest = collect11.get(collect11.size() - 3);

        Integer secondLowest = collect11.get(1);
        Integer thirdLowest = collect11.get(2);

        System.out.println("secondHighest: "+secondHighest+" thirdHighest:"+thirdHighest+" \nsecondLowest :"+secondLowest+" thirdLowest: "+thirdLowest);

        //2nd approach
        Integer secondHighestWithSkipandLimit = Arrays.stream(intArray).boxed().distinct().sorted(Comparator.reverseOrder()).skip(1).limit(1).findFirst().get();
        System.out.println("secondHighestWithSkipAndLimit :"+secondHighestWithSkipandLimit);

        //<<==================================================================================================================>>

        //Q7. Find Longest/Shortest string from Given array of String

        String[] stringArray = {"hello", "there", "SpringBoot", "Docker", "Micros"};

        //Mistake to avoid, this will sort based on alphabetical order and find the min but not with lengtj
        //String shortest1 = Stream.of(stringArray).min(Comparator.naturalOrder()).stream().findFirst().orElseThrow();

        //1st approach
        Map<String, Integer> mapOf = Arrays.stream(stringArray).collect(Collectors.toMap(Function.identity(), String::length));
        Map.Entry<String, Integer> longestByToMapApproach = mapOf.entrySet().stream().max((e1, e2) -> e1.getValue().compareTo(e2.getValue())).get();
        Map.Entry<String, Integer> ShortestByToMapApproach = mapOf.entrySet().stream().min((e1, e2) -> e1.getValue().compareTo(e2.getValue())).get();
        System.out.println("longestByToMapApproach : " + longestByToMapApproach);
        System.out.println("ShortestByToMapApproach : " + ShortestByToMapApproach);

        //2nd Approach //Best Approach
        String longestByMaxComparatorComparingApproach = Arrays.stream(stringArray).max(Comparator.comparing(String::length)).get();
        //Stream.of(stringArray).max(Comparator.comparingInt(s->s.length()));
        String ShortestByMinComparatorComparingApproach = Arrays.stream(stringArray).min(Comparator.comparing(String::length)).get();
        System.out.println("longestByMaxComparatorComparingApproach : " + longestByMaxComparatorComparingApproach);
        System.out.println("ShortestByMinComparatorComparingApproach : " + ShortestByMinComparatorComparingApproach);

        //OR
        String longestByMaxComparatorCompareToApproach = Arrays.stream(stringArray).max((s1, s2) -> {
            Integer si = s1.length();
            Integer sii = s2.length();
            return si.compareTo(sii);
        }).get();

        String ShortestByMinComparatorCompareToApproach = Arrays.stream(stringArray).min((s1, s2) -> {
            Integer si = s1.length();
            Integer sii = s2.length();
            return si.compareTo(sii);
        }).get();
        System.out.println("longestByMaxComparatorCompareToApproach : " + longestByMaxComparatorCompareToApproach);
        System.out.println("ShortestByMinComparatorCompareToApproach : " + ShortestByMinComparatorCompareToApproach);


        //approach - A // A bad approach, we should not sort to find the max
        String longestBySortingApproach = Stream.of(stringArray).sorted((s1, s2) -> {
            Integer si = s1.length();
            Integer sii = s2.length();
            return -si.compareTo(sii);
        }).findFirst().get();
        System.out.println("longestBySortingApproach : "+longestBySortingApproach);

        //approach - B // Also Bad approach, no need of grouping by
        Map<Integer, List<String>> collect1 = Stream.of(stringArray).collect(Collectors.groupingBy(s -> s.length()));
        String longestByGroupingByApproach = collect1.entrySet().stream().sorted(
                (e1, e2) -> -e1.getKey().compareTo(e2.getKey()))
                .findFirst().get().getValue().get(0);
        System.out.println("longestByGroupingByApproach : "+ longestByGroupingByApproach);

        //approach - C // Normal approach
        int maxx = 0;
        int lowest = Integer.MAX_VALUE;
        String longest = "";
        String shortest = "";
        for (String s : stringArray) {
            int length = s.length();
            if (length > maxx) {
                maxx = length;
                longest = s;
            } else if (length < lowest) {
                lowest = length;
                shortest = s;
            }
        }
        System.out.println("LongestByTraditionApproach : "+ longest + " with size: " + maxx);
        System.out.println("ShortestByTraditionApproach : "+ shortest + " with size: " + lowest);

        //<<==================================================================================================================>>

        //Q8. Find all the elements from Array which starts with 1
        int[] intArrays = {1, 2, 31, 4, 5, 16, 7, 8, 19, 20};
        List<String> allNumbersThatStartsWith1 = Arrays.stream(intArrays).boxed().map(i -> i.toString()).filter(i -> i.startsWith("1")).toList();
        System.out.println("allNumbersThatStartsWith1 " + allNumbersThatStartsWith1);

        //<<==================================================================================================================>>

        //TODO:
        //Q9. Missing number in array using stream
        int[] missingArray = {1,2,3,4,5,6,8,9};
        for (int i = 0; i < missingArray.length; i++) {
            int  currentNum = i+1;
            if(missingArray[i]!=currentNum){
                System.out.println("Missing Number is "+ currentNum);
                break;
            }
        }

        //<<==================================================================================================================>>
        //Q10. Remove adjacent duplicates of a string using Stream
        String removeAdjacent = "abbcddefgggghijkabcdd";

        char[] charArray = removeAdjacent.toCharArray();
        List<Character> charsList = new ArrayList<>();
        char initialPointer = charArray[0];
        charsList.add(initialPointer);
        for (int i = 0; i < charArray.length; i++) {
            if(i+1<charArray.length && charArray[i+1]!=initialPointer){
                charsList.add(charArray[i+1]);
                initialPointer =charArray[i+1];
            }
        }
        System.out.println(charsList);

        //<<==================================================================================================================>>
        //Q11. Convert adjacent Numbers into respective characters
        //[a b]
        String AdJacentNumToChar = "ab3c4d4d3sdfe5";
        char[] charArrayAd = AdJacentNumToChar.toCharArray();
        List<Character> charArrayAdjacent = new ArrayList<>();
        charArrayAdjacent.add(charArrayAd[0]);
        for (int i = 1; i < charArrayAd.length; i++) {

            if (Character.isDigit(charArrayAd[i])) {
                int numericValue = Character.getNumericValue(charArrayAd[i]);
                Character c = charArrayAd[i - 1];
                for (int j = 0; j < numericValue-1; j++) {
                    charArrayAdjacent.add(c);
                }
            }else{
                charArrayAdjacent.add(charArrayAd[i]);
            }
        }
        System.out.println(charArrayAdjacent);

        //<<==================================================================================================================>>

        //Q12. Check if String is Anagram using stream
        String s1 = "Silent";
        String s2 = "Listen";
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        String collect9 = Arrays.stream(s1.split("")).map(String::toLowerCase).sorted().collect(Collectors.joining());
        String collect10 = Arrays.stream(s2.split("")).map(String::toLowerCase).sorted().collect(Collectors.joining());
        //System.out.println("collect10: "+collect10+"\ncollect9: "+collect9);

        //<<==================================================================================================================>>
        //Q13. nth Highest salary and the employee

        // Learning: Here we cant do sorted directly on salary and get the nth elements because there are duplicates salary but entry as a whole is unique
        // hence we can't do unique as well so, first we need to group by salary so that all the duplicates salary comes under a one roof
        // and for that we can have different values and then we can sort it as duplicates values will be treated as only one
        //

        List<Employee> employees = createEmployeeObject();

        //Approach 1 - Making the employee as Map
        Map<String, Integer> collect13 = employees.stream().collect(Collectors.toMap(e -> e.getName(), e -> e.getSalary()));
        Map<Integer, List<String>> collect14 = collect13.entrySet().stream().collect(Collectors.groupingBy(e -> e.getValue(), Collectors.mapping(e -> e.getKey(), Collectors.toList())));
        List<Map.Entry<Integer, List<String>>> list = collect14.entrySet().stream()
                .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey())).toList();
        System.out.println(list);

        //Approach 2
        Map<Integer, List<String>> collect15 = employees.stream().collect(Collectors.groupingBy(e -> e.getSalary(), Collectors.mapping(e -> e.getName(), Collectors.toList())));
        List<Map.Entry<Integer, List<String>>> list1 = collect15.entrySet().stream()
                .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey())).toList();
        System.out.println(list1);
        Map.Entry<Integer, List<String>> SecondHighest = list.get(list.size() - 2);

        //<<==================================================================================================================>>

        //Q14. Average of salary
        //1st Approach
        List<Employee> employeeObject = createEmployeeObject();
        Double average = employeeObject.stream().collect(Collectors.averagingInt(s -> s.getSalary()));
        System.out.println("Average Salary using collectors:"+average);

        //2nd Approach
        OptionalDouble average1 = employeeObject.stream().mapToInt(e -> e.getSalary()).average();
        System.out.println("Average Salary using mapToInt:"+average1.getAsDouble());

        //<<==================================================================================================================>>





    }

    private static List<Employee> createEmployeeObject() {
        List<Employee> employees = new ArrayList<>();
        Employee e = new Employee("Gul",10000);
        Employee e1 = new Employee("Gul2",100001);
        Employee e2 = new Employee("Gul3",100002);
        Employee e3 = new Employee("Gul4",100004);
        Employee e4 = new Employee("Gul5",10000);
        Employee e5 = new Employee("Gul6",10000);
        Employee e6 = new Employee("Gul7",100001);
        Employee e7 = new Employee("Gul8",100002);
        Employee e8 = new Employee("Gul9",100004);
        Employee e9 = new Employee("Gul10",100003);
        Employee e10 = new Employee("Gul11",10000);
        employees.add(e);
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);
        employees.add(e6);
        employees.add(e7);
        employees.add(e8);
        employees.add(e9);
        employees.add(e10);
        return employees;
    }






}

@Getter
@Setter
@ToString
class Employee {
    private String name;
    private Integer salary;

    public Employee(String name, Integer salary) {
        this.name = name;
        this.salary = salary;
    }
}


