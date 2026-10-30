/*
 * Oscar Zhu
 * October 29, 2026
 * Tests MyLinkedList, LinkedStack and bracket matching
 */

import java.util.NoSuchElementException;

public class LinkedListTester {
    /**
     * Runs the homework tests
     * Precondition: none
     * Big-O: O(n), where n is the number of items in the large-list test
     *
     * @param args command-line arguments, unused
     */
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        System.out.println("1. new list: " + list
                + ", size " + list.size()
                + ", isEmpty " + list.isEmpty());
        list.addLast("b");
        list.addLast("c");
        list.addFirst("a");
        System.out.println("2. addLast b, addLast c, addFirst a: "
                + list + ", size " + list.size());
        System.out.println("3. get(0) = " + list.get(0) + ", get(2) = " + list.get(2));
        try {
            list.get(3);
            System.out.println("4. FAIL: no exception");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("4. get(3) rejected: " + e.getMessage());
        }
        try {
            list.get(-1);
            System.out.println("5. FAIL: no exception");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("5. get(-1) rejected: " + e.getMessage());
        }
        list.add(1, "x");
        System.out.println("6. add(1, \"x\"): " + list);
        list.add(4, "z");
        System.out.println("7. add(4, \"z\"): " + list + ", getLast " + list.getLast());
        System.out.println("8. indexOf(\"c\") = " + list.indexOf("c")
                + ", indexOf(\"q\") = " + list.indexOf("q")
                + ", contains(\"x\") = " + list.contains("x"));
        String removed = list.remove(1);
        System.out.println("9. remove(1) returned " + removed + ": " + list);
        removed = list.remove(3);
        System.out.println("10. remove(3) returned " + removed + ": " + list + ", getLast " + list.getLast());
        removed = list.removeFirst();
        System.out.println("11. removeFirst returned " + removed + ": " + list + ", size " + list.size());
        list.addLast("d");
        list.reverse();
        System.out.println("12. addLast d, reverse: " + list + ", getLast " + list.getLast());
        list.addLast("e");
        System.out.println("13. addLast e after reverse: " + list);

        for (int i = 0; i < 4; i++) {
            list.removeFirst();
        }
        System.out.println("14. four removeFirsts: " + list + ", size " + list.size() + ", isEmpty " + list.isEmpty());
        try {
            list.removeFirst();
            System.out.println("15. FAIL: no exception");
        } catch (NoSuchElementException e) {
            System.out.println("15. removeFirst on empty rejected: " + e.getMessage());
        }
        list.addLast("solo");
        System.out.println("16. addLast to emptied list: " + list
                + ", getFirst " + list.getFirst()
                + ", getLast " + list.getLast());
        try {
            list.addFirst(null);
            System.out.println("17. FAIL: no exception");
        } catch (IllegalArgumentException e) {
            System.out.println("17. addFirst(null) rejected: " + e.getMessage());
        }
        MyLinkedList<Integer> numbers = new MyLinkedList<>();
        for (int i = 1; i <= 100000; i++) {
            numbers.addLast(i);
        }
        System.out.println("18. 100,000 addLasts: size " + numbers.size() + ", getLast " + numbers.getLast());
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("type a");
        stack.push("type b");
        stack.push("delete b");
        System.out.print("19. stack pops:");
        while (!stack.isEmpty()) {
            System.out.print(" [" + stack.pop() + "]");
        }
        System.out.println();
        try {
            stack.pop();
            System.out.println("20. FAIL: no exception");
        } catch (NoSuchElementException e) {
            System.out.println("20. pop on empty rejected: " + e.getMessage());
        }
        System.out.println("21. isBalanced:");
        System.out.println("  \"(a[b]{c})\" " + Brackets.isBalanced("(a[b]{c})"));
        System.out.println("  \"([)]\" " + Brackets.isBalanced("([)]"));
        System.out.println("  \"((\" " + Brackets.isBalanced("(("));
        System.out.println("  \")(\" " + Brackets.isBalanced(")("));
        System.out.println("  \"\" " + Brackets.isBalanced(""));
        System.out.println("  \"int[] a = {1, 2};\" " + Brackets.isBalanced("int[] a = {1, 2};"));
    }
}
