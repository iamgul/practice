package org.gul.multithreading.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCountDownLatchEx {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CountDownLatch latch = new CountDownLatch(3);

        executorService.submit( new MyDependentService(latch));
        executorService.submit( new MyDependentService(latch));
        executorService.submit( new MyDependentService(latch));

        latch.await();

        System.out.println("CountDown Latch Completed, now main method will be executed");
    }

    public static class MyDependentService implements Runnable{

        private CountDownLatch latch ;

        public MyDependentService(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {

            String thread = Thread.currentThread().getName();
            System.out.println("Countdown happening for: "+thread);
            latch.countDown();
            System.out.println("Countdown completed for: "+thread);

        }
    }
//    Countdown happening for: pool-1-thread-1
//    Countdown happening for: pool-1-thread-3
//    Countdown completed for: pool-1-thread-3
//    Countdown happening for: pool-1-thread-2
//    Countdown completed for: pool-1-thread-1
//    Countdown completed for: pool-1-thread-2
//    CountDown Latch Completed, now main method will be executed



    // Difference between Phaser and CountDown is , phaser does not wait for the dependent services to initialized first

}
