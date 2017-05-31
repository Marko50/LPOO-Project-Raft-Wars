package Logic.Model;


import Logic.Body.EntityBody;

/**
 * Created by André on 21-04-2017.
 */



public class Character extends Entity{
    private Ammo ammo;
    private boolean selected;
    private boolean active;
    private int hp;
    private int armor;

    public Character(int ar, String f, String f2){
        super(f);
        this.selected = false;
        this.armor = ar;
        this.active = true;
        this.hp = 100;
        this.ammo = new SimpleBall(f2);
    }

    @Override
    public void update(EntityBody e){
        if(hp <= 0)
        {
            active = false;
            selected = false;
        }
        else{
            active = true;
        }
        if(e.getBody().getLinearVelocity().x <= FINISHED_MOVEMENT_VELOCITY_X && Math.abs(e.getBody().getLinearVelocity().y) <= FINISHED_MOVEMENT_VELOCITY_Y){
            this.setBeingUsed(false);
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

    public Ammo getAmmo() {
        return ammo;
    }

    public void setAmmo(Ammo ammo) {
        this.ammo = ammo;
    }

}
