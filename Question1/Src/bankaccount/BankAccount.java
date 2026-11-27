
package bankaccount;

public class BankAccount {

    // Variable to store account balance
    private double balance;

    // Constructor
    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Method for depositing money
    public synchronized void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Deposit amount must be positive."
            );
        }

        balance += amount;

        System.out.println("Deposited: MWK " + amount);
        System.out.println("Current Balance: MWK " + balance);

        // Wake up waiting threads
        notifyAll();
    }

    // Method for withdrawing money
    public synchronized void withdraw(double amount)
            throws InsufficientFundsException,
                   InterruptedException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Withdrawal amount must be positive."
            );
        }

        // Check maximum withdrawal limit
        if (amount > 500000) {
            throw new InsufficientFundsException(
                "Withdrawal exceeds MWK 500,000."
            );
        }

        // Wait if balance is insufficient
        while (balance < amount) {

            System.out.println(
                "Insufficient balance. Waiting for deposit..."
            );

            wait();
        }

        // Deduct money
        balance -= amount;

        System.out.println("Withdrawn: MWK " + amount);
        System.out.println("Remaining Balance: MWK " + balance);
    }

    // Method to check account balance
    public synchronized double getBalance() {
        return balance;
    }
}
