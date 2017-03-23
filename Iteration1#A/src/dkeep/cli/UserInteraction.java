
package dkeep.cli;
import java.util.Scanner;
import dkeep.logic.*;


public class UserInteraction {

	public void StartGame() throws CloneNotSupportedException {
		System.out.println("Hello player. What kind of guard would you like to play against?");
		System.out.println("1. Drunken Guard");
		System.out.println("2. Rookie Guard");
		System.out.println("3. Suspicious Guard");

		Scanner s = new Scanner(System.in);
		char rder; 
		while(true) {
			rder = s.next().charAt(0);
			if (rder == '1') {
				Environment(1);
				break;
			}
			else if (rder == '2') {
				Environment(2);
				break;
			}
			else if (rder == '3') {
				Environment(3);
				break;
			}
		}
		s.close();
	}

	public int Environment(int difficulty) throws CloneNotSupportedException {
		GameState game = new GameState(1,1,8,1, difficulty,7,8,2,1,3,1,3);
		Scanner s = new Scanner(System.in);
		char order;
		game.showmap();
		while(game.isVictoryGuard() == false)
		{
			order = s.next().charAt(0);
			if(order == 'p')
			{
				break;
			}
			else {
				if(game.updateGameMode1(order) == false)
				{
					game.showmap();
					System.out.println("This isn't even my final form n00b");
					System.exit(1);
				}				
			}
			game.showmap();
		}
		
		while(game.isVictoryOgre() == false)
		{
			System.out.println("Your move. Choose wisely");
			order = s.next().charAt(0);
		
			if(game.updateGameMode2(order) == false)
			{
				game.showmap();
				System.out.println("You lost n00blord");
				System.exit(1);
			}
			game.showmap();
		}

		s.close();
		return 0;
	}


	public static void main(String[] args) throws CloneNotSupportedException {
		System.out.println("Olá Universo");
		new UserInteraction().StartGame(); 
	}
}