package bankaccount;

public class Withdrawer implements Runnable {

    private BankAccount account;

    public Withdrawer(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {

            System.out.println("\nWithdrawer transaction " + i);

            try {
                account.withdraw(100000);

                Thread.sleep(1000);

            } catch (InsufficientFundsException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (InterruptedException e) {

                System.out.println("Withdrawer interrupted.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("\nWithdrawer finished.");
    }
}