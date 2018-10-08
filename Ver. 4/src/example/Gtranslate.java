package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Gtranslate {
	
	@FXML
	private TextArea textTarget;
	
	@FXML
	private TextArea textExplain;
	
	public void start() throws IOException {
		Stage primaryStage = new Stage();
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("Glayout.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.showAndWait();
	}
	
	public void translateWithGoogle() throws IOException
	{
		textExplain.setText(search(format(textTarget.getText()),Controller.getsourceLang(),Controller.gettargetLang() ));
	}
	public String search(String sourceText, String sourceLang, String targetLang) throws IOException {
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
	}

	public String format(String s)
	{
		s = s.replaceAll(" ", "+");
		return s;
	}

}
