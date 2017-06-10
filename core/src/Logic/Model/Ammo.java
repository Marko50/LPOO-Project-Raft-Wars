package Logic.Model;

import Logic.Body.EntityBody;
import Logic.Body.GameStageController;

/**
 * Class for character's ammo/weapon
 */
public abstract class Ammo extends Entity{
    protected int damage;

    /**
     * Constructor of ammo
     * @param d damage
     * @param f file name for sprite
     */
    public Ammo(int d, String f){
        super(f);
        this.damage = d;
    }

    /**
     * Deals damage to "c" character
     * @param c Character to be hit
     */
    public void hitPlayer(Character c) {
        c.setHp(c.getHp() - this.damage);
    }

    /**
     * Updates position of body, puts it back at the start if velocity is 0
     * @param body body to be moved
     */
    @Override
    public void update(EntityBody body){
        float vx,vy, posX, posY;
        vx = body.getBody().getLinearVelocity().x;
        vy = body.getBody().getLinearVelocity().y;
        posX = body.getBody().getPosition().x;
        posY = body.getBody().getPosition().y;
        if(((Math.abs(vx) <=  FINISHED_MOVEMENT_VELOCITY_X) && (Math.abs(vy) <= FINISHED_MOVEMENT_VELOCITY_Y) || posX < 0 || posX > GameStageController.FIELD_WIDTH || posY > GameStageController.FIELD_HEIGHT) && beingUsed == true) {
            beingUsed = false;
            GameStage.getInstance().changeTurn();
        }
        else if((!((Math.abs(vx) <=  FINISHED_MOVEMENT_VELOCITY_X) && (Math.abs(vy) <= FINISHED_MOVEMENT_VELOCITY_Y) || posX < 0 || posX > GameStageController.FIELD_WIDTH || posY > GameStageController.FIELD_HEIGHT)) && beingUsed == false)
        {
            beingUsed= true;
        }
    }
}
