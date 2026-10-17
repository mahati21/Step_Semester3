package categoryB.Assignment_problemsW10;

public class W10TrainCoachesBothWays {
    static class W10CoachNode {
        String name;
        W10CoachNode prev;
        W10CoachNode next;

        W10CoachNode(String name) {
            this.name = name;
        }
    }
    static class W10TrainList {
        W10CoachNode head;
        W10CoachNode tail;

        void addLast(String name) {
            W10CoachNode node = new W10CoachNode(name);
            if (head == null) {
                head = tail = node;
            }
            else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }

        void remove(String name) {
            W10CoachNode current = head;

            while (current != null && !current.name.equals(name)) {
                current = current.next;
            }
            if (current == null) {
                return;
            }
            if (current.prev != null) {
                current.prev.next = current.next;
            }
            else {
                head = current.next;
            }
            if (current.next != null) {
                current.next.prev = current.prev;
            }
            else {
                tail = current.prev;
            }
        }

        void printForward() {
            W10CoachNode current = head;

            System.out.print("Forward: ");
            while (current != null) {
                System.out.print(current.name);
                if (current.next != null) {
                    System.out.print(" <-> ");
                }
                current = current.next;
            }
            System.out.println();
        }

        void printBackward() {
            W10CoachNode current = tail;
            System.out.print("Backward: ");

            while (current != null) {
                System.out.print(current.name);
                if (current.prev != null) {
                    System.out.print(" <-> ");
                }

                current = current.prev;
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        W10TrainList train = new W10TrainList();

        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");
        train.printForward();
        train.printBackward();
    }
}
