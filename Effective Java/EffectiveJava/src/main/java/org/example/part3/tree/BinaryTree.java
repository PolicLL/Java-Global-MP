package org.example.part3.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
  private TreeNode root;

  public BinaryTree() {
    this.root = null;
  }

  public void insert(int value) {
    root = insertRecursive(root, value);
  }

  private TreeNode insertRecursive(TreeNode current, int value) {
    if (current == null) {
      return new TreeNode(value);
    }
    if (value < current.value) {
      current.left = insertRecursive(current.left, value);
    } else if (value > current.value) {
      current.right = insertRecursive(current.right, value);
    }
    return current;
  }

  public List<Integer> traverseInOrder() {
    List<Integer> result = new ArrayList<>();
    traverseInOrder(root, result);
    return result;
  }

  private void traverseInOrder(TreeNode node, List<Integer> result) {
    if (node != null) {
      traverseInOrder(node.left, result);
      result.add(node.value);
      traverseInOrder(node.right, result);
    }
  }

  public List<Integer> traversePreOrder() {
    List<Integer> result = new ArrayList<>();
    traversePreOrder(root, result);
    return result;
  }

  private void traversePreOrder(TreeNode node, List<Integer> result) {
    if (node != null) {
      result.add(node.value);
      traversePreOrder(node.left, result);
      traversePreOrder(node.right, result);
    }
  }

  public List<Integer> traversePostOrder() {
    List<Integer> result = new ArrayList<>();
    traversePostOrder(root, result);
    return result;
  }

  private void traversePostOrder(TreeNode node, List<Integer> result) {
    if (node != null) {
      traversePostOrder(node.left, result);
      traversePostOrder(node.right, result);
      result.add(node.value);
    }
  }
}