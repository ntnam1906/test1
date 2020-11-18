package uet.oop.bomberman.update;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.BoomExploded;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class PlayerDead {
    public static void checkWhenDead(Bomber player, List<BoomExploded> boomExplodeds) {
        int X1 = (player.getX() + 2) / Sprite.SCALED_SIZE;
        int Y1 = (player.getY() + 2) / Sprite.SCALED_SIZE;
        int X2 = (player.getX() + 22) / Sprite.SCALED_SIZE;
        int Y2 = (player.getY() + 2) / Sprite.SCALED_SIZE;
        int X3 = (player.getX() + 2) / Sprite.SCALED_SIZE;
        int Y3 = (player.getY() + 30) / Sprite.SCALED_SIZE;
        int X4 = (player.getX() + 22) / Sprite.SCALED_SIZE;
        int Y4 = (player.getY() + 30) / Sprite.SCALED_SIZE;
        for (BoomExploded exploded : boomExplodeds) {
            int X = exploded.getX() / Sprite.SCALED_SIZE;
            int Y = exploded.getY() / Sprite.SCALED_SIZE;
            if ((X == X1 && Y == Y1)
                    || (X == X2 && Y == Y2)
                    || (X == X3 && Y == Y3)
                    || (X == X4 && Y == Y4)) {
                player.setDead(true);
                player.setTiming(0);
            }
        }
    }
}
