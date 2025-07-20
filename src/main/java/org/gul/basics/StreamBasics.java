package org.gul.basics;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBasics {

    public static void main(String[] args) {


        //Reduction using reduce method()
        /*{
            //1
            int i0 = IntStream.range(1, 4).reduce((a, b) -> a + b).orElseThrow();
            System.out.println(i0);

            //Replacing lambda expression with  our own method present in Integer class as it takes IntBinaryOperator which
            // mean it takes 2 int input parameter and results int as output as well
            int i1 = IntStream.range(1, 4).reduce(Integer::sum).orElseThrow();


            //2
            int reduce = IntStream.range(1, 4).reduce(10, Integer::sum);
            System.out.println(reduce);

            //3
            Integer i2 = Stream.iterate(1, i -> i + 1).limit(3).reduce(10, Integer::sum);
            System.out.println(i2);


            List<Integer> list = Stream.iterate(1, i -> i + 1).limit(3).toList();
            System.out.println(list);

            //Here Stream.iterate(...).limit(3) gives [1, 2, 3]
            //BUT, .parallel() on a lazy stream (like iterate) behaves unpredictably
            //The identity (10) is applied once per subtask, and iterate + parallel causes the stream to split into many more subtasks than expected.
            //Hence, avoid using iterate and parallel as it produces different output of 86 instead of 36
            Integer i3 = Stream.iterate(1, i -> i + 1).limit(3).parallel().reduce(10, (a, b) -> a + b,
                    (a, b) -> {
                        System.out.println("Combiner called");
                        return a + b;
                    });
            System.out.println(i3);

            //This is a correct way. Note that combiner is only executed if its parallel stream. without parallel there is no need of combiner function
            //.stream().parallel() -> .parallelStream()
            Integer i4 = Arrays.asList(1, 2, 3).stream().parallel().reduce(10, (a, b) -> a + b,
                    (a, b) -> {
                        System.out.println("Combiner called");
                        return a + b;
                    });
            System.out.println(i4);

            //Above can be minimised as below:
            Integer i5 = Arrays.asList(1, 2, 3).parallelStream().reduce(10, Integer::sum, (a, b) -> {
                System.out.println("Combiner called");
                return a + b;
            });
            System.out.println(i5);

            //Above can be further minimised as below:
            Integer i6 = Stream.of(1, 2, 3).parallel().reduce(10, Integer::sum, StreamAPI::myOwnMethod);
            System.out.println(i6);
        }*/

        //Reduction using collect method()
        {
            Stream.of(1, 2, 3).map(e -> e * 5).collect(Collectors.toList());

            List<Employee> employeeList = getEmployees(10);
            String collectingNames = employeeList.stream().map(Employee::getName).collect(Collectors.joining(",","[","]"));
            System.out.println(collectingNames);

            Integer collect = employeeList.stream().collect(Collectors.summingInt(e -> e.getSalary()));
            System.out.println(collect);

            Double collect1 = employeeList.stream().collect(Collectors.averagingInt(Employee::getSalary));
            System.out.println(collect1);

            Map<Department, List<Employee>> collect2 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
            System.out.println(collect2);

            Map<Boolean, List<Employee>> hr = employeeList.stream().collect(Collectors.partitioningBy(e -> e.getDepartment().getName().equals("HR")));
            System.out.println(hr);


        }















    }

    private static List<Employee> getEmployees(int range) {
        Random r = new Random();

        List<Employee> employeeList = IntStream.range(0, range).mapToObj(i -> {
            Employee e = new Employee();
            e.setName("Emp - " + i);
            e.setSalary(10000 + r.nextInt(2000));

            // Randomly get the department and assign it to employee add also add employee to department
            Department department = getDepartmentList().get(r.nextInt(getDepartmentList().size()));
            e.setDepartment(department);

            //department.getEmployees().add(e);
            return e;
        }).toList();
        return employeeList;
    }

    private static List<Department> getDepartmentList() {
        List<Department> departmentlist = getDepartmentNames().map(name -> {
            Department dep = new Department();
            dep.setName(name);
            dep.setEmployees(new ArrayList<>());
            return dep;
        }).toList();
        return departmentlist;
    }

    private static Stream<String> getDepartmentNames() {
        Stream<String> departmentNames = Stream.of("Engineering", "HR", "Sales", "Marketing");
        return departmentNames;
    }

    private static int myOwnMethod(Integer a, Integer b) {
        System.out.println("Combiner called");
        return a + b;
    }


    @Data
    static class Employee {
        private String name;
        private Integer salary;

        @ToString.Exclude
        private Department department;

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }

    @Data
    static class Department {
        private String name;
        private List<Employee> employees;


    }


}