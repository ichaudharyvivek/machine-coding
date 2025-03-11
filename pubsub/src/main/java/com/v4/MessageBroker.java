package com.v4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageBroker {
    private volatile boolean isRunning = true;
    private Map<String, MessageQueue> brokerMap = new ConcurrentHashMap<>();

    public MessageQueue getMessageQueue(String name) {
        return brokerMap.computeIfAbsent(name, k -> new MessageQueue());
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
