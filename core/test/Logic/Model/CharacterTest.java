package Logic.Model;

import org.junit.Test;

import Logic.Body.GameStageController;

import static org.junit.Assert.*;

/**
 * Created by Bruno on 02/06/2017.
 */
public class CharacterTest {

    @Test
    public void update() throws Exception {
        GameStageController testWorld = GameStageController.getInstance();

        testWorld.getBodiesPlayer1().get(0).shootAmmo(7.5f,0.1f);
        assertEquals(GameStage.getInstance().getHeroesPlayer2().get(0).getHp(), 90);
    }

    @Test
    public void attacked() throws Exception {

    }

    @Test
    public void isSelected() throws Exception {

    }

    @Test
    public void setSelected() throws Exception {

    }

    @Test
    public void getArmor() throws Exception {

    }

    @Test
    public void setArmor() throws Exception {

    }

    @Test
    public void getHp() throws Exception {

    }

    @Test
    public void setHp() throws Exception {

    }

    @Test
    public void isActive() throws Exception {

    }

    @Test
    public void setActive() throws Exception {

    }

    @Test
    public void getAmmo() throws Exception {

    }

    @Test
    public void setAmmo() throws Exception {

    }

}