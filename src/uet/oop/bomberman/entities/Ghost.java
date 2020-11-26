package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Path.BFS;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.Random;

public class Ghost extends EntityCanDead {

    public Ghost(int x, int y, Image img) {
        super(x, y, img);
    }
    private char directionToGo = ' ';
    private int speed = 1;

    @Override
    public void whenDead() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.ghost_dead.getFxImage();
                Sound.play("AA126_11");
            }
            timing++;
        }
    }

    public void goLeft() {
        x -= speed;
        img = Sprite.ghost_left3.getFxImage();
    }

    public void goRight() {
        x += speed;
        img = Sprite.ghost_right3.getFxImage();
    }

    public void goUp() {
        y -= speed;
        if (timing % 21 == 0) {
            img = Sprite.ghost_right1.getFxImage();
        } else if (timing % 21 == 10) {
            img = Sprite.ghost_right2.getFxImage();
        }
    }

    public void goDown() {
        y += speed;
        if (timing % 21 == 0) {
            img = Sprite.ghost_left1.getFxImage();
        } else if (timing % 21 == 10) {
            img = Sprite.ghost_left2.getFxImage();
        }
    }

    @Override
    public void update() {
        whenDead();
        if(dead) {
            return;
        }
        if(x % 32 == 0 && y % 32 == 0) {
            timing = 0;
            char[] a = new char[4];
            int dem = -1;
            int X = x / Sprite.SCALED_SIZE;
            int Y = y / Sprite.SCALED_SIZE;
            int newX = X + 1, newY = Y;
            if (newX <= BombermanGame.WIDTH- 1)  {
                ++dem;
                a[dem] = 'R';
            }
            newX = X - 1; newY = Y;
            if (newX >= 0) {
                ++dem;
                a[dem] = 'L';
            }
            newX = X; newY = Y - 1;
            if (newY >= 0) {
                ++dem;
                a[dem] = 'U';
            }
            newX = X; newY = Y + 1;
            if (newY <= BombermanGame.HEIGHT - 1) {
                ++dem;
                a[dem] = 'D';
            }
            if (dem != -1) {
                Random random = new Random();
                int value = random.nextInt(dem + 1);
                directionToGo = a[value];
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
