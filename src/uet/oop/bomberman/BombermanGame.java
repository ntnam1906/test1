package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.loadmap.LoadMap;

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

    public static GraphicsContext getGc() {
        return gc;
    }

    private static GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private Bomber player1;
    private List<Boom> boomObjects = new ArrayList<>();
    private List<BoomExploded> boomExplodeds = new ArrayList<>();

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
            //long delta;
            //long lastFrameTime = 0;
            @Override
            public void handle(long l) {
                //delta = l - lastFrameTime;
                //lastFrameTime = l;
                render();
                update();
                //System.out.println(delta);
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
                    entities.add(objectEntity);
                } else if (map[j].charAt(i) == 'x') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Portal(i, j, Sprite.portal.getFxImage());
                    entities.add(objectEntity);
                } else if (map[j].charAt(i) == 'p') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Bomber(i, j, Sprite.player_right.getFxImage());
                    player1 =  (Bomber) objectEntity;
                } else if (map[j].charAt(i) == '1') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Balloon(i, j, Sprite.balloom_left1.getFxImage());
                    entities.add(objectEntity);
                } else if (map[j].charAt(i) == '2') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                    entities.add(objectEntity);
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                }

            }
        }
    }

    public void createBoomExplosion(Boom boomObject) {
        int x = (int) boomObject.getX();
        int y = (int) boomObject.getY();
        int lengthOfBoom = boomObject.getLengthOfBoom();
        for (int i = x + 1; i <= x + lengthOfBoom; ++i) {
            if (map[y].charAt(i) == '#') {
                break;
            }
            if (map[y].charAt(i) == '*') {
                map[y] = map[y].substring(0, i) + " " + map[y].substring(i + 1, map[y].length());
                for (int u = 0; u < entities.size(); ++u) {
                    if (entities.get(u) instanceof Brick) {
                        Brick object = (Brick)entities.get(u);
                        if (object.getX() == i && object.getY() == y) {
                            object.setDead(true);
                            break;
                        }
                    }
                }
                break;
            }
            if ((i < x + lengthOfBoom && map[y].charAt(i + 1) == '#')
                    || (i == x + lengthOfBoom)) {
                BoomExploded boomExploded = new BoomExploded(i, y, Sprite.explosion_horizontal_right_last.getFxImage(), 'r');
                boomExplodeds.add(boomExploded);
                break;
            }
            BoomExploded boomExploded = new BoomExploded(i, y, Sprite.explosion_horizontal.getFxImage(), 'h');
            boomExplodeds.add(boomExploded);
        }
        for (int i = x - 1; i >= x - lengthOfBoom; --i) {
            if (map[y].charAt(i) == '#') {
                break;
            }
            if (map[y].charAt(i) == '*') {
                map[y] = map[y].substring(0, i) + " " + map[y].substring(i + 1, map[y].length());
                for (int u = 0; u < entities.size(); ++u) {
                    if (entities.get(u) instanceof Brick) {
                        Brick object = (Brick)entities.get(u);
                        if (object.getX() == i && object.getY() == y) {
                            object.setDead(true);
                            break;
                        }
                    }
                }
                break;
            }
            if ((i > x - lengthOfBoom && map[y].charAt(i - 1) == '#')
                    || (i == x - lengthOfBoom)) {
                BoomExploded boomExploded = new BoomExploded(i, y, Sprite.explosion_horizontal_left_last.getFxImage(), 'l');
                boomExplodeds.add(boomExploded);
                break;
            }
            BoomExploded boomExploded = new BoomExploded(i, y, Sprite.explosion_horizontal.getFxImage(), 'h');
            boomExplodeds.add(boomExploded);
        }
        for (int j = y + 1; j <= y + lengthOfBoom; ++j) {
            if (map[j].charAt(x) == '#') {
                break;
            }
            if (map[j].charAt(x) == '*') {
                map[j] = map[j].substring(0, x) + " " + map[j].substring(x + 1, map[j].length());
                for (int u = 0; u < entities.size(); ++u) {
                    if (entities.get(u) instanceof Brick) {
                        Brick object = (Brick)entities.get(u);
                        if (object.getX() == x && object.getY() == j) {
                            object.setDead(true);
                            break;
                        }
                    }
                }
                break;
            }
            if ((j < y + lengthOfBoom && map[j + 1].charAt(x) == '#')
                    || j == y + lengthOfBoom) {
                BoomExploded boomExploded = new BoomExploded(x, j, Sprite.explosion_vertical_down_last.getFxImage(), 'd');
                boomExplodeds.add(boomExploded);
                break;
            }
            BoomExploded boomExploded = new BoomExploded(x, j, Sprite.explosion_vertical.getFxImage(), 'v');
            boomExplodeds.add(boomExploded);
        }
        for (int j = y - 1; j >= y - lengthOfBoom; --j) {
            if (map[j].charAt(x) == '#') {
                break;
            }
            if (map[j].charAt(x) == '*') {
                map[j] = map[j].substring(0, x) + " " + map[j].substring(x + 1, map[j].length());
                for (int u = 0; u < entities.size(); ++u) {
                    if (entities.get(u) instanceof Brick) {
                        Brick object = (Brick)entities.get(u);
                        if (object.getX() == x && object.getY() == j) {
                            object.setDead(true);
                            break;
                        }
                    }
                }
                break;
            }
            if ((j > y - lengthOfBoom && map[j - 1].charAt(x) == '#')
                    || j == y - lengthOfBoom) {
                BoomExploded boomExploded = new BoomExploded(x, j, Sprite.explosion_vertical_top_last.getFxImage(), 't');
                boomExplodeds.add(boomExploded);
                break;
            }
            BoomExploded boomExploded = new BoomExploded(x, j, Sprite.explosion_vertical.getFxImage(), 'v');
            boomExplodeds.add(boomExploded);
        }
    }

    public void updateObject() {
        for (int i = 0; i < boomObjects.size(); ++i) {
            if (boomObjects.get(i).getTiming() == 120) {
                createBoomExplosion(boomObjects.get(i));
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
        for (int i = 0; i < entities.size(); ++i) {
            if (entities.get(i) instanceof Brick) {
                int time = ((Brick) entities.get(i)).getTiming();
                if (time == 15) {
                    entities.remove(i);
                    --i;
                }
            }
        }
    }

    public void update() {
        try {
            Thread.sleep(30);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        entities.forEach(Entity::update);
        boomObjects.forEach(Boom::update);
        boomExplodeds.forEach(BoomExploded::update);
        player1.update();
        updateObject();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        boomObjects.forEach(g -> g.render(gc));
        boomExplodeds.forEach(g -> g.render(gc));
        player1.render(gc);

    }
}
