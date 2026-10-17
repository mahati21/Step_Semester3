package categoryB.Assignment_problemsW10;

public class W10CancelledOrdersCleanup {
    static class W10OrderNode {
        int data;
        W10OrderNode next;
        W10OrderNode(int data) {
            this.data = data;
        }
    }

    public static W10OrderNode removeAll(
            W10OrderNode head, int code) {
        while (head != null && head.data == code) {
            head = head.next;
        }
        W10OrderNode current = head;
        while (current != null && current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    public static void display(W10OrderNode head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        W10OrderNode head = new W10OrderNode(5);
        head.next = new W10OrderNode(3);
        head.next.next = new W10OrderNode(5);
        head.next.next.next = new W10OrderNode(8);
        head.next.next.next.next = new W10OrderNode(5);
        head = removeAll(head, 5);
        display(head);
    }
}
