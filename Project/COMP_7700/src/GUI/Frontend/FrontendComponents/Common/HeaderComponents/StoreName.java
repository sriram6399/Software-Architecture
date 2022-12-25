/**
 * File to set up the StoreName Label.
 */

package FinalProject;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class StoreName extends JLabel {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new StoreName());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("StoreName");
    frame.pack();
    frame.setVisible(true);
  }

  StoreName() {
    super("Shopping.Com");
  }
}
