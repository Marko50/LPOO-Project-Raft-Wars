package dkeep.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*; 
import dkeep.logic.*;

public class GraphicsPanel extends JPanel implements KeyListener {
	private GraphicalInterface Gi;
	

	public GraphicsPanel()
	{
		
	}
	
	public void removeListener()
	{
		removeKeyListener(this);
	}
	
	public GraphicsPanel(GraphicalInterface gi)
	{
		this.Gi = gi;
		addKeyListener(this);
		this.setFocusable(true);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(450, 450);
	}
 
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i < Gi.getGame().getMapa().getMap().length; i++)
		{
			for(int j = 0 ; j < Gi.getGame().getMapa().getMap()[i].length; j++)
			{
				g.drawImage(Gi.getGame().getMapa().getMapChar()[i][j].getIm(), 10 + 25*j, 75+30*i, 25, 30, null);
			}	
		}
		g.drawImage(Gi.getGame().getHero().getIm(), 10 + 25 * Gi.getGame().getHero().getPos()[0],
				75 + 30 * Gi.getGame().getHero().getPos()[1], 25, 30, null);
		
		
		if (Gi.getGame().getGMode() == 1)
			g.drawImage(Gi.getGame().getGuard().getIm(), 10 + 25 * Gi.getGame().getGuard().getPos()[0],
					75 + 30 * Gi.getGame().getGuard().getPos()[1], 25, 30, null);
		
		if(Gi.getGame().getGMode() == 2)
		{
			for (int i = 0; i < Gi.getGame().getOgres().length; i++) {
				g.drawImage(Gi.getGame().getOgres()[i].getIm(), 10 + 25 * Gi.getGame().getOgres()[i].getPos()[0],
						75 + 30 * Gi.getGame().getOgres()[i].getPos()[1], 25, 30, null);

				g.drawImage(Gi.getGame().getOgres()[i].getClub().getIm(),
						10 + 25 * Gi.getGame().getOgres()[i].getClub().getPos()[0],
						75 + 30 * Gi.getGame().getOgres()[i].getClub().getPos()[1], 25, 30, null);
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
