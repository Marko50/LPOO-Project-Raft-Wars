package dkeep.test;

import org.junit.Assert.*;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.GameMap;
import dkeep.logic.GameState;

public class TestDungeonGameLogic {
	
	char[][] mapa = {
			{'X','X','X','X','X'},
			{'X',' ',' ',' ','X'},
			{'I',' ',' ',' ','X'},
			{'I',' ',' ',' ','I'},
            {'X','X','I','X','X'}
			};
	
	char[] l0 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
	char[] l1 = {'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ' ,' ', 'X' };
	char[] l2 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
	char[] l3 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ' ,' ', 'X' };
	char[] l4 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
	char[] l5 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l6 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l7 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X' ,' ', 'X' };
	char[] l8 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' };
	char[] l9 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
	
	char[] l0a = {'X', 'S', 'I', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
	char[] l1a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'S' };
	char[] l2a = {'I', ' ', ' ', 'S', ' ', ' ', ' ', ' ' ,' ', 'I' };
	char[] l3a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l4a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l5a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l6a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l7a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l8a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' };
	char[] l9a = {'X', 'S', 'I', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
	
	public char[][] mapa1 = {l0,l1,l2,l3,l4,l5,l6,l7,l8,l9};	
	public char[][] mapa2 = {l0a,l1a,l2a,l3a,l4a,l5a,l6a,l7a,l8a,l9a};
	
	
	
	@Test
	public void testSetGameMap()
	{
		int[] hx = {1,1}, gx = {3,1}, lx = {1,3}, kx = {1,3}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		GameMap m = new GameMap(mapa);
		game.setMapa(m);
		assertEquals(m, game.getMapa());
		m.setPos(1, 1, 'S');
		assertEquals('S', m.getMap()[1][1]);
	}

	@Test public void TestSuspicious()
	{
		int[] hx = {1,1}, gx = {8,1}, lx = {1,3}, kx = {2,1}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,3,lx,kx,og);
		int contador  = 0;
		boolean random = false;
		while(contador < game.getGuard().getMovs().length)
		{
			game.getGuard().gMove(contador);
			if(game.getGuard().getDirection() != 1)
			{
				random = true;
				break;
			}
			
			contador++;
		}
		assertTrue(random);
	}
	
	
	@Test public void TestDrunkenGuard()
	{
		int[] hx = {1,1}, gx = {8,1}, lx = {1,3}, kx = {2,1}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,1,lx,kx,og);
		int contador  = 0;
		boolean random = false;
		while(contador < game.getGuard().getMovs().length)
		{
			game.getGuard().gMove(contador);
			if(game.getGuard().getSleep() > 0)
			{
				random = true;
				break;
			}
			
			contador++;
		}
		assertTrue(random);
	}
	
	@Test
	public void TestMoveHeroIntoFreeCell() throws CloneNotSupportedException
	{
		
		int[] hx = {1,1}, gx = {8,1}, lx = {1,3}, kx = {2,1}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa);
		game.moveHero('s');
		assertEquals(2, game.getHero().getPos()[1]);
		assertEquals(1, game.getHero().getPos()[0]);
		game.moveHero('w');
		game.moveHero('d');
		game.moveHero('a');
		assertEquals(1, game.getHero().getPos()[1]);
		assertEquals(1, game.getHero().getPos()[0]);

	}
	@Test
	public void HeroMoveFailed() throws CloneNotSupportedException {
		int[] hx = { 1, 1 }, gx = { 8, 1 }, lx = { 1, 3 }, kx = { 2, 1 }, og = { 3, 1, 1 };
		GameState game = new GameState(hx, gx, 2, lx, kx, og);
		game.getMapa().setMap(mapa);
		game.moveHero('a');
		assertEquals(1, game.getHero().getPos()[1]);
		assertEquals(1, game.getHero().getPos()[0]);
		game.moveHero('w');
		assertEquals(1, game.getHero().getPos()[1]);
		assertEquals(1, game.getHero().getPos()[0]);
		game.moveHero('s');
		assertEquals(2, game.getHero().getPos()[1]);
		assertEquals(1, game.getHero().getPos()[0]);
		game.moveHero('a');
		assertEquals(2, game.getHero().getPos()[1]);
		assertEquals(1, game.getHero().getPos()[0]);
		game.moveHero('w');
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('d');
		assertEquals(1, game.getHero().getPos()[1]);
		assertEquals(3, game.getHero().getPos()[0]);
	}
	
	@Test
	public void passDoor() throws CloneNotSupportedException
	{
		int[] hx = {2,2}, gx = {3,1}, lx = {1,3}, kx = {2,1}, og = {3,1,1};
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa2);
		game.moveHero('d');
		assertEquals(4,game.getHero().getPos()[0]);
		game.moveHero('a');
		assertEquals(2,game.getHero().getPos()[0]);
		game.moveHero('w');
		game.moveHero('d');
		game.moveHero('s');
		assertEquals(3,game.getHero().getPos()[1]);
		game.moveHero('w');
		assertEquals(1,game.getHero().getPos()[1]);
	}
	
	@Test
	public void TestHeroIsCapturedByGuard() throws CloneNotSupportedException
	{
		int[] hx = {1,1}, gx = {3,1}, lx = {1,3}, kx = {2,1}, og = {3,1,1};
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa);
		game.moveHero('d');
		game.heroNearGuard();
		assertTrue(game.getDefeat());
	}

	@Test
	public void TestHeroIsCapturedByOgre() throws CloneNotSupportedException
	{
		int[] hx = {1,1}, gx = {3,1}, lx = {1,3}, kx = {2,1}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa);
		game.moveHero('d');
		game.heroNearOgre();
		assertTrue(game.getDefeat());
	 
	}	
	@Test
	public void PickUpKey() throws CloneNotSupportedException
	{
		int[] hx = {1,1}, gx = {3,1}, lx = {1,3}, kx = {1,3}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa);
		game.getMapa().mapSetGameMode(null, game.getHero(), null, null, game.getK());
		game.moveHero('s');
		game.moveHero('s');
		assertEquals(game.getK().getImage(), game.getHero().getImage());
		assertTrue(game.getK().isPickedUp());
	}
	
	@Test
	public void FailOpenDoor() throws CloneNotSupportedException
	{
		int[] hx = {1,1}, gx = {3,1}, lx = {1,3}, kx = {1,3}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa);
		game.moveHero('s');
		game.moveHero('a');
		assertFalse(game.getK().getUsed());	 
	}

	@Test
	public void SuccessOpenDoor() throws CloneNotSupportedException
	{
		int[] hx = {1,1}, gx = {3,1}, lx = {1,3}, kx = {1,3}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa);
		game.getMapa().mapSetGameMode(null, game.getHero(), null, null, game.getK());
		game.moveHero('s');
		game.moveHero('s');
		game.moveHero('a');
		assertTrue(game.getK().getUsed());
		game.moveHero('d');
		game.moveHero('s');
		game.moveHero('s');
		assertTrue(game.getK().getUsed());
	}
	
	@Test 
	public void SuccessPressLever() throws CloneNotSupportedException
	{

		int[] hx = {1,1}, gx = {3,1}, lx = {2,1}, kx = {1,3}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa2);
		game.getMapa().mapSetGameMode(game.getLever(), game.getHero(), null, null, null);
		game.moveHero('s');
		game.moveHero('d');
		game.moveHero('w');
		assertTrue(game.getLever().getPressed());
		game.moveHero('a');
		game.moveHero('w');
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('w');
		assertTrue(game.getLever().getPressed());
	}	
	@Test 
	public void SuccessPickKey() throws CloneNotSupportedException{

		int[] hx = {1,1}, gx = {3,1}, lx = {2,1}, kx = {2,1}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa2);
		game.getMapa().mapSetGameMode(null, game.getHero(), null, null, game.getK());
		game.moveHero('d');
		assertTrue(game.getK().isPickedUp());
		game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa2);
		game.getMapa().mapSetGameMode(null, game.getHero(), null, null, game.getK());
		game.moveHero('s');
		game.moveHero('d');
		game.moveHero('w');
		assertTrue(game.getK().isPickedUp());
		game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa2);
		game.getMapa().mapSetGameMode(null, game.getHero(), null, null, game.getK());
		game.moveHero('s');
		game.moveHero('d');
		game.moveHero('d');	
		game.moveHero('w');
		game.moveHero('a');	
		game.moveHero('a');
		assertTrue(game.getK().isPickedUp());
	}		
	@Test
	public void Victory() throws CloneNotSupportedException
	{
		int[] hx = {1,1}, gx = {3,1}, lx = {1,3}, kx = {1,3}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa);
		game.getMapa().mapSetGameMode(null, game.getHero(), null, null, game.getK());
		game.moveHero('s');
		game.moveHero('s');
		game.moveHero('a');
		game.moveHero('a');
		assertTrue(game.getVictory());	 
		game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa2);
		game.moveHero('w');
		assertTrue(game.getVictory());
		game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa2);
		game.moveHero('s');
		game.moveHero('s');
		game.moveHero('s');
		game.moveHero('s');
		game.moveHero('s');
		game.moveHero('s');
		game.moveHero('s');
		game.moveHero('s');
		assertTrue(game.getVictory());
		game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa2);
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('d');
		game.moveHero('d');
		assertTrue(game.getVictory());
	}
	
	@Test(timeout = 1000)
	public void OgreRandomMovement()
	{
		int c = 1;
		boolean random = false;
		int[] hx = {1,1}, gx = {3,1}, lx = {1,3}, kx = {1,3}, og = {3,1,2};;
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa);
		game.getMapa().mapSetGameMode(null, game.getHero(), null, game.getOgres(), game.getK());
	    int[] temp = game.getOgres().get(0).getPos();
	    while(true)
	    {
	    	if(c == 50)
	    	{
	    		break;
	    	}
	    	game.moveOgres();	
	    	for(int i = 1; i < game.getOgres().size(); i++)
	    	{
	            if(temp != game.getOgres().get(i).getPos())
	            {
	            	temp = game.getOgres().get(i).getPos();
	            	random = true;
	            }
	            else
	            {
	            	temp = game.getOgres().get(i).getPos();
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
		int[] hx = {1,1}, gx = {3,1}, lx = {1,3}, kx = {1,3}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,1,lx,kx,og);
		game.getMapa().setMap(mapa);
		game.getMapa().mapSetGameMode(null, game.getHero(), null, game.getOgres(), null);
		game.moveHero('d');
		assertTrue(game.stunOgre(0));
	}
	
	
	@Test
	public void HeroKilledByOgre() throws CloneNotSupportedException
	{
		int[] hx = {1,1}, gx = {3,1}, lx = {1,3}, kx = {1,3}, og = {3,1,1};;
		GameState game = new GameState(hx,gx,2,lx,kx,og);
		game.getMapa().setMap(mapa);
		game.moveHero('d');
		game.moveHero('d');
		game.heroNearOgre();
		assertTrue(game.getDefeat());		
	}
	
	 @Test public void OgreOnKey()
	{
		int[] hx = { 1, 1 }, gx = { 3, 1 }, lx = { 1,3 }, kx = { 1, 3 }, og = { 1, 3, 1 };
		GameState game = new GameState(hx, gx, 2, lx, kx, og);
		game.getMapa().setMap(mapa);
		game.ogreOnKey();
		assertEquals('*', game.getOgres().get(0).getImage());
	}
	 
	 @Test public void testGameMode1Win()
	 {
		int[] hx = {1,1}, gx = {8,1}, lx = {7,8}, kx = {2,1}, og = {3,1,3};
		GameState game = new GameState(hx, gx, 2, lx, kx, og);
		game.updateGameMode1('d');
		assertEquals(1, game.getGuard().getPos()[1]);
		assertEquals(7, game.getGuard().getPos()[0]);	
		game.updateGameMode1('w');
		assertEquals(1, game.getGuard().getPos()[1]);
		assertEquals(7, game.getGuard().getPos()[0]);
		game.updateGameMode1('d');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('w');
		game.updateGameMode1('w');
		game.updateGameMode1('w');
		game.updateGameMode1('w');
		game.updateGameMode1('w');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('d');
		game.updateGameMode1('d');
		game.updateGameMode1('d');
		game.updateGameMode1('d');
		game.updateGameMode1('d');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('a');
		game.updateGameMode1('w');
		game.updateGameMode1('w');
		game.updateGameMode1('a');
		game.updateGameMode1('a');
		game.updateGameMode1('a');
		game.updateGameMode1('a');
		game.updateGameMode1('a');
		game.updateGameMode1('a');
		game.updateGameMode1('a'); 
		assertEquals(5, game.getGuard().getPos()[1]);
		assertEquals(3, game.getGuard().getPos()[0]);
		game.updateGameMode1('a'); 
		assertTrue(game.isVictoryGuard());
	 }
	 @Test public void testGameMode1Defeat()
	 {
		int[] hx = {1,1}, gx = {8,1}, lx = {7,8}, kx = {2,1}, og = {3,1,3};
		GameState game = new GameState(hx, gx, 2, lx, kx, og);
		game.updateGameMode1('d');
		game.updateGameMode1('d');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('s');
		game.updateGameMode1('d');
		assertTrue(game.getDefeat());		 
	 }
	 
	 @Test public void testGameMode2Win()
	 {
		int[] hx = {1,1}, gx = {8,1}, lx = {7,8}, kx = {2,1}, og = {8,8,1};
		GameState game = new GameState(hx, gx, 2, lx, kx, og);
		game.getMapa().setMap(game.getMapa().mapOgre);
		game.getMapa().mapSetGameMode(null, game.getHero(), null, game.getOgres(), game.getK());
		game.updateGameMode2('d');
		assertTrue(game.getK().isPickedUp());
		game.updateGameMode2('s');
		game.updateGameMode2('a');
		game.updateGameMode2('a');
		assertTrue(game.isVictoryOgre());
	 }
	 
	 @Test public void testGameMode2Defeat()
	{
		int[] hx = { 1, 1 }, gx = { 8, 1 }, lx = { 7, 8 }, kx = { 6, 1 }, og = { 3, 1, 3 };
		GameState game = new GameState(hx, gx, 2, lx, kx, og);
		game.getMapa().setMap(game.getMapa().mapOgre);
		game.getMapa().mapSetGameMode(null, game.getHero(), null, game.getOgres(), game.getK());
		boolean boolDef = false;
		int c = 0;
		while(c < 50)
		{
			game.updateGameMode2('d');
			if(game.getDefeat())
			{
				boolDef = true;
				break;
			}
			else
				c++;
		}
		assertTrue(boolDef);
	 }
	 @Test
	 public void testGameFreeWin()
	 {
		int[] hx = { 1, 1 }, gx = { 8, 1 }, lx = { 2, 1 }, kx = { 3, 1 }, og = { 8, 8, 1 };
		GameState game = new GameState(hx, gx, 2, lx, kx, og);
		game.getMapa().setMap(game.getMapa().mapOgre);	
		game.getMapa().mapSetGameMode(game.getLever(), game.getHero(),null, game.getOgres(),game.getK());
		game.updateGameMode('d');
		game.updateGameMode('s');
		game.updateGameMode('d');
		game.updateGameMode('d');
		game.updateGameMode('w');
		game.updateGameMode('a');
		game.updateGameMode('a');
		game.updateGameMode('a');
		assertTrue(game.getVictory());
	}

	@Test
	public void testGameFreeDefeat() {
		int[] hx = { 1, 1 }, gx = { 8, 1 }, lx = { 7, 8 }, kx = { 6, 1 }, og = { 3, 1, 3 };
		GameState game = new GameState(hx, gx, 2, lx, kx, og);
		game.getMapa().setMap(game.getMapa().mapOgre);
		game.getMapa().mapSetGameMode(game.getLever(), game.getHero(), null, game.getOgres(), game.getK());
		boolean boolDef = false;
		int c = 0;
		while (c < 50) {
			game.updateGameMode2('d');
			if (game.getDefeat()) {
				boolDef = true;
				break;
			} else
				c++;
		}
		assertTrue(boolDef);
	}

}