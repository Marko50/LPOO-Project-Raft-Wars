/**  
* GameState.java - Class with the state of the game at the moment
*/ 
package dkeep.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class GameState {
	GameMap mapa;
	private Hero h;
	private Guard g;
	ArrayList<Ogre> o = new ArrayList<Ogre>();
	private Lever l;
	private Key k;
	private int gameMode;
	private boolean victory, victoryGuard, victoryOgre, defeat;

	public GameState() {
		
	}
	
	public void addOgre(Ogre og)
	{
		o.add(og);
	}

	public void setVictory(boolean vic) { victory = vic; }

	public boolean getVictory() { return this.victory; }

	public boolean isOgreOnKey(Ogre o) {
		if (o.getPos()[0] == k.getPos()[0] && o.getPos()[1] == k.getPos()[1])
			return true;
		else
			return false;
	}

	public void ogreOnKey() {
		for (int i = 0; i < o.size(); i++) {
			if (isOgreOnKey(o.get(i))) {
				o.get(i).setOnTheKey(true);
				o.get(i).setIm('*');
			}
			else {
				o.get(i).setIm('O');
				o.get(i).setOnTheKey(false);
			}
		}
	}

	public boolean stunOgre(int i) {
		int[] oPos = o.get(i).getPos();
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
		int[] oPos = o.get(i).getPos();
		int[] hPos = h.getPos();
		int[] cPos = o.get(i).getClub().getPos();

		if (o.get(i).getStun() > 0) {
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
		for (int i = 0; i < o.size(); i++) {
			if (this.stunOgre(i)) {
				o.get(i).setStun(3);
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
		int[] gPos = o.get(0).getPos();
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

	public void setO(ArrayList<Ogre> og) {
		this.o = og;
	}

	public ArrayList<Ogre> getOgres() {
		return this.o;
	}

	public Lever getLever() {
		return this.l;
	}

	public void setLever(Lever l1) {
		this.l = l1;
	}

	public GameState(int[] hx, int[] gx, int dif, int[] lx, int[] kx, int og[]) {
		this.mapa = new GameMap();
		this.mapa.setMap(this.mapa.mapGuard);
		this.k = new Key(kx[0], kx[1], 'k');
		this.l = new Lever(lx[0], lx[1], 'K');
		this.defeat = false;
		this.victory = false;		
		this.gameMode = 1;
		Hero h1 = new Hero(hx[0], hx[1], 'H');
		this.h = h1;
		if (dif == 1) {
			Drunken d = new Drunken(gx[0], gx[1], 'D');
			this.g = d;
		} else if (dif == 2) {
			Rookie d = new Rookie(gx[0], gx[1], 'R');
			this.g = d;
		} else if (dif == 3) {
			Suspicious d = new Suspicious(gx[0], gx[1], 'S');
			this.g = d;
		}
		this.g.setMovs(this.g.movsPossibility);
		//o = new Ogre[(og[2])];
		for (int i = 0; i < og[2]; i++) {
			Ogre o1 = new Ogre(og[0], og[1], 'O', '*');
			o.add(o1);
		}
		this.getMapa().mapSetGameMode(this.getLever(), this.getHero(), this.getGuard(), null, null);
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
		int[] oPos = o.getPos(); int randomNum;
		while (true) {
			randomNum = ThreadLocalRandom.current().nextInt(1, 5);
			if (randomNum == 1) { //w
				if (map[oPos[1] - 1][oPos[0]] == 'X' || map[oPos[1] - 1][oPos[0]] == 'I' || map[oPos[1] - 1][oPos[0]] == 'S' || map[oPos[1] - 1][oPos[0]] == h.getImage()) {
					continue; }
				else {
					clearSpaceMoveChar(o.getPos(), new int[] {0, -1}, o, 'w');
					break; }
			} else if (randomNum == 2) { //d
				if (map[oPos[1]][oPos[0] + 1] == 'X' || map[oPos[1]][oPos[0] + 1] == 'I' || map[oPos[1]][oPos[0]+1] == 'S'||  map[oPos[1] ][oPos[0]+1] == h.getImage()) {
					continue; }
				else {
					clearSpaceMoveChar(o.getPos(), new int[] {1,0}, o, 'd');
					break; }
			} else if (randomNum == 3) { //s
				if (map[oPos[1] + 1][oPos[0]] == 'X' || map[oPos[1] + 1][oPos[0]] == 'I'|| map[oPos[1] + 1][oPos[0]] == 'S' || map[oPos[1] + 1][oPos[0]] == h.getImage()) {
					continue; }
				else {
					clearSpaceMoveChar(o.getPos(), new int[] {0,1}, o, 's');
					break; }
			} else if (randomNum == 4 ) { //a
				if (map[oPos[1]][oPos[0] - 1] == 'X' || map[oPos[1]][oPos[0] - 1] == 'I' || map[oPos[1]][oPos[0]-1] == 'S' || map[oPos[1]][oPos[0] - 1] == h.getImage()) {
					continue; }
				else {
					clearSpaceMoveChar(o.getPos(), new int[] {-1,0}, o, 'a');
					break; }
			}
		}
	}
	
	public void clearSpaceMoveChar(int[] coord, int[] xy, Character o, char symbol) {
		this.mapa.getMap()[coord[1]][coord[0]] = ' ';
		o.setX(o.getPos()[0] + xy[0]);
		o.setY(o.getPos()[1] + xy[1]);
		o.changeBuffImage(symbol);
	}

	public void moveOgres() {
		for (int i = 0; i < o.size(); i++) {
			if (o.get(i).getStun() > 0) {
				o.get(i).setStun(o.get(i).getStun() - 1);
			} else {
				action(o.get(i), this.mapa.getMap());
				this.mapa.getMap()[o.get(i).getClub().getPos()[1]][o.get(i).getClub().getPos()[0]] = ' ';
				o.get(i).getClub().getPos()[0] = o.get(i).getPos()[0];
				o.get(i).getClub().getPos()[1] = o.get(i).getPos()[1];
				action(o.get(i).getClub(), this.mapa.getMap());
			}
		}
	}

	public boolean checkClosedDoor(int posy, int posx) {
		if (this.getMapa().getMap()[posy][posx] == 'I') {
			return true;
		}
		return false;
	}

	public boolean checkVictory(int posy, int posx, char order)
	{   
		if(order == 'w')
		{
			if(posy - 1 < 0)
				return true;
			else return false;			
		}	
		else if(order == 'a')
		{
			if(posx - 1 < 0)
				return true;
			else return false;
		}
		else if(order == 's')
		{
			if(posy + 1 >= mapa.getMap().length )
				return true;
			else return false;
		}
		else if(order == 'd')
		{
			for (int i = 0; i < this.mapa.getMap().length; i++) {
				if(posx+1 >=this.mapa.getMap()[i].length)
					return true;
			}
			return false;
		}
		else
			return false;
	}
	
	

	public boolean checkOpenDoor(int posy, int posx, char order) {
		System.out.println("posx" + posx);
		if (this.mapa.getMap()[posy][posx] == 'S') {
			if (order == 'w') {
				if (checkVictory(posy, posx, 'w')) {
					this.victory = true;
					return true;
				}
				if (this.mapa.getMap()[posy - 1][posx] == ' ')
					return true;
				else
					return false;
			} else if (order == 'a') {
				if (checkVictory(posy, posx, 'a')) {
					this.victory = true;
					return true;
				}
				if (this.mapa.getMap()[posy][posx - 1] == ' ')
				{
					return true;
				}
				
				else
					return false;
			} else if (order == 's') {
				if (checkVictory(posy, posx, 's')) {
					this.victory = true;
					return true;
				}

				if (this.mapa.getMap()[posy + 1][posx] == ' ')
					return true;
				else
					return false;
			} else if (order == 'd') {
				if (checkVictory(posy, posx, 'd')) {
					this.victory = true;
					return true;
				}

				if (this.mapa.getMap()[posy][posx + 1] == ' ')
					return true;
				else
					return false;
			} else
				return false;
		} else
			return false;
	}

	public boolean checkWall(int posy, int posx) {
		if (this.mapa.getMap()[posy][posx] == 'X' || this.mapa.getMap()[posy][posx] == g.getImage()
				|| this.mapa.getMap()[posy][posx] == o.get(0).getImage()
				|| this.mapa.getMap()[posy][posx] == o.get(0).getClub().getImage()) {
			return true;
		}

		else
			return false;
	}

	public boolean checkLever(int posy, int posx) {
		if (this.mapa.getMap()[posy][posx] == l.getImage()) {
			if (l.getPressed() == false) {
				l.setPressed(true);
				l.changeBuffImage('a');
				return true;
			}
			else {
				l.setPressed(false);
				l.changeBuffImage('a');
				return true;
			}
		}
		else
			return false;
	}

	public boolean checkKey(int posy, int posx) {
		if (this.mapa.getMap()[posy][posx] == k.getImage()) {
			k.setPickedUp(true);
			k.changeBuffImage('a');
			h.setHasKey(true);
			h.changeBuffImage('k');
			return true;
		}

		else
			return false;
	}
	
	public boolean checkEmpty(int posy, int posx)
	{
		if (this.mapa.getMap()[posy][posx] == ' ') {
			return true;
		}
		else
			return false;
	}
		
	public boolean checkOgre(int posy, int posx)
	{
		for(int i = 0; i < o.size(); i++){
			if (this.mapa.getMap()[posy][posx] == o.get(i).getImage()) {
				return true;
			}
		}
		return false;
	}
	public boolean moveHero2(int[] xy, char order) throws CloneNotSupportedException {
		if(checkEmpty(h.getPos()[1] + xy[1], h.getPos()[0] + xy[0])) {
			this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
			h.getPos()[1] = h.getPos()[1] + xy[1];
			h.getPos()[0] = h.getPos()[0] + xy[0];
			return true;
		}
		else if (checkWall(h.getPos()[1] + xy[1], h.getPos()[0] + xy[0]))
			return false;
		else if (checkOgre(h.getPos()[1] + xy[1], h.getPos()[0] + xy[0]))
			return false;
		else if (checkClosedDoor(h.getPos()[1] + xy[1], h.getPos()[0] + xy[0])) {
			if (k.isPickedUp()) {
				k.setUsed(true);
				this.mapa.getMap()[h.getPos()[1] + xy[1]][h.getPos()[0]+ xy[0]] = 'S';
				this.mapa.getMapChar()[h.getPos()[1] + xy[1]][h.getPos()[0] + xy[0]].setIm('S');
				this.mapa.setMapChar();
				this.mapa.getMapChar()[h.getPos()[1] + xy[1]][h.getPos()[0]+ xy[0]].changeBuffImage('a');
			}
			return false;
		}
		else if (checkOpenDoor(h.getPos()[1] + xy[1], h.getPos()[0] + xy[0], order)) {
			this.mapa.getMap()[h.getPos()[1]][h.getPos()[0]] = ' ';
			h.getPos()[1] = h.getPos()[1] + (2*xy[1]);
			h.getPos()[0] = h.getPos()[0] + (2*xy[0]);
			return true;
		}
		else if (checkLever(h.getPos()[1] + xy[1], h.getPos()[0] + xy[0])) {
			this.mapa.changeDoors();
			return false;
		}
		else if (checkKey(h.getPos()[1] + xy[1], h.getPos()[0] + xy[0])) {
			Key test = (Key) k.clone();
			h.setIm(test.getImage());
			this.getMapa().getMap()[k.getPos()[1]][k.getPos()[0]] = ' ';
			return false;
		}
		else return false;
	}
	
	public boolean moveHero(char order) throws CloneNotSupportedException {
		if (order == 'w') {
			h.changeBuffImage('w');
			return moveHero2(new int[] {0,-1}, order);
		} else if (order == 'a') {
			h.changeBuffImage('a');
			return moveHero2(new int[] {-1,0}, order);
		} else if (order == 's') {
			h.changeBuffImage('s');
			return moveHero2(new int[] {0,1}, order);
		} else if (order == 'd') {
			h.changeBuffImage('d');
			return moveHero2(new int[] {1,0}, order);
		}
		else return false;
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
				this.getMapa().mapSetGameMode(this.getLever(), this.getHero(), this.getGuard(),null, null);
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
		if(this.getVictory()) {
			this.victory = false;
			this.defeat = false;
			this.victoryGuard = true;
			this.mapa.setMap(this.mapa.mapOgre);
			this.setGMode(2);
			this.h.setX(1);
			this.h.setY(8);
			this.getMapa().mapSetGameMode(null, this.getHero(), null, this.getOgres(), this.getK());
			return true;
		}
		else if (this.getDefeat()) {
			this.getMapa().mapSetGameMode(this.getLever(), this.getHero(), this.getGuard(),null, null);
			return false;
		}	
		this.getMapa().mapSetGameMode(this.getLever(), this.getHero(), this.getGuard(),null, null);
		return true;
	}
	
	public boolean updateGameMode2(char order) {
		try {
			if(this.moveHero(order) == false) {
				this.getMapa().mapSetGameMode(null, this.getHero(), null, this.getOgres(), this.getK());
				return true;
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		if (moveAndTestOgres())
			return true;
		if (this.getDefeat()) {
			this.getMapa().mapSetGameMode(null, this.getHero(), null, this.getOgres(), this.getK());
			return false;
		}
		this.getMapa().mapSetGameMode(null, this.getHero(), null, this.getOgres(), this.getK());
		return true;
	}
	
	public boolean moveAndTestOgres() {
		this.moveOgres();
		this.ogreOnKey();
		this.heroNearOgre();
		if(this.getVictory()) {
			this.victoryOgre = true;
			return true;
		}
		return false;
	}
	
	public boolean updateGameMode(char order) {
		this.setGuard(new Rookie(100,100,'G'));
		try {
			if(this.moveHero(order) == false) {
				this.getMapa().mapSetGameModeSelfMap(this.getLever(), this.getHero(), this.getOgres(), this.getK());
				return true;
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		if(moveAndTestOgres())
			return true;
		if (this.getDefeat()) {
			this.getMapa().mapSetGameModeSelfMap(this.getLever(), this.getHero(), this.getOgres(), this.getK());
			return false;
		}
		this.getMapa().mapSetGameModeSelfMap(this.getLever(), this.getHero(), this.getOgres(), this.getK());
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
