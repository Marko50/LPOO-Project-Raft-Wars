package Logic.View;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Logic.Body.EntityBody;

/**
 * Created by André on 30-04-2017.
 */

public abstract class EntityView  {
    protected float stateTime;
    protected Sprite sprite;

    EntityView(String filename,int cols, int rows) {
        stateTime = 0f;
        setSprite(filename, cols, rows);
    }
    /**
     * Draws the sprite from this view using a sprite batch.
     *
     * @param batch The sprite batch to be used for drawing.
     */
    public abstract void draw(SpriteBatch batch);
    /**
     * Abstract method that creates the view sprite. Concrete
     * implementation should extend this method to create their
     * own sprites.
     *
     *
     * @return the sprite representing this view.
     */
    public abstract void setSprite(String filename,int cols, int rows);
    public abstract  void update(float delta, EntityBody body);
    public float getStateTime() {
        return stateTime;
    }
    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public Sprite getSprite() {
        return sprite;
    }

}
