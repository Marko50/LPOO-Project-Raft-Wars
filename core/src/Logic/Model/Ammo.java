package Logic.Model;

/**
 * Created by André on 22-04-2017.
 */

public abstract class Ammo{
    protected static final int FINISHED_MOVEMENT_VELOCITY_Y = 0;
    protected static final int FINISHED_MOVEMENT_VELOCITY_X = 0;
    protected float damage;
    protected  float actionLength;
    protected boolean beingUsed;
    protected  String filename;


    public Ammo(float d, float al, String f){
        this.damage = d;
        this.actionLength = al;
        this.filename = f;
        beingUsed = false;
    }

    public void update(float vx, float vy){
        System.out.println("VX: " + vx + "  VY: "+ vy);
        if((Math.abs(vx) <=  FINISHED_MOVEMENT_VELOCITY_X) && (Math.abs(vy) <= FINISHED_MOVEMENT_VELOCITY_Y) && beingUsed == true) {
            System.out.println("BALL HAS STOPPED MOVING");
            beingUsed = false;
            if(GameStage.getInstance().getPlayerTurn() == 1)
                GameStage.getInstance().setPlayerTurn(2);
            else if(GameStage.getInstance().getPlayerTurn() == 2)
                GameStage.getInstance().setPlayerTurn(1);
        }
        else if((Math.abs(vx) <=  FINISHED_MOVEMENT_VELOCITY_X) && (Math.abs(vy) <= FINISHED_MOVEMENT_VELOCITY_Y) && beingUsed == false) {
           // System.out.println("BALL IS NOT BEING USED");
        }
        else
        {
          //  System.out.println("BALL HAS NOT STOPPED MOVING");
            beingUsed= true;
        }

    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public float getActionLength() {
        return actionLength;
    }

    public void setActionLength(float actionLength) {
        this.actionLength = actionLength;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public boolean isBeingUsed() {return beingUsed;}

    public void setBeingUsed(boolean beingUsed) {this.beingUsed = beingUsed;}

}
