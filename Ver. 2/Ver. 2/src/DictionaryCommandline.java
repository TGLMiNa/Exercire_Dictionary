import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DictionaryCommandline {
	private DictionaryManagement DictManage = new DictionaryManagement();

	public void showAllWords() {
		DictManage.showWords();
	}

	/*--------------------------------------------------------------------------------------*/

	public void dictionaryBasic() throws NumberFormatException, IOException {
		int number = 0;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("       ------------------------------------------------------");
		System.out.println("       |             English Dictionary - Ver.1             |");
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
				System.out.print("\tDo you want to exit? (Y/N) : ");
				if (!input.readLine().equals("Y"))
					number = 0;
				break;
			default:
				System.out.println("\t***Your option not found, please select again ;");
				continue;
			}
		} while (number != 3);

	}

	/*--------------------------------------------------------------------------------------*/

	public void dictionaryAdvanced() throws NumberFormatException, IOException {
		DictManage.insertFromFile();
		int number = 0;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("       ------------------------------------------------------");
		System.out.println("       |            English Dictionary - ver.2              |");
		System.out.println("       |   Please select one of the below options :         |");
		System.out.println("       |     1. Search word                                 |");
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
				DictManage.dictionaryLookup();
				break;
			case 2:
				showAllWords();
				break;
			case 3:
				System.out.print("\tDo you want to exit? (Y/N) : ");
				if (!input.readLine().equals("Y"))
					number = 0;
				break;
			default:
				System.out.println("\t***Your option not found, please select again ;");
				continue;
			}
		} while (number != 3);

	}

}
