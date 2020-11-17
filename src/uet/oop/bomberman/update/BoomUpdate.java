package uet.oop.bomberman.update;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Boom;
import uet.oop.bomberman.entities.BoomExploded;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class BoomUpdate {
    public static List<BoomExploded> createBoomExplosion(Boom boomObject,
                                           List<BoomExploded> boomExplodeds,
                                           List<Brick> brickObjects) {
        int x = boomObject.getX() / Sprite.SCALED_SIZE;
        int y = boomObject.getY() / Sprite.SCALED_SIZE;
        int lengthOfBoom = boomObject.getLengthOfBoom();
        for (int i = x + 1; i <= x + lengthOfBoom; ++i) {
            if (BombermanGame.map[y].charAt(i) == '#') {
                break;
            }
            if (BombermanGame.map[y].charAt(i) == '*') {
                BombermanGame.map[y] = BombermanGame.map[y].substring(0, i) + " " + BombermanGame.map[y].substring(i + 1, BombermanGame.map[y].length());
                for (int u = 0; u < brickObjects.size(); ++u) {
                    Brick object = brickObjects.get(u);
                    if (object.getX() == i && object.getY() == y) {
                        object.setDead(true);
                        break;
                    }
                }
                break;
            }
            if ((i < x + lengthOfBoom && BombermanGame.map[y].charAt(i + 1) == '#')
                    || (i == x + lengthOfBoom)) {
                BoomExploded boomExploded = new BoomExploded(i, y, Sprite.explosion_horizontal_right_last.getFxImage(), 'r');
                boomExplodeds.add(boomExploded);
                break;
            }
            BoomExploded boomExploded = new BoomExploded(i, y, Sprite.explosion_horizontal.getFxImage(), 'h');
            boomExplodeds.add(boomExploded);
        }
        for (int i = x - 1; i >= x - lengthOfBoom; --i) {
            if (BombermanGame.map[y].charAt(i) == '#') {
                break;
            }
            if (BombermanGame.map[y].charAt(i) == '*') {
                BombermanGame.map[y] = BombermanGame.map[y].substring(0, i) + " " + BombermanGame.map[y].substring(i + 1, BombermanGame.map[y].length());
                for (int u = 0; u < brickObjects.size(); ++u) {
                    Brick object = brickObjects.get(u);
                    if (object.getX() == i && object.getY() == y) {
                        object.setDead(true);
                        break;
                    }
                }
                break;
            }
            if ((i > x - lengthOfBoom && BombermanGame.map[y].charAt(i - 1) == '#')
                    || (i == x - lengthOfBoom)) {
                BoomExploded boomExploded = new BoomExploded(i, y, Sprite.explosion_horizontal_left_last.getFxImage(), 'l');
                boomExplodeds.add(boomExploded);
                break;
            }
            BoomExploded boomExploded = new BoomExploded(i, y, Sprite.explosion_horizontal.getFxImage(), 'h');
            boomExplodeds.add(boomExploded);
        }
        for (int j = y + 1; j <= y + lengthOfBoom; ++j) {
            if (BombermanGame.map[j].charAt(x) == '#') {
                break;
            }
            if (BombermanGame.map[j].charAt(x) == '*') {
                BombermanGame.map[j] = BombermanGame.map[j].substring(0, x) + " " + BombermanGame.map[j].substring(x + 1, BombermanGame.map[j].length());
                for (int u = 0; u < brickObjects.size(); ++u) {
                    Brick object = brickObjects.get(u);
                    if (object.getX() == x && object.getY() == j) {
                        object.setDead(true);
                        break;
                    }
                }
                break;
            }
            if ((j < y + lengthOfBoom && BombermanGame.map[j + 1].charAt(x) == '#')
                    || j == y + lengthOfBoom) {
                BoomExploded boomExploded = new BoomExploded(x, j, Sprite.explosion_vertical_down_last.getFxImage(), 'd');
                boomExplodeds.add(boomExploded);
                break;
            }
            BoomExploded boomExploded = new BoomExploded(x, j, Sprite.explosion_vertical.getFxImage(), 'v');
            boomExplodeds.add(boomExploded);
        }
        for (int j = y - 1; j >= y - lengthOfBoom; --j) {
            if (BombermanGame.map[j].charAt(x) == '#') {
                break;
            }
            if (BombermanGame.map[j].charAt(x) == '*') {
                BombermanGame.map[j] = BombermanGame.map[j].substring(0, x) + " " + BombermanGame.map[j].substring(x + 1, BombermanGame.map[j].length());
                for (int u = 0; u < brickObjects.size(); ++u) {
                    Brick object = brickObjects.get(u);
                    if (object.getX() == x && object.getY() == j) {
                        object.setDead(true);
                        break;
                    }
                }
                break;
            }
            if ((j > y - lengthOfBoom && BombermanGame.map[j - 1].charAt(x) == '#')
                    || j == y - lengthOfBoom) {
                BoomExploded boomExploded = new BoomExploded(x, j, Sprite.explosion_vertical_top_last.getFxImage(), 't');
                boomExplodeds.add(boomExploded);
                break;
            }
            BoomExploded boomExploded = new BoomExploded(x, j, Sprite.explosion_vertical.getFxImage(), 'v');
            boomExplodeds.add(boomExploded);
        }
        return boomExplodeds;
    }
}
