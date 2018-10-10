package interaction;

import gameObject.*;
import visual.Camera;
import visual.visualInformation.VisualInformation;

public class EnemyDetectionVisitor implements ObjectInteractionVisitor<Interaction> {
    Enemy enemy;

    public EnemyDetectionVisitor(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public Interaction visit(Enemy enemy) {
        return new RegularTwoBallInteraction(this.enemy,enemy);
    }

    @Override
    public Interaction visit(Player player) {
        return new RegularTwoBallInteraction(enemy,player);
    }

    @Override
    public Interaction visit(Prize prize) {
        return new RegularTwoBallInteraction(enemy,prize);
    }

    @Override
    public Interaction visit(Wall wall) {
        return new RegularBallAndWallInteraction(enemy,wall);
    }

    @Override
    public Interaction visit(ClosedWall closedWall) {
        return new RegularBallAndClosedWallInteraction(closedWall,enemy);
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        return new BallVisibleVisitor(enemy).isVisible(camera);

    }
}
