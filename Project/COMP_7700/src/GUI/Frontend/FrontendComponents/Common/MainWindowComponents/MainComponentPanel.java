/**
 * File to co-locate all components of the Main Window.
 */

package FinalProject;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainComponentPanel extends JPanel {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new MainComponentPanel());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("MainComponentPanel");
    frame.pack();
    frame.setVisible(true);
  }

  MainComponentPanel() {
    super(new BorderLayout());
    this.add(new MainPrompt(), BorderLayout.NORTH);
    this.add(new MainUser(), BorderLayout.CENTER);
    this.add(new MainSeller(), BorderLayout.SOUTH);
  }
}

