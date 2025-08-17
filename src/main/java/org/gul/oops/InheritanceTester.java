package org.gul.oops;

class Parent {
    public void eat() {
        System.out.println("Parent Eating");
    }
}

class Child extends Parent {
    public void eat() {
        System.out.println("Child Eating");
    }
}

public class InheritanceTester {
    public static void main(String[] args) {

        Parent p = new Parent();
        p.eat(); //Parent Eating

        Child c = new Child();
        c.eat(); // Child Eating

        Parent pc = new Child();
        pc.eat(); // Child Eating

        Child cp = (Child) new Parent(); // Exception : Parent cannot be cast to class org.gul.oops.Child
        cp.eat();


    }
}

