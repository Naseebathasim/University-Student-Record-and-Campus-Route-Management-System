public class ActionStack {

    private class StackNode {
        ActionRecord record;
        StackNode next;

        StackNode(ActionRecord record) {
            this.record = record;
            this.next = null;
        }
    }

    private StackNode top;

    public ActionStack() {
        top = null;
    }

    // Push action
    public void push(ActionRecord record) {

        StackNode newNode = new StackNode(record);

        newNode.next = top;
        top = newNode;
    }

    // Pop action
    public ActionRecord pop() {

        if (top == null) {
            return null;
        }

        ActionRecord record = top.record;

        top = top.next;

        return record;
    }

    // Peek action
    public ActionRecord peek() {

        if (top == null) {
            return null;
        }

        return top.record;
    }

    // Check empty
    public boolean isEmpty() {
        return top == null;
    }

    // Display stack
    public void display() {

        if (top == null) {
            System.out.println("\nNo recent actions.");
            return;
        }

        StackNode current = top;

        System.out.println("\n========== RECENT ACTIONS ==========");

        int number = 1;

        while (current != null) {

            System.out.println(number + ". " + current.record);

            current = current.next;
            number++;
        }

        System.out.println("====================================");
    }
}