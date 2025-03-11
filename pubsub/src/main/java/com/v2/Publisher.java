package com.v2;

public class Publisher implements Runnable {
    private String name;
    private MessageQueue queue;

    public Publisher(String name, MessageQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    public void publish(String message) {
        System.out.println(name + " publishing message: '" + message + "'");
        queue.publish(message);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                String message = "Message " + i;
                publish(message);
                Thread.sleep(200);
            }

        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            // Thread.currentThread().interrupt();
        }
    }

}
