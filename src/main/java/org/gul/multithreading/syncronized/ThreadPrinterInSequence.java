package org.gul.multithreading.syncronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPrinterInSequence {

    public static void main(String[] args) {

        Printer printer = new Printer();

/*      Runnable r1 = () -> printer.print(1);
        Runnable r2 = () -> printer.print(2);
        Runnable r3 = () -> printer.print(3);
        Runnable r4 = () -> printer.print(4);

        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
        new Thread(r4).start();*/

        int totalNumberOfThreads = 5;
        int maxNumberToPrint = 16;
        ExecutorService executorService = Executors.newFixedThreadPool(totalNumberOfThreads);
        for (int i = 1; i <= totalNumberOfThreads; i++) {
            final int threadNumber = i;
            executorService.submit(() -> printer.print(threadNumber, totalNumberOfThreads, maxNumberToPrint));
        }

    }
}

class Printer {
    private int turn = 1; //basically thread turn, 1 is 1st thread turn
    private int count = 1; //current count
    //private int maxNumToPrint = 12;
    //private int numOfThreads = 3;

    synchronized void print(int threadId, int totalNumberOfThreads, int maxNumberToPrint) {
        while (count <= maxNumberToPrint) {
            //System.out.println("threadId: "+threadId + " turn : "+turn);
            while (threadId != turn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread() + " is interrupted");
                    //throw new RuntimeException(e);
                }
            }

            if (count <= maxNumberToPrint) {
                //System.out.println("Thread-" + threadId + " : " + count);
                //System.out.println(Thread.currentThread() + " : " + count );
                count++;
                turn = turn % totalNumberOfThreads + 1;
                notifyAll();

            }
        }

    }
}

// Very Important
/*
Simple Idea:
Let’s create a shared object that keeps track of:

Current number to print

Whose turn it is (Thread-1, Thread-2, Thread-3)

Each thread will wait for its turn, print the number, and then notify others.*/
