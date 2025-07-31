package org.gul.multithreading.phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class MyPhaserAsCDLEx {
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(3);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Thread.sleep(1000);
        executorService.submit(new DependentService(phaser));
        Thread.sleep(2000);
        executorService.submit(new DependentService(phaser));
        Thread.sleep(3000);
        executorService.submit(new DependentService(phaser));

        phaser.arriveAndAwaitAdvance();
        //phaser.awaitAdvance();
        System.out.println("All dependent service initialized");
    }

    public static class DependentService implements Runnable {
        private Phaser phaser;

        public DependentService(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                if (name.equals("pool-1-thread-1")) {
                    Thread.sleep(1000);
                    System.out.println("Initializing the dependent service for: " + name);
                } else if (name.equals("pool-1-thread-2")) {
                    Thread.sleep(2000);
                    System.out.println("Initializing the dependent service for: " + name);
                } else if (name.equals("pool-1-thread-3")) {
                    System.out.println("Initializing the dependent service for: " + name);
                    Thread.sleep(3000);

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            phaser.arrive();
            System.out.println("Completed the dependent service for: " + name);


        }
    }

}
