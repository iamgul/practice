package org.gul.multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/*
This is a classic Odd–Even number printing using two threads problem.

lock → ensures only one thread runs the printing section at a time.

condition → allows threads to wait and signal each other (so they alternate).


 Why it Works
 Both threads compete for the lock.

 Only one gets the lock → checks if it can print.

  If not (wrong parity), it awaits and releases lock.

 The other thread wakes up, prints, and then signals back.

 This ensures perfect alternation.

 So when thread awaits it releases lock without calling unlock

 When a thread calls: condition.await();

 Two important things happen automatically:

     The thread releases the lock (so another thread can acquire it).

     The thread goes into a waiting state on that condition.

 Later, when some other thread calls: condition.signal();

 One of the waiting threads is woken up and it will try to re-acquire the lock before continuing execution.

 Difference from unlock()
    await() → releases the lock temporarily while waiting, and automatically tries to reacquire it when signaled.
    unlock() → permanently releases the lock and never tries to reacquire it (the thread just leaves that synchronized section).

        */

class OddEvenUsingLock {
    private int count = 1;
    private final int limit = 10;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void printOdd() {
        while (count < limit) {
            String name = Thread.currentThread().getName();
            System.out.println("I am : "+ name + " trying to acquire the lock of printOdd method");
            lock.lock();
            System.out.println("I am : "+ name + " I acquired the lock of printOdd method");
            try {
                while (count % 2 == 0) {
                    System.out.println("I am : "+ name + " I am calling await() and releasing the lock");
                    condition.await();
                }
                System.out.println("Odd: " + count++);
                System.out.println("I am : "+ name + " I am calling signal() and notifying the other awaiting thread");
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
            String name = Thread.currentThread().getName();
            System.out.println("I am : "+ name + " trying to acquire the lock of printEven method");
            lock.lock();
            System.out.println("I am : "+ name + " I acquired the lock of printEven method");
            try {
                while (count % 2 != 0) {
                    System.out.println("I am : "+ name + " I am calling await() and releasing the lock");
                    condition.await();
                }
                System.out.println("Even: " + count++);
                System.out.println("I am : "+ name + " I am calling signal() and notifying the other awaiting thread");
                condition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }


//practice
//    public void printOdd1()  {
//        while (count < limit) {
//            lock.lock();
//            while (count % 2 == 0) {
//                try {
//                    condition.await();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            System.out.println("odd------------ : " + count++);
//            condition.signal();
//            lock.unlock();
//        }
//
//    }
//
//    public void printEven1()  {
//        while (count < limit) {
//            lock.lock();
//            while (count % 2 != 0) {
//                try {
//                    condition.await();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            System.out.println("even------------ : " + count++);
//            condition.signal();
//            lock.unlock();
//        }
//
//    }
}

class OddEvenUsingSynchronized{
    private int count = 0;
    private int limit = 10;


    public synchronized void printOdd() {
        while (count < limit) {
            while (count % 2 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Synchronized Odd : "+ count ++);
            notify();
        }
    }

    public synchronized void printEven(){
        while (count < limit) {
            while (count % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Synchronized Even : "+ count ++);
            notify();
        }
    }
}

public class OddEvenWithLockAndSynchronized {
    public static void main(String[] args) {
        OddEvenUsingLock printer = new OddEvenUsingLock();

        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);

        oddThread.setName("oddThread");
        oddThread.start();

        evenThread.setName("evenThread");
        evenThread.start();


        //-------------------------------------------------------------------------------------//
        OddEvenUsingSynchronized usingSynchronized = new OddEvenUsingSynchronized();
        Runnable printOdd = usingSynchronized::printOdd;
        Runnable printEven = usingSynchronized::printEven;

        Thread printOddThread = new Thread(printOdd);
        printOddThread.start();

        Thread printEvenThread = new Thread(printEven);
        printEvenThread.start();



        //-------------------------------------------------------

//        Thread oddThread1 = new Thread(printer::printOdd1);
//        Thread evenThread1 = new Thread(printer::printEven1);
//        oddThread1.start();
//        evenThread1.start();
    }
}