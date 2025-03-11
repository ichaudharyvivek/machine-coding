# v2

This version uses 
 - Blocking queue
 - Volatile variable for gracefull shutdown


Note:
 - While making this, we use queue.take() in MessageQueue class
    - The problem was, take keeps the thread in blocking state so the program was never shutting down because at the end loop, we were kept listing for new messages from publisher
    - To mitigate this, we used queue.poll with 2 seconds timeout  
    

Usage in main
```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
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

        Thread.sleep(3000);

        messageQueue.stop();
        service.shutdown();

    }
}
```