package Logic.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;


import java.util.ArrayList;

import Logic.Body.GameStageController;
import Logic.Model.Game;
import Logic.Model.GameStage;

/**
 * Created by André on 30-04-2017.
 */

public class GameStageView extends ScreenAdapter implements InputProcessor {
    private Texture backImage;
    private ArrayList<CharacterView> heroesPlayer1 = new ArrayList<CharacterView>();
    private ArrayList<CharacterView> heroesPlayer2 = new ArrayList<CharacterView>();
    private Matrix4 debugMatrix;
    private Box2DDebugRenderer debugRenderer;
    private Vector2 lastTouch;
    public static final float PIXEL_TO_METER = 50f;
    private final OrthographicCamera camera;
    public int c;

    public void loadAssets(){
        Game.getInstance().getAssetManager().load("background3.png", Texture.class);
        Game.getInstance().getAssetManager().finishLoading();
        backImage = Game.getInstance().getAssetManager().get("background3.png");
    }

    public GameStageView() {
        c = 0;
        loadAssets();
        lastTouch = new Vector2();
        for (int i = 0; i < GameStageController.getInstance().getBodiesPlayer1().size(); i++) {
            String ammoFilename = GameStage.getInstance().getHeroesPlayer1().get(i).getAmmo().getFilename();
            heroesPlayer1.add(new CharacterView(GameStage.getInstance().getHeroesPlayer1().get(i).getFilename(), 56, 8, ammoFilename));
        }
        for (int i = 0; i < GameStageController.getInstance().getBodiesPlayer2().size(); i++) {
            String ammoFilename = GameStage.getInstance().getHeroesPlayer2().get(i).getAmmo().getFilename();
            heroesPlayer2.add(new CharacterView(GameStage.getInstance().getHeroesPlayer2().get(i).getFilename(), 56, 8, ammoFilename));
        }
        loadAssets();
        camera = createCamera();
        Gdx.input.setInputProcessor(this);
       // System.out.println("HEIGHT: " + Gdx.graphics.getHeight() + "  WIDTH: " + Gdx.graphics.getWidth());
    }

    OrthographicCamera createCamera() {

        OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        //OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth()));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        debugRenderer = new Box2DDebugRenderer();
      //  camera.zoom = 0.6f;
       // camera.position.set(GameStageController.getInstance().getBodiesPlayer1().get(0).getBody().getPosition().x + 90,GameStageController.getInstance().getBodiesPlayer1().get(0).getBody().getPosition().y + 90,0);
        return camera;
    }

    @Override
    public void render(float delta) {
        this.updateView(delta);
        camera.update();
        Game.getInstance().getBatch().setProjectionMatrix(camera.combined);
        debugMatrix = Game.getInstance().getBatch().getProjectionMatrix().cpy().scale(PIXEL_TO_METER,
                PIXEL_TO_METER, 0);
        Game.getInstance().getBatch().begin();
        Game.getInstance().getBatch().draw(backImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for (int i = 0; i < heroesPlayer1.size(); i++) {
            heroesPlayer1.get(i).getSprite().flip(true, false);
            heroesPlayer1.get(i).draw(Game.getInstance().getBatch());
        }
        for (int i = 0; i < heroesPlayer2.size(); i++) {
            heroesPlayer2.get(i).draw(Game.getInstance().getBatch());
        }
        Game.getInstance().getBatch().end();
        debugRenderer.render(GameStageController.getInstance().getWorld(), debugMatrix);
/*
        if(GameStage.getInstance().getPlayerTurn() == 1)
        {
            //camera.position.set(GameStageController.getInstance().getBodiesPlayer1().get(c).getAmmoBody().getBody().getPosition().x + 90,GameStageController.getInstance().getBodiesPlayer1().get(c).getAmmoBody().getBody().getPosition().y + 50,0);
            camera.position.set(heroesPlayer1.get(c).getAmmoView().getSprite().getX() + 110 ,heroesPlayer1.get(c).getAmmoView().getSprite().getY() + 60,0);
        }
        else if(GameStage.getInstance().getPlayerTurn() == 2)
        {
            //camera.position.set(GameStageController.getInstance().getBodiesPlayer2().get(c).getAmmoBody().getBody().getPosition().x + 90,GameStageController.getInstance().getBodiesPlayer2().get(c).getAmmoBody().getBody().getPosition().y + 50,0);
            camera.position.set(heroesPlayer2.get(c).getAmmoView().getSprite().getX() + 110 ,heroesPlayer2.get(c).getAmmoView().getSprite().getY() + 60,0);
        }
*/

    }

    public void updateView(float delta) {
        GameStageController.getInstance().update();
        c = GameStage.getInstance().getSelectedCharacter();
        for (int i = 0; i < this.getHeroesPlayer1().size(); i++) {
            this.getHeroesPlayer1().get(i).update(delta, GameStageController.getInstance().getBodiesPlayer1().get(i));
            this.getHeroesPlayer1().get(i).getAmmoView().update(delta, GameStageController.getInstance().getBodiesPlayer1().get(i).getAmmoBody());
        }
        for (int i = 0; i < this.getHeroesPlayer2().size(); i++) {
            this.getHeroesPlayer2().get(i).update(delta, GameStageController.getInstance().getBodiesPlayer2().get(i));
            this.getHeroesPlayer2().get(i).getAmmoView().update(delta, GameStageController.getInstance().getBodiesPlayer2().get(i).getAmmoBody());
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 v3 = new Vector3(screenX, screenY, 0);
        v3 = camera.unproject(v3);
        System.out.println("ScreenX: " + v3.x + "  ScreenY: " + v3.y);
        System.out.println("SpriteX: " + this.getHeroesPlayer1().get(c).getAmmoView().getSprite().getX() + "  SpriteY: " + this.getHeroesPlayer1().get(c).getAmmoView().getSprite().getY());

        if(GameStage.getInstance().getPlayerTurn() == 1 && v3.x >= this.getHeroesPlayer1().get(c).getAmmoView().getSprite().getX() && v3.x <=  this.getHeroesPlayer1().get(c).getAmmoView().getSprite().getX() +  this.getHeroesPlayer1().get(c).getAmmoView().getSprite().getWidth()
                && v3.y >= this.getHeroesPlayer1().get(c).getAmmoView().getSprite().getY() && v3.y <= this.getHeroesPlayer1().get(c).getAmmoView().getSprite().getY() + this.getHeroesPlayer1().get(c).getAmmoView().getSprite().getHeight()){
            System.out.println("HIP HIP HURRAY 1");

        }

        else if(GameStage.getInstance().getPlayerTurn() == 2 && v3.x >= this.getHeroesPlayer2().get(c).getAmmoView().getSprite().getX() && v3.x <=  this.getHeroesPlayer2().get(c).getAmmoView().getSprite().getX() +  this.getHeroesPlayer2().get(c).getAmmoView().getSprite().getWidth()
                && v3.y >= this.getHeroesPlayer2().get(c).getAmmoView().getSprite().getY() && v3.y <= this.getHeroesPlayer2().get(c).getAmmoView().getSprite().getY() + this.getHeroesPlayer2().get(c).getAmmoView().getSprite().getHeight()){
            System.out.println("HIP HIP HURRAY 2");
        }


       /* if(GameStage.getInstance().getPlayerTurn() == 1 && this.getHeroesPlayer1().get(c).getAmmoView().getSprite().getX() == screenX){
            lastTouch.set(screenX, screenY);
        }
        else if(GameStage.getInstance().getPlayerTurn() == 2){
            lastTouch.set(screenX, screenY);
        }*/

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 newTouch = new Vector2(screenX, screenY);
        // delta will now hold the difference between the last and the current touch positions
        // delta.x > 0 means the touch moved to the right, delta.x < 0 means a move to the left
        Vector2 delta = newTouch.cpy().sub(lastTouch);

        lastTouch = newTouch;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
