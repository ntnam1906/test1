package uet.oop.bomberman.entities.candead.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.MultiplayerBombermanGame;
import uet.oop.bomberman.StartBombermanGame;
import uet.oop.bomberman.entities.candead.Bomber;
import uet.oop.bomberman.entities.candead.EntityCanDead;

public abstract class Item extends EntityCanDead {

    public Item(int x, int y, Image img) {
        super(x, y, img);
    }

    public abstract void takingItem(Bomber player);

    @Override
    public void whenDead() {

    }

    @Override
    public void update() {
        if (StartBombermanGame.type == 1) {
            takingItem(BombermanGame.player1);
        } else {
            takingItem(MultiplayerBombermanGame.player1);
            takingItem(MultiplayerBombermanGame.player2);
        }
    }
}
