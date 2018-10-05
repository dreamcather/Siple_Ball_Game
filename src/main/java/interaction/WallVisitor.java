package interaction;

import gameObject.*;

public class WallVisitor implements ObjectInteractVisitor<Collision> {
    Wall wall;

    public WallVisitor(Wall wall) {
        this.wall = wall;
    }

    @Override
    public Collision visit(Enemy enemy) {
        return new WallCollision(wall,enemy);
    }

    @Override
    public Collision visit(Player player) {
        return new WallCollision(wall,player);
    }

    @Override
    public Collision visit(Prize prize) {
        return new WallCollision(wall,prize);
    }

    @Override
    public Collision visit(Wall wall) {
        return null;
    }

    @Override
    public Collision visit(ClosedWall closedWall) {
        return null;
    }
}
