package com.revature.project_0.util;

public class LinkedList<T> implements List<T>{

    private int size;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public void add(T data) {

        if (data == null) {
            throw new IllegalArgumentException("This linked list does not accept null arguments");
        }

        Node<T> newNode = new Node<T>(data);
            if (head == null) {
                tail = head = newNode;
            } else {
                tail.nextNode = newNode;
                newNode.prevNode = tail;
                tail = newNode;
            }
            size++;

    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("The index does not exist");
        }
        Node<T> runner = head;
        for (int i = 0; i < size; i++ ) {
            if (i == index) {
                return runner.data;
            }
            runner = runner.nextNode;
        }
        return null;
    }

    @Override
    public boolean contains(T data) {

        if (head == null) {
            return false;
        }

        Node<T> runner = head;

        for (int i = 0; i < size; i++) {

            if (data == runner.data) {
                return true;
            }
            runner = runner.nextNode;
        }
        return false;

    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<T> {
        T data;
        Node<T> nextNode;
        Node<T> prevNode;

        Node(T data) { this.data = data; };

        Node(T data, Node<T> nextNode, Node<T> prevNode) {
            this.data = data;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }
    }

}
