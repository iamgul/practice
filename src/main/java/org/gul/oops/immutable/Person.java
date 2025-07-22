package org.gul.oops.immutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {

    private final String name;
    private final int age;
    private final Car car;
    private final List<String> emails;

    public Person(String name, int age, Car car, List<String> emails) {
        this.name = name;
        this.age = age;
        //this.car = car; // Instead of doing this we need below points
        //Creating Deep Copy of car and assigning so that same reference of Car object won't be used
        // rather new car object will be created
        Car newDeepCopyCar = new Car(car.getModel(), car.getColor());//Copying from argument passed in constructor and creating new Car Object
        this.car = newDeepCopyCar; //Assigning newly created car object

        //Similarly while copying list also we will create the list 1st and copy all
        //this.emails = emails;
        List<String> emailsDeepCopy = new ArrayList<>();
        emailsDeepCopy.addAll(emails);
        this.emails = emailsDeepCopy;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + ", car=" + car + ", emails=" + emails + '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public Car getCar() {
        //return car; // Instead of returning the same reference we create the deep Copy and return it.
        Car deepCopyReturnCar = new Car(this.car.getModel(), this.car.getColor());
        return deepCopyReturnCar;
    }

    public List<String> getEmails() {
        // return emails;
        List<String> emailsDeepCopy = new ArrayList<>();
        emailsDeepCopy.addAll(this.emails); //`this` is optional here, without `this` also it's fine
        return emailsDeepCopy;
    }

    public static void main(String[] args) {
        Car car = new Car("BMW", "RED");
        List<String> personOriginalEmail = new ArrayList<>();
        personOriginalEmail.add("gul@bytemark.co");
        personOriginalEmail.add("iamgul@gmail.com");

        Person person = new Person("Gul", 31, car, personOriginalEmail);
        System.out.println("Person Class Before : " + person);

        car.setColor("Green");
        person.getCar().setColor("Blue");

        personOriginalEmail.add("modified@gmail.com");
        person.getEmails().add("hacker@gmail.co");

        System.out.println("Person Class After : " + person);
    }
}
