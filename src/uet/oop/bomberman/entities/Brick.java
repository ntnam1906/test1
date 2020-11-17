package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    private boolean dead = false;
    private int timing = 0;

    public int getTiming() {
        return timing;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Brick(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public int getX() {
        return x / Sprite.SCALED_SIZE;
    }

    @Override
    public int getY() {
        return y / Sprite.SCALED_SIZE;
    }

    @Override
    public void update() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.brick_exploded.getFxImage();
            }
            timing++;
        }
        if (timing == 5) {
            img = Sprite.brick_exploded1.getFxImage();
        } else if (timing == 10) {
            img = Sprite.brick_exploded2.getFxImage();
        }
    }
}
