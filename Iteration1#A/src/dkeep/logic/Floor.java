package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Floor extends Character {

	public Floor(int x, int y, char im) {
		super(x, y, im);
		try {
			this.setIm(ImageIO.read(new File("images/floor.png")));
		} catch (IOException e) {
		}
		
	}

	@Override
	public void changeBuffImage(char order) {
	}
	
}
