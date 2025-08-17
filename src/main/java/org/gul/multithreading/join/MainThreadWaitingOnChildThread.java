package org.gul.multithreading.join;

public class MainThreadWaitingOnChildThread {

    public static void main(String[] args) throws InterruptedException {
        MyThread childThread = new MyThread();
        childThread.start();
        childThread.join(); //main thread is calling this, So main thread will wait until the completion of child thread
        for (int i = 0; i < 10; i++) {
            System.out.println("I am main thread, I will execute only after completion of child Thread");
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("I am child thread, I will execute first");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

}

