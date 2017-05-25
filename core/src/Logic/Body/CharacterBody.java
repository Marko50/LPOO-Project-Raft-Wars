package Logic.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
/**
 * Created by André on 30-04-2017.
 */

public class CharacterBody extends EntityBody {
    private AmmoBody ammoBody;
    public CharacterBody(int x, int y , World world) {
        //MACROS FOR THE DISTANCE BETWEEN THE PLAYER AND IT'S AMMO
        super(x, y, world);
        this.ammoBody = new AmmoBody(x+50 ,y+50, world);
    }

    @Override
    public void setBody(int x, int y, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.linearVelocity.set(0f,0f);
        bodyDef.position.set(x,y);
        body = world.createBody(bodyDef);
        // Create character fixture
        CircleShape circle = new CircleShape();
        circle.setRadius(0.50f); // 10cm / 2
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = .5f;      // how heavy is the character
        fixtureDef.friction =  .10f;    // how slippery is the character
        fixtureDef.restitution =  .5f; // how bouncy is the character
        // Attach fixture to body
        body.createFixture(fixtureDef);
        circle.dispose();
    }

    @Override
    public void update() {
        if(body.getLinearVelocity().x <= FINISHED_MOVEMENT_VELOCITY_X && Math.abs(body.getLinearVelocity().y) <= FINISHED_MOVEMENT_VELOCITY_Y){
            body.setTransform(originX, originY, body.getAngle());
            body.setLinearVelocity(0,0);
        }
    }

    public void shootAmmo(int x, int y)
    {
        this.ammoBody.getBody().setActive(true);
        this.getAmmoBody().getBody().setLinearVelocity(GameStageController.AMMO_SPEED ,0);
    }

    public AmmoBody getAmmoBody() {
        return ammoBody;
    }

    public void setAmmoBody(AmmoBody ammoBody) {
        this.ammoBody = ammoBody;
    }
}
