package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Gtranslate implements Initializable {

	@FXML
	private TextArea textTarget = new TextArea();

	@FXML
	private TextArea textExplain = new TextArea();

	public void start() throws IOException {
		Stage primaryStage = new Stage();
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("Glayout.fxml"));
		primaryStage.setScene(new Scene(root));
		Image image = new Image(getClass().getResourceAsStream("/icons8_Google_Translate_48px.png"));
		primaryStage.getIcons().add(image);
		primaryStage.setTitle("Google translate");
		primaryStage.setResizable(false);
		primaryStage.showAndWait();
	}

	public void translateWithGoogle() throws IOException {
		textExplain.setText(search(format(textTarget.getText()), Controller.getsourceLang(), Controller.gettargetLang()).replace("~", "."));
	}

	public String search(String sourceText, String sourceLang, String targetLang) throws IOException {
		try {
			URL url = new URL("https://translate.googleapis.com/translate_a/single?client=gtx&sl=" + sourceLang + "&tl="
					+ targetLang + "&dt=t&q=" + sourceText);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
			}
			rd.close();
			String result = response.toString();
			int index = 4;
			while (result.charAt(index) != '"')
				index++;
			return result.substring(4, index);
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please check your internet !!!");
			alert.showAndWait();
			return "";
		}
	}

	public String format(String s) {
		s = s.replaceAll(" ", "+");
		s = s.replace(".", "~");
		return s;
	}

	public void speakText() throws JavaLayerException, IOException {
		try {
			String word = textTarget.getText();
			URL url = new URL("https://translate.google.com.vn/translate_tts?ie=UTF-8&q=" + format(word) + "&tl="
					+ Controller.getsourceLang() + "&client=tw-ob");
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			Player player = new Player(con.getInputStream());
			player.play();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please check your internet !!!");
			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if (!Controller.getGsearchWord().equals("")) {
			textTarget.setText(Controller.getGsearchWord());
			try {
				textExplain.setText(
						search(format(textTarget.getText()), Controller.getsourceLang(), Controller.gettargetLang()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
