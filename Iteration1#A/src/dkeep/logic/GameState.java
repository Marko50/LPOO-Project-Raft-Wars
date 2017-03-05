package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

public class GameState {

	private char[][] map = new char[10][10];
	private Hero h;
	private Guard g;
	private Ogre[] o = new Ogre[3];
	private Lever l;
	private int gameMode;
	private boolean defeat;
	private boolean victory;
	
	public boolean getVictory(){
		return this.victory;
	}
	
	public void ogreOnKey()
	{
		for(int i = 0; i < o.length; i++)
		{
			if(o[i].getOnTheKey())
			{
				o[i].setIm('*');
			}
		}
	}
	
	
	public boolean heroNearOgre()
	{
		int[] hPos = h.getPos();
		int[] oPos;
		int[] cPos;
		for (int i = 0; i <o.length; i++) {
			oPos = o[i].getPos();
			cPos = o[i].getClub().getPos();
			if ((((oPos[0] == hPos[0] - 1 || oPos[0] == hPos[0] + 1) && oPos[1] == hPos[1]) // Near
																							// Ogre
					|| ((oPos[1] == hPos[1] - 1 || oPos[1] == hPos[1] + 1) && oPos[0] == hPos[0] || oPos[1] == hPos[1])
							&& oPos[0] == hPos[0])|| oPos[0] == hPos[0] && oPos[1] == hPos[1]) {
				// this.defeat = true;
				o[i].setStun(3);
				return false;
			} else if ((((cPos[0] == hPos[0] - 1 || cPos[0] == hPos[0] + 1) && cPos[1] == hPos[1]) // Near
					// Ogre stick
					|| ((cPos[1] == hPos[1] - 1 || cPos[1] == hPos[1] + 1) && cPos[0] == hPos[0] || cPos[1] == hPos[1])
							&& cPos[0] == hPos[0] ) || cPos[0] == hPos[0] && cPos[1] == hPos[1]) {
				this.defeat = true;
				return true;
			}
		}
		
		return false;

	}
	
	public boolean heroNearGuard() {
		int[] gPos = g.getPos();
		int[] hPos = h.getPos();
		if ((((gPos[0] == hPos[0] - 1 || gPos[0] == hPos[0] + 1) && gPos[1] == hPos[1]) // Near
																						// Guard
				|| ((gPos[1] == hPos[1] - 1 || gPos[1] == hPos[1] + 1) && gPos[0] == hPos[0]))) {
			this.defeat = true;
			return true;
		}

		else
			return false;
	}
	
	public int getGMode() {
		return this.gameMode;
	}

	public void setGMode(int gmode) {
		this.gameMode = gmode;
	}
	
	public Hero getHero()
	{
		return this.h;
	}
	
	public void setHero(Hero h1)
	{
		this.h = h1;
	}
	
	
	public Guard getGuard()
	{
		return this.g;
	}
	
	public void setGuard(Guard g1)
	{
		this.g = g1;
	}
	
	public Ogre[] getOgres()
	{
		return this.o;
	}
	
	public Lever getLever()
	{
		return this.l;
	}
	
	public void setLever(Lever l1)
	{
		this.l = l1;
	}
	
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
			char[] l8 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'};
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
			map[l.getPos()[1]][l.getPos()[0]] = l.getImage();
			map[h.getPos()[1]][h.getPos()[0]] = h.getImage();
			map[g.getPos()[1]][g.getPos()[0]] = g.getImage();
		}	
		else if(h.getKey() && gameMode == 2 && !l.getDoorOpen())
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
		
		else if(h.getKey() && gameMode == 2 && l.getDoorOpen())
		{
			char[] l0 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			char[] l1 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l2 = {'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
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
		
		else if(!h.getKey() && gameMode == 2)
		{
			char[] l0 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			char[] l1 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,'k', 'X' };
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
		this.l = new Lever(7, 8, 'k');
		this.defeat = false;
		this.victory = false;
		// char[] movs =
		// {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
		int[][] movs = { { 8, 1 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 6, 5 }, { 5, 5 }, { 4, 5 },
				{ 3, 5 }, { 2, 5 }, { 1, 5 }, { 1, 6 }, { 2, 6 }, { 3, 6 }, { 4, 6 }, { 5, 6 }, { 6, 6 }, { 7, 6 },
				{ 8, 6 }, { 8, 5 }, { 8, 4 }, { 8, 3 }, { 8, 2 } };
		this.gameMode = gMode;
		Hero h1 = new Hero(hx, hy, 'H', '*');
		this.h = h1;
		if (dif == 1) {
			Drunken d = new Drunken(gx, gy, 'D', movs);
			this.g = d;
		} else if (dif == 2) {
			Rookie d = new Rookie(gx, gy, 'R', movs);
			this.g = d;
		}

		else if (dif == 3) {
			Suspicious d = new Suspicious(gx,gy,'S',movs);
        	this.g = d;
        }		
	}
	
	public GameState(int gMode, int hx, int hy, int nOgres)
	{
		this.l = new Lever(7,8,'k');
		this.defeat = false;
		this.victory = false;
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
	
	public void action(Ogre o, char[][]map)
	{
		int[] oPos = o.getPos();
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
				
				else if(map[oPos[1]-1][oPos[0]] == l.getImage())
				{
					o.setY(o.getPos()[1]-1);
					//oPos[1] = oPos[1]-1;
					o.setOnTheKey(true);
					
				}
				
				else
				{
					o.setY(o.getPos()[1]-1);
					break;
				}
					
			}
			else if(randomNum == 2)//d
			{
				if(map[oPos[1]][oPos[0]+1] == 'X' || map[oPos[1]][oPos[0]+1] == 'I')
				{
					continue;
				}
				
				else if(map[oPos[1]][oPos[0]+1] == l.getImage())
				{
					o.setOnTheKey(true);
					o.setX(o.getPos()[0] + 1);
				}
			
				else
				{
					o.setX(o.getPos()[0] + 1);
					//oPos[0] = oPos[0]+1;
					break;
				}
				
			}
			else if(randomNum == 3)//s
			{
				if(map[oPos[1]+1][oPos[0]] == 'X' || map[oPos[1]+1][oPos[0]] == 'I')
				{
					continue;
				}
			    
				else if(map[oPos[1]+1][oPos[0]] == l.getImage())
				{
					o.setOnTheKey(true);
					o.setY(o.getPos()[1]+1);
				}
				
				else
				{
					o.setY(o.getPos()[1]+1);
					//oPos[1] = oPos[1]+1;
					break;
				}
				
			}
			else if(randomNum == 4)//a
			{
				if(map[oPos[1]][oPos[0]-1] == 'X' || map[oPos[1]][oPos[0]-1] == 'I')
				{
					continue;
				}
				
				else if(map[oPos[1]][oPos[0]-1] == l.getImage()) 
				{
					o.setOnTheKey(true);
					o.setX(o.getPos()[0]-1);
					continue;
				}
				
				else
				{
					o.setX(o.getPos()[0]-1);
					//oPos[0] = oPos[0]-1;
					break;
				}
				
			}
		}
		
	}
	
	public void action2(Club o, char[][]map)
	{
		int[] oPos = o.getPos();
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
				
				else if(map[oPos[1]-1][oPos[0]] == l.getImage())
				{
					o.setY(o.getPos()[1]-1);
					//oPos[1] = oPos[1]-1;
				}
				
				else
				{
					o.setY(o.getPos()[1]-1);
					break;
				}
					
			}
			else if(randomNum == 2)//d
			{
				if(map[oPos[1]][oPos[0]+1] == 'X' || map[oPos[1]][oPos[0]+1] == 'I')
				{
					continue;
				}
				
				else if(map[oPos[1]][oPos[0]+1] == l.getImage())
				{
					o.setX(o.getPos()[0] + 1);
				}
			
				else
				{
					o.setX(o.getPos()[0] + 1);
					//oPos[0] = oPos[0]+1;
					break;
				}
				
			}
			else if(randomNum == 3)//s
			{
				if(map[oPos[1]+1][oPos[0]] == 'X' || map[oPos[1]+1][oPos[0]] == 'I')
				{
					continue;
				}
			    
				else if(map[oPos[1]+1][oPos[0]] == l.getImage())
				{
					o.setY(o.getPos()[1]+1);
				}
				
				else
				{
					o.setY(o.getPos()[1]+1);
					//oPos[1] = oPos[1]+1;
					break;
				}
				
			}
			else if(randomNum == 4)//a
			{
				if(map[oPos[1]][oPos[0]-1] == 'X' || map[oPos[1]][oPos[0]-1] == 'I')
				{
					continue;
				}
				
				else if(map[oPos[1]][oPos[0]-1] == l.getImage()) 
				{
					o.setX(o.getPos()[0]-1);
					continue;
				}
				
				else
				{
					o.setX(o.getPos()[0]-1);
					//oPos[0] = oPos[0]-1;
					break;
				}
				
			}
		}
		
	}
	
	public void moveOgres() {
		for (int i = 0; i < o.length; i++) {
			if (o[i].getStun() > 0) {
				o[i].setStun(o[i].getStun() - 1);
			} else {
				action(o[i], this.map);
				o[i].getClub().getPos()[0] = o[i].getPos()[0];
				o[i].getClub().getPos()[1] = o[i].getPos()[1];
				action2(o[i].getClub(), this.map);
			}
		}
	}

	public boolean moveHero(char order)
	{
		if(order == 'w')
		{
			if(map[h.getPos()[1]-1][h.getPos()[0]] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				return false;
				
			}
			else if(map[h.getPos()[1]-1][h.getPos()[0]] == 'I')
			{
				if(gameMode == 2 && h.getKey())
				{
					l.setDoorOpen(true);
					return false;
				}
		
				else
				{
					System.out.println("You shall not pass!");
					return false;
				}
				
				
			} else if (map[h.getPos()[1] - 1][h.getPos()[0]] == 'S') {
				if (h.getPos()[1] - 2 < 0 && gameMode == 1) {
					System.out.println("TOP KEK. LV1. YOU HAPPY?");
					this.victory = true;
					return false;
					
				}
				else if (h.getPos()[1] - 2 < 0 && gameMode == 2) {
					this.victory = true;
					return true;
				}
				else if (map[h.getPos()[1] - 2][h.getPos()[0]] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[1] = h.getPos()[1] - 2;
					return true;
					
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
					return false;
				}
			}
			
		
			else if(map[h.getPos()[1]-1][h.getPos()[0]] == l.getImage())
			{
				if(gameMode == 2)
				{
					h.setIm('k');
				}
				System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				l.setPressed(true);
				h.setKey(true);
				return false;
				
			}
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				h.getPos()[1] = h.getPos()[1] - 1;
				return true;
			}
			
		}
		else if(order == 'a')
		{
			System.out.println(order);
			if(map[h.getPos()[1]][h.getPos()[0]-1] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				return false;
			}
			else if(map[h.getPos()[1]][h.getPos()[0]-1] == 'I')
			{
				if(gameMode == 2 && h.getKey())
				{
					l.setDoorOpen(true);
					return true;
				}
					
				else
				{
					System.out.println("You shall not pass!");
					return false;
				}				
				
			}
			else if(map[h.getPos()[1]][h.getPos()[0]-1] == 'S')
			{
				if (h.getPos()[0] - 2 < 0 && gameMode == 1) {
					System.out.println("TOP KEK. LV1. YOU HAPPY?");
					this.victory = true;
					return false;
					
				}
				else if (h.getPos()[0] - 2 < 0 && gameMode == 2) {
					//flagVictory = true;
					//gameMode = 2;
					this.victory = true;
					return false;
					
				}else if (map[h.getPos()[1]][h.getPos()[0]-2] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[0] = h.getPos()[0] - 2;
					return true;
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
					return false;
				}
			}
			
			else if(map[h.getPos()[1]][h.getPos()[0]-1] == l.getImage())
			{
				if(gameMode == 2)
				{
					h.setIm('k');
				}
				System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				l.setPressed(true);
				h.setKey(true);
				return false;
			}
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				h.getPos()[0] = h.getPos()[0] - 1;
				return true;
			}
			
		}
		else if(order == 's')
		{
			System.out.println(order);
			if(map[h.getPos()[1]+1][h.getPos()[0]] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				return false;
				
			}
			else if(map[h.getPos()[1]+1][h.getPos()[0]] == 'I')
			{
				if(gameMode == 2 && h.getKey())
				{
					l.setDoorOpen(true); 
					return true;
				}
				else
				{
					System.out.println("You shall not pass!");
					return false;
				}
					
				
			}
			else if(map[h.getPos()[1]+1][h.getPos()[0]] == 'S')
			{
				if (h.getPos()[1] + 2 > 10 && gameMode == 1) {
					System.out.println("TOP KEK. LV1. YOU HAPPY?");
					this.victory = true;
					return false;
					
				}
				else if (h.getPos()[1] + 2 > 10 && gameMode == 2) {
					this.victory = true;
					return true;
					
				}
				else if (map[h.getPos()[1] + 2][h.getPos()[0]] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[1] = h.getPos()[1] + 2;
					return true;
					
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
					return false;					
				}
			}
			
			else if(map[h.getPos()[1]+1][h.getPos()[0]] == l.getImage())
			{
				if(gameMode == 2)
				{
					h.setIm('k');
				}
				System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				l.setPressed(true);
				h.setKey(true);
				return false;			
			}
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				h.getPos()[1] = h.getPos()[1] + 1;
				return true;				
			}
			
		}
		else if(order == 'd')
		{
			System.out.println(order);
			if(map[h.getPos()[1]][h.getPos()[0]+1] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				return false;
				
			}
			else if(map[h.getPos()[1]][h.getPos()[0]+1] == 'I')
			{
				if(gameMode == 2 && h.getKey())
				{
					l.setDoorOpen(true);
					return true;
				}
				
				else
				{
					System.out.println("You shall not pass!");
					return false;
				}
					
				
				
			}
			else if(map[h.getPos()[1]][h.getPos()[0]+1] == 'S')
			{
				if (h.getPos()[0] + 2 > 10 && gameMode == 1) {
					System.out.println("TOP KEK. LV1. YOU HAPPY?");
					this.victory = true;
					return false;
					
				} 
				else if (h.getPos()[0] + 2 > 10 && gameMode == 2) {
					this.victory = true;
					return true;
					
				} else if (map[h.getPos()[1]][h.getPos()[0]+2] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[0] = h.getPos()[0] + 2;
					return true;
					
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
					return false;
				}
			}
			
			else if(map[h.getPos()[1]][h.getPos()[0]+1] == l.getImage())
			{
				if(gameMode == 2)
				{
					h.setIm('k');
				}
				System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				l.setPressed(true);
				h.setKey(true);
				return false;
				
			}
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				h.getPos()[0] = h.getPos()[0] + 1;
				return true;
			}
			
		}
		else
		{
			System.out.print("INVALID COMMAND \n");
			return false;
		}
	}
	
	public boolean getDefeat() {
		return defeat;
	}

	public void setDefeat(boolean defeat) {
		this.defeat = defeat;
	}
	
}
