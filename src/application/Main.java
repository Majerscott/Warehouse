package application;
	


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ListView<?> list;

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
    
    private String was_Pressed;
    
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));

		//TODO: load item from DB into ListView

		Scene scene = new Scene(root);
		primaryStage.setTitle("Wares My Stuff?");
		primaryStage.setScene(scene);
		primaryStage.show();


	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML
	void pop_Up(ActionEvent event) throws IOException{
		
		if(event.getSource() == remove){
			was_Pressed = "remove";
		}else{
			was_Pressed = "update";
		}
		Stage stage = new Stage();
		Parent root;
				
		root = FXMLLoader.load(getClass().getResource("Pop_UP.fxml"));
		
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setTitle("Are you Sure?");
		stage.setScene(scene);
		stage.show();
		
	}
	
	@FXML
	void close_PU(ActionEvent event) throws IOException{
		Stage stage;
		
		stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
		
		stage.close();
	}
	
	@FXML
	void yes_Click(ActionEvent event) throws IOException{
		if(was_Pressed == "remove"){
			//remove item was confirmed
			//TODO: remove item from DB
		}else{
			//update item was confirmed
			//TODO: update item with new attributes
		}
		
		Stage stage;
		stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	void add_Item(ActionEvent event) throws IOException{
		Stage stage = new Stage();
		Parent root;
		
				
		root = FXMLLoader.load(getClass().getResource("Add_Item.fxml"));
		
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setTitle("Add Item");
		stage.setScene(scene);
		stage.show();
		
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
		
		//TODO: add item to DB
		
		Stage stage;
		stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	void get_info(ActionEvent event) throws IOException{
		//TODO: display selected item in  text fields right of list
	}
	
}
