package uet.oop.bomberman.update;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class PlayerDead {
    public static void checkWhenDead(Bomber player, List<BoomExploded> boomExplodeds,
                                     List<Entity> enemyObjects, List<Boom> booms) {
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
        for (Boom boom : booms) {
            if (boom.getTiming() < 120) {
                continue;
            }
            int X = boom.getX() / Sprite.SCALED_SIZE;
            int Y = boom.getY() / Sprite.SCALED_SIZE;
            if ((X == X1 && Y == Y1)
                    || (X == X2 && Y == Y2)
                    || (X == X3 && Y == Y3)
                    || (X == X4 && Y == Y4)) {
                player.setDead(true);
                player.setTiming(0);
            }
        }
        X1 = (player.getX() + 2);
        Y1 = (player.getY() + 2);
        X2 = (player.getX() + 22);
        Y2 = (player.getY() + 2);
        X3 = (player.getX() + 2);
        Y3 = (player.getY() + 30);
        X4 = (player.getX() + 22);
        Y4 = (player.getY() + 30);

        for (Entity enemy : enemyObjects) {
            int X = enemy.getX();
            int Y = enemy.getY();
            if ((X1 >= X && X1 <= X + 32 && Y1 >= Y && Y1 <= Y + 32)
                    || (X2 >= X && X2 <= X + 32 && Y2 >= Y && Y3 <= Y + 32)
                    || (X3 >= X && X3 <= X + 32 && Y3 >= Y && Y3 <= Y + 32)
                    || (X4 >= X && X4 <= X + 32 && Y4 >= Y && Y4 <= Y + 32)) {
                player.setDead(true);
                player.setTiming(0);
            }
        }
    }
}
