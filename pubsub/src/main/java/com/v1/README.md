# v1

In this version, we made a very simple pub/sub using threads.  
In v2, we will use Java thread safe blocking queue to make pub/sub and futher inhancements.  

Usage in main
```java


public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        MessageQueue messageQueue = new MessageQueue();

        Publisher publisher1 = new Publisher("Pub 1", messageQueue);
        Subscriber subscriber1 = new Subscriber("Sub 1", messageQueue);

        Publisher publisher2 = new Publisher("Pub 2", messageQueue);
        Subscriber subscriber2 = new Subscriber("Sub 2", messageQueue);

        service.submit(publisher1);
        service.submit(subscriber1);

        service.submit(publisher2);
        service.submit(subscriber2);

    }
}
```