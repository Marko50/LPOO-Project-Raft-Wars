package Logic.Model;
import java.util.ArrayList;
/**
 * Created by André on 22-04-2017.
 */
public class GameStage{
    private int playerTurn;
    private int coinsPlayer1;
    private int coinsPlayer2;
    private ArrayList<Character> heroesPlayer1 = new ArrayList<Character>();
    private ArrayList<Character> heroesPlayer2 = new ArrayList<Character>();
    private static GameStage instance;

    private GameStage()
    {
        final Character c = new Character(10,"wyvern_fire.png");
        final Character c2 = new Character(10,"wyvern_water.png");
        heroesPlayer1.add(c);
        heroesPlayer2.add(c2);
        heroesPlayer1.get(0).setSelected(true);
        heroesPlayer2.get(0).setSelected(true);
        this.coinsPlayer1 = 0;
        this.coinsPlayer2 = 0;
        this.playerTurn = 1;
        this.chooseSelected();
    }

    public static GameStage getInstance(){
        if (instance == null)
            instance = new GameStage();
        return instance;
    }

    public void update(){
        for(int i = 0; i < this.getHeroesPlayer1().size(); i++){
            this.getHeroesPlayer1().get(i).update();
        }
        for(int i = 0; i < this.getHeroesPlayer2().size(); i++){
            this.getHeroesPlayer2().get(i).update();
        }
        this.chooseSelected();
    }

    public void chooseSelected(){
        if(playerTurn == 1){
            System.out.println("PLAYER TURN 1 CHOOSE SELECTED!");
            for(int i = 0; i < this.getHeroesPlayer1().size(); i++)
            {
                if(this.getHeroesPlayer1().get(i).isActive())
                {
                    System.out.println("Player turn 1 ACTIVE");
                    this.getHeroesPlayer1().get(i).setSelected(true);
                    break;
                }
            }
        }
        else if(playerTurn == 2){
            System.out.println("PLAYER TURN 2 CHOOSE SELECTED!");
            for(int i = 0; i < this.getHeroesPlayer2().size(); i++)
            {
                if(this.getHeroesPlayer2().get(i).isActive())
                {
                    System.out.println("Player turn 2 ACTIVE");
                    this.getHeroesPlayer2().get(i).setSelected(true);
                    break;
                }
            }
        }
    }
    public int getSelectedCharacter(){
        if(playerTurn == 1){
            System.out.println("GET SELECTED PLAYER 1");
            for(int i = 0; i < this.getHeroesPlayer1().size(); i++)
            {
                if(this.getHeroesPlayer1().get(i).isSelected())
                {
                    System.out.println("PLAYER 1 IS SELECTED");
                    return i;
                }
            }
        }
        else if(playerTurn == 2){
            System.out.println("GET SELECTED PLAYER 2");
            for(int i = 0; i < this.getHeroesPlayer2().size(); i++)
            {
                if(this.getHeroesPlayer2().get(i).isSelected())
                {
                    System.out.println("PLAYER 2 IS SELECTED");
                    return i;
                }
            }
        }

        return 0;
    }

    public int getCoinsPlayer1() {
        return coinsPlayer1;
    }

    public void setCoinsPlayer1(int coinsPlayer1) {
        this.coinsPlayer1 = coinsPlayer1;
    }

    public int getCoinsPlayer2() {
        return coinsPlayer2;
    }

    public void setCoinsPlayer2(int coinsPlayer2) {
        this.coinsPlayer2 = coinsPlayer2;
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
}
