
public class game {
	
	public static void showMaze(char[][] maze)
	{
		for(int i = 0; i < maze.length; i++)
		{
			for(int c = 0; c < maze[i].length; c++)
			{
				System.out.print(maze[i][c]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}

	public static char[][] MazeCreator()
	{
		char[][] res = new char[10][10];
		
		for(int i = 0; i < res.length; i++)
		{
			res[0][i] = 'X';
		}
		
		for(int i = 0; i < res.length; i++)
		{
			res[i][9] = 'X';
		}
		
		for(int i = 0; i < res.length; i++)
		{
			res[res.length - 1][i] = 'X';
		}
		
		char[] l1 = {'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ' ,'G', 'X' };
		char[] l2 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
		char[] l3 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ' ,' ', 'X' };
		char[] l4 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
		char[] l5 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
		char[] l6 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
		char[] l7 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X' ,' ', 'X' };
		char[] l8 = {'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'};
		
		res[1] = l1;
		res[2] = l2;
		res[3] = l3;
		res[4] = l4;
		res[5] = l5;
		res[6] = l6;
		res[7] = l7;
		res[8] = l8;
					
		return res;
		
	}
	
	public static void main(String[] args) {
		System.out.println("Olá Universo");
		
		showMaze(MazeCreator());
		
	}

}
