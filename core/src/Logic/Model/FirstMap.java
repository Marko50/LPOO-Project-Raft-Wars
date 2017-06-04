package Logic.Model;

/**
 * Created by André on 03-06-2017.
 */

public class FirstMap implements GameLevel {
    @Override
    public GameLevel nextLevel() {
        return new SecondMap();
    }
}
