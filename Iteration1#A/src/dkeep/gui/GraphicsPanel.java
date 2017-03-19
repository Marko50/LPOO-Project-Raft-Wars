package dkeep.gui;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*; 
import dkeep.logic.*;

public class GraphicsPanel extends JPanel {
	private GameState game;

	public GameState getGame() {
		return game;
	}

	public void setGame(GameState game) {
		this.game = game;
	}
	
	public GraphicsPanel(GameState game2)
	{
		this.game = game2;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(450, 350);
	}
 
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i < game.getMapa().getMap().length; i++)
		{
			for(int j = 0 ; j < game.getMapa().getMap()[i].length; j++)
			{
				if(game.getMapa().getMap()[i][j] == ' ')
				{
					g.setColor(Color.WHITE);
				}
				else if(game.getMapa().getMap()[i][j] == 'I')
				{
					g.setColor(Color.gray);
				}
				else if(game.getMapa().getMap()[i][j] == 'S')
				{
					g.setColor(Color.ORANGE);
				}
				else if(game.getMapa().getMap()[i][j] == 'X')
				{
					g.setColor(Color.BLACK);
				}
				else if(game.getMapa().getMap()[i][j] == game.getLever().getImage())
				{
					g.setColor(Color.YELLOW);
				}
				else if(game.getMapa().getMap()[i][j] == game.getHero().getImage())
				{
					g.setColor(Color.RED);
				}
				else if(game.getMapa().getMap()[i][j] == game.getOgres()[0].getImage())
				{
					g.setColor(Color.GREEN);
				}
				else if(game.getMapa().getMap()[i][j] == game.getOgres()[0].getClub().getImage())
				{
					Color c = new Color(22, 75, 44);
					g.setColor(c);
				}
				else if(game.getMapa().getMap()[i][j] == game.getGuard().getImage())
				{
					g.setColor(Color.BLUE);
				}
				else if(game.getMapa().getMap()[i][j] == game.getK().getImage())
				{
					g.setColor(Color.PINK);
				}	
				g.fillRect(10 + 20*j, 75 + 20*i, 20, 20);
			}
		}
		

	}

}
