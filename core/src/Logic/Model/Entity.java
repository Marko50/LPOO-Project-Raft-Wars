package Logic.Model;

import Logic.Body.EntityBody;

/**
 * Created by André on 31-05-2017.
 */

public abstract class Entity {
    protected static final float FINISHED_MOVEMENT_VELOCITY_X = 3.5f;
    protected static final float FINISHED_MOVEMENT_VELOCITY_Y = 0f;
    protected String filename;
    protected boolean beingUsed;


    public Entity(String f){
        this.filename = f;
        beingUsed = false;
    }

    public abstract void update(EntityBody e);


    public boolean isBeingUsed() {
        return beingUsed;
    }

    public void setBeingUsed(boolean beingUsed) {
        this.beingUsed = beingUsed;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
