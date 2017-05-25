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
        final Character c = new Character(10,"sprite-animation1.png");
        final Character c2 = new Character(10,"sprite-animation1.png");
        heroesPlayer1.add(c);
        heroesPlayer2.add(c2);
        heroesPlayer1.get(0).setSelected(true);
        heroesPlayer2.get(0).setSelected(false);
        this.coinsPlayer1 = 0;
        this.coinsPlayer2 = 0;
        this.playerTurn = 1;
        this.chooseSelected(1);
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

    }


    public void chooseSelected(int player){
        if(player == 1){
            for(int i = 0; i < this.getHeroesPlayer1().size(); i++)
            {
                if(this.getHeroesPlayer1().get(i).isActive())
                {
                    this.getHeroesPlayer1().get(i).setSelected(true);
                    break;
                }
            }
        }
        else if(player == 2){
            for(int i = 0; i < this.getHeroesPlayer2().size(); i++)
            {
                if(this.getHeroesPlayer2().get(i).isActive())
                {
                    this.getHeroesPlayer2().get(i).setSelected(true);
                }
            }
        }
    }

    public Character getSelectedCharacter(int player,int counter){
        if(player == 1){
            for(int i = 0; i < this.getHeroesPlayer1().size(); i++)
            {
                if(this.getHeroesPlayer1().get(i).isSelected())
                {
                    counter = i;
                    return this.getHeroesPlayer1().get(i);
                }
            }
        }
        else if(player == 2){
            for(int i = 0; i < this.getHeroesPlayer2().size(); i++)
            {
                if(this.getHeroesPlayer2().get(i).isSelected())
                {
                    counter = i;
                    return this.getHeroesPlayer2().get(i);
                }
            }
        }

        return null;
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
