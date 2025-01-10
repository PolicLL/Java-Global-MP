package task3;

import static utils.Constants.RANDOM;
import static utils.Constants.sleepForSomeTime;

import java.util.Random;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class Producer implements Runnable {

  private final MessageBus messageBus;
  private final String topic;
  private final String name;

  @Override
  public void run() {
    while (true) {
      String payload = "Message " + RANDOM.nextInt(1000);

      Message message = new Message(topic, payload);
      System.out.printf("[%s] Producing message " + payload + ".%n", name);
      messageBus.postMessage(message);

      sleepForSomeTime(RANDOM.nextInt(1000));
    }
  }
}