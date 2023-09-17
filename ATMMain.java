package atm;

import java.util.Scanner;

public class ATMMain {
    public static void main(String[] args) {
        // Create an ATM instance with initial balance, account number, and PIN.
        ATM atm = new ATM("1234567890", "1234", 1000.0);

        // Create a Scanner for user input.
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their PIN.
        System.out.print("Enter your PIN: ");
        String enteredPin = scanner.nextLine();

        // Validate the PIN.
        if (atm.validatePin(enteredPin)) {
            while (true) {
                System.out.println("Options:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                
                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Clear the invalid input
                    continue;
                }

                switch (choice) {
                    case 1:
                        double balance = atm.checkBalance();
                        System.out.println("Your balance is: $" + balance);
                        break;
                    case 2:
                        System.out.print("Enter the amount to deposit: ");
                        double depositAmount;
                        try {
                            depositAmount = scanner.nextDouble();
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.nextLine(); // Clear the invalid input
                            continue;
                        }
                        atm.deposit(depositAmount);
                        System.out.println("Deposited $" + depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter the amount to withdraw: ");
                        double withdrawAmount;
                        try {
                            withdrawAmount = scanner.nextDouble();
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.nextLine(); // Clear the invalid input
                            continue;
                        }
                        if (atm.withdraw(withdrawAmount)) {
                            System.out.println("Withdrawn $" + withdrawAmount);
                        }
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid PIN. Exiting...");
        }
    }
}

class ATM {
    private double balance;
    private String accountNumber;
    private String pin;

    public ATM(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    // Implement methods for deposit, withdrawal, and balance inquiry.
    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }

    public double checkBalance() {
        return balance;
    }

    // Implement a method to validate the PIN.
    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
	}
}

