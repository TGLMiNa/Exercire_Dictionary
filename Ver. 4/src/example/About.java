package example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class About implements Initializable{
	
	@FXML
	ImageView logo = new ImageView();
	
	public void start() throws IOException {
		Stage primaryStage = new Stage();
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("aboutLayout.fxml"));
		primaryStage.setScene(new Scene(root));
		Image image = new Image(getClass().getResourceAsStream("/icons8_About_48px.png"));
		primaryStage.getIcons().add(image);
		primaryStage.setTitle("About");
		primaryStage.setResizable(false);
		primaryStage.showAndWait();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Image image = new Image(getClass().getResourceAsStream("/icons8_Address_Book_50px.png"));
		logo.setImage(image);
	}
	
}
