package part3;

import org.example.part3.tree.BinaryTree;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class BinaryTreeTest {

  private BinaryTree prepareTree() {
    BinaryTree tree = new BinaryTree();

//         5
//        / \
//       3   7
//      / \ / \
//     1  4 6  8

    tree.insert(5);
    tree.insert(3);
    tree.insert(7);
    tree.insert(1);
    tree.insert(4);
    tree.insert(6);
    tree.insert(8);
    return tree;
  }

  @Test
  public void testInOrderTraversal() {
    BinaryTree tree = prepareTree();
    List<Integer> expected = Arrays.asList(1, 3, 4, 5, 6, 7, 8);
    assertEquals(expected, tree.traverseInOrder());
  }

  @Test
  public void testPreOrderTraversal() {
    BinaryTree tree = prepareTree();
    List<Integer> expected = Arrays.asList(5, 3, 1, 4, 7, 6, 8);
    assertEquals(expected, tree.traversePreOrder());
  }

  @Test
  public void testPostOrderTraversal() {
    BinaryTree tree = prepareTree();
    List<Integer> expected = Arrays.asList(1, 4, 3, 6, 8, 7, 5);
    assertEquals(expected, tree.traversePostOrder());
  }
}