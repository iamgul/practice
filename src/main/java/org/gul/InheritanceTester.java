package org.gul;

public class InheritanceTester {
    public static void main(String[] args) {
        Parent p =  new Parent();
        p.eat();

        Child c  = new Child();
        c.eat();


        Parent pc = new Child();
        pc.eat();

        Child cp = (Child) new Parent();
        c.eat();



    }
}

class Parent{
    public void eat(){
        System.out.println("Parent Eating");
    }
}

class Child extends Parent{
    public void eat(){
        System.out.println("Child Eating");
    }
}

