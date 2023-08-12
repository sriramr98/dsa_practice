package org.dsa_practise.algos;

import org.dsa_practise.dsa.linkedlist.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    private final int capacity;
    private final HashMap<Integer, DoublyLinkedList<Integer, LFUNode>> freqMap;
    private final HashMap<Integer, DoublyLinkedList.LinkedListNode<Integer, LFUNode>> nodeCache;
    int minFrequency = 0;

    public static class LFUNode {
        private int value;
        private int freq;

        public LFUNode(int value) {
            this.value = value;
            this.freq = 1;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getFreq() {
            return freq;
        }

        public void setFreq(int freq) {
            this.freq = freq;
        }
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        freqMap = new HashMap<>();
        nodeCache = new HashMap<>();
    }

    public void set(int key, int value) {
        if (this.get(key) == -1) {
            // new entry
            evictIfRequired();

            // Since new key and not used yet, default frequency to 1
            minFrequency = 1;
            DoublyLinkedList<Integer, LFUNode> currentMinFrequencyList = freqMap.getOrDefault(minFrequency, new DoublyLinkedList<>());
            DoublyLinkedList.LinkedListNode<Integer, LFUNode> insertedNode = currentMinFrequencyList.insertAtTail(key, new LFUNode(value));
            nodeCache.put(key, insertedNode);
            freqMap.put(minFrequency, currentMinFrequencyList);
        } else {
            // old entry
            // Doubt: Do we increase frequency if updating value of existing node?
            // Implementation: Assuming we only update frequency for GET

            DoublyLinkedList.LinkedListNode<Integer, LFUNode> node = nodeCache.get(key);
            node.getValue().setValue(value);
        }
    }

    public int get(int key) {
        if (!nodeCache.containsKey(key)) {
            return -1;
        }

        DoublyLinkedList.LinkedListNode<Integer, LFUNode> node = nodeCache.get(key);
        int currentNodeFreq = node.getValue().getFreq();
        DoublyLinkedList<Integer, LFUNode> currentFreqList = freqMap.get(currentNodeFreq);

        currentFreqList.removeNode(node);

        int nextFrequency = currentNodeFreq + 1;
        node.getValue().setFreq(nextFrequency);

        DoublyLinkedList<Integer, LFUNode> nextFreqList = freqMap.getOrDefault(nextFrequency, new DoublyLinkedList<>());
        DoublyLinkedList.LinkedListNode<Integer, LFUNode> insertedNode = nextFreqList.insertAtTail(key, node.getValue());

        nodeCache.put(key, insertedNode);
        freqMap.put(nextFrequency, nextFreqList);

        return node.getValue().getValue();
    }

    private void evictIfRequired() {
        if (capacity > nodeCache.size() || nodeCache.isEmpty()) {
            return;
        }

        if (minFrequency == 0 || !freqMap.containsKey(minFrequency)) {
            return;
        }

        DoublyLinkedList<Integer, LFUNode> minFreqList = freqMap.get(minFrequency);
        if (minFreqList.size == 0) {
            minFrequency++;
            evictIfRequired();
            return;
        }

        DoublyLinkedList.LinkedListNode<Integer, LFUNode> deletedNode = minFreqList.removeHead();
        nodeCache.remove(deletedNode.getKey());
    }

    void print() {
        for (Map.Entry<Integer, DoublyLinkedList.LinkedListNode<Integer, LFUNode>> entry : nodeCache.entrySet()) {
            System.out.print("(" + entry.getKey() + ", " + entry.getValue().getValue() + ")");
        }
        System.out.println("");
    }

}
