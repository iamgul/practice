package org.gul;

class Printer {
    int number = 1;
    int turn = 1; // 1 for Thread-1, 2 for Thread-2, 3 for Thread-3
    int max = 9;

    synchronized void print(int threadId) {
        while (number <= max) {
            while (turn != threadId) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            if (number <= max) {
                System.out.println("Thread-" + threadId + " : " + number);
                number++;
                turn = turn % 3 + 1; // 1->2->3->1
                notifyAll();
            }
        }
    }
}

public class ThreadPrinting {
    public static void main(String[] args) {
        Printer printer = new Printer();

        Runnable task1 = () -> printer.print(1);
        Runnable task2 = () -> printer.print(2);
        Runnable task3 = () -> printer.print(3);

        new Thread(task1).start();
        new Thread(task2).start();
        new Thread(task3).start();
    }
}
