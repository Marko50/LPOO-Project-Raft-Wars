package dkeep.logic;



public class GameState {

	private char[][] map;
	private Hero h;
	private Guard g;
	private Ogre[] o;
	private Lever l;
	private int gameMode;
	
	public char[][] mazeSet()
	{
				
		if(l.getPressed())
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
		}
		else
		{
			char[] l0 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			char[] l1 = {'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ' ,' ', 'X' };
			char[] l2 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
			char[] l3 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ' ,' ', 'X' };
			char[] l4 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
			char[] l5 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l6 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l7 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X' ,' ', 'X' };
			char[] l8 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'};
			char[] l9 = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ,'X', 'X' };
			
			map[1] = l1;
			map[2] = l2;
			map[3] = l3;
			map[4] = l4;
			map[5] = l5;
			map[6] = l6;
			map[7] = l7;
			map[8] = l8;
		}				
		map[hPos[1]][hPos[0]] = 'H';
		map[gPos[1]][gPos[0]] = 'G';
		return res;
	}
	
	public GameState(int gMode, int hx, int hy, int gx, int gy)
	{
		
		
	}
	
	public void showMaze() {
		for (int i = 0; i < map.length; i++) {
			for (int c = 0; c < map[i].length; c++) {
				System.out.print(map[i][c]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	
	
}
