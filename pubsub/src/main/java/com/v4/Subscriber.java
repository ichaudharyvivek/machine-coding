package com.v4;

public abstract class Subscriber implements Runnable {
    private MessageBroker messageBroker;
    private MessageQueue queue;

    protected String name;

    public Subscriber(String name, MessageBroker messageBroker) {
        this.name = name;
        this.messageBroker = messageBroker;
        this.queue = messageBroker.getMessageQueue(name);
    }

    public abstract void onMessageReceived(String message);

    @Override
    public void run() {
        try {
            while (messageBroker.isRunning()) {
                String message = queue.subscribe();
                if (message != null) {
                    // Trigger the callback
                    onMessageReceived(message);

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
