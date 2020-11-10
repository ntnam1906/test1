package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;


public class Bomber extends Entity {

    private static double speedX = 0.2;

    private static double speedY = 0.2;

    private int east, west, north, south;

   // protected Keyboard keyboard;

    public static double getSpeedX() {
        return speedX;
    }

    public static void setSpeedX(double speedX) {
        Bomber.speedX = speedX;
    }

    public static double getSpeedY() {
        return speedY;
    }

    public static void setSpeedY(double speedY) {
        Bomber.speedY = speedY;
    }

    public Bomber(double x, double y, Image img) {
        super( x, y, img);
    }

    public void goEast() {
        east++;
        west = 0;
        north = 0;
        south = 0;
        x += speedX;
        int intX = (int) x + 1;
        int intY = (int) (y + 32.0/Sprite.SCALED_SIZE - 0.01);
        boolean checkImg = false;
        if (BombermanGame.map[intY].charAt(intX) == '#'
                || BombermanGame.map[intY].charAt(intX) == '*') {
            if (x + 24.0/Sprite.SCALED_SIZE >= intX) {
                x = intX - 24.0/Sprite.SCALED_SIZE;
                checkImg = true;
            }
        }
        intY = (int) (y + 0.01);
        if (BombermanGame.map[intY].charAt(intX) == '#'
                || BombermanGame.map[intY].charAt(intX) == '*') {
            if (x + 24.0/Sprite.SCALED_SIZE >= intX) {
                x = intX - 24.0/Sprite.SCALED_SIZE;
                checkImg = true;
            }
        }
        if (checkImg) {
            img = Sprite.player_right.getFxImage();
        } else if (east % 2 == 0) {
            img = Sprite.player_right_1.getFxImage();
        } else {
            img = Sprite.player_right_2.getFxImage();
        }
    }

    public void goWest() {
        east = 0;
        west++;
        north = 0;
        south = 0;
        x -= speedX;
        int intX = (int) x;
        int intY = (int) (y + 32.0/Sprite.SCALED_SIZE - 0.01);
        boolean checkImg = false;
        if (BombermanGame.map[intY].charAt(intX) == '#'
                || BombermanGame.map[intY].charAt(intX) == '*') {
            if (x <= intX + 1) {
                x = intX + 1;
                checkImg = true;
            }
        }
        intY = (int) (y + 0.01);
        if (BombermanGame.map[intY].charAt(intX) == '#'
                || BombermanGame.map[intY].charAt(intX) == '*') {
            if (x <= intX + 1) {
                x = intX + 1;
                checkImg = true;
            }
        }
        if (checkImg) {
            img = Sprite.player_left.getFxImage();
        } else if (west % 2 == 0) {
            img = Sprite.player_left_1.getFxImage();
        } else {
            img = Sprite.player_left_2.getFxImage();
        }
    }

    public void goNorth() {
        east = 0;
        west = 0;
        north++;
        south = 0;
        y -= speedY;
        int intX = (int) (x + 0.01);
        int intY = (int) y;
        boolean checkImg = false;
        if (BombermanGame.map[intY].charAt(intX) == '#'
                || BombermanGame.map[intY].charAt(intX) == '*') {
            if (y <= intY + 1) {
                y = intY + 1;
                checkImg = true;
            }
        }
        intX = (int) (x + 24.0/Sprite.SCALED_SIZE - 0.01);
        if (BombermanGame.map[intY].charAt(intX) == '#'
                || BombermanGame.map[intY].charAt(intX) == '*') {
            if (y <= intY + 1) {
                y = intY + 1;
                checkImg = true;
            }
        }
        if (checkImg) {
            img = Sprite.player_up.getFxImage();
        } else if (west % 2 == 0) {
            img = Sprite.player_up_1.getFxImage();
        } else {
            img = Sprite.player_up_2.getFxImage();
        }
    }

    public void goSouth() {
        east = 0;
        west = 0;
        north = 0;
        south++;
        y += speedY;
        int intX = (int) (x + 0.01);
        int intY = (int) y + 1;
        boolean checkImg = false;
        if (BombermanGame.map[intY].charAt(intX) == '#'
                || BombermanGame.map[intY].charAt(intX) == '*') {
            if (y + 32.0/Sprite.SCALED_SIZE >= intY) {
                y = intY - 32.0/Sprite.SCALED_SIZE;
                checkImg = true;
            }
        }
        intX = (int) (x + 24.0/Sprite.SCALED_SIZE - 0.01);
        if (BombermanGame.map[intY].charAt(intX) == '#'
                || BombermanGame.map[intY].charAt(intX) == '*') {
            if (y + 32.0/Sprite.SCALED_SIZE >= intY) {
                y = intY - 32.0/Sprite.SCALED_SIZE;
                checkImg = true;
            }
        }
        if (checkImg) {
            img = Sprite.player_down.getFxImage();
        } else if (west % 2 == 0) {
            img = Sprite.player_down_1.getFxImage();
        } else {
            img = Sprite.player_down_2.getFxImage();
        }
    }
    @Override
    public void update() {
        if (BombermanGame.goEast) {
            goEast();
        } else
        if (BombermanGame.goWest) {
            goWest();
        } else
        if (BombermanGame.goNorth) {
            goNorth();
        } else
        if (BombermanGame.goSouth) {
            goSouth();
        } else
        {
            if (east > 0) img = Sprite.player_right.getFxImage();
            else if (west > 0) img = Sprite.player_left.getFxImage();
            else if (north > 0) img = Sprite.player_up.getFxImage();
            else if (south > 0) img = Sprite.player_down.getFxImage();
        }

    }
}
