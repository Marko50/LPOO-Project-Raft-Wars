package Logic.View;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import Logic.Body.EntityBody;
import Logic.Model.Game;



/**
 * Created by André on 30-04-2017.
 */

public class CharacterView extends EntityView {
    private AmmoView ammoView;
    private Animation<TextureRegion> animation;
    private TextureRegion[] frames;

    CharacterView(String filename, int c, int r, String ammoFilename) {
        super(filename,c,r);
        this.ammoView = new AmmoView(ammoFilename, 0 ,0);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
        ammoView.draw(batch);
    }

    @Override
    public void setSprite(String filename, int cols, int rows) {
        Texture t =  Game.getInstance().getAssetManager().get(filename);
        TextureRegion[][] region = TextureRegion.split(t,t.getWidth() / cols, t.getHeight()/rows);
        frames = new TextureRegion[cols*rows];
        System.arraycopy(region[0],0,frames,0, cols);
        animation = new Animation<TextureRegion>(.11f, frames);
        sprite = new Sprite(animation.getKeyFrame(0));
        sprite.setSize(animation.getKeyFrame(0).getRegionWidth()/2, animation.getKeyFrame(0).getRegionHeight()/2);
        sprite.setCenter(animation.getKeyFrame(0).getRegionWidth() /4, animation.getKeyFrame(0).getRegionHeight() /4);
    }

    @Override
    public void update(float delta, EntityBody body) {
        sprite.setPosition((body.getBody().getPosition().x * GameStageView.PIXEL_TO_METER)-sprite.getWidth()/2,(body.getBody().getPosition().y * GameStageView.PIXEL_TO_METER) - sprite.getHeight()/2);

        if(animation.getKeyFrame(stateTime, true) != null)
        {
            sprite.setRegion(animation.getKeyFrame(stateTime, true));
            stateTime += delta;
        }
        else
            stateTime = 0f;
    }

    public AmmoView getAmmoView() {
        return ammoView;
    }
}
