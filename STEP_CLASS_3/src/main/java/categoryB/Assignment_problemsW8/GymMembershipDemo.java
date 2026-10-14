package categoryB.Assignment_problemsW8;

interface MembershipPlan {
    String getName();
    int getMonths();
    double calculateFee();
}
class MonthlyPlan implements MembershipPlan {
    public String getName() { return "Monthly"; }
    public int getMonths() { return 1; }
    public double calculateFee() { return 1000.00; }
}
class QuarterlyPlan implements MembershipPlan {
    public String getName() { return "Quarterly"; }
    public int getMonths() { return 3; }
    public double calculateFee() { return 3000.00 * 0.90; }
}
class AnnualPlan implements MembershipPlan {
    public String getName() { return "Annual"; }
    public int getMonths() { return 12; }
    public double calculateFee() { return 12000.00 * 0.75; }
}
class Member {
    private final String name;
    private Membership membership;
    Member(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
    void buyMembership(MembershipPlan plan) {
        membership = new Membership(plan);
        System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: %s.%n", plan.getName(), name, plan.calculateFee(), membership.getStatus());
    }
    void checkIn() {
        if (membership == null) {
            System.out.println("Check-in denied: " + name + " has no membership.");
        } else if (membership.canCheckIn()) {
            System.out.println(name + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " + name + "'s membership is " + membership.getStatus() + ".");
        }
    }
    Membership getMembership() {
        return membership;
    }
}
class Membership {
    private final MembershipPlan plan;
    private Status status = Status.ACTIVE;

    private enum Status {
        ACTIVE, FROZEN, EXPIRED
    }

    Membership(MembershipPlan plan) {
        this.plan = plan;
    }
    String getStatus() {
        return status.toString().substring(0, 1) + status.toString().substring(1).toLowerCase();
    }
    boolean canCheckIn() {
        return status == Status.ACTIVE;
    }

    void freeze(String memberName) {
        if (status == Status.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
        } else if (status == Status.FROZEN) {
            System.out.println("Membership is already Frozen.");
        } else {
            status = Status.FROZEN;
            System.out.println(memberName + "'s membership frozen. Status: " + getStatus() + ".");
        }
    }

    void unfreeze(String memberName) {
        if (status == Status.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else if (status == Status.ACTIVE) {
            System.out.println("Membership is already Active.");
        } else {
            status = Status.ACTIVE;
            System.out.println(memberName + "'s membership unfrozen. Status: " + getStatus() + ".");
        }
    }

    void expire(String memberName) {
        if (status == Status.EXPIRED) {
            System.out.println("Membership is already Expired.");
        } else {
            status = Status.EXPIRED;
            System.out.println(memberName + "'s membership expired. Status: " + getStatus() + ".");
        }
    }
}

public class GymMembershipDemo {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");
        asha.buyMembership(new QuarterlyPlan());
        ravi.buyMembership(new MonthlyPlan());

        asha.checkIn();
        asha.getMembership().freeze(asha.getName());
        asha.checkIn();

        ravi.getMembership().expire(ravi.getName());
        ravi.getMembership().freeze(ravi.getName());
    }
}
