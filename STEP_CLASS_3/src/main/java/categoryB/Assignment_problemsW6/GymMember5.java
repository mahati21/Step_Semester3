package categoryB.Assignment_problemsW6;

class GymMember5 {
    private static int memberCounter = 2000;
    private static int membersEnrolled = 0;
    public final String membershipNumber;
    protected int monthlyFee;
    private int feesPaid;
    public GymMember5 (String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        this.monthlyFee = monthlyFee;
        memberCounter++;
        membersEnrolled++;
        membershipNumber = "GYM-" + memberCounter;
    }
    public GymMember5 (int monthlyFee) {
        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("Invalid fee");
        }
        this.monthlyFee = monthlyFee;
        memberCounter++;
        membersEnrolled++;
        membershipNumber = "GYM-" + memberCounter;
    }
    public void payFee(int amount) {
        feesPaid += amount;
    }
    public void payFee(int amount, String mode) {
        System.out.println("Payment mode: " + mode);
        payFee(amount);
    }
    public int getFeesPaid() {
        return feesPaid;
    }
    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'G' && Character.isDigit(code.charAt(1)) && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }
    public static int getMembersEnrolled() {
        return membersEnrolled;
    }
}
class GroupClassMember2 extends GymMember5 {
    private String className;
    public GroupClassMember2 (int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }
    public GroupClassMember2 (String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }
}
class GymAudit {
    static String processWeeklyCheckIn(GymMember5[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;
        for (GymMember5 member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (member instanceof GroupClassMember2) {
                group++;
            }
            else {
                individual++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }
}
