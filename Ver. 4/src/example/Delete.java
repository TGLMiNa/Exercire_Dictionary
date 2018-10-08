package example;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class Delete {
	
	@FXML
	private TextArea textTarget;
	
	@FXML
	private Button exit;
	
	public void start() throws IOException {
		Stage primaryStage = new Stage();
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("deleteLayout.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.showAndWait();
	}
	
	public void deleteWord()
	{
		if (textTarget.getText().equals(""))
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please enter your word !!!");
			alert.showAndWait();
		}
		else
		if (!Controller.contain(textTarget.getText()))
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Your word does not exist !!!");
			alert.showAndWait();
		}
		else
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure to delete this word?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK)
			{
				Controller.delete(textTarget.getText());
				Stage stage = (Stage) exit.getScene().getWindow();
			    stage.close();
			}
		}
	}
	
	public void closeWindow()
	{
		Stage stage = (Stage) exit.getScene().getWindow();
	    stage.close();
	}
}
