package categoryB.Assignment_problemsW8;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    private final String title;
    private final double maxMarks;
    private final LocalDate dueDate;
    Assignment(String title, double maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }
    String getTitle() { return title; }
    double getMaxMarks() { return maxMarks; }
    LocalDate getDueDate() { return dueDate; }
    abstract double getPenaltyPerDay();
    long getLateDays(LocalDate submittedOn) {
        return Math.max(0, ChronoUnit.DAYS.between(dueDate, submittedOn));
    }
}
class CodingAssignment extends Assignment {
    CodingAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }
    double getPenaltyPerDay() {
        return 0.10;
    }
}
class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }
    double getPenaltyPerDay() {
        return 0.20;
    }
}
class Student {
    private final String name;
    Student(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
}
class Submission {
    private final Student student;
    private final Assignment assignment;
    private final LocalDate submittedOn;
    private boolean graded;
    Submission(Student student, Assignment assignment, LocalDate submittedOn) {
        this.student = student;
        this.assignment = assignment;
        this.submittedOn = submittedOn;
    }
    boolean isGraded() {
        return graded;
    }
    double grade(double awardedMarks) {
        if (graded) {
            throw new IllegalStateException("This submission has already been graded.");
        }
        if (awardedMarks < 0 || awardedMarks > assignment.getMaxMarks()) {
            throw new IllegalArgumentException("Marks must be between 0 and the maximum marks.");
        }
        long lateDays = assignment.getLateDays(submittedOn);
        double penalty = Math.min(1.0, lateDays * assignment.getPenaltyPerDay());
        graded = true;
        return awardedMarks * (1.0 - penalty);
    }
    String getStudentName() { return student.getName(); }
    String getAssignmentTitle() { return assignment.getTitle(); }
    double getMaxMarks() { return assignment.getMaxMarks(); }
    long getLateDays() { return assignment.getLateDays(submittedOn); }
    double getPenaltyPerDay() { return assignment.getPenaltyPerDay(); }
}
class AssignmentPortal {
    Submission submit(Student student, Assignment assignment, LocalDate date) {
        Submission submission = new Submission(student, assignment, date);
        long lateDays = submission.getLateDays();

        if (lateDays == 0) {
            System.out.println(student.getName() + "'s submission for '"
                    + assignment.getTitle() + "' received (on time). Status: Submitted.");
        } else {
            System.out.println(student.getName() + "'s submission for '"
                    + assignment.getTitle() + "' received (" + lateDays
                    + " days late). Status: Submitted.");
        }
        return submission;
    }

    void grade(Submission submission, double awardedMarks) {
        try {
            double finalMarks = submission.grade(awardedMarks);
            double penaltyPercent =
                    submission.getLateDays() * submission.getPenaltyPerDay() * 100;
            if (submission.getLateDays() == 0) {
                System.out.printf("%s graded: %.1f/%.1f. Status: Graded.%n",
                        submission.getStudentName(), finalMarks, submission.getMaxMarks());
            } else {
                System.out.printf(
                        "%s graded: %.1f/%.1f after %.0f%% late penalty. Status: Graded.%n",
                        submission.getStudentName(), finalMarks,
                        submission.getMaxMarks(), penaltyPercent
                );
            }
        } catch (IllegalStateException e) {
            System.out.println("Cannot resubmit: '" + submission.getAssignmentTitle()
                    + "' has already been graded.");
        }
    }
}

public class AssignmentPortalDemo {
    public static void main(String[] args) {
        AssignmentPortal portal = new AssignmentPortal();
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        Assignment written = new WrittenAssignment("Design Essay", 50, LocalDate.of(2026, 3, 12));

        Submission ashaSubmission = portal.submit(asha, coding, LocalDate.of(2026, 3, 10));
        Submission raviSubmission = portal.submit(ravi, written, LocalDate.of(2026, 3, 14));

        portal.grade(ashaSubmission, 45);
        portal.grade(raviSubmission, 40);
        portal.grade(ashaSubmission, 45);
    }
}