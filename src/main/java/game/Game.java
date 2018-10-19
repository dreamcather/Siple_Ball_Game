package game;

import geometry.MyPoint;
import control.MotionControl;
import javafx.animation.AnimationTimer;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import gameObject.*;
import save.Reader;
import visual.Camera;

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private Player hero;
    private Label score;
    private Label lifeCounter;
    private int countPoint;
    private PhysicGame physicGame;
    private int prizeCount;
    private MotionControl motionControl;
    private AnimationTimer animationTimer;
    private boolean active;
    private VisualGame visualGame;
    private Camera camera;

    public Game(AnchorPane panel) throws IOException {
        physicGame = new PhysicGame();
        Reader reader = new Reader("output.txt",physicGame);
        score = new Label("Score");
        score.setLayoutX(650);
        score.setLayoutY(50);
        panel.getChildren().add(score);
        countPoint = 0;
        lifeCounter = new Label("Life");
        panel.getChildren().add(lifeCounter);
        lifeCounter.setLayoutX(650);
        lifeCounter.setLayoutY(70);
        prizeCount = 0;
        motionControl = new MotionControl();
        active = true;
        int width = 500;
        visualGame = new VisualGame(panel, new MyPoint(50, 50), width, 1000, 1000);
        camera = visualGame.getCamera();
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

    }

    private void createPrize() {
        Prize prize = new Prize(Math.random(),
                                Math.random(),
                                Math.random() * 5,
                                Math.random() * 500,
                                Math.random() * 500,
                                15,0);
        physicGame.addBall(prize);
    }

    public void mouseClick(MouseEvent event) {
        motionControl = new MotionControl(event, camera);
        physicGame.setMotionControl(motionControl);
    }

    public void click(KeyCode keyCode) {
    }

    public void start() {
        animationTimer.start();
        active = true;
    }

    public void stop() {
        animationTimer.stop();
        active = false;
    }

    private void newGame() {
        hero.setLifeCount(5);
    }

    private void gameOver() {
        stop();
    }

    private void update() {
        ArrayList<GameObject> state = physicGame.getObjectList();
        Player player =findPlayer(state);
        camera.setPosition(player.getPosition());
        visualGame.update(state);


    }

    private Player findPlayer(ArrayList<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            if(gameObject.type.equals("P")){
                return (Player)gameObject;
            }
        }
        return null;
    }
    public boolean isActive() {
        return active;
    }

    public void exit(){
        stop();
        physicGame.exit();
    }

}
