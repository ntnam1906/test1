package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Oneal extends Entity {
    public Oneal(double x, double y, Image img) {
        super(x, y, img);
    }
    int dem = 0;
    @Override
    public void update() {
        if (dem % 3 == 0) x += 1; else x -= 1;
        ++dem;
    }
}
