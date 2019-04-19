package application;

import application.Item;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Add_ItemController {

    @FXML
    private ImageView add_Picture_Frame;

    @FXML
    private TextField add_Name;

    @FXML
    private TextField add_ID;

    @FXML
    private TextField add_Color;

    @FXML
    private TextField add_Price;

    @FXML
    private TextField add_Condition;

    @FXML
    private TextField add_Size;

    @FXML
    private TextField add_Location;

    @FXML
    private Button add_Item;

    @FXML
    private Button add_Picture;

    private Item selectedItem;
    
    private ListView<String> list;
    
    /**
     * Accepts a prepared Item and initializes the view
     * @param item prepared Item
     */
    public void initData(Item item) {
    	selectedItem = item;
    	list= selectedItem.getList();
    	
    }
    
	@FXML
	void add_Picture(ActionEvent event) throws IOException{
		FileChooser choose = new FileChooser();
		
		choose.getExtensionFilters().add(new ExtensionFilter("Pictures", "*.jpg"));
		File f = choose.showOpenDialog(null);
		
		if(f!=null){
			FileInputStream inputstream = new FileInputStream(f.getAbsolutePath());
			Image image = new Image(inputstream);
			
			add_Picture_Frame.setImage(image);
		}	
	}

	@FXML
	void add_to_DB(ActionEvent event) throws IOException{
		
		//TODO: add picture storage system (or implement BLOB datatype)
		
		try {
			ResultSet rs=Main.ctrl.addItem(add_Name.getText(),add_Color.getText(),add_Price.getText(),add_Size.getText(),add_Condition.getText(),add_Location.getText());
			String itemID =rs.getString(1);
			Main.itemListArray.add(itemID);
//			Main.list.getItems().add(itemID+" : "+add_Name.getText());
			list.getItems().add(itemID+" : "+add_Name.getText());
		} catch (SQLException e) {
			System.out.println("Failure adding item to ListView.");
			e.printStackTrace();
		}
		
		Stage stage;
		stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
		stage.close();
	}
}