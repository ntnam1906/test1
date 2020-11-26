package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Path.BFS;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.Random;

public class CoinOrange extends EntityCanDead {

    public CoinOrange(int x, int y, Image img) {
        super(x, y, img);
    }
    private char directionToGo = ' ';
    private int speed = 2;

    @Override
    public void whenDead() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.coinOrange_dead.getFxImage();
                Sound.play("AA126_11");
            }
            timing++;
        }
    }

    public void goLeft() {
        x -= speed;
        if (timing % 21 == 0) {
            img = Sprite.coinOrange_left1.getFxImage();
        } else if (timing % 21 == 7) {
            img = Sprite.coinOrange_left2.getFxImage();
        } else if (timing % 21 == 14) {
            img = Sprite.coinOrange_left3.getFxImage();
        }
    }

    public void goRight() {
        x += speed;
        if (timing % 21 == 0) {
            img = Sprite.coinOrange_right1.getFxImage();
        } else if (timing % 21 == 7) {
            img = Sprite.coinOrange_right2.getFxImage();
        } else if (timing % 21 == 14) {
            img = Sprite.coinOrange_right3.getFxImage();
        }
    }

    public void goUp() {
        y -= speed;
        if (timing % 21 == 0) {
            img = Sprite.coinOrange_right1.getFxImage();
        } else if (timing % 21 == 7) {
            img = Sprite.coinOrange_right2.getFxImage();
        } else if (timing % 21 == 14) {
            img = Sprite.coinOrange_right3.getFxImage();
        }
    }

    public void goDown() {
        y += speed;
        if (timing % 21 == 0) {
            img = Sprite.coinOrange_left1.getFxImage();
        } else if (timing % 21 == 7) {
            img = Sprite.coinOrange_left2.getFxImage();
        } else if (timing % 21 == 14) {
            img = Sprite.coinOrange_left3.getFxImage();
        }
    }

    @Override
    public void update() {
        whenDead();
        if (dead) {
            return;
        }
        if (x % 32 == 0 && y % 32 == 0) {
            goDoor();
            directionToGo =  BFS.bfs(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE,
                    BombermanGame.player1.getX() / Sprite.SCALED_SIZE,
                    BombermanGame.player1.getY() / Sprite.SCALED_SIZE).charAt(0);
            timing = 0;
            if (directionToGo == ' ') {
                char[] a = new char[4];
                int dem = -1;
                int X = x / Sprite.SCALED_SIZE;
                int Y = y / Sprite.SCALED_SIZE;
                int newX = X + 1, newY = Y;
                if (BombermanGame.map[newY].charAt(newX) != '#'
                        && BombermanGame.map[newY].charAt(newX) != '*'
                        && BombermanGame.map[newY].charAt(newX) != 'B') {
                    ++dem;
                    a[dem] = 'R';
                }
                newX = X - 1; newY = Y;
                if (BombermanGame.map[newY].charAt(newX) != '#'
                        && BombermanGame.map[newY].charAt(newX) != '*'
                        && BombermanGame.map[newY].charAt(newX) != 'B') {
                    ++dem;
                    a[dem] = 'L';
                }
                newX = X; newY = Y - 1;
                if (BombermanGame.map[newY].charAt(newX) != '#'
                        && BombermanGame.map[newY].charAt(newX) != '*'
                        && BombermanGame.map[newY].charAt(newX) != 'B') {
                    ++dem;
                    a[dem] = 'U';
                }
                newX = X; newY = Y + 1;
                if (BombermanGame.map[newY].charAt(newX) != '#'
                        && BombermanGame.map[newY].charAt(newX) != '*'
                        && BombermanGame.map[newY].charAt(newX) != 'B') {
                    ++dem;
                    a[dem] = 'D';
                }
                if (dem != -1) {
                    Random random = new Random();
                    int value = random.nextInt(dem + 1);
                    directionToGo = a[value];
                }
            }
        }
        switch (directionToGo) {
            case 'L': goLeft(); break;
            case 'R': goRight(); break;
            case 'U': goUp(); break;
            case 'D': goDown(); break;
        }
        timing++;
    }

}
