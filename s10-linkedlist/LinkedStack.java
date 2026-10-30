/*
 * Oscar Zhu
 * October 29, 2026
 * Implements a last-in, first-out stack using MyLinkedList
 */

import java.util.NoSuchElementException;

public class LinkedStack<E> {
    private final MyLinkedList<E> items = new MyLinkedList<>();

    /**
     * Adds an item to the top of the stack
     * Precondition: value is not null
     * Big-O: O(1)
     *
     * @param value the item to push
     * @throws IllegalArgumentException if value is null
     */
    public void push(E value) {
        items.addFirst(value);
    }

    /**
     * Removes and returns the top item
     * Precondition: the stack is not empty
     * Big-O: O(1)
     *
     * @return the removed top item
     * @throws NoSuchElementException if the stack is empty
     */
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("stack is empty");
        }
        return items.removeFirst();
    }

    /**
     * Returns the top item without removing it
     * Precondition: the stack is not empty
     * Big-O: O(1)
     *
     * @return the top item
     * @throws NoSuchElementException if the stack is empty
     */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("stack is empty");
        }
        return items.getFirst();
    }

    /**
     * Checks whether the stack is empty
     * Precondition: none
     * Big-O: O(1)
     *
     * @return true if the stack has no items
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Returns the number of items in the stack
     * Precondition: none
     * Big-O: O(1)
     *
     * @return the number of items
     */
    public int size() {
        return items.size();
    }
}
