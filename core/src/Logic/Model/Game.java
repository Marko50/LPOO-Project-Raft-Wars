package Logic.Model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Logic.View.GameStageView;


/**
 * Created by André on 21-04-2017.
 */

public class Game extends com.badlogic.gdx.Game { //Singleton Design Pattern
    private SpriteBatch batch;
    private static Game instance;
    private GameStage gameStage;
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

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public void create() {
        this.assetManager = new AssetManager();
        batch = new SpriteBatch();
        this.gameStage = GameStage.getInstance();
        setScreen(new GameStageView());
    }


    public GameStage getGameStage() {
        return gameStage;
    }

    public void setGameStage(GameStage gameStage) {
        this.gameStage = gameStage;
    }
}
