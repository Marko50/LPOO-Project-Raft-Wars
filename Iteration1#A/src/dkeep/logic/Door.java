package dkeep.logic;

public class Door extends Character{
	private boolean open;
	public Door(int x, int y, char im, boolean op) {
		super(x, y, im);
		open = op;		
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	@Override
	public void changeBuffImage(char order) {
		// TODO Auto-generated method stub
		
	}

}
