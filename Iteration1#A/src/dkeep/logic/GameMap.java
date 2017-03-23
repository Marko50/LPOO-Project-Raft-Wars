package dkeep.logic;

public class GameMap {
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
	
	char[] l0a = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
	char[] l1a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l2a = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l3a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l4a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l5a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l6a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l7a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
	char[] l8a = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' };
	char[] l9a = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
	
	public char[][] mapGuard = {l0,l1,l2,l3,l4,l5,l6,l7,l8,l9};	
	public char[][] mapOgre = {l0a,l1a,l2a,l3a,l4a,l5a,l6a,l7a,l8a,l9a};
	
	
	private char[][] map;
	private Character[][] mapChar;
	
	
	public GameMap()
	{
		
	}
	public GameMap(char[][] map1)
	{
		this.map = map1;
		mapChar =   new Character[map.length][map[0].length];
		this.setMapChar();
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
		mapChar = new Character[map.length][map[0].length];
		this.setMapChar();
	}
	
	public void setPos(int x, int y, char im)
	{
		map[y][x] = im;
		this.setMapChar();
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
		this.setMapChar();
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
	public Character[][] getMapChar() {
		return mapChar;
	}
	public void setMapChar() {
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				if(map[i][j] == 'X')
				{
					Wall w = new Wall(j,i,'X');
					mapChar[i][j] = w;
				}
				
				else if(map[i][j] == 'I')
				{
					Door w = new Door(j,i,'X',false);
					mapChar[i][j] = w;
				}
				
				else if(map[i][j] == 'S')
				{
					Door w = new Door(j,i,'X',true);
					mapChar[i][j] = w;
				}
						
				else if(map[i][j] == ' ')
				{
					Floor w = new Floor(j,i,' ');
					mapChar[i][j] = w;
				}
			}
		}
		
		
	}
}
