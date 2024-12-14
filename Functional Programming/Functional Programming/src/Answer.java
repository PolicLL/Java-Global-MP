// Java code​​​​​​‌‌​​​‌​​​​‌‌​‌​‌​​​​​​​​​ below
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Write your answer here, and then test your code.
// Your job is to implement the getFilteredData() method.

class Answer {

  // Change these boolean values to control whether you see
  // the expected answer and/or hints.
  static boolean showExpectedResult = true;
  static boolean showHints = false;

  // Filter and sort the items array.
  static List<Item> getFilteredData(List<Item> items, float maxPrice) {

    return items.stream()
        .filter(item -> item.price >= maxPrice)
        .sorted(Comparator.comparing(Item::getPrice))
        .toList();

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
      return "[" + this.id + ", " + this.name + ", " + this.price + ", " + this.quantity + "]";
    }
  }

}
