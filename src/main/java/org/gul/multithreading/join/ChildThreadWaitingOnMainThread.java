package org.gul.multithreading.join;

public class ChildThreadWaitingOnMainThread {

    static Thread mainThread;

    public static void main(String[] args) throws InterruptedException {
        mainThread = Thread.currentThread();
        MyThread childThread = new MyThread();
        childThread.start();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("I am main thread, I will execute 1st");
        }
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                ChildThreadWaitingOnMainThread.mainThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("I am child thread, I will execute after completion of main Thread");
            }
        }
    }

}


