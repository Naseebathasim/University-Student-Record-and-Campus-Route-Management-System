
public class ServiceQueue<T> {

    // Internal node for the singly linked list
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public ServiceQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    /** Adds a new element to the rear of the queue. */
    public void enqueue(T data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    /** Removes and returns the element at the front of the queue. */
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. No pending service requests.");
            return null;
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null; // queue became empty
        }
        size--;
        return data;
    }

    /** Returns the front element without removing it. */
    public T peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    /** Displays all pending requests from front to rear. */
    public void display() {
        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("----- Pending Service Requests (Arrival Order) -----");
        Node current = front;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.data);
            current = current.next;
            count++;
        }
        System.out.println("-----------------------------------------------------");
    }
}