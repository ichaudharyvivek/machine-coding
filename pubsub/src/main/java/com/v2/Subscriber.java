package com.v2;

public class Subscriber implements Runnable {
    private String name;
    private MessageQueue queue;

    public Subscriber(String name, MessageQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (queue.isRunning()) {
                String message = queue.subscribe();
                if (message != null) {
                    System.out.println("Subscriber " + name + " has consumed message: '" + message + "'");
                    Thread.sleep(200);
                }
            }

            System.out.println(Thread.currentThread().getName() + ": Stopped Gracefully...");
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }
    }

}
