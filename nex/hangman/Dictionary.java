package nex.hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

import nex.hangman.Exceptions.WrongCategoryExpection;

public class Dictionary {
	
	private TreeMap<String, ArrayList<String>> map = new TreeMap<String, ArrayList<String>>();
	private ArrayList<String> original_case_categories = new ArrayList<String>();
	
	/*
	 * We assume that the dictionary file in not corrupted and contains proper data
	 * Parse all categories and map the specific words to them in the TreeMap
	 * 
	 * original_case_categories are only for external to the class access
	 * */
	public Dictionary(Path loc) 
	{
		File f = null;
		
		try {
			f = new File(loc.toString() + File.separator + "dictionary.hang");
		}
		catch (Exception e) //Kill program if dictionary isn't found
		{ 
			System.out.println("Error! Dictionary file not found. Terminating program.");
			
			System.exit(0);
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		    
			String line, category = null;
			ArrayList<String> words = new ArrayList<String>();
		    
		    while ((line = br.readLine()) != null) {
		       
		    	if(line.charAt(0) == '_')
		    	{
		    		if(category != null)
		    		{
		    			map.put(category.toLowerCase().substring(1), words);
		    			
		    			words = new ArrayList<String>();
		    		}
		    		category = line;
		    		
		    		original_case_categories.add(category.substring(1));
		    	}
		    	else
		    		words.add(line);
		    }
		    
		    map.put(category.toLowerCase().substring(1), words);
		} catch (IOException io) {}
	}
	
	//Lower case categories are only for internal use
	public ArrayList<String> getCategories () 
	{
		return original_case_categories;
	}
	
	//Return random word from category
	public String getRandomWordFromCategory(String input) throws WrongCategoryExpection
	{
		String lowc_input = input.toLowerCase();
		
		if(!map.containsKey(lowc_input))
			throw new WrongCategoryExpection(input);
		else
		{
			ArrayList<String> category_words = map.get(lowc_input);
			
			Random r = new Random();
			
			return category_words.get(r.nextInt(category_words.size()));
		}
	}
	
}
