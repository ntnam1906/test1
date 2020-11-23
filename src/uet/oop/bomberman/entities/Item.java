package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Item extends EntityCanDead {
    private char typeOfItem;
    private boolean active;

    public Item(int x, int y, Image img, char typeOfItem) {
        super(x, y, img);
        this.typeOfItem = typeOfItem;
        this.active = false;
    }

    public char getTypeOfItem() {
        return typeOfItem;
    }

    public void setTypeOfItem(char typeOfItem) {
        this.typeOfItem = typeOfItem;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void whenDead() {

    }

    @Override
    public void update() {

    }
}
