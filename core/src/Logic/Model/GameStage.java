package Logic.Model;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import Logic.Body.GameStageController;
import Logic.View.MenuView;

/**
 * Created by André on 22-04-2017.
 */


public class GameStage{
    private int playerTurn;
    private int player1Score;
    private int player2Score;
    private GameLevel gameLevel;
    private ArrayList<Character> heroesPlayer1 = new ArrayList<Character>();
    private ArrayList<Character> heroesPlayer2 = new ArrayList<Character>();
    private static GameStage instance;
    private boolean changedLevel;
    private GameStage()
    {
        player1Score = 0;
        player2Score = 0;
        final Character c = new Character("wyvernfire.png", "ballfire.png");
        final Character c2 = new Character("wyvernwater.png", "ballwater.png");
        final Character c3 = new Character("wyvernfire.png", "ballfire.png");
        final Character c4 = new Character("wyvernwater.png", "ballwater.png");
        final Character c5 = new Character("wyvernfire.png", "ballfire.png");
        final Character c6 = new Character("wyvernwater.png", "ballwater.png");
        heroesPlayer1.add(c);
        heroesPlayer1.add(c3);
        heroesPlayer2.add(c2);
        heroesPlayer1.add(c5);
        heroesPlayer2.add(c4);
        heroesPlayer2.add(c6);
        heroesPlayer1.get(heroesPlayer1.size()-1).setSelected(true);
        heroesPlayer2.get(heroesPlayer2.size()-1).setSelected(true);
        this.playerTurn = 1;
        changedLevel = false;
        gameLevel = new FirstMap();
    }

    public static GameStage getInstance(){
        if (instance == null)
            instance = new GameStage();
        return instance;
    }

    public void changeTurn(){
        if(this.getPlayerTurn() == 1)
            this.setPlayerTurn(2);
        else if(this.getPlayerTurn() == 2)
            this.setPlayerTurn(1);
    }

    public boolean checkVictoryPlayer1(){
        for(int i = 0; i < this.getHeroesPlayer2().size(); i++) {
            if(this.getHeroesPlayer2().get(i).isActive()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkVictoryPlayer2(){
        for(int i = 0; i < this.getHeroesPlayer1().size(); i++) {
            if(this.getHeroesPlayer1().get(i).isActive()) {
                return false;
            }
        }
        return true;
    }

    public void resetGame(){
        this.playerTurn = 1;
        for(int i = 0; i < this.heroesPlayer1.size(); i++){
            heroesPlayer1.get(i).setHp(3);
            heroesPlayer1.get(i).getAmmo().setBeingUsed(false);
            heroesPlayer1.get(i).setActive(true);
            heroesPlayer1.get(i).setSelected(false);
        }
        for(int i = 0; i < this.heroesPlayer2.size(); i++){
            heroesPlayer2.get(i).setHp(3);
            heroesPlayer2.get(i).getAmmo().setBeingUsed(false);
            heroesPlayer2.get(i).setActive(true);
            heroesPlayer2.get(i).setSelected(false);
        }
        chooseSelected();
    }

    public void update(){
        if(this.isChangedLevel())
            this.changedLevel = false;
        if(checkVictoryPlayer1()){
            this.setPlayer1Score(this.getPlayer1Score() + 1);
            if(this.gameLevel instanceof ThirdMap)
            {
                this.setPlayer1Score(0);
                this.setPlayer2Score(0);
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuView());
            }
            this.setChangedLevel(true);
            gameLevel = gameLevel.nextLevel();
            resetGame();
        }
        else if (checkVictoryPlayer2()){
            this.setPlayer2Score(this.getPlayer2Score() + 1);
            if(this.gameLevel instanceof ThirdMap)
            {
                this.setPlayer1Score(0);
                this.setPlayer2Score(0);
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuView());
            }
            this.setChangedLevel(true);
            gameLevel = gameLevel.nextLevel();
            resetGame();
        }
        for(int i = 0; i < this.getHeroesPlayer1().size(); i++){
            this.getHeroesPlayer1().get(i).getAmmo().update(GameStageController.getInstance().getBodiesPlayer1().get(i).getAmmoBody());
            this.getHeroesPlayer1().get(i).update(GameStageController.getInstance().getBodiesPlayer1().get(i));
        }
        for(int i = 0; i < this.getHeroesPlayer2().size(); i++){
            this.getHeroesPlayer2().get(i).getAmmo().update(GameStageController.getInstance().getBodiesPlayer2().get(i).getAmmoBody());
            this.getHeroesPlayer2().get(i).update(GameStageController.getInstance().getBodiesPlayer2().get(i));
        }
    }
    public void chooseSelected() {
        for (int i = this.getHeroesPlayer1().size() - 1; i >= 0; i--) {
            if (this.getHeroesPlayer1().get(i).isActive()) {
                this.getHeroesPlayer1().get(i).setSelected(true);
                break;
            }
        }
        for (int i = this.getHeroesPlayer2().size() - 1; i >= 0; i--) {
            if (this.getHeroesPlayer2().get(i).isActive()) {
                this.getHeroesPlayer2().get(i).setSelected(true);
                break;
            }
        }
    }
    public void changeSelected(int c){
        if(playerTurn == 1)
        {
            this.getHeroesPlayer1().get(c).setSelected(false);
            if(c == 0)
                c = this.getHeroesPlayer1().size();
            for (int i = c - 1; i >= 0; i--) {
                if (this.getHeroesPlayer1().get(i).isActive()) {
                    this.getHeroesPlayer1().get(i).setSelected(true);
                    c = i;
                    break;
                }
            }
        }
        else if(playerTurn == 2)
        {
            this.getHeroesPlayer2().get(c).setSelected(false);
            if(c == 0)
                c = this.getHeroesPlayer2().size();
            for (int i = c - 1; i >= 0; i--) {
                if (this.getHeroesPlayer2().get(i).isActive()) {
                    this.getHeroesPlayer2().get(i).setSelected(true);
                    c = i;
                    break;
                }
            }
        }
    }
    public int getSelectedCharacter(){
        if(playerTurn == 1){
            for(int i = 0; i < this.getHeroesPlayer1().size(); i++) {
                if(this.getHeroesPlayer1().get(i).isSelected()) {
                    return i;
                }
            }
        }
        else if(playerTurn == 2){
            for(int i = 0; i < this.getHeroesPlayer2().size(); i++) {
                if(this.getHeroesPlayer2().get(i).isSelected()) {
                    return i;
                }
            }
        }
        return 0;
    }

    public ArrayList<Character> getHeroesPlayer1() {
        return heroesPlayer1;
    }

    public ArrayList<Character> getHeroesPlayer2() {
        return heroesPlayer2;
    }

    public int getPlayerTurn() {return playerTurn;}

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean isChangedLevel() {
        return changedLevel;
    }

    public void setChangedLevel(boolean changedLevel) {
        this.changedLevel = changedLevel;
    }

    public GameLevel getGameLevel() {
        return gameLevel;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }
}
