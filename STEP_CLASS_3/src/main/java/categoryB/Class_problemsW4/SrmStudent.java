package categoryB.Class_problemsW4;

class SrmStudent {
    static String collegeName;
    static String academicYear;
    String name;
    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026-2027";
        System.out.println("College info loaded");
    }
    SrmStudent(String name)
    {
        this.name = name;
    }
    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};
        for (String name : names)
        {
            SrmStudent student = new SrmStudent(name);
            System.out.println("Student record created: " + student.name);
        }
    }
}