package hangman;

import java.io.Serializable;

public class Player implements Serializable
{
	private String name;
	private int wins;
	private int losses;
	
	private static int maxNameLength = 16; //Default 16
	
	public Player()
	{
		this("Unknown", 0, 0);
	}
	public Player(String name)
	{
		this(name, 0, 0);
	}
	public Player(String name, int w, int l)
	{
		if (name.length() > Player.maxNameLength)
			name = name.substring(0, Player.maxNameLength); //Quick way of ensuring name is not too long for formatting
		this.name = name;
		this.wins = w;
		this.losses = l;
	}
	
	public static int getMaxNameLength()
	{
		return Player.maxNameLength;
	}
	public static void setMaxNameLength(int len)
	{
		Player.maxNameLength = len;
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getWins()
	{
		return this.wins;
	}
	public int getLosses()
	{
		return this.losses;
	}
	
	public int getTotal()
	{
		return this.wins - this.losses;
	}
	public double getWinLossRatio()
	{
		return this.wins / (double) this.losses;
	}
	public int getGamesPlayed()
	{
		return this.wins + this.losses;
	}
	public void addWin()
	{
		this.wins++;
	}
	public void addLoss()
	{
		this.losses++;
	}
	
}
