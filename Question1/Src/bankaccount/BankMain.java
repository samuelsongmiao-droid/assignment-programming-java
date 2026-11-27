package bankaccount;

import java.util.Scanner;
import java.util.InputMismatchException;

public class BankMain {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String holderName = "";
        double openingBalance = 0;
        boolean validInput = false;

        try {
            System.out.print("Enter account holder name: ");
            holderName = input.nextLine();

            System.out.print("Enter opening balance: MWK ");
            openingBalance = input.nextDouble();

            if (openingBalance < 0) {
                throw new IllegalArgumentException(
                    "Balance cannot be negative."
                );
            }

            validInput = true;

        } catch (InputMismatchException e) {

            System.out.println("Error: Please enter a valid number.");

        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());

        } finally {

            System.out.println("Input section closed.");
            input.close();
        }

        if (validInput) {

            System.out.println("\nWelcome, " + holderName);

            BankAccount account = new BankAccount(openingBalance);

            Depositor depositor = new Depositor(account);

            Thread withdrawer =
                new Thread(new Withdrawer(account));

            depositor.setPriority(Thread.MAX_PRIORITY);
            withdrawer.setPriority(Thread.NORM_PRIORITY);

            depositor.start();
            withdrawer.start();

            try {
                depositor.join();
                withdrawer.join();

                System.out.println("\nBoth threads have finished.");
                System.out.println(
                    "Final balance: MWK " + account.getBalance()
                );

            } catch (InterruptedException e) {

                System.out.println("Main thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}