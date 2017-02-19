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

	public static char[][] MazeCreator(char[][] res,int[] hPos, int[]gPos, boolean lever)
	{
		
		
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
		
		if(lever)
		{
			char[] l1 = {'X', ' ', ' ', ' ', 'S', ' ', 'X', ' ' ,' ', 'X' };
			char[] l2 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
			char[] l3 = {'X', ' ', 'S', ' ', 'S', ' ', 'X', ' ' ,' ', 'X' };
			char[] l4 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ' ,' ', 'X' };
			char[] l5 = {'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l6 = {'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l7 = {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X' ,' ', 'X' };
			char[] l8 = {'X', ' ', 'S', ' ', 'S', ' ', 'X', 'K', ' ', 'X'};
			
			res[1] = l1;
			res[2] = l2;
			res[3] = l3;
			res[4] = l4;
			res[5] = l5;
			res[6] = l6;
			res[7] = l7;
			res[8] = l8;
		}
		else
		{
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
		}
				
		res[hPos[1]][hPos[0]] = 'H';
		res[gPos[1]][gPos[0]] = 'G';
		
		return res;
		
	}
	
	public static void environment()
	{
		int[] hPos = {1,1};
		int[] gPos = {8,1};
		char[][] maze = new char[10][10];
		boolean flagVictory = false;
		boolean flagDefeat = false;
		boolean lever = false;
		Scanner s = new Scanner(System.in);
		char order;
		while(!flagVictory && !flagDefeat)
		{
			showMaze(MazeCreator(maze, hPos, gPos, lever));
			while(true)
			{
				System.out.println("Your move. Choose wisely");
				order = s.next().charAt(0);
				if(order == 'w')
				{
					System.out.println(order);
					if(maze[hPos[1]-1][hPos[0]] == 'X')
					{
						System.out.println("You triggered feminist.. Only men can do that");
						continue;
					}
					else if(maze[hPos[1]-1][hPos[0]] == 'I')
					{
						System.out.println("You shall not pass!");
						continue;
					}
					else if(maze[hPos[1]-1][hPos[0]] == 'S')
					{
						System.out.println("You opened the door. Congratz. ");
						hPos[1] = hPos[1] - 1;
						flagVictory = true;
						break;
					}
					
					else if(maze[hPos[1]-1][hPos[0]] == 'K')
					{
						System.out.println("You pulled a mysterious lever. Jesus Christ superstar..");
						lever = true;
						break;
					}
					else
					{
						System.out.println("Wow fascinating...You just moved..");
						hPos[1] = hPos[1] - 1;
						break;
					}
					
				}
				else if(order == 'a')
				{
					System.out.println(order);
					if(maze[hPos[1]][hPos[0]-1] == 'X')
					{
						System.out.println("You triggered feminist.. Only men can do that");
						continue;
					}
					else if(maze[hPos[1]][hPos[0]-1] == 'I' && !lever)
					{
						System.out.println("You shall not pass!");
						continue;
					}
					else if(maze[hPos[1]][hPos[0]-1] == 'S')
					{
						System.out.println("You opened the door. Congratz. ");
						hPos[0] = hPos[0] - 1;
						flagVictory = true;
						break;
					}
					
					else if(maze[hPos[1]][hPos[0]-1] == 'K')
					{
						System.out.println("You pulled a mysterious lever. Jesus Christ superstar..");
						lever = true;
						break;
					}
					else
					{
						System.out.println("Wow fascinating...You just moved..");
						hPos[0] = hPos[0] - 1;
						break;
					}
					
				}
				else if(order == 's')
				{
					System.out.println(order);
					if(maze[hPos[1]+1][hPos[0]] == 'X')
					{
						System.out.println("You triggered feminist.. Only men can do that");
						continue;
					}
					else if(maze[hPos[1]+1][hPos[0]] == 'I' && !lever)
					{
						System.out.println("You shall not pass!");
						continue;
					}
					else if(maze[hPos[1]+1][hPos[0]] == 'S')
					{
						System.out.println("You opened the door. Congratz. ");
						hPos[1] = hPos[1] + 1;
						flagVictory = true;
						break;
					}
					
					else if(maze[hPos[1]+1][hPos[0]] == 'K')
					{
						System.out.println("You pulled a mysterious lever. Jesus Christ superstar..");
						lever = true;
						break;
					}
					else
					{
						System.out.println("Wow fascinating...You just moved..");
						hPos[1] = hPos[1] + 1;
						break;
					}
					
				}
				else if(order == 'd')
				{
					System.out.println(order);
					if(maze[hPos[1]][hPos[0]+1] == 'X')
					{
						System.out.println("You triggered feminist.. Only men can do that");
						continue;
					}
					else if(maze[hPos[1]][hPos[0]+1] == 'I' && !lever)
					{
						System.out.println("You shall not pass!");
						continue;
					}
					else if(maze[hPos[1]][hPos[0]+1] == 'S')
					{
						System.out.println("You opened the door. Congratz. ");
						hPos[0] = hPos[0] + 1;
						flagVictory = true;
						break;
					}
					
					else if(maze[hPos[1]][hPos[0]+1] == 'K')
					{
						System.out.println("You pulled a mysterious lever. Jesus Christ superstar..");
						lever = true;
						break;
					}
					else
					{
						System.out.println("Wow fascinating...You just moved..");
						hPos[0] = hPos[0] + 1;
						break;
					}
					
				}
			}
			
			
		}
		
		
	}
	
	
	public static void main(String[] args) {
		System.out.println("Olá Universo");
		environment();		
	}

}
