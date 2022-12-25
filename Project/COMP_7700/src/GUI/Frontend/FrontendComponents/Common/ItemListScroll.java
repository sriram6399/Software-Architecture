package FinalProject;

import FinalProject.Item;
import FinalProject.ItemPanel; 
import FinalProject.ItemList; 

import java.util.*;
import javax.swing.*; 
import java.awt.*; 

public class ItemListScroll extends JScrollPane {

    private ItemList itemsInList; 
    private int itemPanels; 
    private ListSummaryPanel summaryPanel; 
    
    String description;

    public ItemListScroll(ItemList list){
        super();

        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        this.itemsInList = list;
        itemPanels = this.itemsInList.getDistinctItemCount(); 
        createItemPanels(); 
    }

    public void setListSummaryPanel(ListSummaryPanel lsp){
        this.summaryPanel = lsp; 
    }
    public ListSummaryPanel getListSummaryPanel(){
        return this.summaryPanel; 
    }


    public void updateView(){
        //If the number of panels doesn't match the number of items in list update list panel
        if(itemPanels != itemsInList.getDistinctItemCount()){

            JPanel tmpVpt = new JPanel(); 
            tmpVpt.setLayout(new GridLayout(0,1)); 
            for(int i = 0; i < itemsInList.getDistinctItemCount(); i++){
                Item tmpItem = itemsInList.getItem(i); 
                if(tmpItem.getPanel() == null){// ItemPanel doesn't exists - create it
                    ItemPanel itemP = new ItemPanel(tmpItem);
                    tmpItem.setPanel(itemP); 
                    tmpVpt.add(itemP); 

                }else{
                    tmpVpt.add(tmpItem.getPanel()); 
                }

            }
            this.getViewport().setView(tmpVpt); 
            itemPanels = itemsInList.getDistinctItemCount(); 
        }
        if(summaryPanel != null){
            summaryPanel.updatePanel(); 
        }

    }
    public void createItemPanels(){
        JPanel cont = new JPanel();  
        cont.setLayout(new GridLayout(0,1));
        // Add all Item Panels - creating any that don't current exists; 
        for(int i = 0; i< itemPanels; i++){

            Item tmpItem = itemsInList.getItem(i); 
            if(tmpItem.getPanel() == null){// ItemPanel doesn't exists - create it
                ItemPanel itemP = new ItemPanel(tmpItem);
                //this.getViewport().add(itemP);
                cont.add(itemP); 

            }else{
                //this.getViewport().add(tmpItem.getPanel()); 
                cont.add(tmpItem.getPanel()); 
            }
            

        }
        this.getViewport().setView(cont); 

    }

}
