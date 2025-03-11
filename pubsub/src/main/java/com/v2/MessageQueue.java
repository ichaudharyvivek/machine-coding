package com.v2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MessageQueue {
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private volatile boolean isRunning = true;

    public void publish(String message) {
        queue.add(message);
    }

    public String subscribe() throws InterruptedException {
        return queue.poll(2, TimeUnit.SECONDS);
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
