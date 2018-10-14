package example;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import application.TST;

public class DictionaryManagement {
	private Dictionary dict = new Dictionary();
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private TST<String> trie;

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
			trie.put(EWord, VWord);
		}

	}

	public void insertFromFile() throws IOException {
		trie = null;
		trie =  new TST<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("resources/dictionaries.txt"), "UTF-8"));
		String Line = reader.readLine();
		while ((Line = reader.readLine()) != null) {
			String[] word = Line.split("##");
			trie.put(word[0], word[1]);
		}
		reader.close();
	}

	public void insertFromFileVA() throws IOException {
		trie = null;
		trie =  new TST<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("resources/VA.txt"), "UTF-8"));
		String Line = reader.readLine();
		while ((Line = reader.readLine()) != null) {
			String[] word = Line.split("##");
			trie.put(word[0], word[1]);
		}
		reader.close();
	}
	
	private void addDict(String EWord, String VWord) {
		Word newWord = new Word();
		newWord.setWord_target(EWord);
		newWord.setWord_explain(VWord);
		dict.add(newWord);
	}

	public String dictionaryLookup(String EWord) {
		return trie.get(EWord);
	}


	public Iterable<String> showWords() {
		return trie.keys();
	}

	public void addWord(String EWord, String VWord) {
		trie.put(EWord, VWord);
	}

	public boolean contains(String word)
	{
		return trie.contains(word);
	}
	
	public String get(String word)
	{
		return trie.get(word);
	}
	public void changeWord(String EWord, String VWord) {
		trie.put(EWord, VWord);
	}

	public void deleteWord(String Word){
		trie.put(Word, null);
	}

	public Iterable<String> searchWord(String word)  {
		return trie.keysWithPrefix(word);
	}	

	public void dictionaryExportToFile() throws IOException {
		PrintWriter writer = new PrintWriter("Output/output.txt", "UTF-8");
		for (String s : trie.keys()) {
			writer.println(s + ": " + trie.get(s));
		}
		writer.close();
	}
}
