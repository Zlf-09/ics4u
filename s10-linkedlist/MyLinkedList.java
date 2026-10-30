/*
 * Oscar Zhu
 * October 28, 2026
 * Implements a generic singly linked list with a head, tail and size.
 */

import java.util.NoSuchElementException;

public class MyLinkedList<E> {
    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Returns the number of items in the list
     * Precondition: none
     * Big-O: O(1).
     *
     * @return the number of items
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the list is empty
     * Precondition: none
     * Big-O: O(1)
     *
     * @return true if the list contains no items
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds an item at the front of the list
     * Precondition: value is not null
     * Big-O: O(1)
     *
     * @param value the item to add
     * @throws IllegalArgumentException if value is null
     */
    public void addFirst(E value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        Node<E> node = new Node<>(value);
        node.next = head;
        head = node;
        if (size == 0) {
            tail = node;
        }
        size++;
    }

    /**
     * Adds an item at the end of the list
     * Precondition: value is not null
     * Big-O: O(1)
     *
     * @param value the item to add
     * @throws IllegalArgumentException if value is null
     */
    public void addLast(E value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        Node<E> node = new Node<>(value);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * Returns the first item in the list.
     * Precondition: the list is not empty
     * Big-O: O(1)
     *
     * @return the first item
     * @throws NoSuchElementException if the list is empty
     */
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return head.value;
    }

    /**
     * Returns the last item in the list
     * Precondition: the list is not empty
     * Big-O: O(1)
     *
     * @return the last item
     * @throws NoSuchElementException if the list is empty
     */
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return tail.value;
    }

    /**
     * Returns the item at the given index
     * Precondition: index is between 0 and size - 1
     * Big-O: O(n)
     *
     * @param index the position to read
     * @return the item at index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + ", size " + size);
        }
        Node<E> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }

    /**
     * Inserts an item at the given index
     * Precondition: index is between 0 and size, and value is not null
     * Big-O: O(n)
     *
     * @param index the position for the new item
     * @param value the item to add
     * @throws IndexOutOfBoundsException if index is out of range
     * @throws IllegalArgumentException  if value is null
     */
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index " + index + ", size " + size);
        }
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        if (index == 0) {
            addFirst(value);
            return;
        }
        if (index == size) {
            addLast(value);
            return;
        }
        Node<E> prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        Node<E> node = new Node<>(value);
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    /**
     * Removes and returns the first item
     * Precondition: the list is not empty
     * Big-O: O(1)
     *
     * @return the removed item
     * @throws NoSuchElementException if the list is empty
     */
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        E removed = head.value;
        ;
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
        return removed;
    }

    /**
     * Removes and returns the item at the given index
     * Precondition: index is between 0 and size - 1
     * Big-O: O(n)
     *
     * @param index the position to remove
     * @return the removed item
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + ", size " + size);
        }
        if (index == 0) {
            return removeFirst();
        }
        Node<E> prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        Node<E> removed = prev.next;
        prev.next = removed.next;

        if (removed == tail) {
            tail = prev;
        }
        size--;
        return removed.value;
    }

    /**
     * Finds the first occurrence of an item
     * Precondition: none
     * Big-O: O(n)
     *
     * @param value the item to find
     * @return the first matching index, or -1 if not found
     */
    public int indexOf(E value) {
        Node<E> cur = head;
        int index = 0;
        while (cur != null) {
            if (cur.value.equals(value)) {
                return index;
            }
            cur = cur.next;
            index++;
        }
        return -1;
    }

    /**
     * Checks whether the list contains an item
     * Precondition: none
     * Big-O:O(n)
     *
     * @param value the item to find
     * @return true if the item is in the list
     */
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    /**
     * Reverses the list in place without creating new nodes
     * Precondition: none
     * Big-O: O(n)
     */
    public void reverse(){
        Node<E> prev = null;
        Node<E> cur = head;
        tail = head;
        while (cur != null) {
            Node<E> next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head = prev;
    }

    /**
     * Returns the items in square brackets, separated by commas
     * Precondition: none
     * Big-O: O(n), assuming each item's string conversion is O(1)
     *
     * @return the string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> cur = head;
        while (cur != null) {
            result.append(cur.value);
            if (cur.next != null) {
                result.append(", ");
            }
            cur = cur.next;
        }
        result.append("]");
        return result.toString();
    }
}
