package FinalProject;


import javax.swing.*;
import java.awt.*;

public class OrderConfirmationScreen extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add( new OrderConfirmationScreen());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("MainWindow");
        frame.pack();
        frame.setVisible(true);
    }

    public OrderConfirmationScreen() {
        super();
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Your order has been placed. Your confirmation number for this order is 89rT54BN24."), BorderLayout.CENTER);
    }
}
