package categoryB.Class_problemsW6;

class LibraryMember3 {
    protected String memberId;
    protected int borrowLimit;
    private int booksBorrowed;
    private int[] fineHistory = new int[10];
    private int fineCount = 0;
    public LibraryMember3 (String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }
    public void borrowBook() {
        booksBorrowed++;
    }
    public int getBooksBorrowed() {
        return booksBorrowed;
    }
    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
    }
    public int[] getFineHistory() {
        int[] copy = new int[fineCount];
        for (int i = 0; i < fineCount; i++) {
            copy[i] = fineHistory[i];
        }
        return copy;
    }
    public int getTotalFine() {
        int total = 0;
        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }
        return total;
    }
}
class StudentMember3 extends LibraryMember3 {
    private String course;
    public StudentMember3 (String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}
