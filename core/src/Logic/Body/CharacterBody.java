package Logic.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import Logic.Model.Entity;
import Logic.View.GameStageView;

/**
 * Created by André on 30-04-2017.
 */

public class CharacterBody extends EntityBody {
    private AmmoBody ammoBody;
    public CharacterBody(int x,int y,int ax,int ay, World world, Entity e1, Entity e2) {
        super(x,y, world, e1);
        this.ammoBody = new AmmoBody(ax,ay, world, e2);
    }

    @Override
    public void setBody(int x, int y, World world, Entity e) {
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
        body.setUserData(e);
        circle.dispose();
    }

    @Override
    public void update(Entity e) {
        if(e.isBeingUsed() == false){
            body.setTransform(originX, originY, body.getAngle());
            body.setLinearVelocity(0,0);
        }
    }

    public void shootAmmo(float x, float y)
    {
        this.ammoBody.getBody().setActive(true);
        this.ammoBody.getBody().applyForceToCenter(x, y, true);
    }

    public AmmoBody getAmmoBody() {
        return ammoBody;
    }

    public void setAmmoBody(AmmoBody ammoBody) {
        this.ammoBody = ammoBody;
    }
}
