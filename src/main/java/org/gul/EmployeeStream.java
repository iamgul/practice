package org.gul;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeStream {

    public static void main(String[] args) {

/*        Employee class - empId, employeeName, salary, dept.
                Create objects of employee and add to a list.
                Using streams, find the sum of salaries in each dept.*/

        Employee e1 = new Employee(1,"Gul1", 1200.0, "E1");
        Employee e2 = new Employee(2,"Gul2", 1300.0, "E2");
        Employee e3 = new Employee(3,"Gul3", 1400.0, "E3");
        Employee e4 = new Employee(4,"Gul1", 1200.0, "E1");
        Employee e5 = new Employee(5,"Gul2", 1300.0, "E2");
        Employee e6 = new Employee(6,"Gul3", 1400.0, "E3");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);
        employeeList.add(e5);
        employeeList.add(e6);

        //Map<Department, List<Employee>>
        Map<String, List<Employee>> collect1 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDept));

        Map<String,Double> mapOfDeptAndSal =  new HashMap<>();
        collect1.forEach((k,v)->{
            Double sumOfSal = v.stream().map(Employee::getSal).reduce(0.0, Double::sum);
            mapOfDeptAndSal.put(k,sumOfSal);
        });
        System.out.println(mapOfDeptAndSal);



        Map<String, Double> collect = employeeList.stream().collect(
                Collectors.groupingBy(
                        employee -> employee.getDept(),
                        Collectors.summingDouble(e -> e.getSal())
                ));
        System.out.println(collect);


        List<Employee> collect2 = employeeList.stream().collect(Collectors.toList());

        ArrayList<Employee> collect3 = employeeList.stream().collect(
                () -> new ArrayList<Employee>(),
                (a1, a2) -> {
                    a1.add(a2);
                },
                (b1, b2) -> {
                    b1.addAll(b2);
                });

        ArrayList<Employee> collect4 = employeeList.stream().collect(
                ArrayList::new,
                ArrayList::add,
                ArrayList::addAll);

        List<Employee> collect5 = employeeList.stream().collect(
                Collector.of(
                        () -> new ArrayList<Employee>(),
                        List::add,
                        (l1, l2) -> {
                            l1.addAll(l2);
                            return l2;
                        },
                        (l) -> l.subList(1, 3)
                ));


    }


}

class Employee{
    private int id;
    private String name;
    private double sal;
    private String dept;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Employee(int id, String name, double sal, String dept) {
        this.id = id;
        this.name = name;
        this.sal = sal;
        this.dept = dept;
    }
}


