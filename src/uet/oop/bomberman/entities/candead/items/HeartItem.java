package uet.oop.bomberman.entities.candead.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.candead.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

public class HeartItem extends Item {
    public HeartItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void takingItem(Bomber player) {
        int bomberX = player.getLocationX();
        int bomberY = player.getLocationY();
        int X = x / Sprite.SCALED_SIZE;
        int Y = y / Sprite.SCALED_SIZE;
        if (bomberX == X && bomberY == Y) {
            Sound.play("Item");
            player.setHeart(player.getHeart() + 1);
            dead = true;
        }
    }

    @Override
    public void whenDead() {

    }

}
