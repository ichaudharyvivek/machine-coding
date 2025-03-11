package com.broker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.v4.MessageBroker;
import com.v4.OrderUpdatesPublisher;
import com.v4.OrderUpdatesSubscriber;
import com.v4.Publisher;
import com.v4.Subscriber;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        MessageBroker messageBroker = new MessageBroker();

        Publisher orderUpdatesPublisher = new OrderUpdatesPublisher("order-updates", messageBroker);
        Subscriber orderUpdatesSubscriber = new OrderUpdatesSubscriber("order-updates", messageBroker);

        // Publisher notificationUpdatesPublisher = new
        // PubnotificationUpdatesPublisherlisher("notification-updates", messageBroker);
        // Subscriber notificationUpdatesSubscriber = new
        // notificationUpdatesSubscriber("notification-updates", messageBroker);

        service.submit(orderUpdatesPublisher);
        service.submit(orderUpdatesSubscriber);
        // service.submit(notificationUpdatesPublisher);
        // service.submit(notificationUpdatesSubscriber);

        orderUpdatesPublisher.publish("Order 1 placed");
        orderUpdatesPublisher.publish("Order 2 placed");
        orderUpdatesPublisher.publish("Order 3 placed");

        Thread.sleep(3000);

        messageBroker.stop();
        service.shutdown();

    }
}