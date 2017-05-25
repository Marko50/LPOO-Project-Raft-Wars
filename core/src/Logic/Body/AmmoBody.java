package Logic.Body;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by André on 30-04-2017.
 */

public class AmmoBody extends EntityBody {
    public AmmoBody(int x, int y,World world) {
        super(x, y,world);
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
        circle.setRadius(0.05f); // 10cm / 2
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = .5f;      // how heavy is the character
        fixtureDef.friction =  1;    // how slippery is the character
        fixtureDef.restitution =  .5f; // how bouncy is the character
        // Attach fixture to body
        body.createFixture(fixtureDef);
        body.setBullet(true);
        circle.dispose();
    }

    @Override
    public void update() {
        if(body.getLinearVelocity().x <= FINISHED_MOVEMENT_VELOCITY_X && Math.abs(body.getLinearVelocity().y) <= FINISHED_MOVEMENT_VELOCITY_Y){
            body.setTransform(originX, originY, body.getAngle());
            body.setLinearVelocity(0,0);
            body.setActive(false);
        }
    }

}
