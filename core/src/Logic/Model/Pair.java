package Logic.Model;

/**
 * Created by André on 26-04-2017.
 */

public class Pair {
    private  Ammo left;
    private  long right;

    public Pair(Ammo left, long right) {
        this.left = left;
        this.right = right;
    }

    public Ammo getLeft() { return left; }
    public long getRight() { return right; }
    public void setLeft(Ammo left) {
        this.left = left;
    }

    public void setRight(long right) {
        this.right = right;
    }


}
