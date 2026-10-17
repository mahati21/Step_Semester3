package categoryB.Assignment_problemsW10;

public class W10PriorityTokenInsertion {
    static class W10PriorityNode {
        int data;
        W10PriorityNode next;

        W10PriorityNode(int data) {
            this.data = data;
        }
    }
    static class W10PriorityList {
        W10PriorityNode head;

        void addFirst(int token) {
            W10PriorityNode node = new W10PriorityNode(token);
            node.next = head;
            head = node;
        }
        void addLast(int token) {
            W10PriorityNode node = new W10PriorityNode(token);
            if (head == null) {
                head = node;
                return;
            }
            W10PriorityNode current = head;

            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }

        void insertAt(int index, int token) {
            if (index < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (index == 0) {
                addFirst(token);
                return;
            }
            W10PriorityNode current = head;

            for (int i = 0; current != null && i < index - 1; i++) {
                current = current.next;
            }
            if (current == null) {
                throw new IndexOutOfBoundsException();
            }
            W10PriorityNode node = new W10PriorityNode(token);
            node.next = current.next;
            current.next = node;
        }

        void display() {
            W10PriorityNode current = head;
            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }
    public static void main(String[] args) {
        W10PriorityList list = new W10PriorityList();

        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        list.display();
    }
}
