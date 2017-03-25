package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Door extends Character{
	private boolean open;
	public Door(int x, int y, char im, boolean op) {
		super(x, y, im);
		open = op;
		changeBuffImage('a');
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	@Override
	public void changeBuffImage(char order) {
		if(open)
			try {
				this.setIm(ImageIO.read(new File("images/OpenDoor.png")));
			} catch (IOException e) {
				System.out.print("OpenDoor sprite not found\n");
			}
		
		else
			try {
				this.setIm(ImageIO.read(new File("images/ClosedDoor.png")));
			} catch (IOException e) {
				System.out.print("ClosedDoor sprite not found\n");
			}		
	}
}
