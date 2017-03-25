package dkeep.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Key extends Character  implements Cloneable{

    private boolean Used;
    private boolean pickedUp;
	public Key(int x, int y, char im) {
		super(x, y, im);
		this.Used = false;
		this.pickedUp = false;
		try {
			this.setIm(ImageIO.read(new File("images/key.png")));
		} catch (IOException e) {
			System.out.print("key sprite not found\n");
		}
	}
	
	public Key (Key k)
	{
		super(k.getPos()[1], k.getPos()[0], k.getImage());
        this.Used = k.Used;
        this.pickedUp = k.Used;
	}

	public boolean getUsed() {
		return Used;
	}
	public void setUsed(boolean pickedUp) {
		this.Used = pickedUp;
	}
	public boolean isPickedUp() {
		return pickedUp;
	}
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
	
	
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	@Override
	public void changeBuffImage(char order) {
		if (pickedUp)
			this.setIm(null);
		else
			try {
				this.setIm(ImageIO.read(new File("images/key.png")));
			} catch (IOException e) {
				System.out.print("key sprite not found\n");
			}
	}

}
