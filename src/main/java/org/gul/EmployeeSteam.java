package org.gul;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeSteam {

    public static void main(String[] args) {

/*        Employee class - empId, employeeName, salary, dept.
                Create objects of employee and add to a list.
                Using streams, find the sum of salaries in each dept.*/

        Employee e1 = new Employee(1,"Gul1", 1200.0, "E1");
        Employee e2 = new Employee(2,"Gul2", 1300.0, "E2");
        Employee e3 = new Employee(3,"Gul3", 1400.0, "E3");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);


        Map<String, Double> collect = employeeList.stream().collect(Collectors.groupingBy(employee -> employee.getDept(), Collectors.summingDouble(e -> e.getSal())));
        System.out.println(collect);


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


