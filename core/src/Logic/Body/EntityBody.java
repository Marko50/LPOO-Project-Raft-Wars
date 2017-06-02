package Logic.Body;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import Logic.Model.Entity;

/**
 * Created by André on 30-04-2017.
 */

public abstract class EntityBody{
    protected Body body;
    protected int originX, originY;
    public EntityBody(int x, int y, World world, Entity e){
        setBody(x, y, world, e);
        this.originX = x;
        this.originY = y;
    }

    public Body getBody(){return body;}

    public abstract void setBody(int x, int y, World world, Entity e);

    public  abstract void update(Entity e);

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
