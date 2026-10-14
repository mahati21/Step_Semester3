package categoryB.Assignment_problemsW8;

import java.util.ArrayList;
import java.util.List;

class NoticeStudent {
    private final String name;
    private final String department;
    private final String preferredChannel;
    NoticeStudent(String name, String department, String preferredChannel) {
        this.name = name;
        this.department = department;
        this.preferredChannel = preferredChannel;
    }
    String getName() {
        return name;
    }
    String getDepartment() {
        return department;
    }
    String getPreferredChannel() {
        return preferredChannel;
    }
}
class CampusNotice {
    private final String title;
    private final List<String> departments;
    CampusNotice(String title, List<String> departments) {
        this.title = title;
        this.departments = departments;
    }
    String getTitle() {
        return title;
    }
    boolean isForDepartment(String department) {
        return departments.contains(department);
    }
}
public class NoticeBroadcasterDemo {
    public static void main(String[] args) {
        List<NoticeStudent> students = new ArrayList<>();

        students.add(new NoticeStudent("Asha", "CSE", "Email"));
        students.add(new NoticeStudent("Ravi", "ECE", "SMS"));
        students.add(new NoticeStudent("Mina", "CSE", "App"));

        CampusNotice notice = new CampusNotice("Lab Closed Tomorrow", List.of("CSE"));

        System.out.println("Notice: " + notice.getTitle());

        for (NoticeStudent student : students) {
            if (notice.isForDepartment(student.getDepartment())) {
                System.out.println("[" + student.getPreferredChannel() + " to " + student.getName() + "] " + notice.getTitle());
            }
        }
    }
}
