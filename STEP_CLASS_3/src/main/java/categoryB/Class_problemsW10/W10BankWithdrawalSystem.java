package categoryB.Class_problemsW10;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class W10BankWithdrawalSystem {
    static class W10BankAccount {
        String id;
        String type;
        int balance;

        W10BankAccount(String id, String type, int balance) {
            this.id = id;
            this.type = type;
            this.balance = balance;
        }
        void withdraw(int amount) {
            if (type.equals("Savings")) {
                if (balance - amount < 1000) {
                    System.out.println(id + " rejected: minimum balance 1000");
                }
                else {
                    balance -= amount;
                    System.out.println(id + " balance " + balance);
                }
            }
            else {
                if (balance - amount < -5000) {
                    System.out.println(id + " rejected: overdraft limit 5000");
                }
                else {
                    balance -= amount;
                    System.out.println(id + " balance " + balance);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, W10BankAccount> accounts = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();
            if (operation.equals("Savings") || operation.equals("Current")) {
                String id = sc.next();
                int balance = sc.nextInt();
                accounts.put(id, new W10BankAccount(id, operation, balance));

            }
            else if (operation.equals("WITHDRAW")) {
                String id = sc.next();
                int amount = sc.nextInt();
                W10BankAccount account = accounts.get(id);
                if (account == null) {
                    System.out.println("Account not found");
                }
                else {
                    account.withdraw(amount);
                }
            }
        }
        sc.close();
    }
}