public class ServiceQueue {

    private class QueueNode {

        ServiceRequest request;
        QueueNode next;

        QueueNode(ServiceRequest request) {
            this.request = request;
            this.next = null;
        }
    }

    private QueueNode front;
    private QueueNode rear;

    public ServiceQueue() {
        front = null;
        rear = null;
    }

    // Add request
    public void enqueue(ServiceRequest request) {

        QueueNode newNode = new QueueNode(request);

        if (rear == null) {
            front = newNode;
            rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    // Process next request
    public ServiceRequest dequeue() {

        if (front == null) {
            return null;
        }

        ServiceRequest request = front.request;

        front = front.next;

        if (front == null) {
            rear = null;
        }

        return request;
    }

    // View next request
    public ServiceRequest peek() {

        if (front == null) {
            return null;
        }

        return front.request;
    }

    // Check empty
    public boolean isEmpty() {
        return front == null;
    }

    // Display queue
    public void display() {

        if (front == null) {
            System.out.println("\nNo service requests in the queue.");
            return;
        }

        QueueNode current = front;

        System.out.println("\n========== SERVICE REQUEST QUEUE ==========");

        int number = 1;

        while (current != null) {

            System.out.println(number + ". " + current.request);

            current = current.next;
            number++;
        }

        System.out.println("==========================================");
    }
}