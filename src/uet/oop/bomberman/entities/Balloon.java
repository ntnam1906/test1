package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends EntityCanDead {
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void whenDead() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.balloom_dead.getFxImage();
            }
            timing++;
        }
    }


    @Override
    public void update() {
        whenDead();
    }
}
