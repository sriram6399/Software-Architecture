/**
 * File to set up the component containing the StoreName and User Name text boxes.
 */

package FinalProject;

import javax.swing.JFrame;
import javax.swing.JPanel; 
import javax.swing.JSplitPane;
import javax.swing.DefaultListModel; 

import java.awt.*; 


import FinalProject.ItemList; 
import FinalProject.ItemListScroll; 



public class SellerDataPanel extends JPanel {

   private ItemList sellerItemsForSale; 
   private ItemListScroll sellerItemsScroll; 

  SellerDataPanel() {
    super();

    this.setLayout(new BorderLayout()); 

    //Get Current Users Items for Sale & add them to the seller Data Panel; 
    sellerItemsForSale = Frontend.currentUser.getSellerForSaleList();    
                    
    AddExampleItems();  // TODO remove once there's a database with this information. 

    sellerItemsScroll = new ItemListScroll(sellerItemsForSale);
    sellerItemsForSale.setScrollPane(sellerItemsScroll); 
    this.add(sellerItemsScroll, BorderLayout.CENTER); 
    
  }

  void AddExampleItems(){
   	for( int i = 0; i <= 4; i++){
        Item tmpItem = new Item("Item "+i, i, "Desc: this is item " + i); 
        //sellerItemsForSale.addItemToList(tmpItem); 
        Frontend.currentUser.addItemToForSaleList(tmpItem); 
    }

  }
}
