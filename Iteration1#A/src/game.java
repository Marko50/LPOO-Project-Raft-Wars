import java.util.Scanner;


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

	public static char[][] MazeCreator(int[] hPos, int[]gPos)
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
		
		char[] l1 = {'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ' ,' ', 'X' };
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
		
		res[hPos[0]][hPos[1]] = 'H';
		res[gPos[0]][gPos[1]] = 'G';
		
		return res;
		
	}
	
	public static void ChangeMaze(char[][] maze)
	{
		int[] hPos = {1,1};
		int[] gPos = {1,8};
		
		boolean flagVictory = false;
		boolean flagDefeat = false;
		boolean lever = false;
		Scanner s = new Scanner(System.in);
		String order;
		while(!flagVictory && !flagDefeat)
		{
			System.out.println("Your move. Choose wisely");
			showMaze(MazeCreator(hPos, gPos));
			while(true)
			{
				order = s.next();
				if(order == "w")
				{
					if(maze[hPos[0]][hPos[1]-1] == 'W')
					{
						System.out.println("You triggered feminist.. Only men can do that");
						continue;
					}
					else if(maze[hPos[0]][hPos[1]-1] == 'I' && !lever )
					{
						System.out.println("You shall not pass!");
						continue;
					}
					
				}
				else if(order == "a")
				{
					
				}
				else if(order == "s")
				{
					
				}
				else if(order == "d")
				{
					
				}
			}
			
			
		}
		
		
	}
	
	
	public static void main(String[] args) {
		System.out.println("Olá Universo");
		
	
		
	}

}
