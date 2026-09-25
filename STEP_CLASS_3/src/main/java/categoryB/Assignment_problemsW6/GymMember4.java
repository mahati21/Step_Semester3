package categoryB.Assignment_problemsW6;

class GymMember4 {
    protected String memberId;
    protected int monthlyFee;
    private int sessionsAttended;
    public GymMember4 (String memberId, int monthlyFee) {
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
        System.out.print("Standard | Sessions: " + sessionsAttended);
    }
}
class PremiumMember3 extends GymMember4 {
    private String trainerName;
    public PremiumMember3 (String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }
    public String getTrainerName() {
        return trainerName;
    }
    @Override
    public void displayInfo() {
        System.out.print("Premium | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended());
    }
}
class GymReport {
    static String batchPrint(GymMember[] members) {
        StringBuilder result = new StringBuilder();
        for (GymMember member : members) {
            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member;
                member.displayInfo();
                result.append(getInfo(member));
                result.append(" [Trainer via downcast: ").append(premium.getTrainerName()).append("]");
            } else {
                result.append(getInfo(member));
            }
            result.append(" | ");
        }
        return result.toString();
    }
    private static String getInfo(GymMember member) {
        if (member instanceof PremiumMember) {
            PremiumMember p = (PremiumMember) member;
            return "Premium | Trainer: " + p.getTrainerName() + " | Sessions: " + p.getSessionsAttended();
        }
        return "Standard | Sessions: " + member.getSessionsAttended();
    }
}