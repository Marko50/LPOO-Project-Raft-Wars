package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

public class GameState {

	private char[][] map;
	private Hero h;
	private Guard g;
	private Ogre[] o;
	private Lever l;
	private int gameMode;
	private boolean defeat;
	
	public void mapSet()
	{
		if(l.getPressed() && gameMode == 1)
		{
			char[] l0 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			char[] l1 = {'X', ' ', ' ', ' ', 'S', ' ', 'X', ' ' ,' ', 'X' };
			char[] l2 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
			char[] l3 = {'X', ' ', 'S', ' ', 'S', ' ', 'X', ' ' ,' ', 'X' };
			char[] l4 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
			char[] l5 = {'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l6 = {'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l7 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X' ,' ', 'X' };
			char[] l8 = {'X', ' ', 'S', ' ', 'S', ' ', 'X', 'k', ' ', 'X'};
			char[] l9 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			
			map[0] = l0;
			map[1] = l1;
			map[2] = l2;
			map[3] = l3;
			map[4] = l4;
			map[5] = l5;
			map[6] = l6;
			map[7] = l7;
			map[8] = l8;
			map[9] = l9;
			
			map[h.getPos()[1]][h.getPos()[0]] = h.getImage();
			map[g.getPos()[1]][g.getPos()[0]] = g.getImage();
		}
		else if(!l.getPressed() && gameMode == 1)
		{
			char[] l0 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			char[] l1 = {'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ' ,' ', 'X' };
			char[] l2 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
			char[] l3 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ' ,' ', 'X' };
			char[] l4 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
			char[] l5 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l6 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l7 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X' ,' ', 'X' };
			char[] l8 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'};
			char[] l9 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			
			map[0] = l0;
			map[1] = l1;
			map[2] = l2;
			map[3] = l3;
			map[4] = l4;
			map[5] = l5;
			map[6] = l6;
			map[7] = l7;
			map[8] = l8;
			map[9] = l9;
			
			map[h.getPos()[1]][h.getPos()[0]] = h.getImage();
			map[g.getPos()[1]][g.getPos()[0]] = g.getImage();
		}	
		else if(l.getPressed() && gameMode == 2)
		{
			char[] l0 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			char[] l1 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l2 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l3 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l4 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l5 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l6 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l7 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l8 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' };
			char[] l9 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			
			map[0] = l0;
			map[1] = l1;
			map[2] = l2;
			map[3] = l3;
			map[4] = l4;
			map[5] = l5;
			map[6] = l6;
			map[7] = l7;
			map[8] = l8;
			map[9] = l9;
			
			if(l.getDoorOpen())
				map[2][0] = 'S';
			
			map[h.getPos()[1]][h.getPos()[0]] = h.getImage();
			
			for(int i = 0; i < o.length; i++)
			{
				map[o[i].getPos()[1]][o[i].getPos()[0]] = o[i].getImage();
				map[o[i].getClub().getPos()[1]][o[i].getClub().getPos()[0]] = o[i].getClub().getImage();
			}
			
		}
		
		else if(!l.getPressed() && gameMode == 2)
		{
			char[] l0 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			char[] l1 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,'K', 'X' };
			char[] l2 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l3 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l4 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l5 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l6 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l7 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l8 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' };
			char[] l9 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			
			map[0] = l0;
			map[1] = l1;
			map[2] = l2;
			map[3] = l3;
			map[4] = l4;
			map[5] = l5;
			map[6] = l6;
			map[7] = l7;
			map[8] = l8;
			map[9] = l9;

			map[h.getPos()[1]][h.getPos()[0]] = h.getImage();

			for (int i = 0; i < o.length; i++) {
				map[o[i].getPos()[1]][o[i].getPos()[0]] = o[i].getImage();
				map[o[i].getClub().getPos()[1]][o[i].getClub().getPos()[0]] = o[i].getClub().getImage();
			}
		}

	}
	
	public GameState(int gMode, int hx, int hy, int gx, int gy, int dif)
	{
		this.defeat = false;
		char[] movs = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
		this.gameMode = gMode;
        Hero h1 = new Hero(hx, hy, 'H', '*');
        this.h = h1;
        if(dif == 1)
        {
        	Drunken d = new Drunken(gx,gy,'D',movs);
        	this.g = d;
        }
        else if(dif == 2)
        {
        	Rookie d = new Rookie(gx,gy,'D',movs);
        	this.g = d;
        }
        
        else if(dif == 3)
        {
        	Suspicious d = new Suspicious(gx,gy,'D',movs);
        	this.g = d;
        }		
	}
	
	public GameState(int gMode, int hx, int hy, int nOgres)
	{
		this.defeat = false;
		char[] movs = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
		this.gameMode = gMode;
        Hero h1 = new Hero(hx, hy, 'H', '*');
        this.h = h1;
        for(int i = 0; i < nOgres; i++)
        {
        	Ogre o1 = new Ogre(8, 1, 'O', '*');
        	o[i] = o1;
        }
	}
	
	public void showmap() {
		for (int i = 0; i < map.length; i++) {
			for (int c = 0; c < map[i].length; c++) {
				System.out.print(map[i][c]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	
	public static void action(int[] oPos, char[][]map)
	{
		int randomNum;
		while(true)
		{
			randomNum = ThreadLocalRandom.current().nextInt(1,5);
			if(randomNum == 1)//w
			{
				if(map[oPos[1]-1][oPos[0]] == 'X' || map[oPos[1]-1][oPos[0]] == 'I')
				{
					continue;
				}
				else
				{
					oPos[1] = oPos[1]-1;
					break;
				}
					
			}
			else if(randomNum == 2)//d
			{
				if(map[oPos[1]][oPos[0]+1] == 'X' || map[oPos[1]][oPos[0]+1] == 'I')
				{
					continue;
				}
			
				else
				{
					oPos[0] = oPos[0]+1;
					break;
				}
				
			}
			else if(randomNum == 3)//s
			{
				if(map[oPos[1]+1][oPos[0]] == 'X' || map[oPos[1]+1][oPos[0]] == 'I')
				{
					continue;
				}
			
				else
				{
					oPos[1] = oPos[1]+1;
					break;
				}
				
			}
			else if(randomNum == 4)//a
			{
				if(map[oPos[1]][oPos[0]-1] == 'X' || map[oPos[1]][oPos[0]-1] == 'I')
				{
					continue;
				}
				else
				{
					oPos[0] = oPos[0]-1;
					break;
				}
				
			}
		}
		
	}
	
	public void moveOgres()
	{
		for(int i = 0; i < o.length; i++)
		{
			action(o[i].getPos(), this.map);
			o[i].getClub().getPos()[0] = o[i].getPos()[0];
			o[i].getClub().getPos()[1] = o[i].getPos()[1];
			action (o[i].getClub().getPos(), this.map);	
		}
	}

	public void moveHero(char order)
	{
		if(order == 'w')
		{
			if(map[h.getPos()[1]-1][h.getPos()[0]] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				
			}
			else if(map[h.getPos()[1]-1][h.getPos()[0]] == 'I')
			{
				if(gameMode == 2)
					l.setDoorOpen(true);
				
				else
					System.out.println("You shall not pass!");
				
				
			} else if (map[h.getPos()[1] - 1][h.getPos()[0]] == 'S') {
				if (h.getPos()[1] - 2 < 0 && gameMode == 1) {
					//flagVictory = true;
					gameMode = 2;
					l.setPressed(false);
					
				}
				else if (h.getPos()[1] - 2 < 0 && gameMode == 2) {
					//flagVictory = true;
					//gameMode = 2;
					
				}
				else if (map[h.getPos()[1] - 2][h.getPos()[0]] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[1] = h.getPos()[1] - 2;
					
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
					
				}
			}
			
		
			else if(map[h.getPos()[1]-1][h.getPos()[0]] == 'K')
			{
				System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				l.setPressed(true);
				
			}
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				h.getPos()[1] = h.getPos()[1] - 1;
				
			}
			
		}
		else if(order == 'a')
		{
			System.out.println(order);
			if(map[h.getPos()[1]][h.getPos()[0]-1] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				
			}
			else if(map[h.getPos()[1]][h.getPos()[0]-1] == 'I')
			{
				if(gameMode == 2)
					l.setDoorOpen(true);
				
				else
					System.out.println("You shall not pass!");
				
				
			}
			else if(map[h.getPos()[1]][h.getPos()[0]-1] == 'S')
			{
				if (h.getPos()[0] - 2 < 0 && gameMode == 1) {
					//flagVictory = true;
					gameMode = 2;
					l.setPressed(false);
					
				}
				else if (h.getPos()[0] - 2 < 0 && gameMode == 2) {
					//flagVictory = true;
					//gameMode = 2;
					
				}else if (map[h.getPos()[1]][h.getPos()[0]-2] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[0] = h.getPos()[0] - 2;
					
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
					
				}
			}
			
			else if(map[h.getPos()[1]][h.getPos()[0]-1] == 'K')
			{
				System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				l.setPressed(true);
				
			}
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				h.getPos()[0] = h.getPos()[0] - 1;
				
			}
			
		}
		else if(order == 's')
		{
			System.out.println(order);
			if(map[h.getPos()[1]+1][h.getPos()[0]] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				
			}
			else if(map[h.getPos()[1]+1][h.getPos()[0]] == 'I')
			{
				if(gameMode == 2)
					l.setDoorOpen(true); 
				
				else
					System.out.println("You shall not pass!");
				
				
			}
			else if(map[h.getPos()[1]+1][h.getPos()[0]] == 'S')
			{
				if (h.getPos()[1] + 2 > 10 && gameMode == 1) {
					//flagVictory = true;
					gameMode = 2;
					l.setPressed(false);
					
				}
				else if (h.getPos()[1] + 2 > 10 && gameMode == 2) {
					//flagVictory = true;
					//gameMode = 2;
					
				}
				else if (map[h.getPos()[1] + 2][h.getPos()[0]] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[1] = h.getPos()[1] + 2;
					
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
					
				}
			}
			
			else if(map[h.getPos()[1]+1][h.getPos()[0]] == 'K')
			{
				System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				l.setPressed(true);
				
			}
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				h.getPos()[1] = h.getPos()[1] + 1;
				
			}
			
		}
		else if(order == 'd')
		{
			System.out.println(order);
			if(map[h.getPos()[1]][h.getPos()[0]+1] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				
			}
			else if(map[h.getPos()[1]][h.getPos()[0]+1] == 'I')
			{
				if(gameMode == 2)
					l.setDoorOpen(true);
				
				else
					System.out.println("You shall not pass!");
				
				
			}
			else if(map[h.getPos()[1]][h.getPos()[0]+1] == 'S')
			{
				if (h.getPos()[0] + 2 > 10 && gameMode == 1) {
					//flagVictory = true;
					gameMode = 2;
					l.setPressed(false);
					
				} 
				else if (h.getPos()[0] + 2 > 10 && gameMode == 2) {
					//flagVictory = true;
					//gameMode = 2;
					
				} else if (map[h.getPos()[1]][h.getPos()[0]+2] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[0] = h.getPos()[0] + 2;
					
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
				}
			}
			
			else if(map[h.getPos()[1]][h.getPos()[0]+1] == 'K')
			{
				System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				l.setPressed(true);
				
			}
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				h.getPos()[0] = h.getPos()[0] + 1;
				
			}
			
		}
	}
	
	public boolean getDefeat() {
		return defeat;
	}

	public void setDefeat(boolean defeat) {
		this.defeat = defeat;
	}
	
}
