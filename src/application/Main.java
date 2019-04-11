package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class Main extends Application {
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
    
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));

		

		Scene scene = new Scene(root);
		primaryStage.setTitle("Wares My Stuff?");
		primaryStage.setScene(scene);
		primaryStage.show();


	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
