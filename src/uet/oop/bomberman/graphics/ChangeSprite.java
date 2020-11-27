package uet.oop.bomberman.graphics;

public class ChangeSprite {
    public static void changeTo() {
        Sprite.grass = new Sprite(Sprite.DEFAULT_SIZE, 8, 4, SpriteSheet1.tiles, 16, 16);
        Sprite.brick = new Sprite(Sprite.DEFAULT_SIZE, 9, 9, SpriteSheet1.tiles, 16, 16);
        Sprite.wall = new Sprite(Sprite.DEFAULT_SIZE, 3, 13, SpriteSheet1.tiles, 16, 16);
    }
}