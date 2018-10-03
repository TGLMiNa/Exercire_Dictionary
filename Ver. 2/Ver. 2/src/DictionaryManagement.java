import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javafx.scene.shape.Line;

public class DictionaryManagement {
	public Dictionary dict = new Dictionary();
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private final HashMap<String,String> map = new HashMap<>();
	
	
	public void insertFromCommandline() throws IOException {
		System.out.print("Number of Words : ");
		int numberWords = Integer.parseInt(input.readLine());
		for (int i = 0; i < numberWords; i++) {
			String EWord,VWord;
			System.out.println("<" + (i + 1) + ">");
			System.out.print("English word: ");
			EWord = input.readLine();
			System.out.print("Vietnamese word: ");
			VWord  = input.readLine();
			System.out.println("------------------------------------------------------");
			addDict(EWord,VWord);
			addMap(EWord,VWord);
		}

	}
	
	public void insertFromFile() throws IOException{
		InputStream res = DictionaryManagement.class.getResourceAsStream("dictionaries.txt");
		BufferedReader reader = new BufferedReader (new InputStreamReader(res));
		String Line = reader.readLine();
		while ((Line = reader.readLine()) != null)
		{
			String[] word = Line.split("\t");
			addDict(word[0],word[1]);
			addMap(word[0],word[1]);
		}
	}
	
	private void addDict(String EWord, String VWord)
	{
		Word newWord = new Word();
		newWord.setWord_target(EWord);
		newWord.setWord_explain(VWord);
		dict.add(newWord);
	}
	
	private void addMap(String EWord, String VWord)
	{
		map.put(EWord, VWord);
	}
	
	public void dictionaryLookup() throws IOException
	{
		System.out.println("------------------------------------------------------------------------");
		System.out.print("Enter your word : ");
		String EWord = input.readLine();
		if (!map.containsKey(EWord))
			System.out.println("Sorry, no exact matches for " + "\"" + EWord +"\"" + ".");
		else
			System.out.println("The meaning : " + map.get(EWord));
		System.out.println("------------------------------------------------------------------------");
	}
	
	private void formatPrint(int count,String target,String explain)
	{
		String tab = "\t";
		if (target.length() < 7)
			tab += "\t";
		System.out.println(count +"\t"+"|" + target + tab + "| " + explain);
	}
	
	public void showWords()
	{
		System.out.println("------------------------------------------------------------------------");
		System.out.println("No\t| English\t| Vietnamese");
		int count = 0;
		for (int i=0;i<dict.length();i++) {
			count++;
			formatPrint(count,dict.getIndex(i).getWord_target(),dict.getIndex(i).getWord_explain());
		}
		System.out.println("------------------------------------------------------------------------");
	}
}
