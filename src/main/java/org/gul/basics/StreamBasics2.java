package org.gul.basics;

import java.util.*;
import java.util.stream.Collectors;

public class StreamBasics2 {

    public static void main(String[] args) {


        StreamBasics2 js = new StreamBasics2();
        js.get();

    }

    public class Employee {
        int id;
        String name;
        Double sal;
        String gender;
        List<Address> addresses;

        public Employee(int id, String name, double sal, String gender, List<Address> addresses) {
            this.id = id;
            this.name = name;
            this.sal = sal;
            this.gender = gender;
            this.addresses = addresses;
        }

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

        public Double getSal() {
            return sal;
        }

        public void setSal(Double sal) {
            this.sal = sal;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public List<Address> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address> addresses) {
            this.addresses = addresses;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", sal=" + sal +
                    ", gender='" + gender + '\'' +
                    ", addresses=" +
                    '}';
        }
    }

    public class Address {
        String city;
        String state;
        String country;

        public Address(String city, String state, String country) {
            this.city = city;
            this.state = state;
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }

    void get() {

        Address ad = new Address("Bng", "Kar", "Ind");
        Address ad1 = new Address("Delhi", "Kar", "Ind");
        List<Address> adrss = new ArrayList<>();
        adrss.add(ad);
        adrss.add(ad1);

        List<Address> adrss2 = new ArrayList<>();
        Address ad3 = new Address("pune", "Mah", "Ind");
        Address ad4 = new Address("Mum", "Mah", "Ind");
        adrss2.add(ad3);
        adrss2.add(ad4);

        Employee employee1 = new Employee(1, "GUL", 30.0, "M", adrss2);
        Employee employee2 = new Employee(2, "MD", 40.0, "M", adrss);
        Employee employee3 = new Employee(3, "SA", 500.0, "F", adrss2);
        Employee employee4 = new Employee(4, "SA", 25.0, "M", adrss);
        Employee employee5 = new Employee(5, "SA", 200.0, "M", adrss2);

        List<Employee> elist = new ArrayList<>();
        elist.add(employee1);
        elist.add(employee2);
        elist.add(employee3);
        elist.add(employee4);
        elist.add(employee5);

        //1.Sort Male Employee based on Salary of employee
        List<Employee> sortedListOfMaleEmployee = elist.stream().filter(e -> e.getGender().equals("M"))
                .sorted(Comparator.comparing(Employee::getSal)).toList();

        System.out.println("Male Employee Sorted based on Salary" + sortedListOfMaleEmployee);

        //2. Second-Highest salary of Male Employee
        Double secondHighestSal = sortedListOfMaleEmployee.get(sortedListOfMaleEmployee.size() - 2).getSal();
        System.out.println("secondHighestSal of Male Employee====>" + secondHighestSal);


        //3.All the Cities
        List<String> cities = elist.stream().flatMap(e -> e.getAddresses().stream()).map(Address::getCity).distinct().toList();
        System.out.println("All the Cities " + cities);

        //4.All Employee of Pune Cities
        List<Employee> employeeOfPune = elist.stream().filter(e -> e.getAddresses().stream().anyMatch(a -> a.getCity().equals("pune"))).toList();
        System.out.println("employeeOfPune :" + employeeOfPune);

        //5.All Employee of Pune Cities Alternative using Map which is completely unnecessary [Not Recommended]
        //Check below code first to understand this better
        //Here also we are using MergeFunction i.e. BinaryOperator because to maintain insertionOrder we need to provide supplier, but if we want to use supplier
        // then we need to use 4 argument method, hence we need to pass the merge function as well
        List<Employee> employeeOfPune1 = elist.stream().collect(Collectors.toMap(
                        employee -> employee,
                        employee -> employee.getAddresses(),
                        (s1, s2) -> s1,
                        LinkedHashMap::new))
                .entrySet().stream().filter(e -> e.getValue().stream().anyMatch(a -> a.getCity().equals("pune")))
                .map(e -> e.getKey()).toList();

        System.out.println("employeeOfPune :" + employeeOfPune1);

        //6.All Employee and Their Respective Cities
        //Map<Employee, List<String>> employeesWithRespectiveCities;
        //Conversion from Stream<String> to List<String> by using .toList()
        //Here since our key(i.e. name) is duplicate, we will get DuplicateKeyException
        //So if we want to merge the values of Duplicate key, we need to use mergeFunction which is BinaryOperator -> (s1,s2) -> merge the value
        Map<String, Set<String>> employeesWithRespectiveCities = elist.stream().collect(Collectors.toMap(
                e -> e.getName(),
                e -> e.getAddresses().stream().map(a -> a.getCity()).collect(Collectors.toSet()),
                //merge function i.e. BinaryOperator operates with value part of Map since it has to merge the values, so it takes values as argument of same key
                //Here in this case the value part is List<String>, so it will take 2 List<String> and return List<String>
                //Either we can merge both or we can return specific value
                //return specific value i.e. only value of v1 ==> (v1,v2) -> v1
                //Merging all values and returning the unique ones so using Set, if we want all the values then List will be used:
                (v1, v2) -> {
                    v1.addAll(v2);
                    return v1;
                },
                //We have one more parameter which is Supplier<M> mapFactory
                //a supplier providing a new empty Map into which the results will be inserted
                //<M> – the type of the resulting Map
                LinkedHashMap::new
        ));
        System.out.println("All Employee and Their Respective Cities " + employeesWithRespectiveCities);
    }


}
