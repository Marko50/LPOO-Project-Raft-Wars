/**  
* Hero.java - Sub-Class of Character for the Hero
*/ 

package dkeep.logic;

import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class Hero  extends Character {
	private boolean hasKey;
	public Hero(int x, int y, char im) {
		super(x,y,im);
		setHasKey(false);
		changeBuffImage('d');

	}
	
    public void changeBuffImage(char order) {
		if (hasKey)
			try { this.setIm(ImageIO.read(new File("images/key.png")));
			} catch (IOException e) { System.out.print("key sprite not found\n");
			}		
		else {
			if(order == 'w') {
	    		try { this.setIm(ImageIO.read(new File("images/HeroMoveUp.png")));
				} catch (IOException e) { System.out.print("HeroMoveUp sprite not found\n");
				}
	    	}
	    	else if(order == 'a') {
	    		try { this.setIm(ImageIO.read(new File("images/HeroMoveLeft.png")));
				} catch (IOException e) { System.out.print("HeroMoveLeft sprite not found\n");
				}	
	    	}
	    	else if(order == 's') {
	    		try { this.setIm(ImageIO.read(new File("images/HeroMoveDown.png")));
				} catch (IOException e) { System.out.print("HeroMoveDown sprite not found\n");
				}
	    	}
	    	else if(order == 'd') {
	    		try { this.setIm(ImageIO.read(new File("images/HeroMoveRight.png")));
	    		} catch (IOException e) { System.out.print("HeroMoveRight sprite not found\n");
	    		}		
	    	}
		}
    }

	public boolean isHasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}
}
