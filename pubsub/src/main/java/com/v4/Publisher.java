package com.v4;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Publisher implements Runnable {
    private MessageBroker messageBroker;
    private MessageQueue queue;
    private Queue<String> publisherQueue;

    protected String name;

    public Publisher(String name, MessageBroker messageBroker) {
        this.name = name;
        this.messageBroker = messageBroker;
        this.queue = messageBroker.getMessageQueue(name);
        this.publisherQueue = new ConcurrentLinkedQueue<>();
    }

    public abstract void onMessagePublished(String message);

    public void publish(String message) {
        if (!messageBroker.isRunning()) {
            return;
        }

        System.out.println(name + " publishing message: '" + message + "'");
        publisherQueue.add(message);
    }

    @Override
    public void run() {
        try {
            while (messageBroker.isRunning() || !publisherQueue.isEmpty()) {
                String messaage = publisherQueue.poll();
                if (messaage != null) {
                    // Publish the message to the broker or to the actual queue
                    queue.publish(messaage);

                    // Callback from client
                    onMessagePublished(messaage);
                    Thread.sleep(400);
                }

            }

        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            // Thread.currentThread().interrupt();
        }
    }

}
