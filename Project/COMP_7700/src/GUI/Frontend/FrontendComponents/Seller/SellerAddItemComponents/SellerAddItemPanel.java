/**
 * Frontend Seller Home
 */

package FinalProject;

import FinalProject.User; 


import java.awt.BorderLayout;
import java.awt.GridLayout; 
import java.awt.GridBagLayout; 
import java.awt.GridBagConstraints; 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField; 
import javax.swing.*; 




import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 


public class SellerAddItemPanel extends JPanel {
   JPanel ProductInfoP; 
   JPanel AddItemP; 

   // Labels 
   JLabel TitleLabel; 
   JLabel PriceLabel; 
   JLabel QTYLabel; 
   JLabel DescLabel; 

   // Text Fields 
   JTextField TitleText; 
   JTextField PriceText; 
   JTextField QTYText; 
   JTextField DescText; 

   // Button
   JButton AddItemB;    


  SellerAddItemPanel() {
    super();
    this.setLayout(new BorderLayout());

    ProductInfoP = new JPanel(); 
    ProductInfoP.setLayout(new GridBagLayout());  
    GridBagConstraints c = new GridBagConstraints(); 
 
    AddItemP = new JPanel();
    AddItemP.setLayout(new BorderLayout());  

    // Labels 
    TitleLabel = new JLabel("Title:"); 
    PriceLabel = new JLabel("Price:"); 
    QTYLabel = new JLabel("QTY:"); 
    DescLabel = new JLabel("Description:"); 

    // Text Fields 
    TitleText = new JTextField(10); 
    PriceText = new JTextField(10); 
    QTYText = new JTextField(10);
    DescText = new JTextField(10); 

    // Buttons
    AddItemB = new JButton("Launch Item");
    AddItemB.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e){
 		AddItemToListAndClearInput();  
        Frontend.showSellerHome(); 
	}
    }); 

    c.gridx = 0; 
    c.gridy = 0; 
    ProductInfoP.add(TitleLabel, c);
    c.gridx = 1; 
    c.gridy = 0; 
    ProductInfoP.add(TitleText, c); 

    c.gridx = 0; 
    c.gridy = 1; 
    ProductInfoP.add(PriceLabel, c); 
    c.gridx = 1; 
    c.gridy = 1; 
    ProductInfoP.add(PriceText, c); 

    c.gridx = 0; 
    c.gridy = 2; 
    ProductInfoP.add(QTYLabel, c); 
    c.gridx = 1; 
    c.gridy = 2; 
    ProductInfoP.add(QTYText, c); 
    
    c.gridx = 0; 
    c.gridy = 3; 
    ProductInfoP.add(DescLabel, c); 
    c.gridx = 1; 
    c.gridy = 3; 
    ProductInfoP.add(DescText, c); 

    AddItemP.add(AddItemB, BorderLayout.EAST); 

    
    this.add(ProductInfoP, BorderLayout.NORTH); 
    this.add(AddItemP, BorderLayout.SOUTH); 


  }

  private void AddItemToListAndClearInput(){

    
    Item newItem = new Item(TitleText.getText(), Double.parseDouble(PriceText.getText()), DescText.getText());  
    Frontend.currentUser.addItemToForSaleList(newItem); 
    Frontend.currentUser.getSellerForSaleList().getScrollPane().updateView(); 

    // Clear the Text Boxes
    TitleText.setText(""); 
	PriceText.setText(""); 
	QTYText.setText(""); 
	DescText.setText("");
 
  }
}
