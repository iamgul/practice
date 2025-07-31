package org.gul.multithreading.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrant {

    private static ReentrantLock lock = new ReentrantLock();

    static void accessResource() {
        lock.lock();
        lock.lock();
        lock.unlock();
        lock.unlock();
        int holdCount = lock.getHoldCount();

        System.out.println("holdCount : " + holdCount);


    }


    public static void main(String[] args) {

        accessResource();

    }
}
