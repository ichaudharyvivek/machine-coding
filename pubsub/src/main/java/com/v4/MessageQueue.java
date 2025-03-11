package com.v4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public void publish(String message) {
        queue.add(message);
    }

    public String subscribe() throws InterruptedException {
        return queue.poll();
    }

}
