package com.v1;

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
            while (true) {
                String message = queue.subscribe();
                if (message != null) {
                    System.out.println("Subscriber " + name + " has consumed message: '" + message + "'");
                    Thread.sleep(200);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }

    }

}
