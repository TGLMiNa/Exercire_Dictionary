import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DictionaryManagement {
	public Dictionary dict = new Dictionary();

	public void insertFromCommandline() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Number of Words : ");
		int numberWords = Integer.parseInt(input.readLine());
		for (int i = 0; i < numberWords; i++) {
			Word newWord = new Word();
			System.out.println("<" + (i + 1) + ">");
			System.out.print("English word: ");
			newWord.setWord_target(input.readLine());
			System.out.print("Vietnamese word: ");
			newWord.setWord_explain(input.readLine());
			System.out.println("------------------------------------------------------");
			dict.arrWord.add(newWord);
		}

	}
}
