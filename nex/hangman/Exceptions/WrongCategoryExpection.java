package nex.hangman.Exceptions;

@SuppressWarnings("serial")
public class WrongCategoryExpection extends Throwable {
	
	private String wrong_cat;
	
	public WrongCategoryExpection(String wrong_cat) 
	{
		this.wrong_cat = wrong_cat;
	}
	
	public void printError() 
	{
		System.out.println(wrong_cat + " is not a valid category!");
	}
}
