package Logic.Model;

import Logic.Body.EntityBody;

/**
 * Class for every entity
 */
public abstract class Entity {
    protected static final float FINISHED_MOVEMENT_VELOCITY_X = 3.5f;
    protected static final float FINISHED_MOVEMENT_VELOCITY_Y = 0f;
    protected String filename;
    protected boolean beingUsed;


    protected boolean active;

    /**
     * Constructor of class Entity
     * @param f string with filename for sprite
     */
    public Entity(String f){
        this.filename = f;
        beingUsed = false;
    }

    public abstract void update(EntityBody e);

    /**
     * getter for whether or not an entity is being used
     * @return true if being used
     */
    public boolean isBeingUsed() {
        return beingUsed;
    }

    /**
     * setter for whether or not an entity is being used
     * @param beingUsed true if being used
     */
    public void setBeingUsed(boolean beingUsed) {
        this.beingUsed = beingUsed;
    }

    /**
     * getter for sprite filename
     * @return string with filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * getter for entity active status
     * @return true if active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * setter for entity active status
     * @param active true if active
     */
    public void setActive(boolean active) {
        this.active = active;
    }


}
