package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

public class Bomber extends Entity {

    private static double speedX = 0;

    private static double speedY = 0;

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

    @Override
    public void update() {
        if (BombermanGame.goEast) {
            east++;
            west = 0;
            north = 0;
            south = 0;
            x += 0.4;
            if (east % 2 == 0) img = Sprite.player_right_1.getFxImage();
            else img = Sprite.player_right_2.getFxImage();
        } else
        if (BombermanGame.goWest) {
            east = 0;
            west++;
            north = 0;
            south = 0;
            x -= 0.4;
            if (west % 2 == 0) img = Sprite.player_left_1.getFxImage();
            else img = Sprite.player_left_2.getFxImage();
        } else
        if (BombermanGame.goNorth) {
            east = 0;
            west = 0;
            north++;
            south = 0;
            y -= 0.4;
            if (north % 2 == 0) img = Sprite.player_up_1.getFxImage();
            else img = Sprite.player_up_2.getFxImage();
        } else
        if (BombermanGame.goSouth) {
            east = 0;
            west = 0;
            north = 0;
            south++;
            y += 0.4;
            if (south % 2 == 0) img = Sprite.player_down_1.getFxImage();
            else img = Sprite.player_down_2.getFxImage();
        } else
        {
            if (east > 0) img = Sprite.player_right.getFxImage();
            else if (west > 0) img = Sprite.player_left.getFxImage();
            else if (north > 0) img = Sprite.player_up.getFxImage();
            else if (south > 0) img = Sprite.player_down.getFxImage();
        }

    }
}
