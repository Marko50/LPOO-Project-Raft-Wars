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

    public void hitPlayer(Character c){
        c.setHp(c.getHp() - (this.damage - c.getArmor()));
    }


    @Override
    public void update(EntityBody body){
        float vx,vy, posX, posY;
        vx = body.getBody().getLinearVelocity().x;
        vy = body.getBody().getLinearVelocity().y;
        posX = body.getBody().getPosition().x;
        posY = body.getBody().getPosition().y;
       // System.out.println("VX: " + vx + "  VY: "+ vy);
        if(((Math.abs(vx) <=  FINISHED_MOVEMENT_VELOCITY_X) && (Math.abs(vy) <= FINISHED_MOVEMENT_VELOCITY_Y) || posX < 0 || posX > GameStageController.FIELD_WIDTH || posY > GameStageController.FIELD_HEIGHT) && beingUsed == true) {
           // System.out.println("BALL HAS STOPPED MOVING");
            beingUsed = false;
            if(GameStage.getInstance().getPlayerTurn() == 1)
                GameStage.getInstance().setPlayerTurn(2);
            else if(GameStage.getInstance().getPlayerTurn() == 2)
                GameStage.getInstance().setPlayerTurn(1);
        }
        else if((!((Math.abs(vx) <=  FINISHED_MOVEMENT_VELOCITY_X) && (Math.abs(vy) <= FINISHED_MOVEMENT_VELOCITY_Y) || posX < 0 || posX > GameStageController.FIELD_WIDTH || posY > GameStageController.FIELD_HEIGHT)) && beingUsed == false)
        {
           // System.out.println("BALL HAS NOT STOPPED MOVING");
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
