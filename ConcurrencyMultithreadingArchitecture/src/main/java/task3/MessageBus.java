package task3;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class MessageBus {
  private final Map<String, List<MyConsumer>> subscribers = new HashMap<>();
  private final Map<String, Queue<Message>> messageQueues = new HashMap<>();

  public synchronized void registerConsumer(String topic, MyConsumer consumer) {
    subscribers.computeIfAbsent(topic, k -> new ArrayList<>()).add(consumer);
    messageQueues.computeIfAbsent(topic, k -> new LinkedList<>());
  }

  public synchronized void postMessage(Message message) {
    Queue<Message> queue = messageQueues.computeIfAbsent(message.topic(), k -> new LinkedList<>());
    queue.add(message);
    notifyConsumers(message.topic());
  }

  private synchronized void notifyConsumers(String topic) {
    List<MyConsumer> topicConsumers = subscribers.get(topic);
    if (topicConsumers != null) {
      Message retrievedMessage = retrieveMessage(topic);
      topicConsumers.forEach(consumer -> consumer.action(retrievedMessage));
    }
  }

  public synchronized Message retrieveMessage(String topic) {
    Queue<Message> queue = messageQueues.get(topic);
    return (queue != null) ? queue.poll() : null;
  }
}