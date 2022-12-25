/**
 * File for initializing the frontend
 */

package FinalProject;
import FinalProject.FrontendConstants;
import FinalProject.BuyerReviewCartPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class Frontend extends JFrame {
  public static void main(String[] args) {
    new Frontend();
  }
 
  //Pointers to Current User 
  public static User currentUser;
 

  private static JFrame frame;
  private static JPanel panel = new JPanel();
  private static MainWindow mainWindow = new MainWindow();

  // Buyer Screens 
  private static BuyerHome buyerHome;
  private static BuyerReviewCartPanel buyerReviewCartPanel; // = new BuyerReviewCart();
  private static CheckoutScreen checkoutScreen; // = new CheckoutScreen();
  private static OrderConfirmationScreen orderConfirm; // = new OrderConfirmationScreen();

  // Seller Screens
  private static SellerHome sellerHome;
  private static SellerAddItemPanel sellerAddItemPanel;// = new SellerAddItemPanel(); 
 

  Frontend() {
    super();

    currentUser = DataSender.getUserDataCall(0);
    //{new User("FirstUser", "BadPassword"); 
    buyerHome = new BuyerHome();
    sellerHome = new SellerHome();
    frame = this;
    panel.add(mainWindow);
    this.setLayout(new BorderLayout());
    this.add(new Header(), BorderLayout.NORTH);
    this.add(panel, BorderLayout.CENTER);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Header");
    this.setMinimumSize(new Dimension(FrontendConstants.MIN_HEADER_WIDTH, FrontendConstants.MIN_HEADER_HEIGHT));
    this.setMaximumSize(new Dimension(FrontendConstants.MAX_WINDOW_WIDTH, FrontendConstants.MAX_WINDOW_HEIGHT)); 
    initWindows();
    this.pack();

    this.setSize(FrontendConstants.MINIMUM_SCREEN_X, FrontendConstants.MINIMUM_SCREEN_Y); 
    this.setVisible(true);
  }

  private void initWindows() {
    mainWindow.setVisible(true);
    buyerHome.setVisible(false);
  }

  private static void hideWindows() {
    mainWindow.setVisible(false);
    buyerHome.setVisible(false);

    if(sellerAddItemPanel != null){
        panel.remove(sellerAddItemPanel); 
        sellerAddItemPanel = null; 
    }
    if(buyerReviewCartPanel != null){
        panel.remove(buyerReviewCartPanel); 
        buyerReviewCartPanel = null; 
    }
    if(checkoutScreen != null){
        panel.remove(checkoutScreen); 
        checkoutScreen = null; 
    }
    if(orderConfirm != null){
        panel.remove(orderConfirm); 
        orderConfirm = null; 
    }
  }

  public static void showBuyerHome() {
    hideWindows();
    panel.removeAll();
    panel.add(buyerHome);
    buyerHome.setVisible(true);
    frame.pack();
    frame.setSize(FrontendConstants.MINIMUM_SCREEN_X, FrontendConstants.MINIMUM_SCREEN_Y); 
  }

  public static void showSellerHome() {
    hideWindows();
    panel.removeAll();

    panel.add(sellerHome);
    sellerHome.setVisible(true);
    frame.pack();
    frame.setSize(FrontendConstants.MINIMUM_SCREEN_X, FrontendConstants.MINIMUM_SCREEN_Y); 
  }

  public static void showBuyerReviewCartPanel() {
    hideWindows();
    panel.removeAll();
    
    buyerReviewCartPanel = new BuyerReviewCartPanel(); 
    panel.add(buyerReviewCartPanel); 

    buyerReviewCartPanel.setVisible(true);
    frame.pack();
    frame.setSize(FrontendConstants.MINIMUM_SCREEN_X, FrontendConstants.MINIMUM_SCREEN_Y); 
  }

  public static void showCheckoutScreen() {
    hideWindows();
    panel.removeAll();

    checkoutScreen = new CheckoutScreen(); 
    panel.add(checkoutScreen);

    checkoutScreen.setVisible(true);
    frame.pack();
    frame.setSize(FrontendConstants.MINIMUM_SCREEN_X, FrontendConstants.MINIMUM_SCREEN_Y); 
  }

  public static void showOrderConfirm() {
    hideWindows();
    panel.removeAll();

    orderConfirm = new OrderConfirmationScreen(); 
    panel.add(orderConfirm);

    orderConfirm.setVisible(true);
    frame.pack();
    frame.setSize(FrontendConstants.MINIMUM_SCREEN_X, FrontendConstants.MINIMUM_SCREEN_Y); 
  }

  public static void showSellerAddItem(){
    hideWindows(); 
    panel.removeAll();

    sellerAddItemPanel = new SellerAddItemPanel(); 
    panel.add(sellerAddItemPanel); 

    sellerAddItemPanel.setVisible(true); 
    frame.pack();  
    frame.setSize(FrontendConstants.MINIMUM_SCREEN_X, FrontendConstants.MINIMUM_SCREEN_Y); 
  }  

   public static void showMainWindow(){
    hideWindows(); 
    panel.removeAll(); 
    panel.add(mainWindow); 
    mainWindow.setVisible(true); 
    frame.pack();    
    frame.setSize(FrontendConstants.MINIMUM_SCREEN_X, FrontendConstants.MINIMUM_SCREEN_Y); 
   }

  public static boolean setContent(JPanel content_in) {
    System.out.println("worked");
    return true;
  }
}
