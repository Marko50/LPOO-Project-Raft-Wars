package dkeep.logic;

public class Lever extends Character {
    private boolean pressed;
	public Lever(int x, int y, char im)
	{
		super(x,y,im);
		this.pressed = false;	
	}
	
	public boolean getPressed()
	{
		return this.pressed;
	}
	
	public void setPressed(boolean set)
	{
		this.pressed = set;
	}

	@Override
	public void changeBuffImage(char order) {
		
	}
}
