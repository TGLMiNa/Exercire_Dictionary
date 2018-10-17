package example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller extends Application implements Initializable {
	private static DictionaryManagement DictManage = new DictionaryManagement();

	@FXML
	private TextField fieldSearch;

	@FXML
	private TextArea textArea;

	@FXML
	private TextFlow textFlow;

	private Text text = new Text();

	private Hyperlink link = new Hyperlink("Google Translate !!!");
	@FXML
	private Label nameWord;

	@FXML
	private Button speakUKbtn, speakUSbtn;

	@FXML
	private ImageView speakUSLB, speakUKLB, changeImg = new ImageView(), addImg = new ImageView(),
			deleteImg = new ImageView(), GtranslateImg = new ImageView(), minimizeWindow = new ImageView(),
			maximizeWindow = new ImageView(), closeWindow = new ImageView(),logo = new ImageView();

	@FXML
	private Label textUSLB, textUKLB, speakUSLBA, speakUKLBA, changeLB, GtranslateLB, addLB, deleteLB;

	@FXML
	private Button recentAfter, searchAfter;

	@FXML
	private ListView<String> recentList, listWord;

	@FXML
	private AnchorPane pane;

	private VoiceManager vm;

	private Voice v;

	private Gtranslate gtranslate = new Gtranslate();

	private static String sourceLang = "en", targetLang = "vi", GsearchWord = "";

	private Add addWindow = new Add();
	private Change changeWindow = new Change();
	private Delete deleteWindow = new Delete();
	private About aboutWindow = new About();
	ObservableList<String> elementsListWord = FXCollections.observableArrayList();

	ObservableList<String> elementsRecentWord = FXCollections.observableArrayList();

	private ArrayList<String> listRecentWord = new ArrayList<>();

	private double x, y;

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
		primaryStage.setScene(new Scene(root));
		Image image = new Image(getClass().getResourceAsStream("/icons8_Address_Book_50px.png"));
		primaryStage.getIcons().add(image);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure to exit?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				primaryStage.close();
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image image = new Image(getClass().getResourceAsStream("/logo.png"));
		logo.setImage(image);
		image = new Image(getClass().getResourceAsStream("/icons8_Available_Updates_32px.png"));
		changeImg.setImage(image);
		image = new Image(getClass().getResourceAsStream("/icons8_Plus_32px.png"));
		addImg.setImage(image);
		image = new Image(getClass().getResourceAsStream("/icons8_Trash_32px.png"));
		deleteImg.setImage(image);
		image = new Image(getClass().getResourceAsStream("/icons8_Google_Translate_32px.png"));
		GtranslateImg.setImage(image);
		image = new Image(getClass().getResourceAsStream("/icons8_Speaker_32px.png"));
		speakUSLB.setImage(image);
		speakUKLB.setImage(image);
		image = new Image(getClass().getResourceAsStream("/icons8_Minimize_Window_48px.png"));
		minimizeWindow.setImage(image);
		image = new Image(getClass().getResourceAsStream("/icons8_Plus_48px.png"));
		maximizeWindow.setImage(image);
		image = new Image(getClass().getResourceAsStream("/icons8_Cancel_48px.png"));
		closeWindow.setImage(image);
		recentList.setVisible(false);
		textFlow.setVisible(false);
		try {
			DictManage.insertFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String s : DictManage.showWords()) {
			elementsListWord.add(s);
		}
		listWord.setItems(elementsListWord);
		listWord.getSelectionModel().select(0);
		recentList.setItems(elementsRecentWord);
		System.setProperty("mbrola.base", "mbrola");
		vm = VoiceManager.getInstance();
		v = vm.getVoice("mbrola_us1");
		v.allocate();
		text.setFont(Font.font("Helvetica neue", FontWeight.THIN, FontPosture.ITALIC, 21));
		text.setText("The word not found in Dictionary data. You can search with ");
		link.setFont(Font.font("Helvetica neue", FontPosture.ITALIC, 21));
		link.setOnAction(e -> {
			try {
				GsearchWord = fieldSearch.getText();
				gtranslate.start();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}


	public void changeToRecent() {
		recentList.setVisible(true);
		recentAfter.setStyle("-fx-background-color: white;-fx-text-fill: #757575");
		searchAfter.setStyle("-fx-background-color: SLATEBLUE;-fx-text-fill: #eee7e7;");

	}

	public void changeToSearch() {
		recentList.setVisible(false);
		recentAfter.setStyle("-fx-background-color: SLATEBLUE;-fx-text-fill: #eee7e7");
		searchAfter.setStyle("-fx-background-color: white;-fx-text-fill: #757575;");

	}

	public void onEnter() {
		String word = listWord.getSelectionModel().getSelectedItem();
		nameWord.setText(word);
		if (word == null) {
			textArea.setVisible(false);
			textFlow.setVisible(true);
			textFlow.getChildren().add(text);
			textFlow.getChildren().add(link);
		} else {
			textArea.setVisible(true);
			textFlow.setVisible(false);
			textArea.setText(DictionaryManagement.formatOutput(DictManage.get(word)));
			if (listRecentWord.isEmpty() || !word.equals(listRecentWord.get(listRecentWord.size() - 1))) {
				listRecentWord.add(word);
				elementsRecentWord.clear();
				for (int i = listRecentWord.size() - 1; i >= 0; i--)
					elementsRecentWord.add(listRecentWord.get(i));
			}
		}
	}

	@FXML
	public void listOnEnter(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			onEnter();
	}

	@FXML
	public void listOnClick() {
		onEnter();
	}

	@FXML
	public void keyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.DOWN)
			listWord.requestFocus();
		elementsListWord.clear();
		String word = fieldSearch.getText();
		if (word.length() == 0) {
			for (String s : DictManage.showWords())
				elementsListWord.add(s);
		} else
			for (String s : DictManage.searchWord(word)) {
				elementsListWord.add(s);
			}
		listWord.getSelectionModel().select(0);
	}

	public void openGtranslateWindow() throws IOException {
		GsearchWord = "";
		gtranslate.start();
	}

	public void openAddWindow() throws IOException {
		addWindow.start();
	}

	public void openChangeWindow() throws IOException {
		changeWindow.start();
	}

	public void openDeleteWindow() throws IOException {
		deleteWindow.start();
	}
	
	public void openAboutWindow() throws IOException {
		aboutWindow.start();
	}

	public void setSpeakUSVoice() {
		speakUSLB.setStyle("-fx-opacity: 0.5");
		textUSLB.setStyle("-fx-text-fill : SLATEBLUE");
		v.deallocate();
		v = vm.getVoice("mbrola_us1");
		v.allocate();
	}

	public void setSpeakUKVoice() {
		speakUKLB.setStyle("-fx-opacity: 0.5");
		textUKLB.setStyle("-fx-text-fill : SLATEBLUE");
		v.deallocate();
		v = vm.getVoice("kevin16");
		v.allocate();
	}

	public void speakUSVoice() {
		if (nameWord.getText() != null)
			v.speak(nameWord.getText());
		speakUSLB.setStyle("-fx-opacity: 1");
		textUSLB.setStyle("-fx-opacity: 1");
	}

	public void speakUKVoice() {
		if (nameWord.getText() != null)
			v.speak(nameWord.getText());
		speakUKLB.setStyle("-fx-opacity: 1");
		textUKLB.setStyle("-fx-opacity: 1");
	}

	private void changeBackground(Label lb) {
		lb.setStyle("-fx-background-color:BEIGE;-fx-border-color:black;-fx-border-radius:3px;");
	}

	private void removeBackground(Label lb) {
		lb.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-radius:3px;");
	}

	public void changeBackgroundChangeLB() {
		changeBackground(changeLB);
	}

	public void removeBackgroundChangeLB() {
		removeBackground(changeLB);
	}

	public void changeBackgroundAddLB() {
		changeBackground(addLB);
	}

	public void removeBackgroundAddLB() {
		removeBackground(addLB);
	}

	public void changeBackgroundDeleteLB() {
		changeBackground(deleteLB);
	}

	public void removeBackgroundDeleteLB() {
		removeBackground(deleteLB);
	}

	public void changeBackgroundGtranslateLB() {
		changeBackground(GtranslateLB);
	}

	public void removeBackgroundGtranslateLB() {
		removeBackground(GtranslateLB);
	}

	public void changeBackgroundSpeakUSLB() {
		changeBackground(speakUSLBA);
	}

	public void removeBackgroundSpeakUSLB() {
		removeBackground(speakUSLBA);
	}

	public void changeBackgroundSpeakUKLB() {
		changeBackground(speakUKLBA);
	}

	public void removeBackgroundSpeakUKLB() {
		removeBackground(speakUKLBA);
	}

	public static void add(String target, String explain) {
		DictManage.addWord(target, explain);
	}

	public static void change(String target, String explain) {
		DictManage.addWord(target, explain);
	}

	public static void delete(String target) {
		DictManage.addWord(target, null);
	}

	public static boolean contain(String target) {
		return DictManage.contains(target);
	}

	public static String getsourceLang() {
		return sourceLang;
	}

	public static String gettargetLang() {
		return targetLang;
	}

	public static String getGsearchWord() {
		return GsearchWord;
	}

	public void exportFile() throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to Export File?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			pane.setStyle("-fx-cursor: WAIT");
			DictManage.dictionaryExportToFile();
			pane.setStyle("-fx-cursor: DISAPPEAR");
			System.out.println("Done");
		}
	}

	public void exitWindow() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to exit?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) listWord.getScene().getWindow();
			stage.close();
		}
	}

	public void minimizeWindow() {
		Stage stage = (Stage) listWord.getScene().getWindow();
		stage.setIconified(true);
	}

	public void changeEVtoVE() throws IOException {
		if (sourceLang.equals("en")) {
			pane.setStyle("-fx-cursor: WAIT");
			nameWord.setText("");
			textArea.setText("");
			textFlow.getChildren().clear();
			DictManage.insertFromFileVA();
			speakUKLBA.setVisible(false);
			speakUSLBA.setVisible(false);
			textUSLB.setVisible(false);
			textUKLB.setVisible(false);
			elementsListWord.clear();
			for (String s : DictManage.showWords())
				elementsListWord.add(s);
			pane.setStyle("-fx-cursor: DISAPPEAR");
			speakUKbtn.setVisible(false);
			speakUSbtn.setVisible(false);
			sourceLang = "vi";
			targetLang = "en";
		}
	}

	public void changeVEtoEV() throws IOException {
		if (sourceLang.equals("vi")) {
			pane.setStyle("-fx-cursor: WAIT");
			nameWord.setText("");
			textArea.setText("");
			textFlow.getChildren().clear();
			DictManage.insertFromFile();
			speakUKLBA.setVisible(true);
			speakUSLBA.setVisible(true);
			textUSLB.setVisible(true);
			textUKLB.setVisible(true);
			elementsListWord.clear();
			for (String s : DictManage.showWords())
				elementsListWord.add(s);
			pane.setStyle("-fx-cursor: DISAPPEAR");
			speakUKbtn.setVisible(true);
			speakUSbtn.setVisible(true);
			sourceLang = "en";
			targetLang = "vi";
		}
	}

	public void saveDataDictionary() throws FileNotFoundException, UnsupportedEncodingException, URISyntaxException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to Save File?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			pane.setStyle("-fx-cursor: WAIT");
			File file;
			if (targetLang.equals("vi"))
				file = new File("resources/dictionaries.txt");
			else
				file = new File("resources/VA.txt");
			PrintWriter writer = new PrintWriter(file,"UTF-8");
			writer.println("");
			for (String s : DictManage.showWords())
				writer.println(s + "##" + DictManage.get(s));
			writer.close();
			pane.setStyle("-fx-cursor: DISAPPEAR");
			System.out.println("Done");
		}
	}

	public void dragged(MouseEvent event) {
		Stage stage = (Stage) listWord.getScene().getWindow();
		stage.setX(event.getScreenX() - x);
		stage.setY(event.getScreenY() - y);
	}

	public void pressed(MouseEvent e) {
		x = e.getSceneX();
		y = e.getSceneY();
	}
}
