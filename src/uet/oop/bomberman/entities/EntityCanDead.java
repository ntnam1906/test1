package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class EntityCanDead extends Entity {

    protected boolean dead;

    protected int timing;

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
