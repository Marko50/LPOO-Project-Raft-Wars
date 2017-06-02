package Logic.Model;

/**
 * Created by André on 26-04-2017.
 */

public class SimpleBall extends Ammo {
    private static final int SIMPLE_BALL_DAMAGE = 60;
    private static final float SIMPLE_BALL_ACTION_LENGTH = 0f;
    public SimpleBall(String f){
        super( SIMPLE_BALL_DAMAGE, SIMPLE_BALL_ACTION_LENGTH, f);
    }
}
