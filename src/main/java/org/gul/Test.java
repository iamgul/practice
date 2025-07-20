package org.gul;

public class Test {

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }
    }
}