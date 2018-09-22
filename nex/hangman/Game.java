package nex.hangman;

import java.util.HashSet;
import java.util.Set;

public class Game {
	
	private String word , lowc_word;
	private short attempts = 10;
	private Set<Character> characters = new HashSet<Character>();	
	
	//Store word in lower case too for easier internal use
	public Game(String word)
	{
		this.word = word;
		lowc_word = word.toLowerCase();
	}
	
	public String getWord() 
	{
		return word;
	}
	
	public boolean isLost() 
	{
		if (attempts > 0)
			return false;
		else
			return true;
	}
	
	//If all characters of the word excluding ' ' are contained in the characters ArrayList => true
	public boolean isWon() 
	{
		for(char c: lowc_word.toCharArray()) {
			if(!characters.contains(c) && c != ' ')
				return false;
		}
		
		return true;
	}
	
	//Attempt to guess one letter of the word
	public void play() 
	{
		System.out.println("Attempts left: " + attempts);
		System.out.print("Current word/phrase: ");
		
		//Print word progress
		for(char c: word.toCharArray()) {
			if(c !=' ')
			{
				//Make sure that character case does not matter when checking if it has been inputed
				if(!characters.contains(Character.toUpperCase(c)) && !characters.contains(Character.toLowerCase(c)))
					System.out.print("_ ");
				else
					System.out.print(c + " ");
			}
			else
				System.out.print("  ");
		}
		 
		System.out.println(); //Some formatting
		
		String input = "";
		char first_char = 0;
		
		//Make sure the player enters in an unique character
		while(input.length()!=1 || characters.contains(first_char))
		{
			if(input.length() == 1 && characters.contains(first_char))
				System.out.println("You have already used this letter. Please try another one.");
			
			System.out.println("Please enter a letter: ");
			
			input = Main.in.nextLine();
			
			//Store the first character of input separately as it is saves code
			if(input.length()!=0) 
				first_char = Character.toLowerCase(input.toCharArray()[0]);
		} 
		
		characters.add(first_char);
		
		//Word does not contain character
		if(!lowc_word.contains(input.toLowerCase()))
			attempts--;
	}
}
