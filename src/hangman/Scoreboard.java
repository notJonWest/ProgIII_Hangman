package hangman;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import linked_data_structures.*;
import util.custom.SaveLoad;

public class Scoreboard implements Serializable
{
	private DoublyLinkedList<Player> players;
	
	public Scoreboard()
	{
		players = new DoublyLinkedList<Player>();
	}
	
	public DoublyLinkedList<Player> getPlayers()
	{
		return this.players;
	}
	
	public int getSize()
	{
		return this.players.getLength();
	}
	
	public Player findPlayer(String name)
	{
		this.sort();
		Player targetPlr = null;
		int top = this.getSize() - 1;
		int bottom = 0;
		while (top >= bottom && targetPlr == null)
		{
			int mid = (bottom + top)/2;
			Player midPlayer = this.getPlayerAt(mid);
			if (midPlayer.getName().compareToIgnoreCase(name) == 0)
				targetPlr = midPlayer;
			else if (midPlayer.getName().compareToIgnoreCase(name) > 0)
				top = mid - 1;
			else if (midPlayer.getName().compareToIgnoreCase(name) < 0)
				bottom = mid + 1;
		}
		return targetPlr;
	}
	
	public Player getPlayerAt(int index)
	{
			if (index < 0 || index >= this.getSize())
				return null;
			else
				return this.getPlayers().getElementAt(index);
	}
	
	public void sort()
	{
		boolean sorted = false;
		int i = this.getSize();
		while (!sorted && i > 1)
		{
			sorted = true;
			for (int j = 1; j < players.getLength(); j++)
			{
				if (getPlayerAt(j-1).getName().compareToIgnoreCase(getPlayerAt(j).getName()) > 0)
				{
					Player temp = getPlayerAt(j-1);
					players.remove(j-1);
					players.add(temp, j);
					sorted = false;
				}
			}
			i--;
		}
	}
	
	public String displayScoreboard()
	{
		int nameLen = Player.getMaxNameLength();
		int winLen = 7;
		int lossLen = 7;
		int gameLen = 5;
		String formatTemp = "%-" + nameLen + "s%-" + winLen + "s%-" + lossLen + "s%-" + gameLen + "s";
		StringBuffer scrbrdStr = new StringBuffer(String.format(formatTemp + "\r\n", "Player", "Wins", "Losses", "Games"));
		for (int i = 0; i < nameLen+winLen+lossLen+gameLen; i++)
			scrbrdStr.append('-');
		scrbrdStr.append("\r\n");
		for (int i = 0; i < getSize(); i++)
		{
			Player plrI = getPlayerAt(i);
			scrbrdStr.append(String.format(formatTemp, plrI.getName(), plrI.getWins(), plrI.getLosses(), plrI.getGamesPlayed()));
			if (i != getSize() - 1)
				scrbrdStr.append("\r\n");
		}
		return scrbrdStr.toString();
	}
	
	public void add(Player plr)
	{
		this.players.add(plr);
		this.sort();
	}
	
	public int save(String filename)
	{
		int saved;
		try
		{
			SaveLoad.save(filename, this);
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
	
	public int load(String filename)
	{
		int loaded;
		try
		{
			SaveLoad.load(filename, this);
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
}
