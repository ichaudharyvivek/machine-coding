package com.v1;

import java.util.LinkedList;
import java.util.List;

public class MessageQueue {
    private List<String> queue = new LinkedList<>();
    private Object lock = new Object();


    public void publish(String message) {
        synchronized (lock) {
            queue.add(message);
            lock.notifyAll();
        }
    }

    public String subscribe() throws InterruptedException {
        String message;
        synchronized (lock) {
            while (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " waiting for new messages");
                lock.wait();
            }

            message = queue.remove(0);
        }

        return message;
    }
}
