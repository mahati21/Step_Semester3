package categoryB.Class_problemsW10;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class W10StudentClubRegistration {
    static class W10ClubMember {
        String rollNumber;
        String name;

        W10ClubMember(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;

            if (!(obj instanceof W10ClubMember)) return false;

            W10ClubMember other = (W10ClubMember) obj;
            return rollNumber.equals(other.rollNumber);
        }
        @Override
        public int hashCode() {
            return Objects.hash(rollNumber);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<W10ClubMember> members = new HashSet<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("ADD")) {
                String roll = sc.next();
                String name = sc.next();

                if (members.add(new W10ClubMember(roll, name))) {
                    System.out.println("Added");
                }
                else {
                    System.out.println("duplicate rejected");
                }

            }
            else if (operation.equals("CONTAINS")) {
                String roll = sc.next();
                String name = sc.next();
                System.out.println("contains: " + members.contains(new W10ClubMember(roll, name)));
            }
        }
        System.out.println("member count " + members.size());
        sc.close();
    }
}
