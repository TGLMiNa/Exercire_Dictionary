package example;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class About {
		
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
	
}
