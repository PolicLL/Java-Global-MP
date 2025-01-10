package task3;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcreteConsumer implements MyConsumer{

  private final MessageBus messageBus;
  private final String topic;
  private final String name;

  @Override
  public void action(Message message) {
    System.out.printf("[%s] Consuming message " + message + ".%n", name);
  }
}
