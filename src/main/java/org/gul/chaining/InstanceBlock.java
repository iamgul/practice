package org.gul.chaining;

class Parent{
    {
		System.out.println("Parent Instance Block");
    }
}

class Child extends Parent{
    {
		System.out.println("Child Instance Block");
    }

    Child(int num){
        System.out.println("Child Constructor");
    }

}

public class InstanceBlock {
    public static void main(String[] args) {
        Child child = new Child(10);
        /*
         * O/P
         * Parent Instance Block
         * Child Instance Block
         * Child Constructor
         *
         *
         * */
    }

}