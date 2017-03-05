
package dkeep.cli;
import java.util.Scanner;
import dkeep.logic.*;


public class UserInteraction {

	public void StartGame() {
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

	public int Environment(int difficulty) {
		GameState game = new GameState(1,1,1,8,1, difficulty);

		int contador = 0;
		Scanner s = new Scanner(System.in);
		char order;
		while(game.getVictory() == false)
		{
			game.mapSet();
			game.showmap();
			System.out.println("Your move. Choose wisely");
			order = s.next().charAt(0);
			if(order == 'p')
			{
				break;
			}
			else {
				if (!game.moveHero(order)) {
					continue;	
				}
				game.getGuard().gMove(contador);
				if(game.getGuard().getSleep() == 0)
					contador = contador + game.getGuard().getDirection();
				if (contador == 24 && game.getGuard().getDirection() == 1)
					contador = 0;
				else if (contador == 0 && game.getGuard().getDirection() == -1) {
					contador = 24;
				}
			}
			game.heroNearGuard();
			if(game.getDefeat())
			{
				game.mapSet();
				game.showmap();
				System.out.println("This isn't even my final form n00b");
				return 1;
			}
		}

		GameState game2 = new GameState(2,1,8,3);
		while(game2.getVictory() == false)
		{
			game2.mapSet(); 
			game2.showmap();
			System.out.println("Your move. Choose wisely");
			order = s.next().charAt(0);

			if(!game2.moveHero(order))
				continue;
		
			game2.moveOgres();
			game2.heroNearOgre();
			if (game2.getDefeat()) {
				game2.mapSet();
				game2.showmap();
				System.out.println("You lost n00blord");
				break;
			}
		}


		s.close();
		return 0;
	}


	public static void main(String[] args) {
		System.out.println("Olá Universo");
		new UserInteraction().StartGame(); 
	}
}