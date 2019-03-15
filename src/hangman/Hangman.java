package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

import linked_data_structures.*;
import util.custom.*;

public class Hangman implements Serializable
{
	private SinglyLinkedList<Character> word;
	private SinglyLinkedList<Character> guessed;
	private Player plr;
	
	private static Scoreboard scoreboard;
	public static final int MAX_GUESSES = 6;
	private static SinglyLinkedList<SinglyLinkedList<Character>> dictionary;

	public static final String SAVEFILE = "./data/hangman.dat";
	public static final String DICT_SAVEFILE = "./data/dictionary.dat";
	public static final String SCRBRD_SAVEFILE = "./data/scoreboard.dat";
	
	public Hangman()
	{
		this.word = new SinglyLinkedList<Character>();
		this.guessed = new SinglyLinkedList<Character>();
		
		new File("./data").mkdirs(); //Make sure data directory exists
		
		try
		{
			Hangman.dictionary = SaveLoad.load(DICT_SAVEFILE, Hangman.dictionary);
		}
		catch(FileNotFoundException e)
		{
			Hangman.resetDictionary("./dictionary.txt"); //If first time playing, load a dictionary.
		}
		catch (IOException e)
		{
			System.out.println("IOException, could not read saved dictionary. Resetting dictionary...");
			Hangman.resetDictionary();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Could not find DoublyLinkedList<Character> class.");
			e.printStackTrace();
			System.exit(-1);
		}
		
		try
		{
			Hangman.scoreboard = SaveLoad.load(SCRBRD_SAVEFILE, Hangman.scoreboard);
		}
		catch (FileNotFoundException e)
		{
			Hangman.scoreboard = new Scoreboard();
		}
		catch (IOException e)
		{
			System.out.println("IOException, could not read saved dictionary. Resetting dictionary...");
			Hangman.scoreboard = new Scoreboard();
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Could not find Scoreboard class.");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private void deleteSavedGame()
	{
		File savedGame = new File(Hangman.SAVEFILE);
		if (savedGame.exists())
			if (!savedGame.delete())
				System.out.println("Delete unsuccessful");
	}
	
	public void newGame(String playerName)
	{
		this.plr = Hangman.scoreboard.findPlayer(playerName);
		this.guessed = new SinglyLinkedList<Character>();
		this.word = Hangman.randomDictionaryWord();
		Hangman.saveDictionary();
		deleteSavedGame();
		
	}
	public void newPlayer(String playerName) throws ExistingEntryException
	{
		if (Hangman.scoreboard.findPlayer(playerName) == null) //If player name is not already taken
		{
			this.plr = new Player(playerName);
			this.guessed = new SinglyLinkedList<Character>();
			Hangman.scoreboard.add(this.plr);
			Hangman.scoreboard.save(SCRBRD_SAVEFILE);
			this.word = Hangman.randomDictionaryWord();
			Hangman.saveDictionary();
			deleteSavedGame();
		}
		else
			throw new ExistingEntryException("Name is already taken, please choose another");
	}
	
	public SinglyLinkedList<Character> getWord()
	{
		return this.word;
	}
	public void setWord(SinglyLinkedList<Character> word)
	{
		this.word = word;
	}
	
	public SinglyLinkedList<Character> getGuessed()
	{
		return this.guessed;
	}
	public void setGuessed(SinglyLinkedList<Character> guessed)
	{
		this.guessed = guessed;
	}
	
	public Player getPlr()
	{
		return this.plr;
	}
	public void setPlr(Player plr)
	{
		this.plr = plr;
	}
	
	public boolean isEnded()
	{
		//Considered ended when the maximum number of guesses have been reached OR if guessed contains every letter in word
		return (this.getLimbs() >= Hangman.MAX_GUESSES || ListEnhancer.containsAll(guessed, word, true));
	}
	
	public static SinglyLinkedList<SinglyLinkedList<Character>> getDictionary()
	{
		return Hangman.dictionary;
	}
	public static void setDictionary(SinglyLinkedList<SinglyLinkedList<Character>> dict)
	{
		Hangman.dictionary = dict;
	}
	public static SinglyLinkedList<Character> randomDictionaryWord()
	{
		Random rand = new Random();
		SinglyLinkedList<Character> wrd = null;
		if (Hangman.dictionary != null)
			if (Hangman.dictionary.getLength() > 0)
			{
				int randInt = rand.nextInt(Hangman.dictionary.getLength());
				wrd = Hangman.dictionary.getElementAt(randInt);
				Hangman.dictionary.remove(randInt);
			}
			else
				resetDictionary();
		return wrd;
	}
	
	public static void resetDictionary()
	{
		Hangman.resetDictionary("./dictionary.txt");
	}
	public static void resetDictionary(String file)
	{
		Hangman.resetDictionary(false, file);
	}
	public static void resetDictionary(boolean checkIfEmpty)
	{
		Hangman.resetDictionary(checkIfEmpty, "./dictionary.txt");
	}
	public static void resetDictionary(boolean checkIfEmpty, String file)
	{
		if (Hangman.dictionary == null)
			Hangman.dictionary = new SinglyLinkedList<SinglyLinkedList<Character>>();
		if (checkIfEmpty || Hangman.dictionary.getLength() <= 0) //Only resets if checkIfEmpty is false or if the dictionary length is 0
		{
			File dictFile = new File(file);
			Scanner inFile;
			try
			{
				inFile = new Scanner(dictFile);
				inFile.useDelimiter("\r\n");
				
				while (inFile.hasNext())
				{
					Hangman.dictionary.add(ListEnhancer.stringToSList(inFile.next()));
				}
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}
	public static int saveDictionary()
	{
		int saved;
		try
		{
			SaveLoad.save(DICT_SAVEFILE, dictionary);
			saved = 1;
		}
		catch (FileNotFoundException e)
		{
			saved = -1;
		}
		catch (IOException e)
		{
			saved = -2;
		}
		return saved;
	}
	
	public static Scoreboard getScoreboard()
	{
		return Hangman.scoreboard;
	}
	public static void setScoreboard(Scoreboard scrbrd)
	{
		Hangman.scoreboard = scrbrd;
	}

	
	public int guess(Character letter)
	{
		int flag;
		if (ListEnhancer.includes(guessed, letter, true)) //If already guessed
		{
			flag = 0;
		}
		else
		{
			ListEnhancer.addToEnd(guessed, letter);
			if (ListEnhancer.includes(word, letter, true)) //If the guess is correct, check if
			{
				SinglyLinkedList<Character> wordLetters = new SinglyLinkedList<Character>();
				for (int i = 0; i < word.getLength(); i++)
				{
					if (Character.isLetter(word.getElementAt(i)))
						ListEnhancer.addToEnd(wordLetters, word.getElementAt(i));
				}
				if(ListEnhancer.containsAll(guessed, wordLetters, true)) //Check if all letters in word have been guessed
				{
					this.getPlr().addWin();
					this.endGame();
					flag = 2; //End game with a win
				}
				else
				{
					flag = 1;
				}
			}
			else //If the guess is wrong, check if
			{
				if (this.guessed.getLength() - this.guessedInWord().getLength() > Hangman.MAX_GUESSES - 1)
				{
					this.getPlr().addLoss();
					this.endGame();
					flag = -2; //End game is totalGuesses - correctGusses is more than 6 (Loss)
				}
				else
				{
					flag = -1; //Just indicates that guess was incorrect and game continues
				}
			}
		}
		return flag;
	}
	
	/**
	 * @return SinglyLinkedList of all characters that are guessed and in word.
	 */
	private SinglyLinkedList<Character> guessedInWord()
	{
		SinglyLinkedList<Character> goodGuesses = new SinglyLinkedList<Character>();
		for (int i = 0; i < word.getLength(); i++)
			if (ListEnhancer.includes(guessed, word.getElementAt(i), true) && !ListEnhancer.includes(goodGuesses, word.getElementAt(i), true))
				ListEnhancer.addToEnd(goodGuesses, word.getElementAt(i));
		return goodGuesses;
	}
	
	private SinglyLinkedList<Character> lettersLeftInWord()
	{
		SinglyLinkedList<Character> lettersLeft = new SinglyLinkedList<Character>();
		for (int i = 0; i < word.getLength(); i++)
			if (!ListEnhancer.includes(lettersLeft, word.getElementAt(i), true) &&
				!ListEnhancer.includes(guessed, word.getElementAt(i), true) && Character.isLetter(word.getElementAt(i)))
				lettersLeft.add(word.getElementAt(i));
		return lettersLeft;
	}
	
	public String displayGuessedInWord()
	{
		StringBuffer goodGuesses = new StringBuffer();
		for (int i = 0; i < word.getLength(); i++)
		{
			if (ListEnhancer.includes(guessed, word.getElementAt(i), true) || !Character.isLetter(word.getElementAt(i)))
				goodGuesses.append(word.getElementAt(i));
			else
				goodGuesses.append('_');
			if (i != word.getLength() - 1)
				goodGuesses.append(' ');
		}
		return goodGuesses.toString();
	}
	
	/**
	 * 
	 * @return number of bad guesses
	 */
	public int getLimbs()
	{
		return this.guessed.getLength() - this.guessedInWord().getLength();
	}
	
	public boolean hint()
	{
		if (this.lettersLeftInWord().getLength() > 1) //No hints if there's only 1 letter left to guess
		{
			Random rand = new Random();
			Character goodLetter;
			do
				goodLetter = Character.toUpperCase(word.getElementAt(rand.nextInt(word.getLength())));
			while (ListEnhancer.includes(guessed, goodLetter, true) || !Character.isLetter(goodLetter));
			
			guess(goodLetter);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void endGame()
	{
		scoreboard.save(SCRBRD_SAVEFILE);
		deleteSavedGame();
	}
	
	public int save()
	{
		int saved;
		try
		{
			SaveLoad.save(SAVEFILE, this);
			saved = 1;
		}
		catch (FileNotFoundException e)
		{
			saved = -1;
		}
		catch (IOException e)
		{
			saved = -2;
		}
		return saved;
	}
	
	public int load()
	{
		return this.load(true);
	}
	
	/**
	 * 
	 * @param refreshSB: Attempt to refresh the scoreboard, in case it was deleted but the old game was not
	 * @return status int
	 */
	public int load(boolean refreshSB)
	{
		int loaded;
		try
		{
			this.fill(SaveLoad.load(SAVEFILE, this));
			if (refreshSB)
			{
				if (Hangman.scoreboard.findPlayer(this.getPlr().getName()) == null)
				{
					Hangman.scoreboard.add(this.getPlr());
					Hangman.scoreboard.save(Hangman.SCRBRD_SAVEFILE);
				}
			}
			loaded = 1;
		}
		catch(FileNotFoundException e)
		{
			loaded = -1;
		}
		catch(IOException e)
		{
			loaded = -2;
		}
		catch (ClassNotFoundException e)
		{
			loaded = -3;
		}
		return loaded;
	}
	
	private void fill(Hangman obj)
	{
		this.plr = obj.getPlr();
		this.word = obj.getWord();
		this.guessed = obj.getGuessed();
	}
}
