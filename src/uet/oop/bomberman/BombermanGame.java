package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.Path.BFS;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.loadmap.LoadMap;
import uet.oop.bomberman.update.BoomUpdate;
import uet.oop.bomberman.update.EnemyDead;
import uet.oop.bomberman.update.ItemUpdate;
import uet.oop.bomberman.update.PlayerDead;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static int WIDTH = 31;
    public static int HEIGHT = 13;
    public static boolean goNorth;
    public static boolean goSouth;
    public static boolean goWest;
    public static boolean goEast;
    public static String[] map;


    private static GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> enemyObjects = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    public static Bomber player1;
    private List<Boom> boomObjects = new ArrayList<>();
    private List<BoomExploded> boomExplodeds = new ArrayList<>();
    private List<Brick> brickObjects = new ArrayList<>();
    private List<Item> itemObjects = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = true; break;
                case DOWN:  goSouth = true; break;
                case LEFT:  goWest  = true; break;
                case RIGHT: goEast  = true; break;
                case SPACE: {
                    if (boomObjects.size() >= player1.getSizeOfBoom()) {
                        break;
                    }
                    Boom boom = new Boom(player1.getLocationX(), player1.getLocationY(), Sprite.bomb.getFxImage());
                    boomObjects.add(boom);
                }
            }
        });
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = false; break;
                case DOWN:  goSouth = false; break;
                case LEFT:  goWest  = false; break;
                case RIGHT: goEast  = false; break;
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };

        timer.start();
        createMap();
    }

    public void createMap() {
        map = LoadMap.loadMap("res/levels/Level1.txt");
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                Entity objectEntity;
                if (map[j].charAt(i) == '#') {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                    stillObjects.add(object);
                } else if (map[j].charAt(i) == '*') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Brick(i, j, Sprite.brick.getFxImage());
                    brickObjects.add((Brick)objectEntity);
                } else if (map[j].charAt(i) == 'x') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Portal(i, j, Sprite.portal.getFxImage());
                    stillObjects.add(objectEntity);
                } else if (map[j].charAt(i) == 'p') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Bomber(i, j, Sprite.player_right.getFxImage());
                    player1 =  (Bomber) objectEntity;
                } else if (map[j].charAt(i) == '1') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Balloon(i, j, Sprite.balloom_left1.getFxImage());
                    enemyObjects.add(objectEntity);
                } else if (map[j].charAt(i) == '2') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                    enemyObjects.add(objectEntity);
                } else if (map[j].charAt(i) == 'b' || map[j].charAt(i) == 'f' || map[j].charAt(i) == 's') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Brick(i, j, Sprite.brick.getFxImage());
                    brickObjects.add((Brick)objectEntity);
                    Item itemObject = new Item(i, j, Sprite.powerup_bombs.getFxImage(), 'b');
                    switch (map[j].charAt(i)) {
                        case 'f':
                            itemObject = new Item(i, j, Sprite.powerup_flames.getFxImage(), 'f');
                            break;
                        case 's':
                            itemObject = new Item(i, j, Sprite.powerup_speed.getFxImage(), 's');
                    }
                    itemObjects.add(itemObject);
                    BombermanGame.map[j] = BombermanGame.map[j].substring(0, i) + "*" + BombermanGame.map[j].substring(i + 1, BombermanGame.map[j].length());
                } else if (map[j].charAt(i) == '3') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Ghost(i, j, Sprite.ghost_left1.getFxImage());
                    enemyObjects.add(objectEntity);
                }
                else if (map[j].charAt(i) == '4') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new CoinRed(i, j, Sprite.coinRed_left1.getFxImage());
                    enemyObjects.add(objectEntity);
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                }

            }
        }
    }

    public void updateObject() {
        for (int i = 0; i < boomObjects.size(); ++i) {
            if (boomObjects.get(i).getTiming() == 120) {
                boomExplodeds = BoomUpdate.createBoomExplosion(boomObjects.get(i),
                        boomExplodeds, brickObjects, boomObjects);
            }
            if (boomObjects.get(i).getTiming() == 135) {
                boomObjects.remove(i);
                --i;
            }
        }
        for (int i = 0; i < boomExplodeds.size(); ++i) {
            if (boomExplodeds.get(i).getTiming() == 15) {
                boomExplodeds.remove(i);
                --i;
            }
        }
        for (int i = 0; i < brickObjects.size(); ++i) {
            int time = brickObjects.get(i).getTiming();
            if (time == 15) {
                ItemUpdate.createItem(brickObjects.get(i), itemObjects);
                brickObjects.remove(i);
                --i;
            }
        }
        for (int i = 0; i < enemyObjects.size(); ++i) {
            EntityCanDead entity = (EntityCanDead)enemyObjects.get(i);
            if (!entity.isDead()) {
                EnemyDead.checkWhenDead(entity, boomExplodeds);
            } else {
                if (entity.getTiming() == 20) {
                    enemyObjects.remove(i);
                    --i;
                    if (entity instanceof CoinRed) {
                        Entity newEntity = new CoinOrange(entity.getX()/Sprite.SCALED_SIZE, entity.getY()/Sprite.SCALED_SIZE, Sprite.coinOrange_left1.getFxImage());
                        enemyObjects.add(newEntity);
                    }
                }
            }
        }
        if (!player1.isDead()) {
            PlayerDead.checkWhenDead(player1, boomExplodeds, enemyObjects);
        }
    }

    public void update() {
        try {
            Thread.sleep(20);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        boomObjects.forEach(Boom::update);
        boomExplodeds.forEach(BoomExploded::update);
        brickObjects.forEach(Brick::update);
        itemObjects.forEach(Item::update);
        enemyObjects.forEach(Entity::update);
        player1.update();
        updateObject();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        boomObjects.forEach(g -> g.render(gc));
        boomExplodeds.forEach(g -> g.render(gc));
        itemObjects.forEach(g -> g.render(gc));
        brickObjects.forEach(g -> g.render(gc));
        enemyObjects.forEach(g -> g.render(gc));
        player1.render(gc);
    }
}
