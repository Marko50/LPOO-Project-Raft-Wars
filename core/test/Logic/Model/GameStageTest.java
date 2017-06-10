package Logic.Model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Bruno on 08/06/2017.
 */
public class GameStageTest {
    @Test
    public void changeTurn() throws Exception {
        GameStage.getInstance().changeTurn();
        Assert.assertEquals(2,GameStage.getInstance().getPlayerTurn());
    }

    @Test
    public void resetGame() throws Exception {

    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void chooseSelected() throws Exception {

    }

    @Test
    public void changeSelected() throws Exception {

    }

    @Test
    public void getSelectedCharacter() throws Exception {

    }

    @Test
    public void getHeroesPlayer1() throws Exception {

    }

    @Test
    public void setHeroesPlayer1() throws Exception {

    }

    @Test
    public void getHeroesPlayer2() throws Exception {

    }

    @Test
    public void setHeroesPlayer2() throws Exception {

    }

    @Test
    public void addHeroPlayer1() throws Exception {

    }

    @Test
    public void addHeroPlayer2() throws Exception {

    }

    @Test
    public void isChangedLevel() throws Exception {

    }

    @Test
    public void setChangedLevel() throws Exception {

    }

    @Test
    public void setGameLevel() throws Exception {
        GameLevel gameLevel = new FirstMap();
        GameStage.getInstance().setGameLevel(gameLevel);
        Assert.assertEquals(gameLevel, GameStage.getInstance().getGameLevel());
    }

    @Test
    public void getPlayer1Score() throws Exception {

    }

    @Test
    public void setPlayer1Score() throws Exception {

    }

    @Test
    public void getPlayer2Score() throws Exception {

    }

    @Test
    public void setPlayer2Score() throws Exception {

    }

}