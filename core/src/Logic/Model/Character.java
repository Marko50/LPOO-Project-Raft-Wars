package Logic.Model;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by André on 21-04-2017.
 */



public class Character extends Actor {
    private Ammo ammo;
    private boolean selected;
    private boolean active;
    private int hp;
    private int armor;
    private String filename;

    public Character(){}

    public Character(int ar, String f, String f2){
        this.selected = false;
        this.armor = ar;
        this.active = true;
        this.hp = 100;
        this.ammo = new SimpleBall(f2);
        this.filename = f;
    }

    public void update(){
        if(hp <= 0)
        {
            active = false;
            selected = false;
        }
        else{
            active = true;
        }

    }

    public void attacked(int dmg){this.hp = this.hp - (dmg - armor);}

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Ammo getAmmo() {
        return ammo;
    }

    public void setAmmo(Ammo ammo) {
        this.ammo = ammo;
    }

}
