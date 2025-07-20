package org.gul.staticex;


class Parent {
    public static void parentMethod() {
        System.out.println("Parent Method");
    }

    public void instanceMethod() {
        System.out.println("Parents Instance Method");
    }
}
class Child extends Parent {
    public static void childMethod() {
        System.out.println("Child Method");
    }

    @Override
    public void instanceMethod() {
        System.out.println("Child's Instance Method");
    }

    public static void parentMethod() {
        System.out.println("Child's Parent Method");
    }

}

public class Test {
    public static void main(String[] args) {
//        Parent.parentMethod();
//        Child.parentMethod();
//        Child.childMethod();

        Parent p = new Parent();
        p.parentMethod();
        p.instanceMethod();




        Child c = new Child();
        c.parentMethod();
        c.instanceMethod();


        Parent pc = new Child();
        pc.parentMethod();
        pc.instanceMethod();



    }
}