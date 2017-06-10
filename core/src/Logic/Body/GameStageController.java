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

import Logic.Model.Ammo;
import Logic.Model.Character;
import Logic.Model.GameStage;
import Logic.View.GameStageView;

/**
 * Created by André on 30-04-2017.
 */

public class GameStageController implements ContactListener {
    public final Body floor;
    private final World world;
    public static float FIELD_WIDTH;
    public static float FIELD_HEIGHT;

    private static GameStageController instance;
    private ArrayList<CharacterBody> bodiesPlayer1 = new ArrayList<CharacterBody>();
    private ArrayList<CharacterBody> bodiesPlayer2 = new ArrayList<CharacterBody>();

    private GameStageController() {
        FIELD_WIDTH = GameStageView.VIEWPORT_WIDTH / GameStageView.PIXEL_TO_METER;
        FIELD_HEIGHT = GameStageView.VIEWPORT_HEIGHT / GameStageView.PIXEL_TO_METER;
       // System.out.println("FIELD_WIDTH: " + FIELD_WIDTH);
        //System.out.println("FIELD_HEIGHT: " + FIELD_HEIGHT);
        world = new World(new Vector2(0, -10), true);
        world.setContactListener(this);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.linearVelocity.set(0f, 0f);
        bodyDef.position.set(0, 0);
        floor = world.createBody(bodyDef);
        // Create character fixture
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(FIELD_WIDTH, .5f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = 1f;      // how heavy is the character
        fixtureDef.friction = 1;    // how slippery is the character
        fixtureDef.restitution = .0f; // how bouncy is the character
        // Attach fixture to body
        floor.createFixture(fixtureDef);
        rectangle.dispose();
        for (int i = 0; i < GameStage.getInstance().getHeroesPlayer1().size(); i++) {
            bodiesPlayer1.add(new CharacterBody(1+i,0.9f,2+i,2,world, GameStage.getInstance().getHeroesPlayer1().get(i), GameStage.getInstance().getHeroesPlayer1().get(i).getAmmo()));
        }
        for (int i = 0; i < GameStage.getInstance().getHeroesPlayer2().size(); i++) {
            bodiesPlayer2.add(new CharacterBody(FIELD_WIDTH - 1 - i , 0.9f, FIELD_WIDTH - 2 - i, 2, world, GameStage.getInstance().getHeroesPlayer2().get(i), GameStage.getInstance().getHeroesPlayer2().get(i).getAmmo()));
        }
    }

    public static GameStageController getInstance() {
        if (instance == null)
            instance = new GameStageController();
        return instance;
    }

    public void shootPlayerAmmo(float x, float y) {
        int c = GameStage.getInstance().getSelectedCharacter();
        if (GameStage.getInstance().getPlayerTurn() == 1) {
            System.out.println(x);
            System.out.println(y);
            this.getBodiesPlayer1().get(c).shootAmmo(x, y);
        }
        else if (GameStage.getInstance().getPlayerTurn() == 2) {
            this.getBodiesPlayer2().get(c).shootAmmo(x, y);
        }
    }

    public void reset(){
        FIELD_WIDTH = GameStageView.VIEWPORT_WIDTH / GameStageView.PIXEL_TO_METER;
        FIELD_HEIGHT = GameStageView.VIEWPORT_HEIGHT / GameStageView.PIXEL_TO_METER;
        for(int i = 0; i < this.getBodiesPlayer1().size(); i++){
            this.getBodiesPlayer1().get(i).getBody().setActive(true);
            this.getBodiesPlayer1().get(i).getBody().setTransform(1+i,0.9f, this.getBodiesPlayer1().get(i).getBody().getAngle());
            this.getBodiesPlayer1().get(i).setOriginX(1+i);
            this.getBodiesPlayer1().get(i).setOriginY(0.9f);
            this.getBodiesPlayer1().get(i).getAmmoBody().getBody().setActive(false);
            this.getBodiesPlayer1().get(i).getAmmoBody().getBody().setTransform(2+i,2,this.getBodiesPlayer1().get(i).getAmmoBody().getBody().getAngle());
            this.getBodiesPlayer1().get(i).getAmmoBody().setOriginX(2+i);
            this.getBodiesPlayer1().get(i).getAmmoBody().setOriginY(2);
        }
        for(int i = 0; i < this.getBodiesPlayer2().size(); i++){
            this.getBodiesPlayer2().get(i).getBody().setActive(true);
            this.getBodiesPlayer2().get(i).getBody().setTransform(FIELD_WIDTH - 1 - i ,0.9f,0);
            this.getBodiesPlayer2().get(i).setOriginX(FIELD_WIDTH - 1 - i);
            this.getBodiesPlayer2().get(i).setOriginY(0.9f);
            this.getBodiesPlayer2().get(i).getAmmoBody().getBody().setActive(false);
            this.getBodiesPlayer2().get(i).getAmmoBody().getBody().setTransform(FIELD_WIDTH - 2 - i, 2,this.getBodiesPlayer2().get(i).getAmmoBody().getBody().getAngle());
            this.getBodiesPlayer2().get(i).getAmmoBody().setOriginX(FIELD_WIDTH - 2 - i);
            this.getBodiesPlayer2().get(i).getAmmoBody().setOriginY(2);
        }
    }

    public void update() {
        world.step(1 / 60f, 6, 2);
        GameStage.getInstance().update();
        for (int i = 0; i < bodiesPlayer1.size(); i++) {
            bodiesPlayer1.get(i).update(GameStage.getInstance().getHeroesPlayer1().get(i));
            bodiesPlayer1.get(i).getAmmoBody().update(GameStage.getInstance().getHeroesPlayer1().get(i).getAmmo());
        }
        for (int i = 0; i < bodiesPlayer2.size(); i++) {
            bodiesPlayer2.get(i).update(GameStage.getInstance().getHeroesPlayer2().get(i));
            bodiesPlayer2.get(i).getAmmoBody().update(GameStage.getInstance().getHeroesPlayer2().get(i).getAmmo());
        }

    }

    public World getWorld() {
        return world;
    }


    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        if (bodyA.getUserData() instanceof Ammo && bodyB.getUserData() instanceof Character){
            ((Ammo) bodyA.getUserData()).hitPlayer((Character) bodyB.getUserData());
            System.out.println("ola1");
        }
        if (bodyA.getUserData() instanceof Character && bodyB.getUserData() instanceof Ammo){
            ((Ammo) bodyB.getUserData()).hitPlayer((Character) bodyA.getUserData());
            System.out.println("ola1");
        }

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
