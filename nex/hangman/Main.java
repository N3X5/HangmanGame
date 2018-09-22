package nex.hangman;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import nex.hangman.Exceptions.WrongCategoryExpection;

public class Main {

	//Universal scanner used by all classes
	public static Scanner in = new Scanner(System.in);
	private static short score = 0;
	
	public static void main(String[] args) {
		
		//Get working path of .jar file where the dictionary file is stored
		Path path = null;
		
		try {
			path = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		
		Dictionary dic = new Dictionary(path);
		
		Game game = setupGame(dic);
		
		while(!game.isLost())
		{	
			game.play();
			
			if(game.isWon()) 
			{
				System.out.println("Congratulations you have revealed the word/phrase:");
				System.out.println(game.getWord()+"\n");
				
				score++;
				System.out.println("Current score: " + score);
				
				game = setupGame(dic);
			}
		}
		
		System.out.println("\nYou have lost!");
		
		in.close();
	}
	
	//Initial setup; Pick category and make sure its a valid one; Returns new game instance
	private static Game setupGame(Dictionary dic) 
	{
		String word = "";
		
		while(true)
		{
			System.out.println("Please choose a category:");
		
			for(String s: dic.getCategories())
				System.out.println(s);

			try {
				word = dic.getRandomWordFromCategory(in.nextLine());
			
				break;
			}
			catch (WrongCategoryExpection e)
			{
				e.printError();
			}
		}
	
		return new Game(word);
	}
}
