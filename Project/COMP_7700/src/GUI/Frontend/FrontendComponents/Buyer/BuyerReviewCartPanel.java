/**
 * Frontend Main window
 */

package FinalProject;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BuyerReviewCartPanel extends JPanel {

  private ItemListScroll reviewOrderScroll; 

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add( new BuyerReviewCartPanel());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("BuyerReviewCartPanel");
    frame.pack();
    frame.setVisible(true);
  }

  BuyerReviewCartPanel() {
    super();

    this.setLayout(new BorderLayout());
    this.add(Frontend.currentUser.getMyCart().getScrollPane().getListSummaryPanel(), BorderLayout.CENTER); 

  }
}
