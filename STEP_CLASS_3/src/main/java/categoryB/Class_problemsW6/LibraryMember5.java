package categoryB.Class_problemsW6;

class LibraryMember5 {
    private static int memberCounter = 100;
    private static int membersEnrolled = 0;
    public final String memberNumber;
    protected int borrowLimit;
    private int booksBorrowed;
    public LibraryMember5 (String memberId, int borrowLimit) {
        this.memberIdValidation(memberId);
        this.borrowLimit = borrowLimit;
        memberCounter++;
        membersEnrolled++;
        memberNumber = "LIB-" + memberCounter;
    }
    public LibraryMember5 (int borrowLimit) {
        this.borrowLimit = borrowLimit;
        memberCounter++;
        membersEnrolled++;
        memberNumber = "LIB-" + memberCounter;
    }
    private void memberIdValidation(String memberId) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }
    }
    public void borrowBook() {
        booksBorrowed++;
    }
    public void borrowBook(String genre) {
        System.out.println("Genre: " + genre);
        borrowBook();
    }
    public int getBooksBorrowed() {
        return booksBorrowed;
    }
    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1)) && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }
    public static int getMembersEnrolled() {
        return membersEnrolled;
    }
}
class FacultyMember5 extends LibraryMember5 {
    private String department;
    public FacultyMember5 (int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}
class LibraryAudit {
    static String processNightlyAudit(LibraryMember5[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;
        for (LibraryMember5 member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (member instanceof FacultyMember5) {
                faculty++;
            } else {
                regular++;
            }
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + faculty + " faculty | " + regular + " regular";
    }
}
