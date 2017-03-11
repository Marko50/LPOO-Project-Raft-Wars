package dkeep.test;

import org.junit.Assert.*;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.CellPosition;

public class TestDungeonGameLogic {
	char[][] mapa = {
			{'X','X','X','X','X'},
			{'X',' ',' ',' ','X'},
			{'I',' ',' ',' ','X'},
			{'I','k',' ',' ','X'},
            {'X','X','X','X','X'}
			};
	

	@Test
	public void TestMoveHeroIntoFreeCell() throws CloneNotSupportedException
	{
		GameState game = new GameState(1,1,1,3,1, 2, mapa,1,3);
		game.moveHero('s');
		assertEquals(2, game.getHero().getPos()[1]);
		assertEquals(1, game.getHero().getPos()[0]);	
	}
	
	@Test
	public void TestHeroIsCapturedByGuard() throws CloneNotSupportedException
	{
		GameState game = new GameState(1,1,1,3,1, 2,mapa,1,3);
		game.moveHero('d');
		game.heroNearGuard();
		assertTrue(game.getDefeat());
	}
	
	@Test
	public void TestHeroIsCapturedByOgre() throws CloneNotSupportedException
	{
		GameState game = new GameState(2,1,1,1,mapa,1,3);
		game.moveHero('d');
		game.heroNearOgreSimple();
		assertTrue(game.getDefeat());
	 
	}
	
	@Test
	public void PickUpKey() throws CloneNotSupportedException
	{
		GameState game2 = new GameState(2,1,1,1, mapa,1,3);
		game2.moveHero('s');
		game2.moveHero('s');
		assertEquals(game2.getK().getImage(), game2.getHero().getImage());
		assertTrue(game2.getK().isPickedUp());
	}
	
	@Test
	public void FailOpenDoor() throws CloneNotSupportedException
	{
		GameState game = new GameState(2,1,1,1,mapa,1,3);
		game.moveHero('s');
		game.moveHero('a');
		assertFalse(game.getK().getUsed());	 
	}

	@Test
	public void SuccessOpenDoor() throws CloneNotSupportedException
	{
		GameState game = new GameState(2,1,1,1,mapa,1,3);
		game.moveHero('s');
		game.moveHero('s');
		game.moveHero('a');
		assertTrue(game.getK().getUsed());	 
	}
	
	
	@Test
	public void Victory() throws CloneNotSupportedException
	{
		GameState game = new GameState(2,1,1,1,mapa,1,3);
		game.moveHero('s');
		game.moveHero('s');
		game.moveHero('a');
		game.moveHero('a');
		assertTrue(game.getVictory());	 
	}
	
	
	@Test(timeout = 1000)
	public void OgreRandomMovement()
	{
		int c = 1;
		boolean random = false;
	    GameState game = new GameState(2,1,1,3,mapa,1,3);
	    int[] temp = game.getOgres()[0].getPos();
	    while(true)
	    {
	    	if(c == 50)
	    	{
	    		break;
	    	}
	    	game.moveOgres();	
	    	for(int i = 1; i < game.getOgres().length; i++)
	    	{
	            if(temp != game.getOgres()[i].getPos())
	            {
	            	temp = game.getOgres()[i].getPos();
	            	random = true;
	            }
	            else
	            {
	            	temp = game.getOgres()[i].getPos();
	            	random = false;
	            }
	            	
	    	}
	    	
	    	if(random)
	    	{
	    		break;
	    	}
	    	c++;
	    }
	    
	    assertTrue(random);
	}
	
	@Test
	public void OgreStunned() throws CloneNotSupportedException
	{
		GameState game = new GameState(2,1,1,1,mapa,1,3);
		game.moveHero('d');
		assertTrue(game.stunOgre(0));
	}
	
	
	@Test
	public void HeroKilledByOgre() throws CloneNotSupportedException
	{
		GameState game = new GameState(2,1,1,3,mapa,1,3);
		game.moveHero('d');
		game.moveHero('d');
		game.heroNearOgre();
		assertTrue(game.getDefeat());		
	}
	
	 @Test public void OgreOnKey()
	 {
		 
	 }
	
	
		
}
