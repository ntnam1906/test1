package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Item extends EntityCanDead {
    private char typeOfItem;

    public Item(int x, int y, Image img, char typeOfItem) {
        super(x, y, img);
        this.typeOfItem = typeOfItem;
    }

    public char getTypeOfItem() {
        return typeOfItem;
    }

    public void setTypeOfItem(char typeOfItem) {
        this.typeOfItem = typeOfItem;
    }

    @Override
    public void whenDead() {

    }

    @Override
    public void update() {

    }
}
