/**
 * File to set up the collections of components that make up the Header - the WebURL, the
 * NamesComponent (Store Name and User Name), and the Search Bar.
 */

package FinalProject;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Header extends JPanel {

  private int borderTop = 10;
  private int borderBottom = 10;
  private int borderLeft = 10;
  private int borderRight = 10;

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new Header());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Header");
    frame.pack();
    frame.setVisible(true);
  }

  Header() {
    super(new BorderLayout());
    this.setBorder(new EmptyBorder(borderTop, borderLeft, borderBottom, borderRight));
    this.add(new WebURL(), BorderLayout.NORTH);
    this.add(new NamesComponent(), BorderLayout.CENTER);
    this.add(new SearchBar(), BorderLayout.SOUTH);
  }
}
