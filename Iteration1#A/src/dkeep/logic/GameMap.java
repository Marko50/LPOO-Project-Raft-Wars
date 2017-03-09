package dkeep.logic;

public class GameMap {
	private char[][] map;
	
	public GameMap(char[][] map1)
	{
		this.map = map1;
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

}
