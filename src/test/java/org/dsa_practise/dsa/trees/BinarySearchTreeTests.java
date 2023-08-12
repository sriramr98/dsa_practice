package org.dsa_practise.dsa.trees;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTests {

    @Test
    void insertAtEmptyInsertsAtRoot() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(1);

        assertNotNull(tree.root);
        assertEquals(1, tree.root.data);
        assertNull(tree.root.left);
        assertNull(tree.root.right);
    }

    @Test
    void insertingSmallerThanRootInsertsAtLeftOfRoot() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(1);

        assertNotNull(tree.root);
        assertEquals(5, tree.root.data);
        assertNotNull(tree.root.left);
        assertNull(tree.root.right);

        assertEquals(1, tree.root.getLeft().data);
    }


    @Test
    void insertingLargerThanRootInsertsAtLeftOfRoot() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(7);

        assertNotNull(tree.root);
        assertEquals(5, tree.root.data);
        assertNull(tree.root.left);
        assertNotNull(tree.root.right);

        assertEquals(7, tree.root.getRight().data);
    }

    @Test
    void depthTraversalReturnsValidList() {
        BinarySearchTree<Integer> tree = integerBinarySearchTree();

        List<Integer> traversed = tree.traverseDepth();
        List<Integer> expected = List.of(7,5,1,6,9,8,12);

        assertEquals(expected, traversed);
    }

    @Test
    void breadthTraversalReturnsValidList() {
        BinarySearchTree<Integer> tree = integerBinarySearchTree();
        List<Integer> traversed = tree.traverseBreadth();
        List<Integer> expected = List.of(7, 5,9,1,6,8,12);

        assertEquals(expected, traversed);
    }

    @Test
    void hasDataReturnsTrueIfPresent() {
        BinarySearchTree<Integer> tree = integerBinarySearchTree();
        assertTrue(tree.hasData(5));
        assertTrue(tree.hasData(8));
        assertTrue(tree.hasData(12));
    }


    @Test
    void hasDataReturnsFalseIfPresent() {
        BinarySearchTree<Integer> tree = integerBinarySearchTree();
        assertFalse(tree.hasData(20));
        assertFalse(tree.hasData(4));
        assertFalse(tree.hasData(-5));
    }

    private BinarySearchTree<Integer> integerBinarySearchTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(7);
        tree.insert(5);
        tree.insert(9);
        tree.insert(1);
        tree.insert(6);
        tree.insert(8);
        tree.insert(12);

        return tree;
    }

}
