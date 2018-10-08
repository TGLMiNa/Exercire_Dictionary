package example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DictionaryCommandline {
	private DictionaryManagement DictManage = new DictionaryManagement();
	
	private void formatPrint(int count, String target, String explain) {
		String tab = "\t";
		if (target.length() < 7)
			tab += "\t";
		System.out.println(count + "\t" + "|" + target + tab + "| " + explain);
	}
	
	public void showAllWords() {
		System.out.println("------------------------------------------------------------------------");
		System.out.println("No\t| English\t| Vietnamese");
		int count = 0;
		for (String s : DictManage.showWords()) {
			count++;
			formatPrint(count, s, DictManage.get(s));
		}
		System.out.println("------------------------------------------------------------------------");
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
		String EWord, VWord;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("       ------------------------------------------------------");
		System.out.println("       |            English Dictionary - ver.3              |");
		System.out.println("       |   Please select one of the below options :         |");
		System.out.println("       |     1. Search word                                 |");
		System.out.println("       |     2. Show all words                              |");
		System.out.println("       |     3. Add word                                    |");
		System.out.println("       |     4. Change word                                 |");
		System.out.println("       |     5. Delete word                                 |");
		System.out.println("       |     6. Find word with prefix                       |");
		System.out.println("       |     7. Export file (Output/output.txt)             |");
		System.out.println("       |     8. Exit                                        |");
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
				System.out.println("------------------------------------------------------------------------");
				System.out.print("Enter your word : ");
				EWord = input.readLine();
				VWord = DictManage.dictionaryLookup(EWord);
				if (VWord == null || VWord.equals(""))
					System.out.println("Sorry, no exact matches for " + "\"" + EWord + "\"" + ".");
				else
					System.out.println("The meaning : " + VWord) ;
				System.out.println("------------------------------------------------------------------------");
				break;
			case 2:
				showAllWords();
				break;
			case 3:
				System.out.println("------------------------------------------------------");
				System.out.print("English word: ");
				EWord = input.readLine();
				if (DictManage.contains(EWord)) {
					System.out.println("The word already exist.");
					System.out.println("------------------------------------------------------");
					return;
				}
				System.out.print("Vietnamese word: ");
				VWord = input.readLine();
				DictManage.addWord(EWord, VWord);
				System.out.println("------------------------------------------------------");
				break;
			case 4:
				System.out.println("------------------------------------------------------");
				System.out.print("Enter, the word can change : ");
				EWord = input.readLine();
				System.out.print("The new meaning : ");
				VWord = input.readLine();
				if (!DictManage.contains(EWord))
					System.out.println("Sorry, no exact matches for " + "\"" + EWord + "\"" + ".");
				else
					DictManage.changeWord(EWord, VWord);
				System.out.println("------------------------------------------------------");

				break;
			case 5:
				System.out.println("------------------------------------------------------");
				System.out.print("Enter, the word can delete : ");
				EWord = input.readLine();
				if (!DictManage.contains(EWord))
					System.out.println("Sorry, no exact matches for " + "\"" + EWord + "\"" + ".");
				else {
					DictManage.deleteWord(EWord);
				}
				System.out.println("------------------------------------------------------");
				break;
			case 6:
				System.out.println("------------------------------------------------------");
				System.out.print("Enter, the word can search : ");
				EWord = input.readLine();
				int count = 0;
				for (String s : DictManage.searchWord(EWord)) {
					count++;
					System.out.println(count + "." + s);
				}
				System.out.println("------------------------------------------------------");
				break;
			case 7:
				DictManage.dictionaryExportToFile();
				break;
			case 8:
				System.out.print("\tDo you want to exit? (Y/N) : ");
				if (!input.readLine().equals("Y"))
					number = 0;
				break;
			default:
				System.out.println("\t***Your option not found, please select again.");
			}
		} while (number != 8);

	}

}
