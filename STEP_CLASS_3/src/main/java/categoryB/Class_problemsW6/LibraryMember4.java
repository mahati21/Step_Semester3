package categoryB.Class_problemsW6;

class LibraryMember4 {
    protected String memberId;
    protected int borrowLimit;
    private int booksBorrowed;
    public LibraryMember4 (String memberId, int borrowLimit) {
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
        System.out.print("General | Books: " + booksBorrowed);
    }
}
class StudentMember4 extends LibraryMember4 {
    private String course;
    public StudentMember4 (String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
    public String getCourse() {
        return course;
    }
    @Override
    public void displayInfo() {
        System.out.print("Student | Course: " + course + " | Books: " + getBooksBorrowed());
    }
}
class LibraryReport {
    static String batchPrint(LibraryMember4[] members) {
        StringBuilder result = new StringBuilder();
        for (LibraryMember4 member : members) {
            member.displayInfo();
            if (member instanceof StudentMember4) {
                StudentMember4 student = (StudentMember4) member;
                result.append("Student | Course: ").append(student.getCourse()).append(" | Books: ")
                        .append(student.getBooksBorrowed()).append(" [Course via downcast: ")
                        .append(student.getCourse()).append("]");
            }
            else {
                result.append("General | Books: ").append(member.getBooksBorrowed());
            }
            result.append(" | ");
        }
        return result.toString();
    }
}
