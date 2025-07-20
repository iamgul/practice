package org.gul;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class NumberPrinter {
    private int count = 1;
    private final int limit = 10;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void printOdd() {
        while (count < limit) {
            lock.lock();
            try {
                while (count % 2 == 0) {
                    condition.await();
                }
                System.out.println("Odd: " + count++);
                condition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        while (count <= limit) {
            lock.lock();
            try {
                while (count % 2 != 0) {
                    condition.await();
                }
                System.out.println("Even: " + count++);
                condition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}

public class OddEvenWithLock {
    public static void main(String[] args) {
        NumberPrinter printer = new NumberPrinter();

        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);

        oddThread.start();
        evenThread.start();
    }
}