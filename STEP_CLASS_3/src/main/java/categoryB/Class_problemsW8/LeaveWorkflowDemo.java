package categoryB.Class_problemsW8;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
interface LeavePolicy {
    int getMaximumDays();
}
class FullTimePolicy implements LeavePolicy {
    public int getMaximumDays() {
        return 20;
    }
}
class PartTimePolicy implements LeavePolicy {
    public int getMaximumDays() {
        return 5;
    }
}
class ContractorPolicy implements LeavePolicy {
    public int getMaximumDays() {
        return 2;
    }
}
class LeaveEmployee {
    private final String name;
    private final LeavePolicy policy;
    LeaveEmployee(String name, LeavePolicy policy) {
        this.name = name;
        this.policy = policy;
    }
    String getName() {
        return name;
    }
    boolean canTakeLeave(int days) {
        return days > 0 && days <= policy.getMaximumDays();
    }
}
class LeaveRequest {
    private final LeaveEmployee employee;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private String status = "Pending";
    LeaveRequest(LeaveEmployee employee, LocalDate startDate, LocalDate endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    boolean submit() {
        if (endDate.isBefore(startDate)) {
            System.out.println("End date cannot be before start date.");
            return false;
        }
        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        if (!employee.canTakeLeave((int) days)) {
            System.out.println(employee.getName() + " cannot request " + days + " days under their leave policy.");
            return false;
        }
        System.out.println("Leave request submitted for " + employee.getName() + " (" + startDate + " to " + endDate + "). Status: " + status + ".");
        return true;
    }
    void review(String decision) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from " + status + " to " + decision + ".");
            return;
        }
        if (!decision.equals("Approved") && !decision.equals("Rejected")) {
            System.out.println("Decision must be Approved or Rejected.");
            return;
        }
        status = decision;
        System.out.println(employee.getName() + "'s leave request (" + startDate + " to " + endDate + ") " + status.toLowerCase() + ". Status: " + status + ".");
    }
    void trySetStatus(String newStatus) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from " + status + " to " + newStatus + ".");
        } else {
            System.out.println("Use review() to approve or reject a pending request.");
        }
    }
}

public class LeaveWorkflowDemo {
    public static void main(String[] args) {
        LeaveEmployee john = new LeaveEmployee("John", new FullTimePolicy());
        LeaveRequest johnRequest = new LeaveRequest(john,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 5));
        if (johnRequest.submit()) {
            johnRequest.review("Approved");
            johnRequest.trySetStatus("Pending");
        }

        LeaveEmployee jane = new LeaveEmployee("Jane", new PartTimePolicy());
        LeaveRequest janeRequest = new LeaveRequest(jane,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 11));

        if (janeRequest.submit()) {
            janeRequest.review("Rejected");
        }
    }
}