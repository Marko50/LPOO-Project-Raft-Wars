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

public class AmmoBody extends EntityBody {
    public AmmoBody(float x, float y, World world, Entity e) {
        super(x,y,world,e);
    }



    @Override
    public void setBody(float x, float y , World world,Entity e) {
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
        fixtureDef.density = .1f;      // how heavy is the character
        fixtureDef.friction =  1f;    // how slippery is the character
        fixtureDef.restitution =  .5f; // how bouncy is the character
        // Attach fixture to body
        body.createFixture(fixtureDef);
        body.setBullet(true);
        body.setActive(false);
        body.setUserData(e);
        circle.dispose();
    }

    @Override
    public void update(Entity e) {
        if(e.isBeingUsed() == false){
            body.setTransform(originX, originY, body.getAngle());
            body.setLinearVelocity(0,0);
            body.setActive(false);
        }
    }

}
