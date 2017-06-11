package Logic.Model;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Logic.View.MenuView;


/**
 * Created by André on 21-04-2017.
 */

public class Game extends com.badlogic.gdx.Game { //Singleton Design Pattern
    private SpriteBatch batch;
    private static Game instance;
    public SpriteBatch getBatch() {
        return batch;
    }

    public static Game getInstance() {
        if(instance == null)
            instance = new Game();
       return instance;
    }
    private AssetManager assetManager;
    public AssetManager getAssetManager() {
        return assetManager;
    }
    @Override
    public void create() {
        this.assetManager = new AssetManager();
        batch = new SpriteBatch();
        setScreen(new MenuView());
    }
}
