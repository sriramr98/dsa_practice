package org.dsa_practise.dsa.linkedlist;

public class DoublyLinkedList<KEY, VALUE> {

    public static class LinkedListNode<K, V> {
        K key;
        V value;
        LinkedListNode<K, V> next;
        LinkedListNode<K, V> prev;

        public LinkedListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public LinkedListNode<K, V> getNext() {
            return next;
        }

        public LinkedListNode<K, V> getPrev() {
            return prev;
        }

        public void setNext(LinkedListNode<K, V> next) {
            this.next = next;
        }

        public void setPrev(LinkedListNode<K, V> prev) {
            this.prev = prev;
        }
    }

    public LinkedListNode<KEY, VALUE> head;
    public LinkedListNode<KEY, VALUE> tail;
    public int size;

    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public LinkedListNode<KEY, VALUE> insertAtHead(KEY key, VALUE data) {
        LinkedListNode<KEY, VALUE> newNode = new LinkedListNode<>(key, data);
        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        this.size++;

        return newNode;
    }

    public LinkedListNode<KEY, VALUE> insertAtTail(KEY key, VALUE data) {
        LinkedListNode<KEY, VALUE> newNode = new LinkedListNode<>(key, data);
        if (this.tail == null){
            this.tail = newNode;
            this.head = newNode;
            newNode.next = null;
            newNode.prev = null;
        }

        else{
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
            newNode.next = null;
        }
        this.size++;

        return newNode;
    }

    public LinkedListNode<KEY, VALUE> getHead(){
        return this.head;
    }

    public LinkedListNode<KEY, VALUE> getTail(){
        return this.tail;
    }

    public LinkedListNode<KEY, VALUE> removeNode(LinkedListNode<KEY, VALUE> node){
        if(node == null){
            return null;
        }

        if(node.prev != null){
            node.prev.next = node.next;
        }

        if(node.next != null){
            node.next.prev = node.prev;
        }

        if(node == this.head){
            this.head = this.head.next;
        }
        if(node == this.tail){
            this.tail = this.tail.prev;
        }
        this.size--;
        return node;
    }

    public void remove(VALUE data){
        LinkedListNode<KEY, VALUE> i = this.getHead();
        while(i != null){
            if(i.value.equals(data)){
                this.removeNode(i);
            }
            i = i.next;
        }
    }

    public LinkedListNode<KEY, VALUE> removeHead(){
        return this.removeNode(this.head);
    }

    public LinkedListNode<KEY, VALUE> removeTail(){
        return this.removeNode(this.tail);
    }
}
