package org.gul.multithreading.phaser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class MyPhaserAsCyclicB {
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(3);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(new DependentService(phaser));
        executorService.submit(new DependentService(phaser));
        executorService.submit(new DependentService(phaser));

        System.out.println("All dependent service initialized");
    }

    public static class DependentService implements Runnable {
        private Phaser phaser;

        public DependentService(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {

        while (true){
            String name = Thread.currentThread().getName();
            try {
                if (name.equals("pool-1-thread-1")) {
                    Thread.sleep(1000);
                    System.out.println("Initializing the dependent service for: " + name);
                } else if (name.equals("pool-1-thread-2")) {
                    Thread.sleep(2000);
                    System.out.println("Initializing the dependent service for: " + name);
                } else if (name.equals("pool-1-thread-3")) {
                    Thread.sleep(3000);
                    System.out.println("Initializing the dependent service for: " + name);

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            phaser.arriveAndAwaitAdvance();
            int phase = phaser.getPhase();
            System.out.println("Number of phase: "+phase);
            int arrivedParties = phaser.getArrivedParties();
            System.out.println("Number of arrivedParties: "+arrivedParties);

            System.out.println("Sending messages to consumer by thread: " + name);
        }

        }
    }
}
