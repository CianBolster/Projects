import java.util.*;
import java.io.*;
public class Project3{
	/**
		A program to demonstrate string manipulation and handling files.
		Spelling in this code is compliant with American English.
	**/
	//This Scanner object is declared globally because multiple methods require it.
	public static Scanner consoleInput = new Scanner(System.in);
	public static ArrayList<String> userDictArrayList;
	public static void main(String[] args) {
		File userDictFile;
		byte menuChoice = -1;
		if (args.length > 0) {
			if (args[0].matches("-h") || args[0].matches("--help")) {
				System.out.println("This program allows users to interact with a dictionary file");
				System.exit(0);
			}
		}
		String dictFileName = "";
		System.out.println("Please enter the name of your dictionary file.");
		dictFileName = consoleInput.nextLine();
		userDictFile = new File(dictFileName);
		if (userDictFile.exists()){
			userDictArrayList = readWordsFromFile(dictFileName);
		} else {
			userDictArrayList = new ArrayList<String>();
			System.out.println("The file was not found, an empty one will be created");
			//Create a new file 
			writeToFile(userDictArrayList, dictFileName);
		}

		while (menuChoice != 0){
			menuChoice = getMenuOption();
			/*
				The switch calls the desired method based on the user's selection and resets
				the menuChoice variable to the default of -1 if a feature (method) was used.
				The option 0 results in a break from the switch construct in order to keep menuChoice
				at 0 and end the while loop.
			*/
				switch (menuChoice){
						case 1: SearchDictionary();
						break;
						case 2: AddDictionary();
						break;
						case 3: DeleteDictionary();
						break;
						case 4: FrequencyWordCount();
						break;
						case 5: LongesyShortestPalindrome();
						break;
						case 6: AddPassage();
						break;
						case 7: Lexicographer();
						break;
						case 0: break;
						default: menuChoice = -1;
			}
		}


	}
	public static byte getMenuOption(){
		byte chosenOption;
		String userChoice = "", menuOptions = "Please select one of the following options by entering the option number:";
		menuOptions += "\n 1) Search.";
		menuOptions += "\n 2) Add.";
		menuOptions += "\n 3) Delete.";
		menuOptions += "\n 4) Frequency of starting letters and word count";
		menuOptions += "\n 5) Longest, shortest word and palindrome.";
		menuOptions += "\n 6) Add passage from file";
		menuOptions += "\n 7) Lexicographer function";
		menuOptions += "\n 0) Exit";
		/*
			gets an option from the user and trims off leading and trailing whitespace
			a better solution would be needed if the menu had more than ten options.
		*/
		System.out.println(menuOptions);
		userChoice = consoleInput.nextLine();
		//If input isn't null, it checks if it's in the range 0-7. If it isn't, the menu will repeat.
		if (userChoice != null){
			 userChoice = userChoice.trim();
			if (userChoice.matches("[0-7]"))
				chosenOption = Byte.parseByte(userChoice);
			else{
				System.out.println("You must enter a number between 0-7");
				chosenOption = -1;	
			}
		}
		else chosenOption = 0;
		return chosenOption;
	}

	public static void SearchDictionary(){}

	public static void AddDictionary(){}

	public static void DeleteDictionary(){}

	public static void FrequencyWordCount()
	{
		int [] frequency = new int [26];
		String lowerWord = "";
		String resultsMessage = "";
		System.out.print("The dictionary contains " + dictionaryArrayList.size() + " words in total.");
		for(int i = 0; i < dictionaryArrayList.size(); i++)
		{
			lowerWord = dictionaryArrayList.get(i);
			lowerWord = lowerWord.toLowerCase();
			char c = lowerWord.charAt(0);
			frequency[c-97] += 1;
		}
		int counter = 0;
		System.out.print("\nThe frequency of the words that begin with each letter of the alphabet is as follows:\n");
		for(int i = 0; i < frequency.length; i++)
		{
			int ch = i + 65;
			char c = (char) ch;
			if(counter%7 == 0)
				resultsMessage+= "\n";
			resultsMessage +=  c + ":" + frequency[i] + "\t";
			counter += 1;
		}
		System.out.println(resultsMessage+"\n");
	}

	public static void LongesyShortestPalindrome()
	{
		int index, maxInt = 0, minInt = 0, count, counter = 0;
		String result = "";
		String palindromeResult = "";
		boolean atLeastOnePal = false;
		int [] palindromePosition = new int [dictionaryArrayList.size()];
		char aChar = 'x', bChar;
		int lengthArray [] = new int [dictionaryArrayList.size()];					
		if(dictionaryArrayList.size() > 1)											
		{
			for(index = 0; index < dictionaryArrayList.size(); index++)
			{
				String tempTrimmed = "";
				String tempReverse = "";
				for(int i = 0; i < (dictionaryArrayList.get(index).length()); i++)
				{
					aChar = (dictionaryArrayList.get(index)).charAt(i);
					if (Character.isLetterOrDigit(aChar) || Character.isWhitespace(aChar))
						tempTrimmed += aChar;
				}
				for(int i = tempTrimmed.length()-1; i >= 0 ; i--)
				{
					bChar = tempTrimmed.charAt(i);
					if (Character.isLetterOrDigit(aChar) || Character.isWhitespace(aChar))
						tempReverse += bChar;
				}
				if(tempReverse.equalsIgnoreCase(tempTrimmed))
				{
					palindromePosition[index]++;
					atLeastOnePal = true;
				}
				if(index == 0)
				{
					maxInt = tempTrimmed.length();
					minInt = tempTrimmed.length();
				}
				lengthArray[index] = tempTrimmed.length();							
				if((lengthArray[index]) > maxInt)									
					maxInt = lengthArray[index];									
				else if ((lengthArray[index]) < minInt)
					minInt = lengthArray[index];
			}
			if(!(dictionaryArrayList.isEmpty()))
			{
				result = "\nThe maximum length of a word in this dictionary file is " + maxInt +					
												"\nThe words with this length are: ";				
				for(index = 0; index < dictionaryArrayList.size(); index++)								
				{
					if(lengthArray[index] == maxInt)
					{
						if(counter%8 == 0)
						{
							result += "\n";
						}
						result += dictionaryArrayList.get(index) + "\t";
						counter ++;
					}
				}
				counter = 0;
				result +="\n___________________________________________________________________________________________________________________\n";
				result += "\nThe minimum length of a word in this sentence is " + minInt +
													"\nThe words with this length are: ";
				for(index = 0; index < dictionaryArrayList.size(); index++)
				{
					if(lengthArray[index] == minInt)
					{
						if(counter%8 == 0)
						{
							result += "\n";
						}
						result += dictionaryArrayList.get(index) + "\t";
						counter++;
					}
				}
				counter = 0;
				result +="\n___________________________________________________________________________________________________________________";
				if(atLeastOnePal)
				{
					result += "\n\nThe following words are palindromes:";
					for(index = 0; index < dictionaryArrayList.size(); index ++)
					{
						if(palindromePosition[index] == 1)
						{
							if(counter%8 == 0)
							{
								result += "\n";
							}
							result += dictionaryArrayList.get(index) + "\t";
						}
					}
				}
				else
					result += "\n\nThere are no plaindromes in the dictionary file as of now.";
			}
			else
				result = "The file contains no words as of yet thus has no longest or shorest words and no words that are palindromes";
		}
		result +="\n___________________________________________________________________________________________________________________";
		System.out.print(result+"\n\n");
	}

	public static void AddPassage(){

}

	public static void Lexicographer(){}



	public static ArrayList<String> readWordsFromFile(String fileName){
		/*
			Accepts a string denoting the relative position of a text file.
			Returns an ArrayList of Strings with a single word in each, including special characters.
		*/
		File fileToRead = new File(fileName);
		Scanner dataFromFile;
		ArrayList<String> fileWordsArrayList = new ArrayList<String>();
		String[] wordsFromFile;
		try {
				if (!fileToRead.exists()){
					dataFromFile = new Scanner(fileToRead);
					while (dataFromFile.hasNext()) {
						//deal with having multiple words on a line.
						wordsFromFile = dataFromFile.nextLine().split(" ");
						//dump all of that business into the ArrayList no matter what.
						for (String word : wordsFromFile) fileWordsArrayList.add(word);
					}
				}
		}
		catch (Exception e){
			//If the file doesn't exist and logic fails, empty out the ArrayList.
			fileWordsArrayList = new ArrayList<String>();
		}
		return fileWordsArrayList;
	}


	public static void writeToFile(ArrayList<String> lines, String fileName){
		/*
			Will print an ArrayList of Strings to a file defined by a supplied String.
			Terminates the application with an error message if exceptions occur.
		*/
		PrintWriter output;
		try {
			output = new PrintWriter(fileName);
			//This is to make a new file, or blank it out if the ArrayList is empty
			output.println("");
			for (String lineToWrite : lines)
				output.println(lineToWrite);
			output.close();
		}
		catch (Exception e) {
			System.err.println("Something dreadful has happened.\nThe application can't access/modify files.\nIt will now exit.");
			System.exit(74);
		}





	}
}
