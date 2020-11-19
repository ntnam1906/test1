package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloon extends EntityCanDead {

    private boolean leftOrRight = true;
    private boolean upOrDown = true;
    private boolean typeOfDirection = true;
    private int speed = 1;

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void whenDead() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.balloom_dead.getFxImage();
            }
            timing++;
        }
    }

    public void followRow() {
        if (!leftOrRight) {
            if (BombermanGame.map[y/32].charAt(x/32+1) != '#'
                    && BombermanGame.map[y/32].charAt(x/32+1) != '*'
                    && BombermanGame.map[y/32].charAt(x/32+1) != 'B') {
                x += speed;
                if (timing % 21 == 0) {
                    img = Sprite.balloom_right1.getFxImage();
                } else if (timing % 21 == 7) {
                    img = Sprite.balloom_right2.getFxImage();
                } else if (timing % 21 == 14) {
                    img = Sprite.balloom_right3.getFxImage();
                }
                timing++;
            } else {
                leftOrRight = true;
                timing = 0;
            }
        } else {
            if (BombermanGame.map[y/32].charAt((x-1)/32) != '#'
                    && BombermanGame.map[y/32].charAt((x-1)/32) != '*'
                    && BombermanGame.map[y/32].charAt((x-1)/32) != 'B') {
                x -= speed;
                if (timing % 21 == 0) {
                    img = Sprite.balloom_left1.getFxImage();
                } else if (timing % 21 == 7) {
                    img = Sprite.balloom_left2.getFxImage();
                } else if (timing % 21 == 14) {
                    img = Sprite.balloom_left3.getFxImage();
                }
                timing++;
            } else {
                leftOrRight = false;
                timing = 0;
            }
        }


    }

    public void followCol() {
        if (!upOrDown) {
            if (BombermanGame.map[y/32+1].charAt(x/32) != '#'
                    && BombermanGame.map[y/32+1].charAt(x/32) != '*'
                    && BombermanGame.map[y/32+1].charAt(x/32) != 'B') {
                y += speed;
                if (timing % 21 == 0) {
                    img = Sprite.balloom_left1.getFxImage();
                } else if (timing % 21 == 7) {
                    img = Sprite.balloom_left2.getFxImage();
                } else if (timing % 21 == 14) {
                    img = Sprite.balloom_left3.getFxImage();
                }
                timing++;
            } else {
                upOrDown = true;
                timing = 0;
            }
        } else {
            if (BombermanGame.map[(y-1)/32].charAt(x/32) != '#'
                    && BombermanGame.map[(y-1)/32].charAt(x/32) != '*'
                    && BombermanGame.map[(y-1)/32].charAt(x/32) != 'B') {
                y -= speed;
                if (timing % 21 == 0) {
                    img = Sprite.balloom_right1.getFxImage();
                } else if (timing % 21 == 7) {
                    img = Sprite.balloom_right2.getFxImage();
                } else if (timing % 21 == 14) {
                    img = Sprite.balloom_right3.getFxImage();
                }
                timing++;
            } else {
                upOrDown = false;
                timing = 0;
            }
        }
    }

    public void ballomAction() {
        if (!typeOfDirection) {
            followRow();
        } else {
            followCol();
        }
        Random random = new Random();
        int value = random.nextInt(2) + 1;
        if (x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
            if (value == 1 && ((BombermanGame.map[y/32].charAt(x/32+1) != '#'
                    && BombermanGame.map[y/32].charAt(x/32+1) != '*'
                    && BombermanGame.map[y/32].charAt(x/32+1) != 'B')
                    || (BombermanGame.map[y/32].charAt((x-1)/32) != '#'
                    && BombermanGame.map[y/32].charAt((x-1)/32) != '*'
                    && BombermanGame.map[y/32].charAt((x-1)/32) != 'B'))) {
                typeOfDirection = false;
                timing = 0;
            }
            if (value == 2 && ((BombermanGame.map[y/32+1].charAt(x/32) != '#'
                    && BombermanGame.map[y/32+1].charAt(x/32) != '*'
                    && BombermanGame.map[y/32+1].charAt(x/32) != 'B')
                    || (BombermanGame.map[(y-1)/32].charAt(x/32) != '#'
                    && BombermanGame.map[(y-1)/32].charAt(x/32) != '*'
                    && BombermanGame.map[(y-1)/32].charAt(x/32) != 'B'))) {
                typeOfDirection = true;
                timing = 0;
            }
        }
    }

    @Override
    public void update() {
        whenDead();
        if (!dead) {
            ballomAction();
        }
    }
}
