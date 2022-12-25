/**
 * File to set up the User Name Label.
 */

package FinalProject;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.*; 
import java.awt.*; 

public class UserName extends JLabel {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new UserName());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("UserName");
    frame.pack();
    frame.setVisible(true);
  }

  UserName() {
    super(Frontend.currentUser.getName());
    this.setHorizontalAlignment(SwingConstants.RIGHT); 
  }
}
