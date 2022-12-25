/**
 * File to set up the WebURL text box.
 */

package FinalProject;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class WebURL extends JTextField {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new WebURL());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("WebURL");
    frame.pack();
    frame.setVisible(true);
  }

  WebURL() {
    super("www.shopping.com");
  }
}
