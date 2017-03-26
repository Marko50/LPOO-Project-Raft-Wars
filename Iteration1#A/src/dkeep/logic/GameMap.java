package dkeep.logic;

import java.util.ArrayList;


/**
 * Class for the game map
 */
public class GameMap {
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
	
	char[] l0a = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
	char[] l1a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l2a = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l3a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l4a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l5a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l6a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l7a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l8a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' };
	char[] l9a = {'X', 'X', 'X', 'X', 'X', 'S', 'X', 'X' ,'X', 'X' };	
	public char[][] mapGuard = {l0,l1,l2,l3,l4,l5,l6,l7,l8,l9};	
	public char[][] mapOgre = {l0a,l1a,l2a,l3a,l4a,l5a,l6a,l7a,l8a,l9a};	
	private char[][] map;
	private Character[][] mapChar;
	/**
	 * Default constructor
	 */
	public GameMap() {
	}

	/**
	 * Constructor of GameMap
	 * @param map1 array of array of chars representing every tile of the map
	 */
	public GameMap(char[][] map1) {
		this.map = map1;
		mapChar =   new Character[map.length][map[0].length];
		this.setMapChar();
	}

	/**
	 * Retrieve the game map of chars
	 * @return array of array of chars of game map
	 */
	public char[][] getMap() {
		return map;
	}

	/**
	 * Sets the game map of chars
	 * @param map game map of chars
	 */
	public void setMap(char[][] map) {
		this.map = map;
		mapChar = new Character[map.length][map[0].length];
		this.setMapChar();
	}
	
	/**
	 * Sets a certain tile of the map to im
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param im char to be set
	 */
	public void setPos(int x, int y, char im)
	{
		map[y][x] = im;
		this.setMapChar();
	}
	
	/**
	 * Changes doors from closed to open
	 */
	public void changeDoors() {
		for(int i  = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 'I') {
					map[i][j] = 'S';
				}
				else if(map[i][j] == 'S') {
					map[i][j] = 'I';
				}
			}
		}
		this.setMapChar();
		for(int i  = 0; i < mapChar.length; i++) {
			for(int j = 0; j < mapChar[i].length; j++) {
				if(mapChar[i][j].getImage() == 'S') {
					mapChar[i][j].changeBuffImage('a');
				}
			}
		}
	}
	/**
	 * Places lever, hero, guard, ogres and key on the map
	 * @param l lever
	 * @param h hero
	 * @param g guard
	 * @param o arraylist of ogres
	 * @param k key
	 */
	public void mapSetGameMode(Lever l, Hero h, Guard g, ArrayList<Ogre> o, Key k) {
		if (l != null) map[l.getPos()[1]][l.getPos()[0]] = l.getImage();
		if (h != null)
			map[h.getPos()[1]][h.getPos()[0]] = h.getImage();
		if (g != null)
			map[g.getPos()[1]][g.getPos()[0]] = g.getImage();
		if (k != null)
			if (!k.isPickedUp())
				map[k.getPos()[1]][k.getPos()[0]] = k.getImage();
		if(o != null)
		for (int i = 0; i < o.size(); i++) {
			map[o.get(i).getPos()[1]][o.get(i).getPos()[0]] = o.get(i).getImage();
			map[o.get(i).getClub().getPos()[1]][o.get(i).getClub().getPos()[0]] = o.get(i).getClub().getImage();
		}
	}

	/**
	 * Retrieve every tile in the map
	 * @return Array of arrays of character representing every tile in the map
	 */
	public Character[][] getMapChar() {
		return mapChar;
	}
	
	/**
	 * Sets a game map of characters
	 */
	public void setMapChar() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 'X') {
					Wall w = new Wall(j,i,'X');
					mapChar[i][j] = w;
				}
				else if(map[i][j] == 'I') {
					Door w = new Door(j,i,'I',false);
					mapChar[i][j] = w;
				}
				else if(map[i][j] == 'S') {
					Door w = new Door(j,i,'S',true);
					mapChar[i][j] = w;
				}		
				else if(map[i][j] == ' ') {
					Floor w = new Floor(j,i,' ');
					mapChar[i][j] = w;
				}
			}
		}
	}
}
