/**
 * File to set up the component containing the StoreName and User Name text boxes.
 */

package FinalProject;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import FinalProject.SellerInfoTitleLabel;
import FinalProject.SellerAddItemButton;

public class SellerTopPanel extends JSplitPane {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new SellerTopPanel());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("SellerTop");
    frame.pack();
    frame.setVisible(true);
  }

  SellerTopPanel() {
    super();
    this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    this.setContinuousLayout(true);
    this.setLeftComponent(new SellerInfoTitleLabel());
    this.setRightComponent(new SellerAddItemButton());
    this.setResizeWeight(1.0);
  }
}
