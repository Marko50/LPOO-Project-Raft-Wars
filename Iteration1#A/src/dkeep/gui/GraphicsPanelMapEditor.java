package dkeep.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*; 
import dkeep.logic.*;

public class GraphicsPanelMapEditor extends JPanel implements MouseListener, MouseMotionListener {
	private MapEditor Gi;

	public GraphicsPanelMapEditor()
	{
		
	}
	
	public GraphicsPanelMapEditor(MapEditor m)
	{
		this.Gi = m;
		this.setFocusable(true);
		this.setVisible(true);
		this.setEnabled(true);
		addMouseMotionListener(this); 
		addMouseListener(this); 
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
		if(Gi.getGame().getHero() != null)
		g.drawImage(Gi.getGame().getHero().getIm(), 10 + 25 * Gi.getGame().getHero().getPos()[0],
				75 + 30 * Gi.getGame().getHero().getPos()[1], 25, 30, null);
		
		if(Gi.getGame().getLever() != null)
		g.drawImage(Gi.getGame().getLever().getIm(), 10 + 25 * Gi.getGame().getLever().getPos()[0],
				75 + 30 * Gi.getGame().getLever().getPos()[1], 25, 30, null);
		
		if(Gi.getGame().getOgres() != null)
		for (int i = 0; i < Gi.getGame().getOgres().length; i++) {
			g.drawImage(Gi.getGame().getOgres()[i].getIm(), 10 + 25 * Gi.getGame().getOgres()[i].getPos()[0],
					75 + 30 * Gi.getGame().getOgres()[i].getPos()[1], 25, 30, null);

			g.drawImage(Gi.getGame().getOgres()[i].getClub().getIm(),
					10 + 25 * Gi.getGame().getOgres()[i].getClub().getPos()[0],
					75 + 30 * Gi.getGame().getOgres()[i].getClub().getPos()[1], 25, 30, null);
		}
		
		if(Gi.getGame().getK() != null)
		g.drawImage(Gi.getGame().getK().getIm(), 10 + 25 * Gi.getGame().getK().getPos()[0],
				75 + 30 * Gi.getGame().getK().getPos()[1], 25, 30, null);

		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		/*
		int x = arg0.getX();
		int y = arg0.getY();
		System.out.println("X: " + x +"   Y: " + y);
		*/
		int x = (arg0.getX()/25);
		int y = (arg0.getY()/30)-2;
		System.out.println("X: " + x +"   Y: " + y);	
		
		
		if(this.Gi.HeroSelected)
		{
			System.out.println("HERO SELECTED\n");
			Hero h = new Hero(x, y, 'H');
			Gi.getGame().setHero(h);
			Gi.getGame().getMapa().setPos(x, y, h.getImage());
			Gi.frame.repaint();
		}
		/*
		else if(this.Gi.GuardSelected)
		{
			
		}
		else if(this.Gi.GuardSelected)
		{
			
		}
		else if(this.Gi.GuardSelected)
		{
			
		}
		else if(this.Gi.GuardSelected)
		{
			
		}
		else if(this.Gi.GuardSelected)
		{
			
		}
		else if(this.Gi.GuardSelected)
		{
			
		}
		else if(this.Gi.GuardSelected)
		{
			
		}
		*/
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public MapEditor getMe() {
		return Gi;
	}

	public void setMe(MapEditor me) {
		this.Gi = me;
	}

	
}