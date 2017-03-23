package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends Character {

	public Wall(int x, int y, char im) {
		super(x, y, im);
		try {
			this.setIm(ImageIO.read(new File("images/Wall.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void changeBuffImage(char order) {
		// TODO Auto-generated method stub
		
	}

}
