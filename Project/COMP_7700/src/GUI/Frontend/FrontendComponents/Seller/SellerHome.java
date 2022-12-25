/**
 * Frontend Seller Home
 */

package FinalProject;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SellerHome extends JPanel {
  public SellerTopPanel top; 
  public SellerDataPanel data; 

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add( new SellerHome());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("SellerHome");
    frame.pack();
    frame.setVisible(true);
  }

  SellerHome() {
    super();
    this.setLayout(new BorderLayout());
    //this.add(new JLabel("SellerHome"), BorderLayout.CENTER);
    top = new SellerTopPanel(); 
    data = new SellerDataPanel(); 

    this.add(top, BorderLayout.NORTH); 
    this.add(data, BorderLayout.SOUTH); 
  }
}
