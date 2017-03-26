package dkeep.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

/**  
 * GameState.java - Class with the state of the game at the moment
 */ 
public class GameState {
	GameMap mapa;
	private Hero h;
	private Guard g;
	ArrayList<Ogre> o = new ArrayList<Ogre>();
	private Lever l;
	private Key k;
	private int gameMode;
	private boolean victory, victoryGuard, victoryOgre, defeat;

	/**
	 * Default constructor of GameState
	 */
	public GameState() {
	}

	/**
	 * Adds an ogre to ogre arraylist
	 * @param og Ogre to be added
	 */
	public void addOgre(Ogre og) {
		o.add(og);
	}

	/**
	 * Set victory
	 * @param vic boolean representing hero's victory
	 */
	public void setVictory(boolean vic) {
		victory = vic; 
	}

	/**
	 * Retrieve victory
	 * @return true if hero won
	 */
	public boolean getVictory() { 
		return this.victory; 
	}

	/**
	 * Retrieve whether or not ogre is on key
	 * @param o ogre
	 * @return true if ogre is standing on the key
	 */
	public boolean isOgreOnKey(Ogre o) {
		if (o.getPos()[0] == k.getPos()[0] && o.getPos()[1] == k.getPos()[1])
			return true;
		else
			return false;
	}

	/**
	 * Changes ogre's image according to whether or not ogre is standing on the key
	 */
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

	/**
	 * Stuns the ogre if the hero is next to him
	 * @param i index of ogre to stun
	 * @return true if ogre was stunned
	 */
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

	/**
	 * Kills Hero if ogre's club is next to hero
	 * @param i index of ogre
	 * @return true if hero was killed
	 */
	public boolean ogreKillsHero(int i) {
		int[] oPos = o.get(i).getPos();
		int[] hPos = h.getPos();
		int[] cPos = o.get(i).getClub().getPos();
		if (o.get(i).getStun() > 0)
			return false;
		else if (((cPos[0] == hPos[0] - 1 || cPos[0] == hPos[0] + 1) && cPos[1] == hPos[1])
				|| ((cPos[1] == hPos[1] - 1 || cPos[1] == hPos[1] + 1) && cPos[0] == hPos[0])
				|| (cPos[0] == hPos[0] && cPos[1] == hPos[1])) {
			return true;
		}
		else if (oPos[0] == hPos[0] && oPos[1] == hPos[1])
			return true;
		else return false;
	}

	/**
	 * For every ogre, check if hero is killed or if he stuns the ogre
	 * @return true if hero is killed
	 */
	public boolean heroNearOgre() {
		boolean ret = false;
		for (int i = 0; i < o.size(); i++) {
			if (ogreKillsHero(i)) {
				this.defeat = true;
				ret = true;
			}
			if (this.stunOgre(i)) {
				o.get(i).setStun(3);
				ret = false;
			}
		}
		return ret;
	}

	/**
	 * Checks if hero is near guard and kills hero if he is
	 * @return true if hero is killed
	 */
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

	/**
	 * Retrieve gamemode
	 * @return integer representing the gamemode
	 */
	public int getGMode() {
		return this.gameMode;
	}

	/**
	 * Sets gamemode
	 * @param gmode integer of gamemode
	 */
	public void setGMode(int gmode) {
		this.gameMode = gmode;
	}

	/**
	 * Retrieve hero
	 * @return Hero 
	 */
	public Hero getHero() {
		return this.h;
	}

	/**
	 * Sets Hero
	 * @param h1 Hero to be set
	 */
	public void setHero(Hero h1) {
		this.h = h1;
	}

	/**
	 * Retrieve the guard
	 * @return Guard
	 */
	public Guard getGuard() {
		return this.g;
	}

	/**
	 * Sets Guard
	 * @param g1 guard to be set
	 */
	public void setGuard(Guard g1) {
		this.g = g1;
	}

	/**
	 * Set Ogres
	 * @param og Arraylist of ogres to be set
	 */
	public void setO(ArrayList<Ogre> og) {
		this.o = og;
	}

	/**
	 * Retrieve ogres
	 * @return Arraylist of ogres
	 */
	public ArrayList<Ogre> getOgres() {
		return this.o;
	}

	/**
	 * Retrieve lever
	 * @return Lever
	 */
	public Lever getLever() {
		return this.l;
	}

	/**
	 * Sets lever
	 * @param l1 lever to be set
	 */
	public void setLever(Lever l1) {
		this.l = l1;
	}

	/**
	 * Constructor of GameState
	 * @param hx coordinates of hero
	 * @param gx coordinates of guard
	 * @param dif difficulty/type of guard
	 * @param lx coordinates of lever
	 * @param kx coordinates of key
	 * @param og coordinates and number of ogres
	 */
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
			Suspicious d = new Suspicious(gx[0], gx[1], 's');
			this.g = d;
		}
		this.g.setMovs(this.g.movsPossibility);
		for (int i = 0; i < og[2]; i++) {
			Ogre o1 = new Ogre(og[0], og[1], 'O', '*');
			o.add(o1);
		}
		this.getMapa().mapSetGameMode(this.getLever(), this.getHero(), this.getGuard(), null, null);
		this.victoryGuard = false;
		this.victoryOgre = false;
	}

	/**
	 * Print out the map
	 */
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


	/**
	 * Checks if a move is possible
	 * @param o Character to be moved
	 * @param xy Distance moved in each coordinate
	 * @param map game map
	 * @return true if move is impossible
	 */
	public boolean ActionFalseMove(Character o, int[] xy, char[][] map) {
		int[] oPos = o.getPos();
		if (map[oPos[1] + xy[1]][oPos[0]+ xy[0]] == 'X' || map[oPos[1] + xy[1]][oPos[0]+ xy[0]] == 'I' || map[oPos[1] + xy[1]][oPos[0]+ xy[0]] == 'S' || map[oPos[1] + xy[1]][oPos[0]+ xy[0]] == h.getImage()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Moves a character, if possible
	 * @param o Character to be moved
	 * @param map game map
	 */
	public void action(Character o, char[][] map) {
		int randomNum;
		while (true) {
			randomNum = ThreadLocalRandom.current().nextInt(1, 5);
			if (randomNum == 1) { //w
				if (ActionFalseMove(o, new int[] {0,-1}, map)) continue;
				else {
					clearSpaceMoveChar(o.getPos(), new int[] {0, -1}, o, 'w');
					break; }
			} else if (randomNum == 2) { //d
				if (ActionFalseMove(o, new int[] {1,0}, map)) continue;
				else {
					clearSpaceMoveChar(o.getPos(), new int[] {1,0}, o, 'd');
					break; }
			} else if (randomNum == 3) { //s
				if (ActionFalseMove(o, new int[] {0,1}, map)) continue;
				else {
					clearSpaceMoveChar(o.getPos(), new int[] {0,1}, o, 's');
					break; }
			} else if (randomNum == 4 ) { //a
				if (ActionFalseMove(o, new int[] {-1,0}, map)) continue;
				else {
					clearSpaceMoveChar(o.getPos(), new int[] {-1,0}, o, 'a');
					break; }
			}
		}
	}

	/**
	 * Moves a character to its new position and clears its previous position
	 * @param coord previous position
	 * @param xy distance moved in each coordinate
	 * @param o character to be moved
	 * @param symbol char representing char
	 */
	public void clearSpaceMoveChar(int[] coord, int[] xy, Character o, char symbol) {
		this.mapa.getMap()[coord[1]][coord[0]] = ' ';
		o.setX(o.getPos()[0] + xy[0]);
		o.setY(o.getPos()[1] + xy[1]);
		o.changeBuffImage(symbol);
	}

	/**
	 * Moves every ogres in a random direction if they are not stunned
	 */
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

	/**
	 * Checks if a door is closed
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @return true of door is closed
	 */
	public boolean checkClosedDoor(int posy, int posx) {
		if (this.getMapa().getMap()[posy][posx] == 'I') {
			return true;
		}
		return false;
	}

	/**
	 * Checks if hero is walking through a door that will lead outside the dungeon
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @param order char representing direction of movement
	 * @return true if hero wins
	 */
	public boolean checkVictory(int posy, int posx, char order) {   
		if(order == 'w') 
			return (posy - 1 < 0);	
		else if(order == 'a')
			return (posx - 1 < 0);
		else if(order == 's')
			return (posy + 1 >= mapa.getMap().length );
		else if(order == 'd') {
			for (int i = 0; i < this.mapa.getMap().length; i++) {
				if(posx+1 >=this.mapa.getMap()[i].length)
					return true;
			}
		}
		return false;
	}

	/**
	 * Checks if hero is moving into an open door
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @param order char representing direction of movement
	 * @return true if hero moved through an open door
	 */
	public boolean checkOpenDoor(int posy, int posx, char order) {
		if (this.mapa.getMap()[posy][posx] == 'S') {
			if (order == 'w') {
				return checkOpenDoorW(posy, posx);
			} else if (order == 'a') {
				return checkOpenDoorA(posy, posx);
			} else if (order == 's') {
				return checkOpenDoorS(posy, posx);
			} else if (order == 'd') {
				return checkOpenDoorD(posy, posx);
			} else return false;
		} else return false;
	}

	/**
	 * Checks if hero is moving into an open door up from him
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @return true if hero moved through an open door
	 */
	public boolean checkOpenDoorW(int posy, int posx) {
		if (checkVictory(posy, posx, 'w')) {
			this.victory = true;
			return true; 
		}
		if (this.mapa.getMap()[posy - 1][posx] == ' ') 
			return true;
		else return false;
	}
	/**
	 * Checks if hero is moving into an open door to his left
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @return true if hero moved through an open door
	 */
	public boolean checkOpenDoorA(int posy, int posx) {
		if (checkVictory(posy, posx, 'a')) {
			this.victory = true;
			return true; 
		}
		if (this.mapa.getMap()[posy][posx - 1] == ' ') 
			return true;
		else return false;
	}
	/**
	 * Checks if hero is moving into an open door down from him
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @return true if hero moved through an open door
	 */
	public boolean checkOpenDoorS(int posy, int posx) {
		if (checkVictory(posy, posx, 's')) {
			this.victory = true;
			return true; 
		}
		if (this.mapa.getMap()[posy + 1][posx] == ' ') 
			return true;
		else return false;
	}

	/**
	 * Checks if hero is moving into an open door to his right
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @return true if hero moved through an open door
	 */
	public boolean checkOpenDoorD(int posy, int posx) {
		if (checkVictory(posy, posx, 'd')) {
			this.victory = true;
			return true; 
		}
		if (this.mapa.getMap()[posy][posx + 1] == ' ') 
			return true;
		else return false;
	}

	/**
	 * Checks for a wall
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @return true if there's a wall
	 */
	public boolean checkWall(int posy, int posx) {
		if (this.mapa.getMap()[posy][posx] == 'X' || this.mapa.getMap()[posy][posx] == g.getImage()
				|| this.mapa.getMap()[posy][posx] == o.get(0).getImage()
				|| this.mapa.getMap()[posy][posx] == o.get(0).getClub().getImage()) {
			return true;
		}
		else return false;
	}

	/**
	 * Checks for a lever and presses it
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @return true if there's a lever
	 */
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
		else return false;
	}

	/**
	 * Checks if there's a key and picks it up
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @return true if there's a key
	 */
	public boolean checkKey(int posy, int posx) {
		if (this.mapa.getMap()[posy][posx] == k.getImage()) {
			k.setPickedUp(true);
			k.changeBuffImage('a');
			h.setHasKey(true);
			h.changeBuffImage('k');
			return true;
		}
		else return false;
	}

	/**
	 * Checks if there's an empty tile
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @return true if tile is empty
	 */
	public boolean checkEmpty(int posy, int posx) {
		if (this.mapa.getMap()[posy][posx] == ' ') {
			return true;
		}
		else return false;
	}

	/**
	 * Check if there's an ogre
	 * @param posy y coordinate
	 * @param posx x coordinate
	 * @return true if there's an ogre
	 */
	public boolean checkOgre(int posy, int posx) {
		for(int i = 0; i < o.size(); i++){
			if (this.mapa.getMap()[posy][posx] == o.get(i).getImage()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Moves the hero, if possible, after checking for walls, enemies or any other obstacle
	 * @param xy distance moved in each coordinate
	 * @param order char representing direction of movement
	 * @return true if hero moved successfully
	 * @throws CloneNotSupportedException
	 */
	public boolean moveHero2(int[] xy, char order) throws CloneNotSupportedException {
		h.changeBuffImage(order);
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

	/**
	 * Receives an order to move the hero in a certain direction, calls moveHero2 with the correct movement
	 * @param order char representing direction of movement
	 * @return true if hero moved successfully
	 * @throws CloneNotSupportedException
	 */
	public boolean moveHero(char order) throws CloneNotSupportedException {
		if (order == 'w') {
			return moveHero2(new int[] {0,-1}, order);
		} else if (order == 'a') {
			return moveHero2(new int[] {-1,0}, order);
		} else if (order == 's') {
			return moveHero2(new int[] {0,1}, order);
		} else if (order == 'd') {
			return moveHero2(new int[] {1,0}, order);
		}
		else return false;
	}
	
	/**
	 * Retrieve mapa
	 * @return GameMap
	 */
	public GameMap getMapa() {
		return mapa;
	}

	/**
	 * Sets GameMap
	 * @param mapa GameMap to be set
	 */
	public void setMapa(GameMap mapa) {
		this.mapa = mapa;
	}

	/** 
	 * Retrieve defeat
	 * @return true if hero has lost
	 */
	public boolean getDefeat() {
		return defeat;
	}

	/**
	 * Sets defeat
	 * @param defeat whether or not hero has lost
	 */
	public void setDefeat(boolean defeat) {
		this.defeat = defeat;
	}

	/**
	 * Retrieve key
	 * @return key
	 */
	public Key getK() {
		return k;
	}

	/**
	 * Sets key
	 * @param k key to be set
	 */
	public void setK(Key k) {
		this.k = k;
	}
	int contador = 0;
	
	/**
	 * Updates first level of dungeon
	 * @param order char representing hero's direction of movement
	 * @return false if hero lost
	 */
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
			changeGameMode2();
			return true;
		}
		else if (this.getDefeat()) {
			this.getMapa().mapSetGameMode(this.getLever(), this.getHero(), this.getGuard(),null, null);
			return false;
		}	
		this.getMapa().mapSetGameMode(this.getLever(), this.getHero(), this.getGuard(),null, null);
		return true;
	}
	
	/**
	 * Changes gamemode and gamemap to second floor of dungeon
	 */
	public void changeGameMode2() {
		this.victory = false;
		this.defeat = false;
		this.victoryGuard = true;
		this.mapa.setMap(this.mapa.mapOgre);
		this.setGMode(2);
		this.h.setX(1);
		this.h.setY(8);
		this.getMapa().mapSetGameMode(null,  this.getHero(), null, this.getOgres(), this.getK());
	}

	/**
	 * Updates second level of the dungeon
	 * @param order char representing direction of hero's movement
	 * @return false if hero lost
	 */
	public boolean updateGameMode2(char order) {
		try { if(this.moveHero(order) == false) {
			this.getMapa().mapSetGameMode(null, this.getHero(), null, this.getOgres(), this.getK());
			return true;
		}
		} catch (CloneNotSupportedException e) { e.printStackTrace(); }
		if (moveAndTestOgres())
			return true;
		if (this.getDefeat()) {
			this.getMapa().mapSetGameMode(null, this.getHero(), null, this.getOgres(), this.getK());
			return false;
		}
		this.getMapa().mapSetGameMode(null, this.getHero(), null, this.getOgres(), this.getK());
		return true;
	}

	/**
	 * Moves ogres and checks if they are on top of key or near the hero
	 * @return true if ogres killed hero
	 */
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
	
	/**
	 * Updates custom game mode
	 * @param order char representing direction of hero movement
	 * @return false if hero loses
	 */
	public boolean updateGameMode(char order) {
		this.setGuard(new Rookie(100,100,'G'));
		try {
			if(this.moveHero(order) == false) {
				this.getMapa().mapSetGameMode(this.getLever(), this.getHero(),null, this.getOgres(), this.getK());
				return true; }
		} catch (CloneNotSupportedException e) { e.printStackTrace(); }
		if (moveAndTestOgres())
			return true;
		if (this.getDefeat()) {
			this.getMapa().mapSetGameMode(this.getLever(), this.getHero(),null, this.getOgres(), this.getK());
			return false; }
		this.getMapa().mapSetGameMode(this.getLever(), this.getHero(),null, this.getOgres(), this.getK());
		return true;
	}

	/**
	 * Retrieve victoryGuard
	 * @return true if guard killed hero
	 */
	public boolean isVictoryGuard() {
		return victoryGuard;
	}
	/**
	 * Sets victoryGuard
	 * @param victoryGuard whether or not guard killed hero
	 */
	public void setVictoryGuard(boolean victoryGuard) {
		this.victoryGuard = victoryGuard;
	}

	/**
	 * Retrieve victoryOgre
	 * @return true if ogre killed hero
	 */
	public boolean isVictoryOgre() {
		return victoryOgre;
	}

	/**
	 * Sets victoryOgre
	 * @param victoryOgre whether or not ogre killed hero
	 */
	public void setVictoryOgre(boolean victoryOgre) {
		this.victoryOgre = victoryOgre;
	}
}
