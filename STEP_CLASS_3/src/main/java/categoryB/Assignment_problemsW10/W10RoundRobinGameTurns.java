package categoryB.Assignment_problemsW10;

public class W10RoundRobinGameTurns {
    static class W10PlayerNode {
        String name;
        W10PlayerNode next;

        W10PlayerNode(String name) {
            this.name = name;
        }
    }
    static class W10PlayerCircle {
        W10PlayerNode tail;

        void addLast(String name) {
            W10PlayerNode node = new W10PlayerNode(name);

            if (tail == null) {
                tail = node;
                tail.next = tail;
            }
            else {
                node.next = tail.next;
                tail.next = node;
                tail = node;
            }
        }
        void printTurns(int turns) {
            if (tail == null) {
                return;
            }

            W10PlayerNode current = tail.next;
            for (int i = 0; i < turns; i++) {
                System.out.print(current.name);
                if (i < turns - 1) {
                    System.out.print(" ");
                }
                current = current.next;
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        W10PlayerCircle players = new W10PlayerCircle();

        players.addLast("Asha");
        players.addLast("Ravi");
        players.addLast("Neha");

        players.printTurns(7);
    }
}
