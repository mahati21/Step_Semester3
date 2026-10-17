package categoryB.Assignment_problemsW10;

public class W10ReverseQueue {
    static class W10ReverseNode {
        int data;
        W10ReverseNode next;

        W10ReverseNode(int data) {
            this.data = data;
        }
    }
    public static W10ReverseNode reverse(W10ReverseNode head) {
        W10ReverseNode previous = null;
        W10ReverseNode current = head;

        while (current != null) {
            W10ReverseNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    public static void display(W10ReverseNode head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
    public static void main(String[] args) {
        W10ReverseNode head = new W10ReverseNode(1);
        head.next = new W10ReverseNode(2);
        head.next.next = new W10ReverseNode(3);
        head.next.next.next = new W10ReverseNode(4);

        head = reverse(head);
        display(head);
    }
}
