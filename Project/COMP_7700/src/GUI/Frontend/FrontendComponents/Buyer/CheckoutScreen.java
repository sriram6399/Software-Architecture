package FinalProject;

import static FinalProject.FrontendConstants.*; 

import java.util.*; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutScreen extends JPanel implements ActionListener {
    private static TextField txtFieldCardNumber = new TextField(CREDIT_CARD_NUMBER_LEN);
    private static TextField txtFieldCSC = new TextField(CREDIT_CARD_CSC_LEN);
    private static TextField txtFieldName = new TextField(CREDIT_CARD_NAME_LEN);

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add( new CheckoutScreen());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("MainWindow");
        frame.pack();
        frame.setVisible(true);
    }

    public CheckoutScreen() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new JLabel("Checkout"));

        JPanel cardNumber = new JPanel();
        cardNumber.add(new JLabel("Card Number"));
        cardNumber.add(txtFieldCardNumber);
        this.add(cardNumber);

        JPanel expirationDate = new JPanel();
        expirationDate.add(new JLabel("Expiration Date"));
        JComboBox<String> months = new JComboBox<String>();
        months.addItem("January");
        months.addItem("February");
        months.addItem("March");
        months.addItem("April");
        months.addItem("May");
        months.addItem("June");
        months.addItem("July");
        months.addItem("August");
        months.addItem("September");
        months.addItem("October");
        months.addItem("November");
        months.addItem("December");
        expirationDate.add(months);
        JComboBox<Integer> years = new JComboBox<Integer>();

        Calendar now = Calendar.getInstance(); 
        int year = now.get(Calendar.YEAR); 

        for (int i = year; i <= year + 10; i++) { years.addItem(i); }
        expirationDate.add(years);
        this.add(expirationDate);

        JPanel csc = new JPanel();
        csc.add(new JLabel("CSC"));
        csc.add(txtFieldCSC);
        this.add(csc);

        JPanel nameOnCard = new JPanel();
        nameOnCard.add(new JLabel("Name on Card"));
        nameOnCard.add(txtFieldName);
        this.add(nameOnCard);

        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(this);
        this.add(placeOrderButton);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (!checkValidityOfTextFields()) {
            JOptionPane.showMessageDialog(null, "At least one field has not been filled in or contains an invalid entry.");
        }
        else {
          Frontend.showOrderConfirm();
        }
    }

    private boolean checkValidityOfTextFields() {
    // TODO add red boarder to the incorrect fields 
        if (txtFieldCardNumber.getText().length() != CREDIT_CARD_NUMBER_LEN) { return false; }
        else {
            try {
                for (int i = 0; i < CREDIT_CARD_NUMBER_LEN; i += 4) {
                    int segmentNumber = Integer.parseInt(txtFieldCardNumber.getText().substring(i, i + 4));
                    if (segmentNumber < 0) { return false; }
                }
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        if (txtFieldCSC.getText().length() != CREDIT_CARD_CSC_LEN) { return false; }
        else {
            try {
                int enteredCSC = Integer.parseInt(txtFieldCSC.getText());
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        if (txtFieldName.getText().length() == 0) { return false; }
        return true;
    }
}
