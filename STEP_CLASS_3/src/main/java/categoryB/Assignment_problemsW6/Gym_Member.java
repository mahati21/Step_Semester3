package categoryB.Assignment_problemsW6;

class Gym_Member {
    protected String memberId;
    protected int monthlyFee;
    private int sessionsAttended;
    public Gym_Member(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Invalid monthly fee");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }
    public void attendSession() {
        sessionsAttended++;
    }
    public int getSessionsAttended() {
        return sessionsAttended;
    }
    public void displayInfo() {
        System.out.println("Standard Member | Sessions: " + sessionsAttended);
    }
}
class Premium_Member extends Gym_Member {
    private String trainerName;
    public Premium_Member(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }
    public String getTrainerName() {
        return trainerName;
    }
    @Override
    public void displayInfo() {
        System.out.println("Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended());
    }
}
class EliteMember extends Premium_Member {
    private String lockerNumber;
    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }
    @Override
    public void displayInfo() {
        System.out.println("Elite Member | Trainer: " + getTrainerName() + " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended());
    }
}
class GroupClassMember extends Gym_Member {
    private String className;
    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }
    @Override
    public void displayInfo() {
        System.out.println("Group Class Member | Class: " + className + " | Sessions: " + getSessionsAttended());
    }
}
class GymUtils {
    static String classifyGeneration(Gym_Member member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        }
        if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }
        return "Standard member";
    }
    static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }
        return total;
    }
}
