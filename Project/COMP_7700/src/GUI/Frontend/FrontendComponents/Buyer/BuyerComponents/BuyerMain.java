package FinalProject;

import FinalProject.Header;
import FinalProject.User; 

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BuyerMain extends JPanel {
    Border blackline = BorderFactory.createLineBorder(Color.black);
    ItemList recommendedItems;
    ItemListScroll recommendedItemsScroll; 

    ItemList myCartItems; 
    ItemListScroll myCartScroll; 

    //BuyerCartPanel myCartPanel; 
    ListSummaryPanel myCartPanel; 

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new BuyerMain());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BuyerMainScreen");
        frame.pack();
        frame.setVisible(true);
    }

    BuyerMain(){
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        recommendedItems = Frontend.currentUser.getRecommendedItemsList(); 
        if(recommendedItems.getDistinctItemCount() == 0){
            recommendedItems.addTestItems(20); 

        }else{
            System.out.println("Already has items in recommended list"); 
        }

        if(recommendedItems.getScrollPane() == null){
            recommendedItemsScroll = new ItemListScroll(recommendedItems); 
            recommendedItems.setScrollPane(recommendedItemsScroll); 
        }else{
            recommendedItemsScroll = recommendedItems.getScrollPane(); 
        }

        myCartItems = Frontend.currentUser.getMyCart(); 
        if(myCartItems.getScrollPane() == null){
            myCartScroll = new ItemListScroll(myCartItems); 
            myCartItems.setScrollPane(myCartScroll); 
        }else{
            myCartScroll = myCartItems.getScrollPane(); 
        }

        // TESTING 
        //myCartPanel = new BuyerCartPanel(); 
        myCartPanel = new ListSummaryPanel(Frontend.currentUser.getMyCart()); 


        //JLabel mainHead = new JLabel("Your Recommendations");
        JLabel recommendedTitle = new JLabel(Frontend.currentUser.getRecommendedItemsList().getTitle());
        recommendedTitle.setBorder(blackline);
        recommendedTitle.setFont(new Font("Arial", Font.PLAIN, 30));

        //JLabel cartHead = new JLabel("MY CART");
        JLabel myCartTitle = new JLabel(Frontend.currentUser.getMyCart().getTitle());
        myCartTitle.setBorder(blackline);
        myCartTitle.setFont(new Font("Arial", Font.PLAIN, 30));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 9;
        c.gridheight = 1;
        this.add(recommendedTitle,c);

        c.gridx = 10;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        this.add(myCartTitle,c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 9;
        c.gridheight = 11;
        this.add(recommendedItemsScroll, c); 

        c.gridx = 10;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 10;
        this.add(myCartPanel, c); 

    }
}
