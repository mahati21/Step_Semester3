package categoryB.Assignment_problemsW6;

class GymMember3 {
    protected String memberId;
    protected int monthlyFee;
    private int sessionsAttended;
    private int[] lateFeeHistory = new int[10];
    private int lateFeeCount = 0;
    public GymMember3(String memberId, int monthlyFee) {
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
    protected void chargeLateFee(int amount) {
        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
        }
    }
    public int[] getLateFeeHistory() {
        int[] copy = new int[lateFeeCount];
        for (int i = 0; i < lateFeeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }
        return copy;
    }
    public int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < lateFeeCount; i++) {
            total += lateFeeHistory[i];
        }
        return total;
    }
}
class PremiumMember2 extends GymMember3 {
    private String trainerName;
    public PremiumMember2(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }
    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}
