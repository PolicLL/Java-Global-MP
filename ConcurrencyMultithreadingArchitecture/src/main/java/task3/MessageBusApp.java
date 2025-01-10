package task3;

import static utils.Constants.sleepForSomeTime;

public class MessageBusApp {
  public static void main(String[] args) {

    final String TOPIC1 = "topic1";
    final String TOPIC2 = "topic2";
    final String TOPIC3 = "topic3";

    MessageBus messageBus = new MessageBus();

    // Create and register consumers
    MyConsumer myConsumer1 = new ConcreteConsumer(messageBus, TOPIC1, "CONSUMER 1");
    MyConsumer myConsumer2 = new ConcreteConsumer(messageBus, TOPIC2, "CONSUMER 2");
    MyConsumer myConsumer3 = new ConcreteConsumer(messageBus, TOPIC2, "CONSUMER 3");
    MyConsumer myConsumer4 = new ConcreteConsumer(messageBus, TOPIC2, "CONSUMER 4");
    MyConsumer myConsumer5 = new ConcreteConsumer(messageBus, TOPIC2, "CONSUMER 5");
    MyConsumer myConsumer6 = new ConcreteConsumer(messageBus, TOPIC2, "CONSUMER 6");

    messageBus.registerConsumer(TOPIC1, myConsumer1);
    messageBus.registerConsumer(TOPIC2, myConsumer2);
    messageBus.registerConsumer(TOPIC2, myConsumer3);
    messageBus.registerConsumer(TOPIC2, myConsumer4);
    messageBus.registerConsumer(TOPIC1, myConsumer5);
    messageBus.registerConsumer(TOPIC3, myConsumer6);

    // Create and start producers
    new Thread(new Producer(messageBus, TOPIC1, "PRODUCER 1")).start();
    new Thread(new Producer(messageBus, TOPIC2, "PRODUCER 2")).start();
    new Thread(new Producer(messageBus, TOPIC3, "PRODUCER 3")).start();

    sleepForSomeTime(5000);

    System.exit(0);
  }
}