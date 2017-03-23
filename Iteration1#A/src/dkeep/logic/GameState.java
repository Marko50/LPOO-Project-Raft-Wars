package dkeep.logic;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

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
	private boolean victoryGuard;
	private boolean victoryOgre;

	public GameState() {

	}

	public void setVictory(boolean vic) {
		victory = vic;
	}

	public boolean getVictory() {
		return this.victory;
	}

	public boolean isOgreOnKey(Ogre o) {
		if (o.getPos()[0] == k.getPos()[0] && o.getPos()[1] == k.getPos()[1])
			return true;

		else
			return false;
	}

	public void ogreOnKey() {
		for (int i = 0; i < o.length; i++) {
			if (isOgreOnKey(o[i])) {
				o[i].setOnTheKey(true);
				o[i].setIm('*');
			}

			else {
				o[i].setIm('O');
				o[i].setOnTheKey(false);
			}

		}
	}

	public boolean stunOgre(int i) {
		int[] oPos = o[i].getPos();
		int[] hPos = h.getPos();
		if (((oPos[0] == hPos[0] - 1 || oPos[0] == hPos[0] + 1) && oPos[1] == hPos[1])
				|| ((oPos[1] == hPos[1] - 1 || oPos[1] == hPos[1] + 1) && oPos[0] == hPos[0] || oPos[1] == hPos[1])
						&& oPos[0] == hPos[0]) {
			return true;
		}

		else
			return false;

	}

	public boolean ogreKillsHero(int i) {
		int[] oPos = o[i].getPos();
		int[] hPos = h.getPos();
		int[] cPos = o[i].getClub().getPos();

		if (o[i].getStun() > 0) {
			return false;
		}

		else if (((cPos[0] == hPos[0] - 1 || cPos[0] == hPos[0] + 1) && cPos[1] == hPos[1])
				|| ((cPos[1] == hPos[1] - 1 || cPos[1] == hPos[1] + 1) && cPos[0] == hPos[0])
				|| (cPos[0] == hPos[0] && cPos[1] == hPos[1])) {
			return true;
		}

		else if (oPos[0] == hPos[0] && oPos[1] == hPos[1]) {
			return true;
		}

		else
			return false;

	}

	public boolean heroNearOgre() {
		boolean ret = false;
		for (int i = 0; i < o.length; i++) {
			if (this.stunOgre(i)) {
				o[i].setStun(3);
				ret = false;
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
		if ((((gPos[0] == hPos[0] - 1 || gPos[0] == hPos[0] + 1) && gPos[1] == hPos[1])
				|| ((gPos[1] == hPos[1] - 1 || gPos[1] == hPos[1] + 1) && gPos[0] == hPos[0]))
				&& this.g.getSleep() == 0) {
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

	public Hero getHero() {
		return this.h;
	}

	public void setHero(Hero h1) {
		this.h = h1;
	}

	public Guard getGuard() {
		return this.g;
	}

	public void setGuard(Guard g1) {
		this.g = g1;
	}

	public void setO(Ogre[] og) {
		this.o = og;
	}

	public Ogre[] getOgres() {
		return this.o;
	}

	public Lever getLever() {
		return this.l;
	}

	public void setLever(Lever l1) {
		this.l = l1;
	}

	public GameState(int hx, int hy, int gx, int gy, int dif, int lx, int ly, int kx, int ky, int ox, int oy,
			int nOgres) {
		this.mapa = new GameMap();
		this.mapa.setMap(this.mapa.mapGuard);
		this.k = new Key(kx, ky, 'k');
		this.l = new Lever(lx, ly, 'k');
		this.defeat = false;
		this.victory = false;		
		this.gameMode = 1;
		Hero h1 = new Hero(hx, hy, 'H');
		this.h = h1;
		if (dif == 1) {
			Drunken d = new Drunken(gx, gy, 'D');
			this.g = d;
		} else if (dif == 2) {
			Rookie d = new Rookie(gx, gy, 'R');
			this.g = d;
		} else if (dif == 3) {
			Suspicious d = new Suspicious(gx, gy, 'S');
			this.g = d;
		}
		this.g.setMovs(this.g.movsPossibility);
		o = new Ogre[nOgres];
		for (int i = 0; i < nOgres; i++) {
			Ogre o1 = new Ogre(ox, oy, 'O', '*');
			o[i] = o1;
		}
		this.mapa.mapSetGameMode1(l, h, g);
		this.victoryGuard = false;
		this.victoryOgre = false;
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

	public void action(Character o, char[][] map) {
		int[] oPos = o.getPos();
		int randomNum;
		while (true) {
			randomNum = ThreadLocalRandom.current().nextInt(1, 5);
			if (randomNum == 1)// w
			{
				if (map[oPos[1] - 1][oPos[0]] == 'X' || map[oPos[1] - 1][oPos[0]] == 'I' || map[oPos[1] - 1][oPos[0]] == h.getImage()) {
					continue;
				}

				else {
					this.mapa.getMap()[o.getPos()[1]][o.getPos()[0]] = ' ';
					o.setY(o.getPos()[1] - 1);
					o.changeBuffImage('w');
					break;
				}

			} else if (randomNum == 2)// d
			{
				if (map[oPos[1]][oPos[0] + 1] == 'X' || map[oPos[1]][oPos[0] + 1] == 'I'|| map[oPos[1] ][oPos[0]+1] == h.getImage()) {
					continue;
				}

				else {
					this.mapa.getMap()[o.getPos()[1]][o.getPos()[0]] = ' ';
					o.setX(o.getPos()[0] + 1);
					o.changeBuffImage('d');
					break;
				}

			} else if (randomNum == 3)// s
			{
				if (map[oPos[1] + 1][oPos[0]] == 'X' || map[oPos[1] + 1][oPos[0]] == 'I' || map[oPos[1] + 1][oPos[0]] == h.getImage()) {
					continue;
				}

				else {
					this.mapa.getMap()[o.getPos()[1]][o.getPos()[0]] = ' ';
					o.setY(o.getPos()[1] + 1);
					o.changeBuffImage('s');
					break;
				}

			} else if (randomNum == 4 )// a
			{
				if (map[oPos[1]][oPos[0] - 1] == 'X' || map[oPos[1]][oPos[0] - 1] == 'I' || map[oPos[1]][oPos[0] - 1] == h.getImage()) {
					continue;
				}

				else {
					this.mapa.getMap()[o.getPos()[1]][o.getPos()[0]] = ' ';
					o.setX(o.getPos()[0] - 1);
					o.changeBuffImage('a');
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
				action(o[i].getClub(), this.mapa.getMap());
			}
		}
	}

	public boolean checkClosedDoor(int posy, int posx) {
		if (this.getMapa().getMap()[posy][posx] == 'I') {
			return true;
		}
		return false;
	}

	public boolean checkVictory(int posy, int posx) {
		for (int i = 0; i < this.mapa.getMap().length; i++) {
			for (int j = 0; j < this.mapa.getMap()[i].length; j++) {
				if (posx + 1 > this.mapa.getMap()[i].length)
					return true;
			}
		}

		if (posx - 1 < 0 || posy - 1 < 0 || posy + 1 > mapa.getMap().length)
			return true;

		else
			return false;
	}
	
	

	public boolean checkOpenDoor(int posy, int posx) {
		if (this.mapa.getMap()[posy][posx] == 'S') {
			if (checkVictory(posx, posy)) {
				this.victory = true;
				return true;
			}

			else if (this.mapa.getMap()[posy - 1][posx] == ' ' || this.mapa.getMap()[posy + 1][posx] == ' '
					|| this.mapa.getMap()[posy][posx - 1] == ' ' || this.mapa.getMap()[posy][posx + 1] == ' ') {
				return true;

			} else {
				return false;
			}
		}

		else
			return false;
	}

	public boolean checkWall(int posy, int posx) {
		if (this.mapa.getMap()[posy][posx] == 'X' || this.mapa.getMap()[posy][posx] == g.getImage()
				|| this.mapa.getMap()[posy][posx] == o[0].getImage()) {
			return true;
		}

		else
			return false;
	}

	public boolean checkLever(int posy, int posx) {
		if (this.mapa.getMap()[posy][posx] == l.getImage() && gameMode == 1) {
			return true;
		}

		else
			return false;

	}

	public boolean checkKey(int posy, int posx) {
		if (this.mapa.getMap()[posy][posx] == k.getImage() && gameMode == 2) {
			return true;
		}

		else
			return false;
	}
	
	public boolean checkOgre(int posy, int posx)
	{
		for(int i = 0; i < o.length; i++){
			if (this.mapa.getMap()[posy][posx] == o[i].getImage() && gameMode == 2) {
				return true;
			}
			else
				return false;
		}
		
		return false;
		
	}
	
	public boolean moveHero(char order) throws CloneNotSupportedException {
		if (order == 'w') {
			h.changeBuffImage('w');
			if (checkWall(h.getPos()[1] - 1, h.getPos()[0])) {
				return false;
			}
			
			else if (checkOgre(h.getPos()[1] - 1, h.getPos()[0]))
			{
				return false;
			}

			else if (checkClosedDoor(h.getPos()[1] - 1, h.getPos()[0])) {
				if (gameMode == 2 && k.isPickedUp()) {
					k.setUsed(true);
					this.mapa.getMap()[h.getPos()[1] - 1][h.getPos()[0]] = 'S';
				}
				return false;
			}

			else if (checkOpenDoor(h.getPos()[1] - 1, h.getPos()[0])) {
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
				h.getPos()[1] = h.getPos()[1] - 2;
				return true;
			}

			else if (checkLever(h.getPos()[1] - 1, h.getPos()[0])) {
				l.setPressed(true);
				this.mapa.changeDoors();
				return false;
			}

			else if (checkKey(h.getPos()[1] - 1, h.getPos()[0])) {
				Key test = (Key) k.clone();
				h.setIm(test.getImage());
				this.getMapa().getMap()[k.getPos()[1]][k.getPos()[0]] = ' ';
				k.setPickedUp(true);
				return false;
			}

			else {
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
				h.getPos()[1] = h.getPos()[1] - 1;
				return true;
			}

		} else if (order == 'a') {
			h.changeBuffImage('a');
			if (checkWall(h.getPos()[1], h.getPos()[0] - 1)) {
				return false;
			}
			

			else if (checkOgre(h.getPos()[1], h.getPos()[0] - 1))
			{
				return false;
			}
			
			else if (checkClosedDoor(h.getPos()[1], h.getPos()[0] - 1)) {
				if (gameMode == 2 && k.isPickedUp()) {
					k.setUsed(true);
					this.mapa.getMap()[h.getPos()[1]][h.getPos()[0] - 1] = 'S';

				}
				return false;
			}

			else if (checkOpenDoor(h.getPos()[1], h.getPos()[0] - 1)) {
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
				h.getPos()[0] = h.getPos()[0] - 2;
				return true;
			}

			else if (checkLever(h.getPos()[1], h.getPos()[0] - 1)) {
				l.setPressed(true);
				this.mapa.changeDoors();
				return false;
			}

			else if (checkKey(h.getPos()[1], h.getPos()[0] - 1)) {
				Key test = (Key) k.clone();
				h.setIm(test.getImage());
				this.getMapa().getMap()[k.getPos()[1]][k.getPos()[0]] = ' ';
				k.setPickedUp(true);
				return false;
			}

			else {
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
				h.getPos()[0] = h.getPos()[0] - 1;
				return true;
			}

		} else if (order == 's') {
			h.changeBuffImage('s');
			if (checkWall(h.getPos()[1] + 1, h.getPos()[0])) {
				return false;
			}
			

			else if (checkOgre(h.getPos()[1] + 1, h.getPos()[0]))
			{
				return false;
			}

			else if (checkClosedDoor(h.getPos()[1] + 1, h.getPos()[0])) {
				if (gameMode == 2 && k.isPickedUp()) {
					k.setUsed(true);
					this.mapa.getMap()[h.getPos()[1] + 1][h.getPos()[0]] = 'S';
				}
				return false;
			}

			else if (checkOpenDoor(h.getPos()[1] + 1, h.getPos()[0])) {
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
				h.getPos()[1] = h.getPos()[1] + 2;
				return true;
			}

			else if (checkLever(h.getPos()[1] + 1, h.getPos()[0])) {
				l.setPressed(true);
				this.mapa.changeDoors();
				return false;
			}

			else if (checkKey(h.getPos()[1] + 1, h.getPos()[0])) {
				Key test = (Key) k.clone();
				h.setIm(test.getImage());
				this.getMapa().getMap()[k.getPos()[1]][k.getPos()[0]] = ' ';
				k.setPickedUp(true);
				return false;
			}

			else {
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
				h.getPos()[1] = h.getPos()[1] + 1;
				return true;
			}

		} else if (order == 'd') {
			h.changeBuffImage('d');
			if (checkWall(h.getPos()[1], h.getPos()[0] + 1)) {
				return false;
			}


			else if (checkOgre(h.getPos()[1], h.getPos()[0] + 1))
			{
				return false;
			}
			
			else if (checkClosedDoor(h.getPos()[1], h.getPos()[0] + 1)) {
				if (gameMode == 2 && k.isPickedUp()) {
					k.setUsed(true);
					this.mapa.getMap()[h.getPos()[1]][h.getPos()[0] + 1] = 'S';
				}
				return false;
			}

			else if (checkOpenDoor(h.getPos()[1], h.getPos()[0] + 1)) {
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
				h.getPos()[0] = h.getPos()[0] + 2;
				return true;
			}

			else if (checkLever(h.getPos()[1], h.getPos()[0] + 1)) {
				l.setPressed(true);
				this.mapa.changeDoors();
				return false;
			}

			else if (checkKey(h.getPos()[1], h.getPos()[0] + 1)) {
				Key test = (Key) k.clone();
				h.setIm(test.getImage());
				this.getMapa().getMap()[k.getPos()[1]][k.getPos()[0]] = ' ';
				k.setPickedUp(true);
				return false;
			}

			else {
				this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
				h.getPos()[0] = h.getPos()[0] + 1;
				return true;
			}
		}

		else
			return false;

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
	
	int contador = 0;
	public boolean updateGameMode1(char order) {
		try {
			if (this.moveHero(order) == false) {
				this.getMapa().mapSetGameMode1(l, h, g);
				//this.showmap();
				return true;
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		if (contador == this.g.getMovs().length-1 && this.getGuard().getDirection() == 1)
			contador = 0;
		else if (contador == 0 && this.getGuard().getDirection() == -1) {
			contador =  this.g.getMovs().length-1;
		} 
		else if (this.getGuard().getSleep() == 0)
			contador = contador + this.getGuard().getDirection();
		this.getMapa().getMap()[this.getGuard().getPos()[1]][this.getGuard().getPos()[0]] = ' ';
		this.getGuard().gMove(contador);
		this.heroNearGuard();
		if(this.getVictory())
		{
			this.victory = false;
			this.defeat = false;
			this.victoryGuard = true;
			this.mapa.setMap(this.mapa.mapOgre);
			this.setGMode(2);
			this.h.setX(1);
			this.h.setY(8);
		    this.getMapa().mapSetGameMode2(h, o, k);
			//this.showmap();
			return true;
		}
		else if (this.getDefeat())
		{
			this.getMapa().mapSetGameMode1(this.getLever(), this.getHero(), this.getGuard());
			//this.showmap();
			return false;
		}	
		this.getMapa().mapSetGameMode1(l, h, g);
		//this.showmap();
		return true;
	}
	
	public boolean updateGameMode2(char order)
	{
		try {
			if(this.moveHero(order) == false)
			{
				this.getMapa().mapSetGameMode2(this.getHero(), this.getOgres(), this.getK());
				//this.showmap();
				return true;
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		this.moveOgres();
		this.ogreOnKey();
		this.heroNearOgre();
		if(this.getVictory())
		{
			this.victoryOgre = true;
			return true;
		}
		if (this.getDefeat()) {
			this.getMapa().mapSetGameMode2(this.getHero(), this.getOgres(), this.getK());
			//this.showmap();
			return false;
		}
	
		this.getMapa().mapSetGameMode2(this.getHero(), this.getOgres(), this.getK());
		//this.showmap();
		return true;
	}

	public boolean isVictoryGuard() {
		return victoryGuard;
	}

	public void setVictoryGuard(boolean victoryGuard) {
		this.victoryGuard = victoryGuard;
	}

	public boolean isVictoryOgre() {
		return victoryOgre;
	}

	public void setVictoryOgre(boolean victoryOgre) {
		this.victoryOgre = victoryOgre;
	}
	
}
