package org.gul.basics;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBasics1 {


    public static void main(String[] args) {


        //Q1. Occurrence each character in a given String

        String sentence = "My name is Gul Mohammed And I Live in Bangalore, and my hobby is playing";
        String[] array = sentence.split("");


        Map<String, List<String>> collect = Arrays.stream(array).collect(Collectors.groupingBy(e -> e));
        //1st Approach
        collect.forEach((k, v) -> {
            System.out.print(" " + k + "=" + v.size());
        });

        //2nd Approach
        Map<String, List<String>> map = Arrays.stream(array).collect(Collectors.groupingBy(e -> e));
        Map<String, Integer> collect1 = map.entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey(), e -> e.getValue().size())
        );


        //3rd Approach //Best Approach
        Map<String, Long> collect2 = Arrays.stream(array).collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        //4th Approach
        IntStream chars = sentence.chars();
        Stream<Character> characterStream = chars.mapToObj(c -> (char) c);
        Map<Character, Long> collect3 = characterStream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //Approach 5 to ignore spaces and case sensitivity
                                                  //.replaceAll(" ", "");
        Map<Character, Long> collect4 = sentence.replace(" ", "").toLowerCase()
                .chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("collect1: " + collect1);
        System.out.println("collect2: " + collect2);
        System.out.println("collect3: " + collect3);
        System.out.println("collect4: " + collect4);
        //<<==================================================================================================================>>

        //Q2. Occurrence of each Word in a sentence
        String[] splitArrayBasedOnSpace = sentence.split(" ");
        Stream<String> stream = Arrays.stream(splitArrayBasedOnSpace);
        Map<String, Long> collect5 = stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("collect5: " + collect5);

        //<<==================================================================================================================>>

        //Q3. First non-repeating character in String
        // [Note: We are using Linked hashMap because groupingBy internally uses HashMap we don't pass the supplier and
        // HashMap will not preserve the order in which they are processed, and we may get any character that is non-repeating
        // but not the 1st, hence we are passing supplier as LinkedHashMap explicitly.
        String repeated = "ooemiaabbccdeeacm";
        Map<String, Long> mapOfRepeat = Arrays.stream(repeated.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,  Collectors.counting()));
        String collect6 = mapOfRepeat.entrySet().stream().filter(e -> e.getValue() < 2).map(Map.Entry::getKey).findFirst().orElse("Not Available");
        System.out.println("collect6 :"+collect6);

        //<<==================================================================================================================>>

        //Q4. Find duplicate characters and their counts.
        Map<String, Long> collect7 = Arrays.stream(repeated.split("")).collect(Collectors.groupingBy(Function.identity(),  Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1)
                //.peek(e -> System.out.print(e + " "))
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        System.out.println("collect7: " + collect7);

        //<<==================================================================================================================>>

        //Q5. Find 1st duplicate characters and their count.

        Map.Entry<String, Long> collect8 = Arrays.stream(repeated.split("")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1).findFirst().get();

        System.out.println("collect8 "+collect8);

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
        Integer secondHighest1 = Arrays.stream(intArray).boxed().distinct().sorted(Comparator.reverseOrder()).skip(1).limit(1).findFirst().get();
        System.out.println("secondHighest1 :"+secondHighest1);


        //<<==================================================================================================================>>

        //Q7. Longest String/Shortest from Given array of String
        //1st approach
        String[] stringArray = {"hello", "there", "SpringBoot", "Docker", "Micros"};

        Map<String, Integer> MapOf = Arrays.stream(stringArray).collect(Collectors.toMap(Function.identity(), String::length));
        Map.Entry<String, Integer> longestString = MapOf.entrySet().stream().max((e1, e2) -> e1.getValue().compareTo(e2.getValue())).get();
        Map.Entry<String, Integer> shortestString = MapOf.entrySet().stream().min((e1, e2) -> e1.getValue().compareTo(e2.getValue())).get();

        //2nd Approach
        String max = Arrays.stream(stringArray).max(Comparator.comparing(String::length)).get();
        String min = Arrays.stream(stringArray).min(Comparator.comparing(String::length)).get();


        System.out.println("collect14 - longestString & shortestString  "+ longestString+" "+shortestString);
        System.out.println("collect14 - max & min  "+ max+" "+min);

        //<<==================================================================================================================>>

        //Q8. Find all the elements from Array which starts with 1
        int[] intArrays = {1, 2, 31, 4, 5, 16, 7, 8, 19, 20};
        List<String> collect133 = Arrays.stream(intArrays).boxed().map(i -> i.toString()).filter(i -> i.startsWith("1")).toList();
        System.out.println("collect133 "+collect133);

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


