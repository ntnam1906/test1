package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Boom extends Entity {
    public Boom(int x, int y, Image img) {
        super(x, y, img);
    }
    private int timing;
    private static int lengthOfBoom = 1;


    public int getTiming() {
        return timing;
    }

    public static int getLengthOfBoom() {
        return lengthOfBoom;
    }

    public static void setLengthOfBoom(int lengthOfBoom) {
        lengthOfBoom = lengthOfBoom;
    }


    @Override
    public void update() {
        timing++;
        if (timing >= 130) {
            img = Sprite.bomb_exploded2.getFxImage();
        } else if (timing >= 125) {
            img = Sprite.bomb_exploded1.getFxImage();
        } else if (timing >= 120) {
            img = Sprite.bomb_exploded.getFxImage();
        } else if (timing % 30 == 0) {
            img = Sprite.bomb.getFxImage();
        } else if (timing % 30 == 10) {
            img = Sprite.bomb_1.getFxImage();
        } else if (timing % 30 == 20) {
            img = Sprite.bomb_2.getFxImage();
        }

    }
}