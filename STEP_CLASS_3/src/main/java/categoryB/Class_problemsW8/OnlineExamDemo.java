package categoryB.Class_problemsW8;
import java.util.ArrayList;
import java.util.List;

abstract class ExamQuestion {
    private final String text;
    private final int points;
    ExamQuestion(String text, int points) {
        this.text = text;
        this.points = points;
    }
    String getText() {
        return text;
    }
    int getPoints() {
        return points;
    }
    abstract boolean isCorrect(String answer);
}
class MultipleChoiceQuestion extends ExamQuestion {
    private final String correctAnswer;
    MultipleChoiceQuestion(String text, int points, String correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }
    boolean isCorrect(String answer) {
        return answer != null && correctAnswer.equalsIgnoreCase(answer.trim());
    }
}
class TrueFalseQuestion extends ExamQuestion {
    private final boolean correctAnswer;
    TrueFalseQuestion(String text, int points, boolean correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }
    boolean isCorrect(String answer) {
        if (answer == null) {
            return false;
        }
        return Boolean.toString(correctAnswer).equalsIgnoreCase(answer.trim());
    }
}
class ExamStudent {
    private final String name;
    ExamStudent(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
}
class ExamAttempt {
    private final ExamStudent student;
    private final List<ExamQuestion> questions;
    private final List<String> answers = new ArrayList<>();
    private boolean submitted;
    ExamAttempt(ExamStudent student, List<ExamQuestion> questions) {
        this.student = student;
        this.questions = questions;
    }
    void answer(int questionNumber, String answer) {
        if (submitted) {
            System.out.println("This exam has already been submitted.");
            return;
        }
        if (questionNumber < 1 || questionNumber > questions.size()) {
            System.out.println("Invalid question number.");
            return;
        }
        while (answers.size() < questions.size()) {
            answers.add(null);
        }
        answers.set(questionNumber - 1, answer);
        System.out.println("Answer saved for question " + questionNumber + ".");
    }
    void submit() {
        if (submitted) {
            System.out.println("This exam has already been submitted.");
            return;
        }
        submitted = true;
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            String answer = i < answers.size() ? answers.get(i) : null;

            if (questions.get(i).isCorrect(answer)) {
                score += questions.get(i).getPoints();
            }
        }
        System.out.println(student.getName() + " submitted the exam.");
        System.out.println("Score: " + score + " out of " + getTotalPoints() + ".");
    }
    private int getTotalPoints() {
        int total = 0;
        for (ExamQuestion question : questions) {
            total += question.getPoints();
        }
        return total;
    }
}
public class OnlineExamDemo {
    public static void main(String[] args) {
        List<ExamQuestion> questions = new ArrayList<>();
        questions.add(new MultipleChoiceQuestion("Which keyword creates a class in Java?", 2, "class"));
        questions.add(new TrueFalseQuestion("Java is case-sensitive.", 1, true));
        ExamStudent student = new ExamStudent("Asha");
        ExamAttempt attempt = new ExamAttempt(student, questions);
        attempt.answer(1, "class");
        attempt.answer(2, "true");
        attempt.submit();
    }
}