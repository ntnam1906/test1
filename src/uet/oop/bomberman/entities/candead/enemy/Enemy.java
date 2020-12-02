package uet.oop.bomberman.entities.candead.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.candead.EntityCanDead;

public abstract class Enemy extends EntityCanDead {
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }
}
