package bankaccount;

public class Depositor extends Thread {

    private BankAccount account;

    public Depositor(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {

            System.out.println("\nDepositor transaction " + i);

            account.deposit(100000);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Depositor interrupted.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("\nDepositor finished.");
    }
}