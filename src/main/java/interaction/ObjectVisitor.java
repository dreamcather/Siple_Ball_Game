package interaction;

import gameObject.*;

public class ObjectVisitor implements ObjectInteractVisitor<ObjectInteractVisitor<Collision>>{

    @Override
    public ObjectInteractVisitor<Collision> visit(Enemy enemy) {
        return new EnemyVisitor(enemy);
    }

    @Override
    public ObjectInteractVisitor<Collision> visit(Player player) {
        return new HeroVisitor(player);
    }

    @Override
    public ObjectInteractVisitor<Collision> visit(Prize prize) {
        return new PrizeVisitor(prize);
    }

    @Override
    public ObjectInteractVisitor<Collision> visit(Wall wall) {
        return new WallVisitor(wall);
    }

    @Override
    public ObjectInteractVisitor<Collision> visit(ClosedWall closedWall) {
        return new ClosedWallVisitor(closedWall);
    }
}