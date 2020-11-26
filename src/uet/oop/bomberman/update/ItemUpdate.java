package uet.oop.bomberman.update;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class ItemUpdate {



    public static List<Item> checkWhenDead(List<Boom> boomObjects,
                                           List<BoomExploded> boomExplodeds,
                                           List<Item> itemObjects) {
        for (int i = 0; i < itemObjects.size(); ++i) {
            Item item = itemObjects.get(i);
            int X1 = (item.getX()) / Sprite.SCALED_SIZE;
            int Y1 = (item.getY()) / Sprite.SCALED_SIZE;
            boolean check = false;
            for (BoomExploded exploded : boomExplodeds) {
                int X = exploded.getX() / Sprite.SCALED_SIZE;
                int Y = exploded.getY() / Sprite.SCALED_SIZE;
                if (X == X1 && Y == Y1) {
                    check = true;
                    break;
                }
            }
            for (Boom boom : boomObjects) {
                if (boom.getTiming() < 120) {
                    continue;
                }
                int X = boom.getX() / Sprite.SCALED_SIZE;
                int Y = boom.getY() / Sprite.SCALED_SIZE;
                if (X == X1 && Y == Y1) {
                    check = true;
                    break;
                }
            }
            if (check) {
                itemObjects.remove(i);
                --i;
            }
        }
        return itemObjects;
    }
}
