package org.gul.multithreading.interrupt;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class InterruptExample {

    public static void main(String[] args) {
        MyThread childThread = new MyThread();
        childThread.start();
        childThread.interrupt();
        System.out.println("Main Thread Completed its execution===============");
    }


    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1_000; i++) {
                System.out.println("I am a lazy thread: " + i);
            }
            System.out.println("I am entering into Sleep State");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("I got Interrupted");
            }
        }
    }
}
