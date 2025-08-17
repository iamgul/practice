package org.gul.multithreading;

public class OddEvenWithThread {

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() +": " + i);
            }
        }
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() +": " + i);
            }
        }
    }
}