/**
 * File to set up the Search text box.
 */

package FinalProject;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class SearchBar extends JTextField {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new SearchBar());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("SearchBar");
    frame.pack();
    frame.setVisible(true);
  }

  SearchBar() {
    super("Search Bar");
  }
}

