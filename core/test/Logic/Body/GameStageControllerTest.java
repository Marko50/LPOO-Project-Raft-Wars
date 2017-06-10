package Logic.Body;

import org.junit.Assert;
import org.junit.Test;

import Logic.GameTest;
import Logic.Model.GameStage;

/**
 * Created by Bruno on 08/06/2017.
 */
public class GameStageControllerTest extends GameTest{
    @Test
    public void shootPlayerAmmo() throws Exception {
        GameStageController.getInstance().shootPlayerAmmo(-2.6f,-1.67f);
        GameStageController.getInstance().update();
        GameStage.getInstance().update();
        Assert.assertNotEquals(3,GameStage.getInstance().getHeroesPlayer1().get(0).getHp());
    }

    @Test
    public void reset() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void getWorld() throws Exception {

    }

    @Test
    public void getBodiesPlayer1() throws Exception {

    }

    @Test
    public void setBodiesPlayer1() throws Exception {

    }

    @Test
    public void getBodiesPlayer2() throws Exception {

    }

    @Test
    public void setBodiesPlayer2() throws Exception {

    }

}