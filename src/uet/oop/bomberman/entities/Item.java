package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Item extends EntityCanDead {
    public Item(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void whenDead() {

    }

    @Override
    public void update() {

    }
}
