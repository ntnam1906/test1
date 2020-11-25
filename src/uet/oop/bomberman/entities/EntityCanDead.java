package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public abstract class EntityCanDead extends Entity {

    protected boolean dead;

    protected int timing;

    public void goDoor() {
        for (Door door : BombermanGame.doorObjects) {
            int X = door.getX();
            int Y = door.getY();
            if (x == X && y == Y) {
                for (Door door1 : BombermanGame.doorObjects) {
                    X = door1.getX();
                    Y = door1.getY();
                    if (x != X || y != Y) {
                        x = X;
                        y = Y;
                        break;
                    }
                }
                break;
            }
        }
    }

    public EntityCanDead(int x, int y, Image img) {
        super(x, y, img);
        dead = false;
        timing = 0;
    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public abstract void whenDead();
}
