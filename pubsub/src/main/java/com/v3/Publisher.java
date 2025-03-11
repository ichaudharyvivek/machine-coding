package com.v3;

public class Publisher implements Runnable {
    private String name;
    private MessageBroker messageBroker;
    private MessageQueue queue;

    public Publisher(String name, MessageBroker messageBroker) {
        this.name = name;
        this.messageBroker = messageBroker;
        this.queue = messageBroker.getMessageQueue(name);
    }

    public void publish(String message) {
        if (!messageBroker.isRunning()) {
            return;
        }

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
