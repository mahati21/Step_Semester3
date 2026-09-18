package categoryB.Assignment_problemsW3;

public class EmployeeStatic {
    String empName;
    double salary;
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;
    EmployeeStatic(String empName, double salary)
    {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }
    static void printCompanyInfo()
    {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
    public static void main(String[] args)
    {
        EmployeeStatic e1 = new EmployeeStatic("Divya", 65000);
        EmployeeStatic e2 = new EmployeeStatic("Arjun", 50000);
        EmployeeStatic e3 = new EmployeeStatic("Priya", 55000);
        EmployeeStatic.printCompanyInfo();
    }
}
