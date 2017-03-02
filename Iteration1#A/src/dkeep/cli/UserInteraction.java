package dkeep.cli;
import java.util.Scanner;
import dkeep.logic.*;


public class UserInteraction {

	public void StartGame() {
		System.out.println("Hello player. What kind of guard would you like to play against?");
		System.out.println("1. Rookie Guard");
		System.out.println("2. Drunken Guard");
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

	public void Environment(int difficulty) {
		GameState game = new GameState(1,1,1,8,1, difficulty);
		int contador = 0;
		Scanner s = new Scanner(System.in);
		char order;
		
		while(true)
		{
			System.out.println("Your move. Choose wisely");
			order = s.next().charAt(0);
			if(order == 'p')
			{
				game.setGMode(2);
				break;
			}
			
			else {
				if (game.getGMode() == 1) {
					game.moveHero(order);
					game.getGuard().gMove(contador);
					contador = contador + game.getGuard().getDirection();
				}
				else if (game.getGMode() == 2) {
					game.moveHero(order);
					game.moveOgres();
				}
			}
			if (contador == 24 && game.getGuard().getDirection() == 1)
				contador = 0;
			else if (contador == 0 && game.getGuard().getDirection() == -1) {
				contador = 24;
			}
			
			if(game.getDefeat())
			{
				System.out.println("Wow you won. Maybe now you'll skip out of bronze 5. NOT");
			}
			else
				System.out.println("You lost n00blord");
		}
		s.close();
	}
}