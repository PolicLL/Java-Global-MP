// Java code​​​​​​‌‌​​​‌​​​​‌‌​‌​​​‌‌‌​​​​​ below
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

// Write your answer here, and then test your code.
// Your job is to implement the getCartTotal() method.

class Answer3 {

  // Change these boolean values to control whether you see
  // the expected answer and/or hints.
  static boolean showExpectedResult = false;
  static boolean showHints = false;

  // Return the total value of the shopping cart in the items array.
  static float getCartTotal(List<Item> items) {
    // Your code goes here. Use an internal iterator (Java's forEach() method)
    // to add each item's value to the cart.
    AtomicReference<Float> result = new AtomicReference<>(0.0f);

    items.forEach(item -> result.updateAndGet(current -> current + (item.price * item.quantity)));
    return result.get();
  }

  // A model class holding each item's values
  public static class Item {
    private int id;
    private String name;
    private float price;
    private int quantity;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public float getPrice() {
      return price;
    }

    public void setPrice(float price) {
      this.price = price;
    }

    public int getQuantity() {
      return quantity;
    }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }

    public Item(int id, String name, float price, int quantity) {
      this.id = id;
      this.name = name;
      this.price = price;
      this.quantity = quantity;
    }

    @Override
    public String toString() {
      return "[" + this.id + ", " + this.name + ", " + this.price + "]";
    }
  }

}
