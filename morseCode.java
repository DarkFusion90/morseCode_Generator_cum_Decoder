import java.util.*;

public class morseCode {
	static Map<Character, String> morseCodeMapTo = new HashMap<>();
	static Map<String, Character> morseCodeMapFrom = new HashMap<>();
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("<<<Welcome to the MorseCode Generator/Decoder Application>>>");
		System.out.println("Enter your preferred choice of Action (1 or 2):");
		System.out.println("1. Generate Morse Code\n2. Decode Morse Code");
		makeYourChoice();
		sc.close();
	}
	
	private static void makeYourChoice() {
		Scanner input = new Scanner(System.in);
		String choice = input.nextLine();
		switch (choice) {
			case "1":                 //ENCRYPTING TO MORSE
				System.out.println("Enter the input to be coded into Morse Code:");
				String morse = transformToMorse(input.nextLine().trim().toLowerCase());
				System.out.println("The Morse Equivalent of the input is: " + morse);
				break;
			case "2":                //DECRYPTING FROM MORSE
				System.out.println("Enter the Morse Code to be decoded:");
				String morse1 = transformFromMorse(input.nextLine().trim());
				System.out.println("The Decoded Morse Code is: " + morse1);
				break;
			
			default:
				System.err.println("Invalid Choice. Please Try Again");
				makeYourChoice();
				break;
		}
	}
	
	private static String transformFromMorse(String morseCode) {
		fillMorseMap("FROM");
		String decodedMorseCode = "";
		String morseWords[] = morseCode.split("[ ]{3}");   //exact three whitespaces: meaning splitting the words
		String morseLetters[];
		
		for (int i = 0; i < morseWords.length; i++) {
			morseLetters = morseWords[i].split(" ");   //one whitespace: meaning splitting letters
			
			for (int j = 0; j < morseLetters.length; j++) {
				String letterInMorse = morseLetters[j];
				if (morseCodeMapFrom.containsKey(letterInMorse)) {
					char letterDecoded = morseCodeMapFrom.get(letterInMorse);
					decodedMorseCode = decodedMorseCode + letterDecoded;
				}
			}
			decodedMorseCode = decodedMorseCode + " "; //adding whitespace between individual words;
		}
		return decodedMorseCode;
	}
	
	private static String transformToMorse(String text) {
		StringBuilder morseCode = new StringBuilder();
		String value;
		fillMorseMap("TO");
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			
			if (ch == ' ') {   //two equal whitespace will be morse equivalent for whitespace
				morseCode.append("  ");
			} else if (morseCodeMapTo.containsKey(ch)) {
				value = morseCodeMapTo.get(ch);
				morseCode.append(value).append(" ");  //every two letters will have a whitespace between them
			}
		}
		return morseCode.toString();
	}
	
	private static void fillMorseMap(String toOrFrom) {
		char characters[] = new char[]{
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
			, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
			, '.', ',', '?', '\'', '!', '/', '(', ')', '&', ':', ';', '=', '+', '-', '_', '\"', '$', '@'};
		String[] morseCharacters = new String[]{
			".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", //from a
			".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
			"...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", //till z
			"-----", ".----", "..---", "...--", "....-", ".....", "-....",  //from 0
			"--...", "---..", "----.", //till 9
			".-.-.-", "--..--", "..--..", ".----.", "-.-.--", "-..-.", "-.--.", //from . (period) to ')'
			"-.--.-", ".-...", "---...", "-.-.-.", "-...-", ".-.-.", "-....-", "..--.-",  //contd. from '(' till '_'
			".-..-.", "...-..-", ".--.-."  //from " till @
		};
		//ensuring that both arrays are of similar length so as to avoid confusions and exceptions beforehand
		int len = characters.length == morseCharacters.length ? characters.length : 0;
		
		if (toOrFrom.equalsIgnoreCase("TO")) {               //filling lists for conversion FROM English TO MORSE
			for (int i = 0; i < len; i++) {
				morseCodeMapTo.put(characters[i], morseCharacters[i]);
			}
		} else if (toOrFrom.equalsIgnoreCase("FROM")) {      //filling lists for conversion FROM morse TO English
			for (int i = 0; i < len; i++) {
				morseCodeMapFrom.put(morseCharacters[i], characters[i]);
			}
		}
	}
}
