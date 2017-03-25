/**  
* Wall.java - Class for walls
*/ 

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
			System.out.print("Wall sprite not found\n");
			e.printStackTrace();
		}
	}

	@Override
	public void changeBuffImage(char order) {
	}

}
