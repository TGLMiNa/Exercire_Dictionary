import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DictionaryCommandline {
	private DictionaryManagement DictManage = new DictionaryManagement();

	public void showAllWords() {
		System.out.println("------------------------------------------------------------------------");
		System.out.println("No   | English          | Vietnamese");
		int count = 0;
		for (Word s : DictManage.dict.arrWord) {
			count++;
			System.out.println(count + "    | " + s.getWord_target() + "\t\t" + "| " + s.getWord_explain());
		}
		System.out.println("------------------------------------------------------------------------");
	}

	public void dictionaryBasic() throws NumberFormatException, IOException {
		int number = 0;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("       ------------------------------------------------------");
		System.out.println("       |                  English Dictionary                |");
		System.out.println("       |   Please select one of the below options :         |");
		System.out.println("       |     1. Add words                                   |");
		System.out.println("       |     2. Show all words                              |");
		System.out.println("       |     3. Exit                                        |");
		System.out.println("       ------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------");
		System.out.println();
		do {
			System.out.print("\tSelect your number of option : ");
			try {
				number = Integer.parseInt(input.readLine());
			} catch (NumberFormatException exception) {
				System.out.println("\t***Your option not found, please select again.");
				System.out.print("\tSelect your number of option : ");
				number = Integer.parseInt(input.readLine());
			}
			switch (number) {
			case 1:
				DictManage.insertFromCommandline();
				break;
			case 2:
				showAllWords();
				break;
			case 3:
				System.out.println("\tDo you want to exit? (Y/N) ");
				if (input.readLine() == "Y")
					continue;
				else
					number = 0;
			default:
				System.out.println("\t***Your option not found, please select again ;");
				continue;
			}
		} while (number != 3);

	}

}
