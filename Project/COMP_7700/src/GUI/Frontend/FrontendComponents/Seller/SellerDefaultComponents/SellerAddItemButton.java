/**
 * File to set the Main Window button to select Seller mode.
 */

package FinalProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class SellerAddItemButton extends JButton implements ActionListener {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new SellerAddItemButton());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("SellerAddItemButton");
    frame.pack();
    frame.setVisible(true);
  }

  SellerAddItemButton() {
    super("Add Item");
    this.addActionListener(this);
  }

  public void actionPerformed(ActionEvent event){
    Frontend.showSellerAddItem();
  }
}
