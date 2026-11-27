package bankaccount;

import java.applet.Applet;
import java.awt.Graphics;

public class BankApplet extends Applet {

    String holderName;
    String bankName;

    public void init() {
        holderName = getParameter("holder");
        bankName = getParameter("bank");
    }

    public void start() {
        System.out.println("Applet started.");
    }

    public void paint(Graphics g) {
        g.drawString("BANK ACCOUNT DETAILS", 50, 50);
        g.drawString("Account Holder: " + holderName, 50, 80);
        g.drawString("Bank Name: " + bankName, 50, 110);
    }

    public void stop() {
        System.out.println("Applet stopped.");
    }

    public void destroy() {
        System.out.println("Applet destroyed.");
    }
}