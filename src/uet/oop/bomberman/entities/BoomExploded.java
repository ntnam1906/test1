package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class BoomExploded extends Entity {
    private char typeExplosion;
    private int timing = 0;

    public int getTiming() {
        return timing;
    }

    public BoomExploded(int x, int y, Image img, char typeExplosion) {
        super(x, y, img);
        this.typeExplosion = typeExplosion;
    }

    @Override
    public void update() {
        ++timing;
        if (timing == 5) {
            switch (typeExplosion) {
                case 'h':
                    img = Sprite.explosion_horizontal1.getFxImage();
                    break;
                case 'v':
                    img = Sprite.explosion_vertical1.getFxImage();
                    break;
                case 't':
                    img = Sprite.explosion_vertical_top_last1.getFxImage();
                    break;
                case 'd':
                    img = Sprite.explosion_vertical_down_last1.getFxImage();
                    break;
                case 'r':
                    img = Sprite.explosion_horizontal_right_last1.getFxImage();
                    break;
                case 'l':
                    img = Sprite.explosion_horizontal_left_last1.getFxImage();
                    break;
            }
        } else if (timing == 10) {
            switch (typeExplosion) {
                case 'h':
                    img = Sprite.explosion_horizontal2.getFxImage();
                    break;
                case 'v':
                    img = Sprite.explosion_vertical2.getFxImage();
                    break;
                case 't':
                    img = Sprite.explosion_vertical_top_last2.getFxImage();
                    break;
                case 'd':
                    img = Sprite.explosion_vertical_down_last2.getFxImage();
                    break;
                case 'r':
                    img = Sprite.explosion_horizontal_right_last2.getFxImage();
                    break;
                case 'l':
                    img = Sprite.explosion_horizontal_left_last2.getFxImage();
                    break;
            }
        }
    }
}
