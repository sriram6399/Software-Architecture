/**
 * File to set up the User Name Label.
 */

package FinalProject;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class SellerInfoTitleLabel extends JLabel {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new SellerInfoTitleLabel());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("SellerInfoTitleLable");
    frame.pack();
    frame.setVisible(true);
  }

 SellerInfoTitleLabel() {
    super(Frontend.currentUser.getSellerForSaleList().getTitle());
  }
}
