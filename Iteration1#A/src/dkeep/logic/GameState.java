package dkeep.logic;

import java.util.concurrent.ThreadLocalRandom;

public class GameState {
	GameMap mapa;
	private Hero h;
	private Guard g;
	private Ogre[] o;
	private Lever l;
	private Key k;
	private int gameMode;
	private boolean defeat;
	private boolean victory;
	
	
	public GameState()
	{
		
	}
	public void setVictory (boolean vic)
	{
		victory = vic;
	}
	
	public boolean getVictory(){
		return this.victory;
	}
	
	public boolean isOgreOnKey(Ogre o)
	{
		if(o.getPos()[0] == k.getPos()[0] && o.getPos()[1] == k.getPos()[1])
			return true;
		
		else 
			return false;
	}
	
	public void ogreOnKey()
	{
		for(int i = 0; i < o.length; i++)
		{
			if(isOgreOnKey(o[i]))
			{
				o[i].setOnTheKey(true);
				o[i].setIm('*');
			}
			
			else{
				o[i].setIm('O');
				o[i].setOnTheKey(false);
			}
			  	
		}
	}
	
	public boolean stunOgre(int i)
	{
		int[] oPos = o[i].getPos();
		int[] hPos = h.getPos();
		if (((oPos[0] == hPos[0] - 1 || oPos[0] == hPos[0] + 1) && oPos[1] == hPos[1]) // Near
				// Ogre
				|| ((oPos[1] == hPos[1] - 1 || oPos[1] == hPos[1] + 1) && oPos[0] == hPos[0] || oPos[1] == hPos[1])
						&& oPos[0] == hPos[0]) {
			return true;
		}
		
		else return false;
		
	}
	
	public boolean ogreKillsHero(int i) {
		int[] oPos = o[i].getPos();
		int[] hPos = h.getPos();
		int[] cPos = o[i].getClub().getPos();
				
		if (((cPos[0] == hPos[0] - 1 || cPos[0] == hPos[0] + 1) && cPos[1] == hPos[1])
				|| ((cPos[1] == hPos[1] - 1 || cPos[1] == hPos[1] + 1) && cPos[0] == hPos[0])
				|| (cPos[0] == hPos[0] && cPos[1] == hPos[1])) {
			return true;
		}

		else if (oPos[0] == hPos[0] && oPos[1] == hPos[1]) {
			return true;
		}
		
		else return false;

	}
		
	public boolean heroNearOgre()
	{
		boolean ret = false;
		for (int i = 0; i <o.length; i++) {
			//System.out.println("HERO NEAR OGRE");
			if (this.stunOgre(i)) {
				o[i].setStun(3);
				ret =  false;
			} 
			if (ogreKillsHero(i)) {
				this.defeat = true;
				ret = true;
			}
		}
		return ret;
	}
	
	public boolean heroNearGuard() {
		int[] gPos = g.getPos();
		int[] hPos = h.getPos();
		if ((((gPos[0] == hPos[0] - 1 || gPos[0] == hPos[0] + 1) && gPos[1] == hPos[1]) // Near
																						// Guard
				|| ((gPos[1] == hPos[1] - 1 || gPos[1] == hPos[1] + 1) && gPos[0] == hPos[0])) && this.g.getSleep() == 0) {
			this.defeat = true;
			return true;
		}

		else
			return false;
	}
	
	public boolean heroNearOgreSimple() {
		int[] gPos = o[0].getPos();
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
	
	public void setO(Ogre[] og)
	{
		this.o = og;
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
	
	public GameState(int gMode, int hx, int hy, int gx, int gy, int dif, char[][] map, int lx, int ly)
	{ 
		this.k = new Key(0,0,'k');
		this.mapa = new GameMap(map);
		this.l = new Lever(lx, ly, 'k');
		this.defeat = false;
		this.victory = false;
		int[][] movs = { { 8, 1 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 6, 5 }, { 5, 5 }, { 4, 5 },
				{ 3, 5 }, { 2, 5 }, { 1, 5 }, { 1, 6 }, { 2, 6 }, { 3, 6 }, { 4, 6 }, { 5, 6 }, { 6, 6 }, { 7, 6 },
				{ 8, 6 }, { 8, 5 }, { 8, 4 }, { 8, 3 }, { 8, 2 } };
		this.gameMode = gMode;
		Hero h1 = new Hero(hx, hy, 'H');
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
	
	public GameState(int hx, int hy, int nOgres, int ox, int oy, char[][] map, int kx, int ky)
	{
		
		this.l = new Lever(0, 0, 'k');	
		this.mapa = new GameMap(map);
		this.k = new Key(kx,ky,'k');
		this.defeat = false;
		this.victory = false;
		this.gameMode = 2;
        Hero h1 = new Hero(hx, hy, 'H');
        this.h = h1;
        o = new Ogre[nOgres];
        for(int i = 0; i < nOgres; i++)
        {
        	Ogre o1 = new Ogre(ox, oy, 'O', '*');//3 1
        	o[i] = o1;
        }
	}
	
	public void showmap() {
		char[][] map = this.mapa.getMap();
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
				
				else
				{
					this.mapa.getMap()[o.getPos()[1]][o.getPos()[0]] = ' ';
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
				
				else
				{
					this.mapa.getMap()[o.getPos()[1]][o.getPos()[0]] = ' ';
					o.setX(o.getPos()[0] + 1);
					break;
				}
				
			}
			else if(randomNum == 3)//s
			{
				if (map[oPos[1] + 1][oPos[0]] == 'X' || map[oPos[1] + 1][oPos[0]] == 'I') {
					continue;
				}

				else {
					this.mapa.getMap()[o.getPos()[1]][o.getPos()[0]] = ' ';
					o.setY(o.getPos()[1] + 1);
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
					this.mapa.getMap()[o.getPos()[1]][o.getPos()[0]] = ' ';
					o.setX(o.getPos()[0]-1);
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
				
				else if(map[oPos[1]-1][oPos[0]] == k.getImage())
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
				
				else if(map[oPos[1]][oPos[0]+1] == k.getImage())
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
			    
				else if(map[oPos[1]+1][oPos[0]] == k.getImage())
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
				
				else if(map[oPos[1]][oPos[0]-1] == k.getImage()) 
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
				action(o[i], this.mapa.getMap());
				this.mapa.getMap()[o[i].getClub().getPos()[1]][o[i].getClub().getPos()[0]] = ' ';
				o[i].getClub().getPos()[0] = o[i].getPos()[0];
				o[i].getClub().getPos()[1] = o[i].getPos()[1];
				action2(o[i].getClub(), this.mapa.getMap());
			}
		}
	}

	public boolean moveHero(char order) throws CloneNotSupportedException
	{
		if(order == 'w')
		{
			System.out.println(order);
			if(this.mapa.getMap()[h.getPos()[1]-1][h.getPos()[0]] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				return false;
				
			}
			else if(this.mapa.getMap()[h.getPos()[1]-1][h.getPos()[0]] == 'I')
			{
				if(gameMode == 2 && k.isPickedUp())
				{
					k.setUsed(true);
					System.out.println("You used the key!");
					this.mapa.getMap()[h.getPos()[1]-1][h.getPos()[0]] = 'S';
					return false;
				}
		
				else
				{
					System.out.println("You shall not pass!");
					return false;
				}
				
				
			} else if (this.mapa.getMap()[h.getPos()[1] - 1][h.getPos()[0]] == 'S') {
				if (h.getPos()[1] - 2 < 0 && gameMode == 1) {
					System.out.println("TOP KEK. LV1. YOU HAPPY?");
					this.victory = true;
					return false;
					
				}
				else if (h.getPos()[1] - 2 < 0 && gameMode == 2) {
					this.victory = true;
					return true;
				}
				else if (this.mapa.getMap()[h.getPos()[1] - 2][h.getPos()[0]] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[1] = h.getPos()[1] - 2;
					return true;
					
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
					return false;
				}
			}
			
			else if (this.mapa.getMap()[h.getPos()[1] - 1][h.getPos()[0]] == l.getImage() && gameMode == 1) {
				System.out.println(
						"The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				l.setPressed(true);
				this.mapa.changeDoors();
				return false;

			}

			else if (this.mapa.getMap()[h.getPos()[1] - 1][h.getPos()[0]] == k.getImage() && gameMode == 2) {
				Key test = (Key) k.clone();
				h.setIm(test.getImage());
				//k.setIm(' ');
				this.getMapa().getMap()[k.getPos()[1]][k.getPos()[0]] = ' ';
				k.setPickedUp(true);
				System.out.println(
						"You can now open doors!Magnificent. The actual doors, not the band. Jesus Christ superstar..");
				return false;
			}
			
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
				h.getPos()[1] = h.getPos()[1] - 1;
				return true;
			}
			
		}
		else if(order == 'a')
		{
			System.out.println(order);
			if(this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]-1] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				return false;
			}
			else if(this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]-1] == 'I')
			{
				if(gameMode == 2 && k.isPickedUp())
				{
					k.setUsed(true);
					System.out.println("You used the key!");
					this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]-1] = 'S';
					return true;
				}
					
				else
				{
					System.out.println("You shall not pass!");
					return false;
				}				
				
			}
			else if(this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]-1] == 'S')
			{
				if (h.getPos()[0] - 2 < 0 && gameMode == 1) {
					System.out.println("TOP KEK. LV1. YOU HAPPY?");
					this.victory = true;
					return false;
					
				}
				else if (h.getPos()[0] - 2 < 0 && gameMode == 2) {
					System.out.println("Ok. Nice cheats bruh");
					this.victory = true;
					return false;
					
				}else if (this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]-2] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[0] = h.getPos()[0] - 2;
					return true;
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
					return false;
				}
			}
			
			else if(this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]-1] == l.getImage() && gameMode == 1)
			{
				System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				this.mapa.changeDoors();
				l.setPressed(true);			
				return false;
			}
			else if(this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]-1] == k.getImage() && gameMode == 2)
			{
				Key test = (Key) k.clone();
				h.setIm(test.getImage());	
			    k.setIm(' ');
			    k.setPickedUp(true);
			    System.out.println("You can now open doors! The actual doors, not the band. Jesus Christ superstar..");
			    return true;
			}
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
				h.getPos()[0] = h.getPos()[0] - 1;
				return true;
			}
			
		}
		else if(order == 's')
		{
			System.out.println(order);
			if(this.mapa.getMap()[h.getPos()[1]+1][h.getPos()[0]] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				return false;
				
			}
			else if(this.mapa.getMap()[h.getPos()[1]+1][h.getPos()[0]] == 'I')
			{
				if(gameMode == 2 && k.isPickedUp())
				{
					k.setUsed(true); 
					this.mapa.getMap()[h.getPos()[1]+1][h.getPos()[0]] = 'S';
					System.out.println("You used the key!");
					return true;
				}
				else
				{
					System.out.println("You shall not pass!");
					return false;
				}
					
				
			}
			else if(this.mapa.getMap()[h.getPos()[1]+1][h.getPos()[0]] == 'S')
			{
				if (h.getPos()[1] + 2 > this.mapa.getMap().length && gameMode == 1) {
					System.out.println("TOP KEK. LV1. YOU HAPPY?");
					this.victory = true;
					return false;
					
				}
				else if (h.getPos()[1] + 2 > this.mapa.getMap().length && gameMode == 2) {
					this.victory = true;
					return true;
					
				}
				else if (this.mapa.getMap()[h.getPos()[1] + 2][h.getPos()[0]] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[1] = h.getPos()[1] + 2;
					return true;
					
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
					return false;					
				}
			}
			
			else if(this.mapa.getMap()[h.getPos()[1]+1][h.getPos()[0]] == l.getImage() && gameMode == 1)
			{
				System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				this.mapa.changeDoors();
				l.setPressed(true);
				return false;			
			}
			else if(this.mapa.getMap()[h.getPos()[1]+1][h.getPos()[0]] == k.getImage() && gameMode == 2)
			{
				Key test = (Key) k.clone();
				h.setIm(test.getImage());
				this.getMapa().getMap()[k.getPos()[1]][k.getPos()[0]] = ' ';
				k.setPickedUp(true);
				System.out.println("You can now open doors.Magnificent. The actual doors, not the band. Jesus Christ superstar..");
				return false;
			}
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
				h.getPos()[1] = h.getPos()[1] + 1;
				return true;				
			}
			
		}
		else if(order == 'd')
		{
			System.out.println(order);
			if(this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]+1] == 'X')
			{
				System.out.println("You triggered feminist.. Only men can do that");
				return false;
				
			}
			else if(this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]+1] == 'I')
			{
				if(gameMode == 2 && k.isPickedUp())
				{
					k.setUsed(true);
					this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]+1] = 'S';
					System.out.println("You used the key!");
					return true;
				}
				
				else
				{
					System.out.println("You shall not pass!");
					return false;
				}
					
				
				
			}
			else if(this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]+1] == 'S')
			{
				if (h.getPos()[0] + 2 > this.mapa.getMap().length && gameMode == 1) {
					System.out.println("TOP KEK. LV1. YOU HAPPY?");
					this.victory = true;
					return false;
					
				} 
				else if (h.getPos()[0] + 2 > this.mapa.getMap().length && gameMode == 2) {
					this.victory = true;
					return true;
					
				} else if (this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]+2] == ' ') {
					System.out.println("You opened the door. Congratz. ");
					h.getPos()[0] = h.getPos()[0] + 2;
					return true;
					
				} else {
					System.out.println("You triggered feminist.. Only men can do that");
					return false;
				}
			}
			
			else if(this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]+1] == l.getImage()&& gameMode == 1)
			{
				System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
				this.mapa.changeDoors();
				l.setPressed(true);
				return false;	
			}
			
			else if(this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]+1] == k.getImage() && gameMode == 2)
			{
				Key test = (Key) k.clone();
				h.setIm(test.getImage());
				//k.setIm(' ');
				this.getMapa().getMap()[k.getPos()[1]][k.getPos()[0]] = ' ';
				k.setPickedUp(true);
				System.out.println("You can now open doors!Magnificent. The actual doors, not the band. Jesus Christ superstar..");
				return false;
			}
			else
			{
				System.out.println("Wow fascinating...You just moved..");
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
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
	
	public GameMap getMapa() {
		return mapa;
	}

	public void setMapa(GameMap mapa) {
		this.mapa = mapa;
	}

	public boolean getDefeat() {
		return defeat;
	}

	public void setDefeat(boolean defeat) {
		this.defeat = defeat;
	}

	public Key getK() {
		return k;
	}

	public void setK(Key k) {
		this.k = k;
	}
	
}
