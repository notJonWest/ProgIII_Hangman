package hangman;

import static org.junit.Assert.*;
import org.junit.Test;

public class ScoreboardTest
{
	/*Notes:
	 ** I intentionally left it possible for players with the same name to be added because
	    that functionality should be decided by the user of the class.
	 **
	 */
	
	@Test
	public void testDefault()
	{
		Scoreboard scr = new Scoreboard();
		assertEquals("Make sure the scoreboard begins with 0 players", scr.getSize(), 0);
		
		Player plr1 = new Player("Test1");
		scr.add(plr1);
		assertEquals("Make sure retrieving players is possible with 1 player", scr.findPlayer("Test1"), plr1);
		
		Player plr2 = new Player("Test2");
		scr.add(plr2);
		
		assertEquals("Make sure retrieving players is possible with 2 players", scr.findPlayer("Test2"), plr2);
		
		assertEquals("Player not found is null", scr.findPlayer("NotFound"), null);
		
		assertEquals("Make sure scoreboard getSize returns the actual size", scr.getSize(), scr.getPlayers().getLength());
	}
	
	@Test
	public void testFind()
	{
		Scoreboard scr = new Scoreboard();
		
		assertEquals("Searching an empty list just returns null", scr.getPlayerAt(0), null);
		assertEquals("Searching an empty list just returns null", scr.findPlayer("empty"), null);
		
		Player plr1 = new Player("T1");
		scr.add(plr1);
		assertEquals("Searching a list of 1 will return correctly", scr.getPlayerAt(0), plr1);
		
		Player plr2 = new Player("ZZZZZ");
		scr.add(plr2);
		
		Player plr3 = new Player("T3");
		scr.add(plr3);
		
		scr.sort();
		assertEquals("Make sure sort sorts alphabetically", scr.getPlayerAt(scr.getSize() - 1), plr2);
		
		assertEquals("Make sure sort sorts numerically", scr.getPlayerAt(1), plr3);
		assertEquals("Make sure sort sorts numerically", scr.getPlayerAt(0), plr1);
	}
}
