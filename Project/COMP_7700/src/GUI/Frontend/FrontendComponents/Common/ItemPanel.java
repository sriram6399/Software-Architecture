package FinalProject;

import FinalProject.Item; 
import FinalProject.ItemListType; 
import FinalProject.FrontendConstants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ItemPanel extends JPanel {

     String name;
     double price;
     String des;

    Item item; 

    //Panels 
    JPanel ProductInfoP;
    JPanel AddItemP;
 
    // Labels
    JLabel TitleLabel;
    JLabel PriceLabel;
    JLabel DescLabel;
    // Button
    JButton ItemB;

    //Misc
    Border blackline = BorderFactory.createLineBorder(Color.black);

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new ItemPanel("dfc",20,"fffs"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BuyerRecommendations");
        frame.pack();
        frame.setVisible(true);
    }
    ItemPanel(Item item){
        super();
        this.item = item;

        this.des = item.getDescription();
        this.name = item.getName();
        this.price = item.getPrice();

        setupPanel();
    }

    ItemPanel(String name,int price,String des){
        super(new FlowLayout());
        this.name = name;
        this.price = price;
        this.des = des;
        setupPanel();
    }
    public void AddItemtoCart(){
            Frontend.currentUser.addItemToUserCartList(this.item); 
            //Frontend.currentUser.getMyCart().getScrollPane().updateView(); 
    }
    public void RemoveItemFromCart(){
            Frontend.currentUser.removeItemFromUserCartList(this.item); 
            //Frontend.currentUser.getMyCart().getScrollPane().updateView(); 
    }
 
    public void RemoveItemFromSaleList(){
        Frontend.currentUser.removeItemFromForSaleList(this.item); 
        //Frontend.currentUser.getSellerForSaleList().getScrollPane().updateView(); 
    }

    private void setupPanel(){
        this.setLayout(new BorderLayout());


        ProductInfoP = new JPanel();
        ProductInfoP.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        AddItemP = new JPanel();
        AddItemP.setLayout(new BorderLayout());

        // Labels
        TitleLabel = new JLabel("Title:"+name);
        PriceLabel = new JLabel("Price:"+price);
        DescLabel = new JLabel("Description:"+des);

        // Buttons
        ItemB = new JButton("Add to Cart");
        ItemB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // What happends depends on the type of list the items is shown on 
                switch (item.getItemListType()) {
                    case SELLER_ITEM:
                        RemoveItemFromSaleList(); 
                        break; 
                    case RECOMMENDED_ITEM:
                        AddItemtoCart(); 
                        break; 
                    case MY_CART_ITEM:
                        RemoveItemFromCart(); 
                        break; 
                    case PAST_PURCHASE_ITEM:
                        AddItemtoCart(); 
                        break; 
                    default: 
                        System.out.println("inside Item Button Switch - No type set"); 
                }


            }
        });

        c.gridx = 0;
        c.gridy = 0;
        ProductInfoP.add(TitleLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        ProductInfoP.add(PriceLabel, c);

        c.gridx = 0;
        c.gridy = 3;
        ProductInfoP.add(DescLabel, c);

        AddItemP.add(ItemB, BorderLayout.EAST);

        this.add(ProductInfoP, BorderLayout.NORTH);
        this.add(AddItemP, BorderLayout.SOUTH);
        this.setBorder(blackline);
        this.setMinimumSize(new Dimension(FrontendConstants.MINIMUM_ITEM_X,FrontendConstants.MINIMUM_ITEM_Y)); 
        //this.setPreferredSize(new Dimension(100,100));
    }
    public void updateButtonValues(ItemListType type){
        switch (type) {
            case SELLER_ITEM:
                ItemB.setText("Remove From Sale"); 
                break; 
            case RECOMMENDED_ITEM:
                ItemB.setText("Add Rec to Cart");
                break; 
            case MY_CART_ITEM:
                ItemB.setText("Remove From Cart");
                break; 
            case PAST_PURCHASE_ITEM:
                ItemB.setText("Add to Cart");
                break; 
            default: 
                System.out.println("inside updateButtonValues- No type set"); 
        } 

    }
}
