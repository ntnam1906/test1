package uet.oop.bomberman.entities.candead;

import javafx.scene.image.Image;
import uet.oop.bomberman.mainplay.BombermanGame;
import uet.oop.bomberman.StartBombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.staticEntity.Door;

import java.util.List;

public abstract class EntityCanDead extends Entity {

    protected boolean dead;

    protected int timing;

    public void goDoor() {
        List<Door> doorList;
        doorList = BombermanGame.doorObjects;

        for (Door door : doorList) {
            int X = door.getX();
            int Y = door.getY();
            if (x == X && y == Y) {
                for (Door door1 : doorList) {
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
