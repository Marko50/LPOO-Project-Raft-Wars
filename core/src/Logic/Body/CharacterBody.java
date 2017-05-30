package Logic.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import Logic.View.GameStageView;

/**
 * Created by André on 30-04-2017.
 */

public class CharacterBody extends EntityBody {
    private AmmoBody ammoBody;
    public CharacterBody(int x,int y,int ax,int ay, World world) {
        //MACROS FOR THE DISTANCE BETWEEN THE PLAYER AND IT'S AMMO
        super(x,y, world);
        this.ammoBody = new AmmoBody(ax,ay, world);

    }

    @Override
    public void setBody(int x, int y, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.linearVelocity.set(0f,0f);
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);
        // Create character fixture
        CircleShape circle = new CircleShape();
        circle.setRadius(10/GameStageView.PIXEL_TO_METER); // 10cm / 2
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
        this.ammoBody.getBody().applyForceToCenter(5f,5f,true);

    }

    public AmmoBody getAmmoBody() {
        return ammoBody;
    }

    public void setAmmoBody(AmmoBody ammoBody) {
        this.ammoBody = ammoBody;
    }
}
