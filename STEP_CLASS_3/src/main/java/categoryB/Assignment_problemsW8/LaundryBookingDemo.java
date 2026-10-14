package categoryB.Assignment_problemsW8;

import java.util.Locale;
interface WashType {
    String getName();
    int getDurationMinutes();
    double getCharge();
}
class QuickWash implements WashType {
    public String getName() { return "Quick"; }
    public int getDurationMinutes() { return 30; }
    public double getCharge() { return 20.00; }
}
class NormalWash implements WashType {
    public String getName() { return "Normal"; }
    public int getDurationMinutes() { return 45; }
    public double getCharge() { return 30.00; }
}
class HeavyWash implements WashType {
    public String getName() { return "Heavy"; }
    public int getDurationMinutes() { return 60; }
    public double getCharge() { return 45.00; }
}
class Student1 {
    private final String name;
    Student1(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
}
class WashCycle {
    private final Student1 student;
    private final WashingMachine machine;
    private final WashType washType;
    WashCycle(Student1 student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }
    Student1 getStudent() { return student; }
    WashingMachine getMachine() { return machine; }
    WashType getWashType() { return washType; }
}
class WashingMachine {
    private final String id;
    private WashCycle currentCycle;
    WashingMachine(String id) {
        this.id = id;
    }
    String getId() {
        return id;
    }
    boolean startWash(Student1 student, WashType washType) {
        if (currentCycle != null) {
            System.out.println("Machine " + id + " is currently busy.");
            return false;
        }
        currentCycle = new WashCycle(student, this, washType);
        System.out.printf(Locale.US, "%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n", washType.getName(), id, student.getName(), washType.getDurationMinutes(), washType.getCharge());
        return true;
    }
    void completeWash() {
        if (currentCycle == null) {
            System.out.println("Machine " + id + " has no active wash.");
            return;
        }
        System.out.println(id + " cycle completed. " + id + " is now free.");
        currentCycle = null;
    }
}
public class LaundryBookingDemo {
    public static void main(String[] args) {
        Student1 asha = new Student1("Asha");
        Student1 ravi = new Student1("Ravi");
        Student1 neha = new Student1("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());
        m1.completeWash();
        m1.startWash(neha, new NormalWash());
    }
}
