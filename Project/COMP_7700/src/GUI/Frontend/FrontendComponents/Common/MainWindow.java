/**
 * Frontend Main window
 */

package FinalProject;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JPanel {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add( new MainWindow());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("MainWindow");
    frame.pack();
    frame.setVisible(true);
  }

  MainWindow() {
    super();
    this.setLayout(new BorderLayout());
    this.add(new MainComponentPanel(), BorderLayout.CENTER);
  }
}
