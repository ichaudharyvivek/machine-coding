package com.broker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.v3.MessageBroker;
import com.v3.Publisher;
import com.v3.Subscriber;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        MessageBroker messageBroker = new MessageBroker();

        Publisher orderUpdatesPublisher = new Publisher("order-updates", messageBroker);
        Subscriber orderUpdatesSubscriber = new Subscriber("order-updates", messageBroker);

        Publisher notificationUpdatesPublisher = new Publisher("notification-updates", messageBroker);
        Subscriber notificationUpdatesSubscriber = new Subscriber("notification-updates", messageBroker);

        service.submit(orderUpdatesPublisher);
        service.submit(orderUpdatesSubscriber);
        service.submit(notificationUpdatesPublisher);
        service.submit(notificationUpdatesSubscriber);

        Thread.sleep(3000);

        messageBroker.stop();
        service.shutdown();

    }
}