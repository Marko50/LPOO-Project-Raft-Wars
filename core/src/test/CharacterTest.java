package test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import org.junit.Assert;
import org.junit.Test;

import Logic.Body.GameStageController;
import Logic.Model.Game;
import Logic.Model.GameStage;
import Logic.View.GameStageView;

import static org.junit.Assert.*;

/**
 * Created by Bruno on 02/06/2017.
 */
public class CharacterTest extends GameTest {

    @Test
    public void update() throws Exception {
        new Game();
        GameStageController.getInstance().getBodiesPlayer1().get(0).shootAmmo(75f,1f);
        Assert.assertEquals(90, GameStage.getInstance().getHeroesPlayer2().get(0).getHp());
    }

    @Test
    public void attacked() throws Exception {

    }

    @Test
    public void isSelected() throws Exception {
        Assert.assertEquals(true, GameStage.getInstance().getHeroesPlayer1().get(0).isSelected());
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
        Assert.assertEquals(100,GameStage.getInstance().getHeroesPlayer1().get(0).getHp());
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