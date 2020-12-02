package uet.oop.bomberman.entities.candead.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.candead.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {
    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void takingItem(Bomber player) {
        int bomberX = player.getLocationX();
        int bomberY = player.getLocationY();
        int X = x / Sprite.SCALED_SIZE;
        int Y = y / Sprite.SCALED_SIZE;
        if (bomberX == X && bomberY == Y) {
            if (player.getSizeOfBoom() <= 5) {
                player.setSizeOfBoom(player.getSizeOfBoom() + 1);
            }
            dead = true;
        }
    }

    @Override
    public void whenDead() {

    }

    @Override
    public void update() {
        takingItem(BombermanGame.player1);
    }
}
