package dkeep.logic;

public class GameMap {
	private char[][] map;
	
	
	public GameMap(int sizeX, int sizeY, char[][] map1)
	{
		if(map1 != null)
		{
			map = map1;
		}
		else
		   this.map = new char[sizeY][sizeX];
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}
	
	

}
