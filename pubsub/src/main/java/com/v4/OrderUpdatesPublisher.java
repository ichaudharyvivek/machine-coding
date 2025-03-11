package com.v4;

public class OrderUpdatesPublisher extends Publisher {

    public OrderUpdatesPublisher(String name, MessageBroker messageBroker) {
        super(name, messageBroker);
    }

    @Override
    public void onMessagePublished(String message) {
        System.out.println(name + " custom published: " + message);
    }

}
