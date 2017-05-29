package Logic.Body;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by André on 30-04-2017.
 */

public abstract class EntityBody{
    protected static final int FINISHED_MOVEMENT_VELOCITY_Y = 3;
    protected static final int FINISHED_MOVEMENT_VELOCITY_X = 5;
    protected Body body;
    protected int originX, originY;
    public EntityBody(int x, int y, World world){
        setBody(x, y, world);
        this.originX = y;
        this.originY = x;
    }

    public Body getBody(){return body;}
    public abstract void setBody(int x, int y, World world);
    public  abstract void update();
    public void setBody(Body body) {
        this.body = body;
    }

    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }

}
