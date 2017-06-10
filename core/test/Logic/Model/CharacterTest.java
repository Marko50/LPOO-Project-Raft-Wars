package Logic.Model;

import org.junit.Assert;
import org.junit.Test;

import Logic.GameTest;

/**
 * Created by Bruno on 02/06/2017.
 */
public class CharacterTest extends GameTest {

    @Test
    public void isSelected() throws Exception {
        Assert.assertEquals(true, GameStage.getInstance().getHeroesPlayer1().get(1).isSelected());
    }

    @Test
    public void setSelected() throws Exception {
        GameStage.getInstance().getHeroesPlayer2().get(0).setSelected(true);
        Assert.assertEquals(true, GameStage.getInstance().getHeroesPlayer2().get(0).isSelected());
    }

    @Test
    public void getHp() throws Exception {
        for(int i = 0; i < GameStage.getInstance().getHeroesPlayer1().size(); i++) {
            Assert.assertEquals(3,GameStage.getInstance().getHeroesPlayer1().get(i).getHp());
        }
        for(int i = 0; i < GameStage.getInstance().getHeroesPlayer2().size(); i++) {
            Assert.assertEquals(3,GameStage.getInstance().getHeroesPlayer2().get(i).getHp());
        }
    }
}