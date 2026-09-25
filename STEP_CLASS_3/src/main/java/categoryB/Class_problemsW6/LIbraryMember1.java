package categoryB.Class_problemsW6;

class LibraryMember1 {
    protected String memberId;
    protected int borrowLimit;
    private int booksBorrowed;
    public LibraryMember1 (String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Invalid borrow limit");
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
    public void displayInfo() {
        System.out.println("General Member | Books Borrowed: " + booksBorrowed);
    }
    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;
        for (String id : memberIds) {
            try {
                new LibraryMember1 (id, borrowLimit);
                enrolled++;
            }
            catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }
}
class StudentMember extends LibraryMember1 {
    private String course;
    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
    public String getCourse() {
        return course;
    }
    @Override
    public void displayInfo() {
        System.out.println("Student Member | Course: " + course + " | Books Borrowed: " + getBooksBorrowed());
    }
}
