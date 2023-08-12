package org.dsa_practise.dsa.trees;

import com.sun.source.tree.Tree;
import lombok.Data;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    @Data
    static class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T data) {
            this.data = data;
        }

        @Override
        public int compareTo(TreeNode<T> o) {
            return this.data.compareTo(o.data);
        }
    }

    TreeNode<T> root;

    public boolean insert(T data) {
        TreeNode<T> node = new TreeNode<>(data);
        if (root == null) {
            root = node;
        } else {
            TreeNode<T> temp = root;
            return insertAtNode(temp, node);
        }

        return true;
    }

    private boolean insertAtNode(TreeNode<T> node, TreeNode<T> nodeToInsert) {
        if (node == null) {
           return false;
        }

        boolean isDataLesserThanNode = node.compareTo(nodeToInsert) > 0;

        if (isDataLesserThanNode) {
            if (node.left == null) {
                node.left = nodeToInsert;
            } else {
                return insertAtNode(node.left, nodeToInsert);
            }
        } else {
            if (node.right == null) {
                node.right = nodeToInsert;
            } else {
                return insertAtNode(node.right, nodeToInsert);
            }
        }

        return true;
    }

    public List<T> traverseDepth() {
        if (root == null) {
            return Collections.emptyList();
        }

        Stack<TreeNode<T>> traversalStack = new Stack<>();
        TreeNode<T> temp = root;
        traversalStack.push(temp);

        List<T> result = new ArrayList<>();

        while (!traversalStack.isEmpty()) {
            TreeNode<T> node = traversalStack.pop();
            if (node.right != null) {
                traversalStack.push(node.right);
            }

            if (node.left != null) {
                traversalStack.push(node.left);
            }

            result.add(node.data);
        }

        return result;

    }

    public List<T> traverseBreadth() {
        if (root == null) {
            return Collections.emptyList();
        }

        Queue<TreeNode<T>> traversalStack = new LinkedList<>();
        TreeNode<T> temp = root;
        traversalStack.add(temp);

        List<T> results = new ArrayList<>();
        while (!traversalStack.isEmpty()) {
            TreeNode<T> node = traversalStack.poll();
            if (node.left != null) {
                traversalStack.add(node.left);
            }

            if (node.right != null) {
                traversalStack.add(node.right);
            }

            results.add(node.data);
        }

        return results;
    }

    public boolean hasData(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> temp = root;
        return hasDataFromRoot(temp, data);
    }

    private boolean hasDataFromRoot(TreeNode<T> node, T data) {
        if (node == null) {
            return false;
        }

        int compared = node.getData().compareTo(data);
        if (compared == 0) {
            return true;
        } else if (compared > 0) {
            return hasDataFromRoot(node.getLeft(), data);
        } else {
            return hasDataFromRoot(node.getRight(), data);
        }
    }
}
