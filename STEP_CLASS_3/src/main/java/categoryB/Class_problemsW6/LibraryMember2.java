package categoryB.Class_problemsW6;

class LibraryMember2 {
    protected String memberId;
    protected int borrowLimit;
    private int booksBorrowed;
    public LibraryMember2 (String memberId, int borrowLimit) {
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
    public void displayInfo() {
        System.out.println("General Member | Books Borrowed: " + booksBorrowed);
    }
}
class StudentMember2 extends LibraryMember2 {
    private String course;
    public StudentMember2 (String memberId, int borrowLimit, String course) {
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
class HonorsStudentMember extends StudentMember2 {
    private int bonusLimit;
    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }
    @Override
    public void displayInfo() {
        System.out.println("Honors Student Member | Course: " + getCourse() + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + getBooksBorrowed());
    }
}
class FacultyMember extends LibraryMember2 {
    private String department;
    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }
    @Override
    public void displayInfo() {
        System.out.println("Faculty Member | Department: " + department + " | Books Borrowed: " + getBooksBorrowed());
    }
}
class LibraryUtils {
    static String classifyGeneration(LibraryMember2 member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }
        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }
        return "General member";
    }
    static int getTotalBooksBorrowed(LibraryMember2[] members) {
        int total = 0;
        for (LibraryMember2 member : members) {
            total += member.getBooksBorrowed();
        }
        return total;
    }
}