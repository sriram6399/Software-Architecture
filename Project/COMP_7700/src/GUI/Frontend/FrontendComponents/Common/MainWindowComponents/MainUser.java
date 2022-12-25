/**
 * File to set the Main Window button to select User mode.
 */

package FinalProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class MainUser extends JButton implements ActionListener {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new MainUser());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("MainUser");
    frame.pack();
    frame.setVisible(true);
  }

  MainUser() {
    super("Buyer");
    this.addActionListener(this);
  }

  public void actionPerformed(ActionEvent event){
    Frontend.showBuyerHome();
  }
}
