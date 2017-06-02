package Logic.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Logic.Model.Game;

/**
 * Created by Bruno on 01/06/2017.
 */

public class MenuView implements Screen{
    Stage stage;
    private SpriteBatch batch;
    private Viewport viewport;
    private Skin skin = new Skin(Gdx.files.internal("rainbow/skin/rainbow-ui.json"));
    private TextButton startGame = new TextButton("Start Game", skin);
    private TextButton howToPlay = new TextButton("How To Play", skin);
    private TextButton Exit = new TextButton("Exit", skin);

    public MenuView() {
        batch = new SpriteBatch();

        viewport = new ExtendViewport(300,400);
        viewport.apply();

        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        //Add listeners to buttons
        this.startGame.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameStageView());
            }
        });
        this.Exit.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });

        mainTable.add(startGame).padTop(40).center();
        mainTable.row();
        mainTable.add(howToPlay).padTop(20).center();
        mainTable.row();
        mainTable.add(Exit).padTop(70).center();
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
      //  debugRenderer.render(GameStageController.getInstance().getWorld(), debugMatrix);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
