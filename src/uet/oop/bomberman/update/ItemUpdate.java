package uet.oop.bomberman.update;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class ItemUpdate {
    public static void createItem(Brick brick, List<Item> itemObjects) {
        int x = brick.getX() / Sprite.SCALED_SIZE;
        int y = brick.getY() / Sprite.SCALED_SIZE;
        for (Item item : itemObjects) {
            int X = item.getX() / Sprite.SCALED_SIZE;
            int Y = item.getY() / Sprite.SCALED_SIZE;
            if (x == X && y == Y) {
                item.setActive(true);
            }
        }
    }
}
