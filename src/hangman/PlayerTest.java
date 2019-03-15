package hangman;

import static org.junit.Assert.*;
import org.junit.Test;

public class PlayerTest
{
	@Test
	public void testPlayerDefaults()
	{
		Player plr = new Player();
		assertTrue("Assure that name has been set", plr.getName() != null);
		assertEquals("Assure that wins starts at 0", plr.getWins(), 0);
		assertEquals("Assure that losses starts at 0", plr.getLosses(), 0);
		assertEquals("Assure that gamesPlayed is correctly calculated", plr.getGamesPlayed(), 0);
	}
	
	@Test
	public void testPlayer()
	{
		String name = "Tester";
		Player plr = new Player(name);
		assertEquals("Assure that player name is correctly set", plr.getName(), name);
		
		plr.addWin();
		assertEquals("Assure that player's wins properly increment", plr.getWins(), 1);
		int moreWins = 6;
		for (int i = 0; i < moreWins; i++)
			plr.addWin();
		assertEquals("Assure that player's wins properly increment multiple times", plr.getWins(), moreWins + 1);
		
		assertEquals("Test gamesPlayed with only wins", plr.getGamesPlayed(), moreWins + 1);
		
		plr.addLoss();
		assertEquals("Assure that player's losses properly increment", plr.getLosses(), 1);
		int moreLosses = 7;
		
		for (int i = 0; i < moreLosses; i++)
			plr.addLoss();
		assertEquals("Assure that player's losses properly increment multiple times", plr.getLosses(), moreLosses + 1);
		
		assertEquals("Test gamesPlayed with wins and losses", plr.getGamesPlayed(), moreWins + 1 + moreLosses + 1);		
	}
	
	@Test
	public void testLosses()
	{
		//Test for only losses
		Player plr = new Player();
		
		plr.addLoss();
		assertEquals("Assure that player's losses properly increment", plr.getLosses(), 1);
		int moreLosses = 4;
		
		for (int i = 0; i < moreLosses; i++)
			plr.addLoss();
		assertEquals("Assure that player's losses properly increment multiple times", plr.getLosses(), moreLosses + 1);
		
		assertEquals("Test gamesPlayed with only losses", plr.getGamesPlayed(), moreLosses + 1);
	}
}
