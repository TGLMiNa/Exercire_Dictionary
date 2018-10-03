import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import application.trieST;
import javafx.scene.shape.Line;

public class DictionaryManagement {
	private Dictionary dict = new Dictionary();
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private final trieST<String> trie = new trieST<>();

	public void insertFromCommandline() throws IOException {
		System.out.print("Number of Words : ");
		int numberWords = Integer.parseInt(input.readLine());
		for (int i = 0; i < numberWords; i++) {
			String EWord, VWord;
			System.out.println("<" + (i + 1) + ">");
			System.out.print("English word: ");
			EWord = input.readLine();
			System.out.print("Vietnamese word: ");
			VWord = input.readLine();
			System.out.println("------------------------------------------------------");
			addDict(EWord, VWord);
			addtrie(EWord, VWord);
		}

	}

	public void insertFromFile() throws IOException {
		InputStream res = DictionaryManagement.class.getResourceAsStream("dictionaries.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(res));
		String Line = reader.readLine();
		while ((Line = reader.readLine()) != null) {
			String[] word = Line.split("\t");
			addDict(word[0], word[1]);
			addtrie(word[0], word[1]);
		}
	}

	private void addDict(String EWord, String VWord) {
		Word newWord = new Word();
		newWord.setWord_target(EWord);
		newWord.setWord_explain(VWord);
		dict.add(newWord);
	}

	private void addtrie(String EWord, String VWord) {
		trie.put(EWord, VWord);
	}

	public void dictionaryLookup() throws IOException {
		System.out.println("------------------------------------------------------------------------");
		System.out.print("Enter your word : ");
		String EWord = input.readLine();
		if (!trie.contains(EWord))
			System.out.println("Sorry, no exact matches for " + "\"" + EWord + "\"" + ".");
		else
			System.out.println("The meaning : " + trie.get(EWord));
		System.out.println("------------------------------------------------------------------------");
	}

	private void formatPrint(int count, String target, String explain) {
		String tab = "\t";
		if (target.length() < 7)
			tab += "\t";
		System.out.println(count + "\t" + "|" + target + tab + "| " + explain);
	}

	public void showWords() {
		System.out.println("------------------------------------------------------------------------");
		System.out.println("No\t| English\t| Vietnamese");
		int count = 0;
		for (String s : trie.keys()) {
			count++;
			formatPrint(count, s, trie.get(s));
		}
		System.out.println("------------------------------------------------------------------------");
	}

	public void addWord() throws IOException {
		System.out.println("------------------------------------------------------");
		String EWord, VWord;
		System.out.print("English word: ");
		EWord = input.readLine();
		if (trie.contains(EWord)) {
			System.out.println("The word already exist.");
			System.out.println("------------------------------------------------------");
			return;
		}
		System.out.print("Vietnamese word: ");
		VWord = input.readLine();
		addDict(EWord, VWord);
		addtrie(EWord, VWord);
		System.out.println("------------------------------------------------------");
	}

	public void changeWord() throws IOException {
		String EWord, VWord;
		System.out.println("------------------------------------------------------");
		System.out.print("Enter, the word can change : ");
		EWord = input.readLine();
		System.out.print("The new meaning : ");
		VWord = input.readLine();
		if (!trie.contains(EWord))
			System.out.println("Sorry, no exact matches for " + "\"" + EWord + "\"" + ".");
		else
			trie.put(EWord, VWord);
		System.out.println("------------------------------------------------------");
	}

	public void deleteWord() throws IOException {
		System.out.println("------------------------------------------------------");
		System.out.print("Enter, the word can delete : ");
		String EWord;
		EWord = input.readLine();
		if (!trie.contains(EWord))
			System.out.println("Sorry, no exact matches for " + "\"" + EWord + "\"" + ".");
		else {
			dict.delete(EWord);
			trie.delete(EWord);
		}
		System.out.println("------------------------------------------------------");
	}

	public void searchWord() throws IOException {
		System.out.println("------------------------------------------------------");
		System.out.print("Enter, the word can search : ");
		String EWord;
		EWord = input.readLine();
		int count = 0;
		for (String s : trie.keysWithPrefix(EWord)) {
			count++;
			System.out.println(count + "." + s);
		}
		System.out.println("------------------------------------------------------");
	}

	public void dictionaryExportToFile() throws IOException {
		PrintWriter writer = new PrintWriter("Output/output.txt", "UTF-8");
		for (String s : trie.keys()) {
			writer.println(s + ": " + trie.get(s));
		}
		writer.close();
		System.out.println("Done");
	}
}
