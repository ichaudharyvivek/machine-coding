package com.v3;

public class Subscriber implements Runnable {
    private String name;
    private MessageBroker messageBroker;
    private MessageQueue queue;

    public Subscriber(String name, MessageBroker messageBroker) {
        this.name = name;
        this.messageBroker = messageBroker;
        this.queue = messageBroker.getMessageQueue(name);
    }

    @Override
    public void run() {
        try {
            while (messageBroker.isRunning()) {
                String message = queue.subscribe();
                if (message != null) {
                    System.out.println("Subscriber '" + name + "' has consumed message: '" + message + "'");
                    Thread.sleep(200);
                }
            }

            System.out.println(Thread.currentThread().getName() + ": Stopped Gracefully...");
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }
    }

}
