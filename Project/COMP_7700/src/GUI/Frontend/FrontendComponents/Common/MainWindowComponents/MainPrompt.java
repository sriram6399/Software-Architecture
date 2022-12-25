/**
 * File to set the Main Window Prompt to select user or seller.
 */

package FinalProject;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainPrompt extends JLabel {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new MainPrompt());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("MainPrompt");
    frame.pack();
    frame.setVisible(true);
  }

  MainPrompt() {
    super("Please Select Buyer or Seller Walk Through");
  }
}
