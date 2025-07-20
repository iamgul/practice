package org.gul.chaining;

class Chaining {

    {
        System.out.println("Instance initializer block");
    }

    public Chaining(String name) {
        this();
        System.out.println("1 argument Constructor - 1");
    }

    public Chaining(){
        System.out.println("No-argument constructor - 0");
    }


}


public class ConstructorChaining {

    public String isFlag = "Somevalue";
    public static void main(String[] args) {
        Chaining chaining = new Chaining("Java");

    }
}
