package categoryB.Class_problemsW10;

import java.util.Scanner;

public class W10PayrollRegisterSystem {
    static class W10Payslip {
        String name;
        String type;
        double pay;

        W10Payslip(String name, String type, double pay) {
            this.name = name;
            this.type = type;
            this.pay = pay;
        }
        @Override
        public String toString() {
            String amount = (pay == (long) pay) ? String.valueOf((long) pay) : String.valueOf(pay);
            return "Payslip[name=" + name + ", type=" + type + ", pay=" + amount + "]";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        W10Payslip[] payslips = new W10Payslip[n];
        double total = 0;
        int topIndex = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            double pay;

            if (type.equals("FullTime")) {
                pay = sc.nextDouble();
            }
            else if (type.equals("PartTime")) {
                double hours = sc.nextDouble();
                double rate = sc.nextDouble();
                pay = hours * rate;
            }
            else {
                pay = sc.nextDouble();
            }

            payslips[i] = new W10Payslip(name, type, pay);
            total += pay;
            if (payslips[i].pay > payslips[topIndex].pay) {
                topIndex = i;
            }
        }
        for (W10Payslip slip : payslips) {
            System.out.println(slip);
        }
        System.out.println("Total " + (long) total);
        System.out.println("top earner " + payslips[topIndex].name);
        sc.close();
    }
}
