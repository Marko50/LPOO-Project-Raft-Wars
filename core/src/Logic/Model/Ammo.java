package Logic.Model;

import Logic.Body.EntityBody;
import Logic.Body.GameStageController;

/**
 * Created by André on 22-04-2017.
 */

public abstract class Ammo extends Entity{
    protected int damage;


    public Ammo(int d, String f){
        super(f);
        this.damage = d;
    }

    public void hitPlayer(Character c)
    {
        c.setHp(c.getHp() - this.damage);
    }


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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


}
