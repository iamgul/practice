package org.gul.java8.defaults;

import java.util.stream.StreamSupport;

interface MyInterface {
    default void myMethod1() {
        System.out.println("Hello from default method 1 of Interface");
    }

    default void myMethod2() {
        System.out.println("Hello from default method 2 of Interface");
    }
}

class MyClass implements MyInterface {
    @Override
    public void myMethod1() {
        MyInterface.super.myMethod1(); // Call the default method
        System.out.println("Hello from default method 1 of Implementing class");
    }

//    @Override
//    public void myMethod2() {
//        System.out.println("Hello from default method 2 of Implementing class");
//    }
}

public class CallingOfDefaultMethods {
    public static void main(String[] args) {
        MyInterface obj = new MyClass();
        obj.myMethod1();

        obj.myMethod2();
    }
}

