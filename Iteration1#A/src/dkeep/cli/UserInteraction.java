
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
		char[][] map = {l0,l1,l2,l3,l4,l5,l6,l7,l8,l9};

		GameState game = new GameState(1,1,1,8,1, difficulty, map, 7,8);

		int contador = 0;
		Scanner s = new Scanner(System.in);
		char order;
		while(game.getVictory() == false)
		{
			game.getMapa().mapSetGameMode1(game.getLever(), game.getHero(), game.getGuard());
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
				if (contador == 23 && game.getGuard().getDirection() == 1)
					contador = 0;
				else if (contador == 0 && game.getGuard().getDirection() == -1) {
					contador = 23;
				}
				else if(game.getGuard().getSleep() == 0)
					contador = contador + game.getGuard().getDirection();
				game.getMapa().getMap()[game.getGuard().getPos()[1]][game.getGuard().getPos()[0]] = ' ';
				game.getGuard().gMove(contador);
				System.out.println(contador);
			}
			game.heroNearGuard();
			if(game.getDefeat())
			{
				game.getMapa().mapSetGameMode1(game.getLever(), game.getHero(), game.getGuard());
				game.showmap();
				System.out.println("This isn't even my final form n00b");
				return 1;
			}
		}

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
		char[][] map2 = {l0a,l1a,l2a,l3a,l4a,l5a,l6a,l7a,l8a,l9a};
		
		GameState game2 = new GameState(2,1,8,3, map2,7,8);
		while(game2.getVictory() == false)
		{
			game2.getMapa().mapSetGameMode2(game2.getHero(), game2.getOgres(), game2.getK());
			game2.showmap();
			System.out.println("Your move. Choose wisely");
			order = s.next().charAt(0);

			if(!game2.moveHero(order))
				continue;
		
			game2.moveOgres();
			game2.heroNearOgre();
			if (game2.getDefeat()) {
				game2.getMapa().mapSetGameMode2(game2.getHero(), game2.getOgres(), game2.getK());
				game2.showmap();
				System.out.println("You lost n00blord");
				break;
			}
		}


		s.close();
		return 0;
	}


	public static void main(String[] args) throws CloneNotSupportedException {
		System.out.println("Olá Universo");
		new UserInteraction().StartGame(); 
	}
}