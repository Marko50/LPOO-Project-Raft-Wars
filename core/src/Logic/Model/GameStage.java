package Logic.Model;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;

import java.util.ArrayList;

import Logic.Body.GameStageController;
import Logic.View.MenuView;

/**
 * Created by André on 22-04-2017.
 */
public class GameStage{
    private int playerTurn;
    private GameLevel gameLevel;
    private ArrayList<Character> heroesPlayer1 = new ArrayList<Character>();
    private ArrayList<Character> heroesPlayer2 = new ArrayList<Character>();
    private static GameStage instance;
    private boolean changedLevel;
    private GameStage()
    {
        final Character c = new Character(10,"wyvernfire.png", "ballfire.png");
        final Character c2 = new Character(10,"wyvernwater.png", "ballwater.png");
        final Character c3 = new Character(10,"wyvernfire.png", "ballfire.png");
        final Character c4 = new Character(10,"wyvernwater.png", "ballwater.png");
        heroesPlayer1.add(c);
        heroesPlayer1.add(c3);
        heroesPlayer2.add(c2);
        heroesPlayer2.add(c4);
        heroesPlayer1.get(0).setSelected(true);
        heroesPlayer2.get(0).setSelected(true);
        this.playerTurn = 1;
        this.chooseSelected();
        changedLevel = false;
        gameLevel = new FirstMap();
    }

    public static GameStage getInstance(){
        if (instance == null)
            instance = new GameStage();
        return instance;
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
            heroesPlayer1.get(i).setHp(100);
            heroesPlayer1.get(i).getAmmo().setBeingUsed(false);
            heroesPlayer1.get(i).setActive(true);
        }
        for(int i = 0; i < this.heroesPlayer2.size(); i++){
            heroesPlayer2.get(i).setHp(100);
            heroesPlayer2.get(i).getAmmo().setBeingUsed(false);
            heroesPlayer2.get(i).setActive(true);
        }
    }

    public void update(){
        if(this.isChangedLevel())
            this.changedLevel = false;
        if(checkVictoryPlayer1() || checkVictoryPlayer2()){
            if(this.gameLevel instanceof ThirdMap)
            {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuView());
            }
           // System.out.println("NEXT LEVEL");
            this.setChangedLevel(true);
            gameLevel = gameLevel.nextLevel();
            resetGame();
        }
        for(int i = 0; i < this.getHeroesPlayer1().size(); i++){
            this.getHeroesPlayer1().get(i).update(GameStageController.getInstance().getBodiesPlayer1().get(i));
            this.getHeroesPlayer1().get(i).getAmmo().update(GameStageController.getInstance().getBodiesPlayer1().get(i).getAmmoBody());
        }
        for(int i = 0; i < this.getHeroesPlayer2().size(); i++){
            this.getHeroesPlayer2().get(i).update(GameStageController.getInstance().getBodiesPlayer2().get(i));
            this.getHeroesPlayer2().get(i).getAmmo().update(GameStageController.getInstance().getBodiesPlayer2().get(i).getAmmoBody());
        }
        this.chooseSelected();
    }

    public void chooseSelected(){
        if(playerTurn == 1){
         //   System.out.println("PLAYER TURN 1 CHOOSE SELECTED!");
            for(int i = 0; i < this.getHeroesPlayer1().size(); i++)
            {
                if(this.getHeroesPlayer1().get(i).isActive())
                {
               //     System.out.println("Player turn 1 ACTIVE");
                    this.getHeroesPlayer1().get(i).setSelected(true);
                    break;
                }
            }
        }
        else if(playerTurn == 2){
         //   System.out.println("PLAYER TURN 2 CHOOSE SELECTED!");
            for(int i = 0; i < this.getHeroesPlayer2().size(); i++)
            {
                if(this.getHeroesPlayer2().get(i).isActive())
                {
           //         System.out.println("Player turn 2 ACTIVE");
                    this.getHeroesPlayer2().get(i).setSelected(true);
                    break;
                }
            }
        }
    }
    public int getSelectedCharacter(){
        if(playerTurn == 1){
          //  System.out.println("GET SELECTED PLAYER 1");
            for(int i = 0; i < this.getHeroesPlayer1().size(); i++)
            {
                if(this.getHeroesPlayer1().get(i).isSelected())
                {
            //        System.out.println("PLAYER 1 IS SELECTED");
                    return i;
                }
            }
        }
        else if(playerTurn == 2){
         //   System.out.println("GET SELECTED PLAYER 2");
            for(int i = 0; i < this.getHeroesPlayer2().size(); i++)
            {
                if(this.getHeroesPlayer2().get(i).isSelected())
                {
             //       System.out.println("PLAYER 2 IS SELECTED");
                    return i;
                }
            }
        }

        return 0;
    }

    public ArrayList<Character> getHeroesPlayer1() {
        return heroesPlayer1;
    }

    public void setHeroesPlayer1(ArrayList<Character> heroesPlayer1) {
        this.heroesPlayer1 = heroesPlayer1;
    }
    public ArrayList<Character> getHeroesPlayer2() {
        return heroesPlayer2;
    }

    public void setHeroesPlayer2(ArrayList<Character> heroesPlayer2) {
        this.heroesPlayer2 = heroesPlayer2;
    }
    public void addHeroPlayer1(Logic.Model.Character c1)
    {
        this.heroesPlayer1.add(c1);
    }
    public void addHeroPlayer2(Logic.Model.Character c1) {this.heroesPlayer2.add(c1);}
    public int getPlayerTurn() {return playerTurn;}

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void ammoHitPlayer(Ammo ammo, Character character) {
        if (playerTurn == 1) {
            for (int i = 0; i < this.getHeroesPlayer1().size(); i++) {
                if (ammo.equals(this.getHeroesPlayer1().get(i).getAmmo())) {
                    ammo.hitPlayer(character);
                    return;
                }
            }
        }
        if (playerTurn == 2) {
            for (int i = 0; i < this.getHeroesPlayer2().size(); i++) {
                if (ammo.equals(this.getHeroesPlayer2().get(i).getAmmo())) {
                    ammo.hitPlayer(character);
                    return;
                }
            }
        }

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

    public void setGameLevel(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

}
