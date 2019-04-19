package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class Pop_UpController {

    @FXML
    private Button PU_Yes;

    @FXML
    private Button PU_No;

    private Item selectedItem;
    
	private ListView<String> list;
	private String itemName;
	private String itemColor;
	private String itemPrice;
	private String itemSize;
	private String itemCondition;
	private String itemLocation;
    
    public void initData(Item item) {
		selectedItem=item;
		list=selectedItem.getList();
		itemName=selectedItem.getItemName();
		itemColor=selectedItem.getItemColor();
		itemPrice=selectedItem.getItemPrice();
		itemSize=selectedItem.getItemSize();
		itemCondition=selectedItem.getItemCondition();
		itemLocation=selectedItem.getItemLocation();
	}
    
    @FXML
	void close_PU(ActionEvent event) throws IOException{
		Stage stage;
		
		stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
		
		stage.close();
	}

	@FXML
	void yes_Click(ActionEvent event) throws IOException{
		int index=list.getSelectionModel().getSelectedIndex();		//index of item to be removed

		if(Main.was_Pressed == "remove"){							//remove item was confirmed
			Main.ctrl.removeItem(Main.itemListArray.get(index));	//remove item from DB
			Main.itemListArray.remove(index);						//remove item from Array
			list.getItems().remove(index);							//remove item from ListView
		}else{														//update item was confirmed
			Main.ctrl.updateItem(itemName, itemColor, itemPrice, itemSize, itemCondition, itemLocation, Main.itemListArray.get(index));
		}
		
		Stage stage;
		stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
		stage.close();
	}
}