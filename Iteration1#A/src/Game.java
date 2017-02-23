import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Game {
	
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

	public static char[][] MazeCreator2(char[][] res, int[]hPos, int[]oPos, boolean lever, boolean near, boolean openDoor, int[] cPos)
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
			char[] l1 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l2 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l3 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l4 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l5 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l6 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l7 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l8 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'};
			
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
			char[] l1 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,'K', 'X' };
			char[] l2 = {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l3 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l4 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l5 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l6 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l7 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', 'X' };
			char[] l8 = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'};
			
			res[1] = l1;
			res[2] = l2;
			res[3] = l3;
			res[4] = l4;
			res[5] = l5;
			res[6] = l6;
			res[7] = l7;
			res[8] = l8;
		}
		res[cPos[1]][cPos[0]] = '*';
		
		if(openDoor)
			res[2][0] = 'S';
		
		if(lever)
		 res[hPos[1]][hPos[0]] = 'K';
		else
		 res[hPos[1]][hPos[0]] = 'H';
		
		if(oPos[0] == 8 && oPos[1] == 1 && res[1][8] == 'K')
			res[oPos[1]][oPos[0]] = '$';
		else
			res[oPos[1]][oPos[0]] = 'O';
		return res;
		
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
			char[] l8 = {'X', ' ', 'S', ' ', 'S', ' ', 'X', 'k', ' ', 'X'};
			
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
		int[] oPos = {7,1};
		int[] cPos = {7,2};
		boolean openDoor = false;
		char[][] maze = new char[10][10];
		boolean flagVictory = false;
		int contador = 0;
		boolean lever = false;
		Scanner s = new Scanner(System.in);
		char order;
		int gameMode = 1;
		boolean near = false;
		while(!flagVictory)
		{
				
			if(gameMode == 1)
			{
				if((((gPos[0] == hPos[0] - 1 || gPos[0] == hPos[0] + 1) && gPos[1] == hPos[1]) || ((gPos[1] == hPos[1] - 1 || gPos[1] == hPos[1] + 1) && gPos[0] == hPos[0])))
				{
					showMaze(MazeCreator(maze, hPos, gPos, lever));
					break;
				}
				showMaze(MazeCreator(maze, hPos, gPos, lever));
				guardMove(gPos, contador);
				contador++;
			} else {
				if ((((oPos[0] == hPos[0] - 1 || oPos[0] == hPos[0] + 1) && oPos[1] == hPos[1])
						|| ((oPos[1] == hPos[1] - 1 || oPos[1] == hPos[1] + 1) && oPos[0] == hPos[0]
								|| oPos[1] == hPos[1]) && oPos[0] == hPos[0] )) {
					showMaze(MazeCreator2(maze, hPos, oPos, lever, near, openDoor, cPos));
					break;
				}
				else if ((((cPos[0] == hPos[0] - 1 || cPos[0] == hPos[0] + 1) && cPos[1] == hPos[1])
						|| ((cPos[1] == hPos[1] - 1 || cPos[1] == hPos[1] + 1) && cPos[0] == hPos[0]
								|| cPos[1] == hPos[1]) && cPos[0] == hPos[0] )) {
					showMaze(MazeCreator2(maze, hPos, oPos, lever, near, openDoor, cPos));
					break;
				}
				showMaze(MazeCreator2(maze, hPos, oPos, lever, near, openDoor, cPos));
				orcMove(oPos, maze, near, cPos);
			}
			
			if(contador == 24)
				contador = 0;
			
			while(true)
			{
				System.out.println("Your move. Choose wisely");
				order = s.next().charAt(0);
				if(order == 'p')
				{
					gameMode = 2;
					break;
				}
					
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
						if(gameMode == 2)
							openDoor = true; 
						
						else
							System.out.println("You shall not pass!");
						
						continue;
					} else if (maze[hPos[1] - 1][hPos[0]] == 'S') {
						if (hPos[1] - 2 < 0 && gameMode == 1) {
							//flagVictory = true;
							gameMode = 2;
							lever = false;
							break;
						}
						else if (hPos[1] - 2 < 0 && gameMode == 2) {
							flagVictory = true;
							//gameMode = 2;
							break;
						}
						else if (maze[hPos[1] - 2][hPos[0]] == ' ') {
							System.out.println("You opened the door. Congratz. ");
							hPos[1] = hPos[1] - 2;
							break;
						} else {
							System.out.println("You triggered feminist.. Only men can do that");
							continue;
						}
					}
					
				
					else if(maze[hPos[1]-1][hPos[0]] == 'K')
					{
						System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
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
					else if(maze[hPos[1]][hPos[0]-1] == 'I')
					{
						if(gameMode == 2)
							openDoor = true; 
						
						else
							System.out.println("You shall not pass!");
						
						continue;
					}
					else if(maze[hPos[1]][hPos[0]-1] == 'S')
					{
						if (hPos[0] - 2 < 0 && gameMode == 1) {
							//flagVictory = true;
							gameMode = 2;
							lever = false;
							break;
						}
						else if (hPos[0] - 2 < 0 && gameMode == 2) {
							flagVictory = true;
							//gameMode = 2;
							break;
						}else if (maze[hPos[1]][hPos[0]-2] == ' ') {
							System.out.println("You opened the door. Congratz. ");
							hPos[0] = hPos[0] - 2;
							break;
						} else {
							System.out.println("You triggered feminist.. Only men can do that");
							continue;
						}
					}
					
					else if(maze[hPos[1]][hPos[0]-1] == 'K')
					{
						System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
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
					else if(maze[hPos[1]+1][hPos[0]] == 'I')
					{
						if(gameMode == 2)
							openDoor = true; 
						
						else
							System.out.println("You shall not pass!");
						
						continue;
					}
					else if(maze[hPos[1]+1][hPos[0]] == 'S')
					{
						if (hPos[1] + 2 > 10 && gameMode == 1) {
							//flagVictory = true;
							gameMode = 2;
							lever = false;
							break;
						}
						else if (hPos[1] + 2 > 10 && gameMode == 2) {
							flagVictory = true;
							//gameMode = 2;
							break;
						}
						else if (maze[hPos[1] + 2][hPos[0]] == ' ') {
							System.out.println("You opened the door. Congratz. ");
							hPos[1] = hPos[1] + 2;
							break;
						} else {
							System.out.println("You triggered feminist.. Only men can do that");
							continue;
						}
					}
					
					else if(maze[hPos[1]+1][hPos[0]] == 'K')
					{
						System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
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
					else if(maze[hPos[1]][hPos[0]+1] == 'I')
					{
						if(gameMode == 2)
							openDoor = true; 
						
						else
							System.out.println("You shall not pass!");
						
						continue;
					}
					else if(maze[hPos[1]][hPos[0]+1] == 'S')
					{
						if (hPos[0] + 2 > 10 && gameMode == 1) {
							//flagVictory = true;
							gameMode = 2;
							lever = false;
							break;
						} 
						else if (hPos[0] + 2 > 10 && gameMode == 2) {
							flagVictory = true;
							//gameMode = 2;
							break;
						} else if (maze[hPos[1]][hPos[0]+2] == ' ') {
							System.out.println("You opened the door. Congratz. ");
							hPos[0] = hPos[0] + 2;
							break;
						} else {
							System.out.println("You triggered feminist.. Only men can do that");
							continue;
						}
					}
					
					else if(maze[hPos[1]][hPos[0]+1] == 'K')
					{
						System.out.println("The doors will now be unlocked. The actual doors, not the band. Jesus Christ superstar..");
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
		if(flagVictory)
		{
			System.out.println("Wow you won. Maybe now you'll skip out of b5. NOT");
		}
		else
			System.out.println("You lost n00blord");
		
	}
	
	public static void action(int[] oPos, char[][]maze)
	{
		int randomNum;
		while(true)
		{
			randomNum = ThreadLocalRandom.current().nextInt(1,5);
			if(randomNum == 1)//w
			{
				if(maze[oPos[1]-1][oPos[0]] == 'X' || maze[oPos[1]-1][oPos[0]] == 'I')
				{
					continue;
				}
				else
				{
					oPos[1] = oPos[1]-1;
					break;
				}
					
				
			}
			else if(randomNum == 2)//d
			{
				if(maze[oPos[1]][oPos[0]+1] == 'X' || maze[oPos[1]][oPos[0]+1] == 'I')
				{
					continue;
				}
			
				else
				{
					oPos[0] = oPos[0]+1;
					break;
				}
				
			}
			else if(randomNum == 3)//s
			{
				if(maze[oPos[1]+1][oPos[0]] == 'X' || maze[oPos[1]+1][oPos[0]] == 'I')
				{
					continue;
				}
			
				else
				{
					oPos[1] = oPos[1]+1;
					break;
				}
				
			}
			else if(randomNum == 4)//a
			{
				if(maze[oPos[1]][oPos[0]-1] == 'X' || maze[oPos[1]][oPos[0]-1] == 'I')
				{
					continue;
				}
				else
				{
					oPos[0] = oPos[0]-1;
					break;
				}
				
			}
		}
		
	}
	
	public static void orcMove(int[]oPos, char[][] maze, boolean near, int[] cPos)
	{
		action(oPos, maze);
		cPos[0] = oPos[0];
		cPos[1] = oPos[1];
		action (cPos, maze);
	
	}
	
	public static void guardMove(int[] gPos, int contador)
	{
		char[] movs = {'a','s','s','s','s','a','a','a','a','a','a','s','d','d','d','d','d','d','d','w','w','w','w','w'};
		if(movs[contador] == 'w')
		{
			gPos[1] = gPos[1] - 1;
		}
		else if(movs[contador] == 'a')
		{
			gPos[0] = gPos[0] - 1;
		}
		else if(movs[contador] == 's')
		{
			gPos[1] = gPos[1] + 1;
		}
		else if(movs[contador] == 'd')
		{
			gPos[0] = gPos[0] + 1;
		}
		
		
	}
	
	public static void main(String[] args) {
		System.out.println("Olá Universo");
		environment();		
	}

}
