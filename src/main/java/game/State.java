package game;

import gameObject.GameObject;
import gameObject.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class State implements Serializable {
    private final Player player;
    private final ArrayList<GameObject> gameObjects;

    public State(Player player, ArrayList<GameObject> gameObjects) {
        this.player = player;
        this.gameObjects = gameObjects;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
}
