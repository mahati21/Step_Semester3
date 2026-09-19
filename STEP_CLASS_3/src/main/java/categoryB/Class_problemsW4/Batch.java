package categoryB.Class_problemsW4;

class Batch {
    void pay(double amount)
    {
        System.out.println("Paid in one go (day-scholar account)");
    }
}
class HostelFeeAccount extends Batch {
    void pay(double amount)
    {
        System.out.println("Paid in two installments (hostel account)");
    }
}
class AccountPaymentProcessor {
    void processPayment(Batch account, double amount)
    {
        account.pay(amount);
    }
    public static void main(String[] args) {
        Batch[] accounts = {
                new HostelFeeAccount(),
                new HostelFeeAccount(),
                new Batch(),
                new Batch()};
        int hostelCount = 0;
        int dayScholarCount = 0;
        AccountPaymentProcessor processor = new AccountPaymentProcessor();
        for (Batch account : accounts)
        {
            processor.processPayment(account, 60000);
            if (account instanceof HostelFeeAccount)
            {
                hostelCount++;
            }
            else
            {
                dayScholarCount++;
            }
        }
        System.out.println("Hostel accounts processed: " + hostelCount + " | Day-scholar accounts processed: " + dayScholarCount);
    }
}
