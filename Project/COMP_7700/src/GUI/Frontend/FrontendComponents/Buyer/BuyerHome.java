
package FinalProject;

import FinalProject.BuyerMain; 

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BuyerHome extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add( new BuyerHome());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BuyerHome");
        frame.pack();
        frame.setVisible(true);
    }

    BuyerHome() {
        super();
        this.setLayout(new BorderLayout());
        this.add(new BuyerMain(), BorderLayout.CENTER);
    }
}
