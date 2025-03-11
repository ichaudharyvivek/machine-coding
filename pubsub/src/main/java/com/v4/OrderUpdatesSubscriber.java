package com.v4;

public class OrderUpdatesSubscriber extends Subscriber {

    public OrderUpdatesSubscriber(String name, MessageBroker messageBroker) {
        super(name, messageBroker);
    }

    @Override
    public void onMessageReceived(String message) {
        System.out.println(name + " custom subscriber callback method finished executing.");
    }

}
