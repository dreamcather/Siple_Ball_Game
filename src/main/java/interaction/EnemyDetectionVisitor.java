package interaction;

import gameObject.*;

public class EnemyDetectionVisitor implements ObjectInteractionVisitor<Interaction> {
    private final Enemy enemy;

    public EnemyDetectionVisitor(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public Interaction visit(Enemy enemy) {
        return new RegularTwoBallInteraction(this.enemy, enemy);
    }

    @Override
    public Interaction visit(Player player) {
        return new KillCollision(player, enemy);
    }

    @Override
    public Interaction visit(Prize prize) {
        return new RegularTwoBallInteraction(enemy, prize);
    }

    @Override
    public Interaction visit(Wall wall) {
        return new RegularBallAndWallInteraction(enemy, wall);
    }

    @Override
    public Interaction visit(ClosedWall closedWall) {
        return new RegularBallAndClosedWallInteraction(closedWall, enemy);
    }
}
