
import java.util.ArrayList;

public class Dictionary {
	private ArrayList<Word> arrWord = new ArrayList<>();

	public void add(Word newWord) {
		arrWord.add(newWord);
	}

	public int length() {
		return arrWord.size();
	}

	public Word getIndex(int index) {
		return arrWord.get(index);
	}

	public void delete(String key) {
		arrWord.remove(key);
	}
}
