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

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

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
            long delta;
            long lastFrameTime = 0;
            @Override
            public void handle(long l) {
                delta = l - lastFrameTime;
                lastFrameTime = l;
                render();
                update();
                System.out.println(delta);
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
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                } else if (map[j].charAt(i) == 'x') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Portal(i, j, Sprite.portal.getFxImage());
                    entities.add(objectEntity);
                } else if (map[j].charAt(i) == 'p') {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    stillObjects.add(object);
                    objectEntity = new Bomber(i, j, Sprite.player_right.getFxImage());
                    entities.add(objectEntity);
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

    public void update() {
        try {
            Thread.sleep(50);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
