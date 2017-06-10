package Logic.Model;

import Logic.Body.EntityBody;
import Logic.Body.GameStageController;

/**
 * class for character
 */
public class Character extends Entity{
    private Ammo ammo;
    private boolean selected;
    private int hp;

    /**
     * constructor of character
     * @param f file name for character sprite
     * @param f2 file name for ammo sprite
     */
    public Character(String f, String f2){
        super(f);
        this.selected = false;
        this.active = true;
        this.hp = 3;
        this.ammo = new SimpleBall(f2);
    }

    /**
     * updates character health and status
     * @param e character to be updated
     */
    @Override
    public void update(EntityBody e){
        if(hp <= 0)
        {
            active = false;
            selected = false;
            GameStage.getInstance().chooseSelected();
        }
        else{
            active = true;
        }
        if(e.getBody().getLinearVelocity().x <= FINISHED_MOVEMENT_VELOCITY_X && Math.abs(e.getBody().getLinearVelocity().y) <= FINISHED_MOVEMENT_VELOCITY_Y || e.getBody().getPosition().x < 0 || e.getBody().getPosition().x  > GameStageController.FIELD_WIDTH ||  e.getBody().getPosition().y  > GameStageController.FIELD_HEIGHT){
            this.setBeingUsed(false);
        }

        else{
            this.setBeingUsed(true);
        }
    }

    /**
     * get method for "selected" status
     * @return true if selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * setter for "selected" status
     * @param selected true if selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * get method for character hp
     * @return integer with character hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * setter for character hp
     * @param hp integer with character hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * get method for character ammo
     * @return Ammo
     */
    public Ammo getAmmo() {
        return ammo;
    }

}
