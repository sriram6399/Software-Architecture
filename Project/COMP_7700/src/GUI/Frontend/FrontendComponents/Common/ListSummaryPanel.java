package FinalProject;

import FinalProject.FrontendConstants;
import FinalProject.ItemListScroll; 

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.*; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListSummaryPanel extends JPanel {
    private ItemList list; 
    private ItemListScroll scroll; 


    private JLabel titleLabel; 
    private JPanel costSummaryPanel; 
    private JLabel totalItemsLabel; 
    private JLabel subtotalCostLabel; 
    private JLabel taxLabel; 
    private JLabel totalCostLabel; 

    private JButton button; 

    Border blackline = BorderFactory.createLineBorder(Color.black);
     
    ListSummaryPanel(ItemList list){
        super(new BorderLayout());
        this.setMinimumSize(new Dimension(FrontendConstants.MINIMUM_ITEM_X, FrontendConstants.MINIMUM_ITEM_Y)); 
        this.list = list; 
  
        // Create Needed Panels and layout 
        // Scroll List 
        if(list.getScrollPane() == null){
            scroll = new ItemListScroll(list); 
            list.setScrollPane(scroll); 
        }else{
          scroll = list.getScrollPane(); 
        }
        scroll.setListSummaryPanel(this); 

        // Title 
        titleLabel = new JLabel(list.getTitle()); 
    
        // Summary Information Panel 
        costSummaryPanel = new JPanel(new GridLayout(0, 1, 1, 1)); 
        totalItemsLabel = new JLabel("Total Number of Items: " + list.getTotalNumberOfItems() + " \n"); 
        subtotalCostLabel = new JLabel("Subtotal: "+ list.getSubtotal() + " \n"); 
        taxLabel = new JLabel("Tax: " + list.getTax()); 
        totalCostLabel = new JLabel("Total Cost: " + list.getTotalValue()); 
 
        costSummaryPanel.add(totalItemsLabel); 
        costSummaryPanel.add(subtotalCostLabel); 
        costSummaryPanel.add(taxLabel); 
        costSummaryPanel.add(totalCostLabel); 

        // Button 
        // Will need to make dynamic so it either goes to checkout, previous page, ect 
        button = new JButton("Needs to be updated");    
        setupButtonInfo(); 
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                goToNextScreen(); 
            }
        });
        costSummaryPanel.add(button); 

        // Add Panels to Summary Panel 
        this.add(titleLabel, BorderLayout.NORTH); 
        this.add(scroll, BorderLayout.CENTER); 
        this.add(costSummaryPanel, BorderLayout.SOUTH); 

        this.setBorder(blackline);

    }
    public void updatePanel(){
        totalItemsLabel.setText("Total Number of Items: " + list.getTotalNumberOfItems() + " \n"); 
        subtotalCostLabel.setText("Subtotal: "+ list.getSubtotal() + " \n"); 
        taxLabel.setText("Tax: " + list.getTax()); 
        totalCostLabel.setText("Total Cost: " + list.getTotalValue()); 
 

    }

    private void setupButtonInfo(){
    
        ItemListType type = this.list.getItemListType(); 
    
        switch(type) {
            case SELLER_ITEM: 
                button.setText("I DONT KNOW YETi1"); 
                break; 
            case RECOMMENDED_ITEM:
                button.setText("I Dont Know Yet2"); 
                break;
            case MY_CART_ITEM: 
                button.setText("Review Order"); 
                break; 
            case ORDER_ITEM:
                button.setText("Checkout"); 
                break; 
            case PAST_PURCHASE_ITEM:
                break; 

        }   
    }

    private void goToNextScreen(){
    
        ItemListType type = this.list.getItemListType(); 
    
        switch(type) {
            case SELLER_ITEM: 
                break; 
            case RECOMMENDED_ITEM:
                break;
            case MY_CART_ITEM: 
                list.setItemListType(ItemListType.ORDER_ITEM); 
                setupButtonInfo(); 
                Frontend.showBuyerReviewCartPanel(); 
                break; 
            case ORDER_ITEM: 
                Frontend.showCheckoutScreen(); 
                break; 
            case PAST_PURCHASE_ITEM:
                break; 

        }   
    }



}
