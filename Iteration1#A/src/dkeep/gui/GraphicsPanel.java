package dkeep.gui;

import java.awt.*;
import java.awt.event.*; 
import javax.swing.*; 
import dkeep.logic.*;

public class GraphicsPanel extends JPanel implements KeyListener {
	private GraphicalInterface Gi;
	

	public GraphicsPanel(GraphicalInterface gi)
	{
		this.Gi = gi;
		addKeyListener(this);
		this.setFocusable(true);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(450, 350);
	}
 
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i < Gi.getGame().getMapa().getMap().length; i++)
		{
			for(int j = 0 ; j < Gi.getGame().getMapa().getMap()[i].length; j++)
			{
				if(Gi.getGame().getMapa().getMap()[i][j] == ' ')
				{
					g.setColor(Color.WHITE);
				}
				else if(Gi.getGame().getMapa().getMap()[i][j] == 'I')
				{
					g.setColor(Color.gray);
				}
				else if(Gi.getGame().getMapa().getMap()[i][j] == 'S')
				{
					g.setColor(Color.ORANGE);
				}
				else if(Gi.getGame().getMapa().getMap()[i][j] == 'X')
				{
					g.setColor(Color.BLACK);
				}
				else if(Gi.getGame().getMapa().getMap()[i][j] == Gi.getGame().getLever().getImage())
				{
					g.setColor(Color.YELLOW);
				}
				else if(Gi.getGame().getMapa().getMap()[i][j] == Gi.getGame().getHero().getImage())
				{
					g.setColor(Color.RED);
				}
				else if(Gi.getGame().getMapa().getMap()[i][j] == Gi.getGame().getOgres()[0].getImage())
				{
					g.setColor(Color.GREEN);
				}
				else if(Gi.getGame().getMapa().getMap()[i][j] == Gi.getGame().getOgres()[0].getClub().getImage())
				{
					Color c = new Color(22, 75, 44);
					g.setColor(c);
				}
				else if(Gi.getGame().getMapa().getMap()[i][j] == Gi.getGame().getGuard().getImage())
				{
					g.setColor(Color.BLUE);
				}
				else if(Gi.getGame().getMapa().getMap()[i][j] == Gi.getGame().getK().getImage())
				{
					g.setColor(Color.PINK);
				}	
				g.fillRect(10 + 20*j, 75 + 20*i, 20, 20);
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			Gi.move('a');
			Gi.frame.repaint();
			break;
		case KeyEvent.VK_RIGHT:
			Gi.move('d');
			Gi.frame.repaint();
			break;
		case KeyEvent.VK_UP:
			Gi.move('w');
			Gi.frame.repaint();
			break;
		case KeyEvent.VK_DOWN:
			Gi.move('s');
			Gi.frame.repaint();
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
