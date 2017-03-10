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
	
	public void changeDoors()
	{
		for(int i  = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				if(map[i][j] == 'I')
				{
					map[i][j] = 'S';
				}
			}
		}
	}

	public void mapSetGameMode1(Lever l, Hero h, Guard g) {
		map[l.getPos()[1]][l.getPos()[0]] = l.getImage();
		map[h.getPos()[1]][h.getPos()[0]] = h.getImage();
		map[g.getPos()[1]][g.getPos()[0]] = g.getImage();

	}

	public void mapSetGameMode2(Hero h, Ogre[] o, Key k) {
		map[h.getPos()[1]][h.getPos()[0]] = h.getImage();
		if(!k.isPickedUp())
			map[k.getPos()[1]][k.getPos()[0]] = k.getImage();
		for (int i = 0; i < o.length; i++) {
			map[o[i].getPos()[1]][o[i].getPos()[0]] = o[i].getImage();
			map[o[i].getClub().getPos()[1]][o[i].getClub().getPos()[0]] = o[i].getClub().getImage();
		}
	}
}
