# Session 10 Homework Answers

## 1. Time complexity

| Method            | Worst-case time |
|-------------------|-----------------|
| addFirst(value)   | O(1)            |
| addLast(value)    | O(1)            |
| get(index)        | O(n)            |
| add(index, value) | O(n)            |
| removeFirst()     | O(1)            |
| remove(index)     | O(n)            |
| reverse()         | O(n)            |

My list stores both head and tail, so adding at either end takes O (1).
Removing the last node still takes O (n), because a singly linked list must traverse from head to find the node before
tail.

## 2. Reversing the list

| State          | List      | head | tail |
|----------------|-----------|------|------|
| Before reverse | [b, c, d] | b    | d    |
| After reverse  | [d, c, b] | d    | b    |

The three references are prev, cur and next.
I save cur.next in next before changing cur.next to prev, so I can still reach the unprocessed nodes.
The old head becomes tail, and after the loop, prev becomes head.

## 3. Indexed traversal benchmark

| List         | Sum        | Time         |
|--------------|------------|--------------|
| MyLinkedList | 1250025000 | 2640.4225 ms |
| ArrayList    | 1250025000 | 0.502416 ms  |

I filled both lists with the integers 1 to 50000 before timing.
I then used get (i) to read every element and calculate the sum.
MyLinkedList starts from head for each get (i), so reading all elements by index takes O (n^2).
ArrayList provides O (1) indexed access, so its whole loop takes O (n).
These are the results of one run; actual times may vary.

## 4. Stack operations

My stack pushes and pops at the head, so both operations take O (1).
If pop removed from the tail instead, it would take O (n), because a singly linked list must traverse from head to find
the node before tail.