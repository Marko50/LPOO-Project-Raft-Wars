package Logic.Model;

/**
 * Created by André on 22-04-2017.
 */

public abstract class Ammo{
    protected static final int FINISHED_MOVEMENT_VELOCITY_Y = 3;
    protected static final int FINISHED_MOVEMENT_VELOCITY_X = 5;
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
        if(vx <=  FINISHED_MOVEMENT_VELOCITY_X && vy <= FINISHED_MOVEMENT_VELOCITY_Y) {
            beingUsed = false;
            if(GameStage.getInstance().getPlayerTurn() == 1)
                GameStage.getInstance().setPlayerTurn(2);
            else if(GameStage.getInstance().getPlayerTurn() == 2)
                GameStage.getInstance().setPlayerTurn(1);
        }
        else
        {
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
