package Logic.Body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import Logic.Model.GameStage;
import Logic.View.GameStageView;

/**
 * Created by André on 30-04-2017.
 */

public class GameStageController implements ContactListener {
    public final Body floor;
    private final World world;

    public static final float FIELD_WIDTH = 100;
    public static final float FIELD_HEIGHT = 50;
    public static final float AMMO_SPEED = 30f;


    private static GameStageController instance;
    ArrayList<CharacterBody> bodiesPlayer1 = new ArrayList<CharacterBody>();
    ArrayList<CharacterBody> bodiesPlayer2 = new ArrayList<CharacterBody>();

    private GameStageController() {
        world = new World(new Vector2(0, -10 / GameStageView.PIXEL_TO_METER), true);
        world.setContactListener(this);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.linearVelocity.set(0f, 0f);
        bodyDef.position.set(0, 0);
        floor = world.createBody(bodyDef);
        // Create character fixture
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(Gdx.graphics.getWidth(), 10f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = .5f;      // how heavy is the character
        fixtureDef.friction = 1;    // how slippery is the character
        fixtureDef.restitution = .0f; // how bouncy is the character
        // Attach fixture to body
        floor.createFixture(fixtureDef);
        rectangle.dispose();
        for (int i = 0; i < GameStage.getInstance().getHeroesPlayer1().size(); i++) {
            bodiesPlayer1.add(new CharacterBody(50, 20, world));
        }
        for (int i = 0; i < GameStage.getInstance().getHeroesPlayer2().size(); i++) {
            bodiesPlayer2.add(new CharacterBody(400, 20, world));
        }
    }

    public static GameStageController getInstance() {
        if (instance == null)
            instance = new GameStageController();
        return instance;
    }

    public void shootPlayerAmmo(int x, int y) {
        int c = GameStage.getInstance().getSelectedCharacter();
        if (GameStage.getInstance().getPlayerTurn() == 1) {

            GameStage.getInstance().chooseSelected();
            this.getBodiesPlayer1().get(c).shootAmmo(x, y);
            GameStage.getInstance().getHeroesPlayer1().get(c).getAmmo().setBeingUsed(true);

        } else if (GameStage.getInstance().getPlayerTurn() == 2) {
            GameStage.getInstance().chooseSelected();
            this.getBodiesPlayer2().get(c).shootAmmo(-x, -y);
            GameStage.getInstance().getHeroesPlayer2().get(c).getAmmo().setBeingUsed(true);
        }
    }

    public void update() {
        world.step(1 / 60f, 6, 2);
        for (int i = 0; i < bodiesPlayer1.size(); i++) {
            bodiesPlayer1.get(i).update();
            bodiesPlayer1.get(i).getAmmoBody().update();
            GameStage.getInstance().getHeroesPlayer1().get(i).getAmmo().update(bodiesPlayer1.get(i).getAmmoBody().getBody().getLinearVelocity().x, bodiesPlayer1.get(i).getAmmoBody().getBody().getLinearVelocity().y);
        }
        for (int i = 0; i < bodiesPlayer2.size(); i++) {
            bodiesPlayer2.get(i).update();
            bodiesPlayer2.get(i).getAmmoBody().update();
            GameStage.getInstance().getHeroesPlayer1().get(i).getAmmo().update(bodiesPlayer2.get(i).getAmmoBody().getBody().getLinearVelocity().x, bodiesPlayer2.get(i).getAmmoBody().getBody().getLinearVelocity().y);
        }

    }

    public World getWorld() {
        return world;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public ArrayList<CharacterBody> getBodiesPlayer1() {
        return bodiesPlayer1;
    }

    public void setBodiesPlayer1(ArrayList<CharacterBody> bodysPlayer1) {
        this.bodiesPlayer1 = bodysPlayer1;
    }

    public ArrayList<CharacterBody> getBodiesPlayer2() {
        return bodiesPlayer2;
    }

    public void setBodiesPlayer2(ArrayList<CharacterBody> bodysPlayer2) {
        this.bodiesPlayer2 = bodysPlayer2;
    }


}
