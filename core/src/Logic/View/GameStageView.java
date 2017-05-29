package Logic.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import java.util.ArrayList;

import Logic.Body.GameStageController;
import Logic.Model.Game;
import Logic.Model.GameStage;

/**
 * Created by André on 30-04-2017.
 */

public class GameStageView extends ScreenAdapter{
    private Texture backImage;
    private ArrayList<CharacterView> heroesPlayer1 = new ArrayList<CharacterView>();
    private ArrayList<CharacterView> heroesPlayer2 = new ArrayList<CharacterView>();

    public static final float PIXEL_TO_METER = 0.04f;
    public static final float VIEWPORT_WIDTH = 20;
    private final OrthographicCamera camera;


    public GameStageView(){
        Game.getInstance().getAssetManager().load("background3.png", Texture.class);
        Game.getInstance().getAssetManager().finishLoading();
        backImage = Game.getInstance().getAssetManager().get("background3.png");
        for (int i = 0; i < GameStageController.getInstance().getBodiesPlayer1().size(); i++)
        {
            String ammoFilename = GameStage.getInstance().getHeroesPlayer1().get(i).getAmmo().getFilename();
            heroesPlayer1.add(new CharacterView(GameStage.getInstance().getHeroesPlayer1().get(i).getFilename(),6,5, ammoFilename));
        }
        for (int i = 0; i < GameStageController.getInstance().getBodiesPlayer2().size(); i++)
        {
            String ammoFilename = GameStage.getInstance().getHeroesPlayer1().get(i).getAmmo().getFilename();
            heroesPlayer2.add(new CharacterView(GameStage.getInstance().getHeroesPlayer2().get(i).getFilename(),6,5, ammoFilename));
        }

        camera = createCamera();

    }
    OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        camera.zoom = 0.5f;
        camera.position.set(GameStageController.getInstance().getBodiesPlayer1().get(0).getBody().getPosition().x + 90,GameStageController.getInstance().getBodiesPlayer1().get(0).getBody().getPosition().y + 90,0);
        return camera;
    }

    @Override
    public void render(float delta) {
        this.handleInputs(delta);
        this.updateView(delta);

        GameStage.getInstance().getSelectedCharacter();

        if(GameStage.getInstance().getPlayerTurn() == 1)
        {

            //camera.position.set(GameStageController.getInstance().getBodiesPlayer1().get().getBody().getPosition().x + 90,GameStageController.getInstance().getBodiesPlayer1().get(0).getBody().getPosition().y + 90,0);
        }
        else if(GameStage.getInstance().getPlayerTurn() == 2)
        {



        }
        camera.update();
        Game.getInstance().getBatch().setProjectionMatrix(camera.combined);
        Game.getInstance().getBatch().begin();
        Game.getInstance().getBatch().draw(backImage,0,0);
        //Game.getInstance().getBatch().draw(backImage, 0, 0, 0, 0, (int)(GameStageController.FIELD_WIDTH / PIXEL_TO_METER), (int) (GameStageController.FIELD_HEIGHT / PIXEL_TO_METER));
        for (int i = 0; i < heroesPlayer1.size(); i++)
        {
           heroesPlayer1.get(i).draw(Game.getInstance().getBatch());
        }
        for (int i = 0; i < heroesPlayer2.size(); i++)
        {
            heroesPlayer2.get(i).draw(Game.getInstance().getBatch());
        }
        Game.getInstance().getBatch().end();
    }

    public void updateView(float delta)
    {
        GameStageController.getInstance().update();
        for(int i = 0; i < this.getHeroesPlayer1().size(); i++)
        {
            this.getHeroesPlayer1().get(i).update(delta, GameStageController.getInstance().getBodiesPlayer1().get(i));
            this.getHeroesPlayer1().get(i).getAmmoView().update(delta,GameStageController.getInstance().getBodiesPlayer1().get(i).getAmmoBody());
        }

        for(int i = 0; i < this.getHeroesPlayer2().size(); i++)
        {
            this.getHeroesPlayer2().get(i).update(delta, GameStageController.getInstance().getBodiesPlayer2().get(i));
            this.getHeroesPlayer2().get(i).getAmmoView().update(delta,GameStageController.getInstance().getBodiesPlayer2().get(i).getAmmoBody());
        }
    }

    private void handleInputs(float delta) {
        if(Gdx.input.justTouched())
        {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            GameStageController.getInstance().shootPlayerAmmo(x,y);
        }

    }

    public Texture getBackImage() {
        return backImage;
    }

    public void setBackImage(Texture backImage) {
        this.backImage = backImage;
    }

    public ArrayList<CharacterView> getHeroesPlayer1() {
        return heroesPlayer1;
    }

    public void setHeroesPlayer1(ArrayList<CharacterView> heroesPlayer1) {
        this.heroesPlayer1 = heroesPlayer1;
    }
    public void addHeroPlayer1(CharacterView h) {
        this.heroesPlayer1.add(h);
    }

    public ArrayList<CharacterView> getHeroesPlayer2() {
        return heroesPlayer2;
    }

    public void setHeroesPlayer2(ArrayList<CharacterView> heroesPlayer2) {
        this.heroesPlayer2 = heroesPlayer2;
    }

}
