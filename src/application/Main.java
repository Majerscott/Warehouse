package application;
	

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

import pcClient.WaresOurStuffControl;

public class Main extends Application {
	@FXML
    private Button update;

    @FXML
    private Button add;

    @FXML
    private Button remove;
    
	@FXML
	private Button PU_Yes;

	@FXML
	private Button PU_No;

	@FXML
    private ImageView item_Picture;

    @FXML
    private ListView<String> list;

    @FXML
    private TextField item_Name;

    @FXML
    private TextField item_ID;

    @FXML
    private TextField item_Color;

    @FXML
    private TextField item_Price;

    @FXML
    private TextField item_Condition;

    @FXML
    private TextField item_Size;

    @FXML
    private TextField item_Location;
    
    @FXML
    private ImageView add_Picture_Frame;

    @FXML
    private TextField add_Name;

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
    
	private ObservableList<String> listItems=FXCollections.observableArrayList();
	
    static String was_Pressed;
	static WaresOurStuffControl ctrl=new WaresOurStuffControl();
	static ArrayList<String> itemListArray=new ArrayList<>();	//itemArray[i] should hold ID of ListView[i]
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root=FXMLLoader.load(getClass().getResource("GUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Wares Our Stuff?");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	void initialize() {
		list.setItems(listItems);

		ctrl.connectToDB();
		itemListArray.clear();					//clear item Array
		list.getItems().setAll();				//clear ListView
		ResultSet rs=ctrl.fetchInventory();		//load items from DB into ListView

		try {
			while(rs.next()) {					//load each row into an entry in ListView
				String rsItemID=rs.getString("itemID");

				list.getItems().add(rsItemID+" : "+rs.getString("itemName"));
				itemListArray.add(rsItemID);
			}
		} catch (SQLException e) {
			System.out.println("Failure populating ListView");
			e.printStackTrace();
		}		
	}
	public static void main(String[] args) {
		launch(args);
	}
	@FXML
	void changeSceneToPop_Up(ActionEvent event) throws IOException{
		
		if(event.getSource() == remove){	//remove button pushed
			was_Pressed = "remove";
		}else{								//update button pushed
			was_Pressed = "update";
		}
		
		Item passingItem=new Item();
		passingItem.setList(list);
		passingItem.setItemName(item_Name.getText());
		passingItem.setItemColor(item_Color.getText());
		passingItem.setItemPrice(item_Price.getText());
		passingItem.setItemSize(item_Size.getText());
		passingItem.setItemCondition(item_Condition.getText());
		passingItem.setItemLocation(item_Location.getText());
		
		//load scene
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/Pop_Up.fxml"));
		Parent root=loader.load();
		Scene scene=new Scene(root);
		
		//grab controller from new scene
		Pop_UpController controller=loader.getController();
		//pass Item
		controller.initData(passingItem);
		
		//set and show stage
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.setTitle("Are you Sure?");
		stage.setScene(scene);
		stage.show();	
	}
	
	@FXML
	void changeSceneToAdd_Item(ActionEvent event) throws IOException{
		
		//store variables into Item
		Item passingItem=new Item();
		passingItem.setList(list);
		
		//load scene
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/Add_Item.fxml"));
		Parent root=loader.load();
		Scene scene=new Scene(root);
		
		//grab controller from new scene
		Add_ItemController controller=loader.getController();
		//pass Item
		controller.initData(passingItem);
		
		//set and show stage
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.setTitle("Add Item");
		stage.setScene(scene);
		stage.show();	
	}
	
	@FXML
	void get_info(MouseEvent event) throws IOException{
		
		String itemID=itemListArray.get(list.getSelectionModel().getSelectedIndex());
		
		try {
			ResultSet itemInfo = ctrl.getInfo(itemID);						//DB Query
			itemInfo.next();												//move reader to start of info
			item_Name.setText(itemInfo.getString("itemName"));
			item_ID.setText(itemID);
			item_Color.setText(itemInfo.getString("color"));
			item_Price.setText(itemInfo.getString("price"));
			item_Size.setText(itemInfo.getString("size"));
			item_Condition.setText(itemInfo.getString("conditionName"));
			item_Location.setText(itemInfo.getString("areaName"));

			while(itemInfo.next()) {										//for items with multiple condition tags
				item_Condition.setText(item_Condition.getText()+", "+itemInfo.getString("conditionName"));
			}
		} catch(SQLException e) {
			System.out.println("Failure setting info text fields");
			e.printStackTrace();
		}
	}
}