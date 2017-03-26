package dkeep.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*; 
import dkeep.logic.*;
import dkeep.logic.Character;

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
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		for(int i = 0; i < Gi.getGi().getGame().getMapa().getMap().length; i++)
			for(int j = 0 ; j < Gi.getGi().getGame().getMapa().getMap()[i].length; j++)
				g.drawImage(Gi.getGi().getGame().getMapa().getMapChar()[i][j].getIm(), 10 + 25 * j, 75 + 30 * i, 25, 30, null);
		if (Gi.getGi().getGame().getHero() != null)
			g.drawImage(Gi.getGi().getGame().getHero().getIm(), 10 + 25 * Gi.getGi().getGame().getHero().getPos()[0], 75 + 30 * Gi.getGi().getGame().getHero().getPos()[1], 25, 30, null);
		if (Gi.getGi().getGame().getLever() != null)
			g.drawImage(Gi.getGi().getGame().getLever().getIm(), 10 + 25 * Gi.getGi().getGame().getLever().getPos()[0], 75 + 30 * Gi.getGi().getGame().getLever().getPos()[1], 25, 30, null);
		for (int i = 0; i < Gi.getGi().getGame().getOgres().size(); i++) {
			g.drawImage(Gi.getGi().getGame().getOgres().get(i).getIm(), 10 + 25 * Gi.getGi().getGame().getOgres().get(i).getPos()[0], 75 + 30 * Gi.getGi().getGame().getOgres().get(i).getPos()[1], 25, 30, null);
			g.drawImage(Gi.getGi().getGame().getOgres().get(i).getClub().getIm(), 10 + 25 * Gi.getGi().getGame().getOgres().get(i).getClub().getPos()[0], 75 + 30 * Gi.getGi().getGame().getOgres().get(i).getClub().getPos()[1], 25, 30, null);
		}
		if (Gi.getGi().getGame().getK() != null)
			g.drawImage(Gi.getGi().getGame().getK().getIm(), 10 + 25 * Gi.getGi().getGame().getK().getPos()[0], 75 + 30 * Gi.getGi().getGame().getK().getPos()[1], 25, 30, null);
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
		int x = (arg0.getX()/25);
		int y = (arg0.getY()/30)-2;	
		if(x == Gi.getGi().getGame().getMapa().getMap()[0].length)
			x = Gi.getGi().getGame().getMapa().getMap()[0].length-1;
		if(y == Gi.getGi().getGame().getMapa().getMap().length)
			y = Gi.getGi().getGame().getMapa().getMap().length-1;	
		if(this.Gi.HeroSelected)
		{
			Hero h = new Hero(x, y, 'H');
			Gi.getGi().getGame().setHero(h);
			Gi.getGi().getGame().getMapa().setPos(x, y, h.getImage());
			Gi.heroUsed = true;
			Gi.frame.repaint();
		}
		else if(this.Gi.KeySelected)
		{
			Key k = new Key(x, y, 'k');
			Gi.getGi().getGame().setK(k);
			Gi.getGi().getGame().getMapa().setPos(x, y, k.getImage());
			Gi.keyUsed = true;
			Gi.frame.repaint();
		}
		else if(this.Gi.OgreSelected)
		{	
			Ogre o = new Ogre(x, y, 'O', '*');
			Gi.getGi().getGame().addOgre(o);
			Gi.getGi().getGame().getMapa().setPos(x, y, o.getImage());
			Gi.getGi().getGame().getMapa().setPos(x, y, o.getClub().getImage());
			Gi.ogreUsed = true;
			Gi.frame.repaint();
		}
		else if(this.Gi.LeverSelected)
		{
			Lever l = new Lever(x, y, 'K');
			Gi.getGi().getGame().setLever(l);
			Gi.getGi().getGame().getMapa().setPos(x, y, l.getImage());
			Gi.leverUsed = true;
			Gi.frame.repaint();
		}
		else if(this.Gi.FloorSelected)
		{
			Floor f = new Floor(x, y, ' ');
			Gi.getGi().getGame().getMapa().setPos(x, y, f.getImage());
			Gi.frame.repaint();
		}

		else if(this.Gi.WallSelected)
		{
			Wall w = new Wall(x, y, 'X');
			Gi.getGi().getGame().getMapa().setPos(x, y, w.getImage());
			Gi.frame.repaint();
		}

		else if(this.Gi.DoorOpenedSelected)
		{
			Door d = new Door(x, y, 'S', true);
			Gi.getGi().getGame().getMapa().setPos(x, y, d.getImage());
			Gi.frame.repaint();
		}

		else if(this.Gi.DoorClosedSelected)
		{
			Door d = new Door(x, y, 'I', false);
			Gi.getGi().getGame().getMapa().setPos(x, y, d.getImage());
			Gi.frame.repaint();
		}
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