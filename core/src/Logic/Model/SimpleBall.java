package Logic.Model;

/**
 * Created by André on 26-04-2017.
 */

public class SimpleBall extends Ammo {
    private static final int SIMPLE_BALL_DAMAGE = 1;
    public SimpleBall(String f){
        super( SIMPLE_BALL_DAMAGE, f);
    }
}
