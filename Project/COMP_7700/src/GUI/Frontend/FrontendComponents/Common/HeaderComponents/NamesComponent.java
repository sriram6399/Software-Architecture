/**
 * File to set up the component containing the StoreName and User Name text boxes.
 */

package FinalProject;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import FinalProject.StoreName;
import FinalProject.UserName;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

import javax.swing.*; 
import java.awt.*; 

public class NamesComponent extends JPanel {
   JButton demoStartB; 

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new NamesComponent());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("NamesComponent");
    frame.pack();
    frame.setVisible(true);
  }

  NamesComponent() {
    super();
    demoStartB = new JButton("Return to Demo Home"); 

    this.setLayout(new GridLayout(1, 3)); 
    
    this.add(new StoreName()); 
    this.add(demoStartB); 
    this.add(new UserName()); 

     demoStartB.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		Frontend.showMainWindow(); 
	}
     });
    
  }
}
