package Logic.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Logic.Body.EntityBody;
import Logic.Model.Game;
/**
 * Created by André on 30-04-2017.
 */
public class AmmoView extends EntityView {
    //MACROS FOR AMMO SPRITE SIZE
    public AmmoView(String filename,int c, int r){
        super(filename,c,r);
        this.sprite.setSize(25,25);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void setSprite(String filename,int c, int r) {
        Game.getInstance().getAssetManager().load(filename, Texture.class);
        Game.getInstance().getAssetManager().finishLoading();
        Texture t =  Game.getInstance().getAssetManager().get(filename);
        TextureRegion textureRegion = new TextureRegion(t);
        sprite = new Sprite(textureRegion);
        sprite.setSize(t.getWidth(), t.getHeight());
        sprite.setCenter(t.getWidth() / 2, t.getHeight() / 2);
    }

    @Override
    public void update(float delta, EntityBody body) {
        sprite.setPosition(body.getBody().getPosition().x, body.getBody().getPosition().y);
    }
}
