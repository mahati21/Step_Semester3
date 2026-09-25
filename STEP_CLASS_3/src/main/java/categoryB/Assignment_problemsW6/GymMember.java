package categoryB.Assignment_problemsW6;

class GymMember {
    protected String memberId;
    protected int monthlyFee;
    private int sessionsAttended;
    public GymMember(String memberId, int monthlyFee) {
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
    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;
        for (String id : memberIds) {
            try {
                new GymMember(id, monthlyFee);
                signedUp++;
            }
            catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }
}
class PremiumMember extends GymMember {
    private String trainerName;
    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }
    public String getTrainerName() {
        return trainerName;
    }
    @Override
    public void displayInfo() {
        System.out.println("Premium Member | Trainer: " + trainerName +
                " | Sessions: " + getSessionsAttended());
    }
}
