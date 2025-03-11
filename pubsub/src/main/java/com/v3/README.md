# v3

In this version we will focus on creating multiple messaging queues i.e one for each topic.
Multiple publishers can post on multiple topics, and subscribers can listed to multiple too.
- To implement this we used a MessageBroker class which stores a map of all message queues.
- This approach is standard as this type of things is used in both kafka and rabbitmq


Usage in main
Note: In this we fulfilled
    - Multiple message queues (topics) where pub can publish and sub can listen
```java
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
```