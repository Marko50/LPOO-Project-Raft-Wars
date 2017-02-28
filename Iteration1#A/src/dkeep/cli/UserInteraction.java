package dkeep.cli;
import java.util.Scanner;
import dkeep.logic.*;

public class UserInteraction {
	
	
	public void Environment(int difficulty)
	{
	    int contador;
		Scanner s = new Scanner(System.in);
		char order;		
		while(true)
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

	public int MainMenu()
	{
		int difficulty = 0;
		
		
		
		return difficulty;
	}
	
	
	public static void main(String[] args) {
		
      
	}

}
