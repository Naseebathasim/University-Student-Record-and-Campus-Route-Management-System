public class StudentStack<T> {

    // Internal node for the singly linked list
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public StudentStack() {
        top = null;
        size = 0;
    }

    /** Pushes a new element on top of the stack. */
    public void push(T data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /** Removes and returns the element at the top of the stack. */
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. No actions to undo/remove.");
            return null;
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    /** Returns the top element without removing it. */
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    /** Displays all elements from top (most recent) to bottom (oldest). */
    public void display() {
        if (isEmpty()) {
            System.out.println("No recent actions recorded.");
            return;
        }
        System.out.println("----- Recent Actions (Most Recent First) -----");
        Node current = top;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.data);
            current = current.next;
            count++;
        }
        System.out.println("-----------------------------------------------");
    }
}