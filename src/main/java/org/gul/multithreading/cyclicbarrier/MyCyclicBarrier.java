package org.gul.multithreading.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCyclicBarrier {


    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(new MyRunnableTask(barrier));
        executorService.submit(new MyRunnableTask(barrier));
        executorService.submit(new MyRunnableTask(barrier));

        System.out.println("Main thread completed executing :" + Thread.currentThread().getName());

    }


    static class MyRunnableTask implements Runnable {

        private CyclicBarrier barrier;

        public MyRunnableTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            while (true) {
                String name = Thread.currentThread().getName();
                try {
                    if (name.equals("pool-1-thread-3")) {
                        System.out.println("sleeping thread: " + name);
                        Thread.sleep(5000);
                    }
                    barrier.await(); //Two other threads will already be awaiting here, until thread 3 comes after
                    // sleeping for 5 sec and after that all  threads continue
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("Sending message to broker by thread: " + name);
            }
        }
    }
}
