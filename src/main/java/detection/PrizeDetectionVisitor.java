package detection;

import gameObject.*;
import interaction.ObjectInteractVisitor;

public class PrizeDetectionVisitor implements ObjectInteractVisitor<Detection> {
    Prize prize;

    public PrizeDetectionVisitor(Prize prize) {
        this.prize = prize;
    }

    @Override
    public Detection visit(Enemy enemy) {
        return new RegularTwoBallDetection(prize,enemy);
    }

    @Override
    public Detection visit(Player player) {
        return new RegularTwoBallDetection(prize,player);
    }

    @Override
    public Detection visit(Prize prize) {
        return new RegularTwoBallDetection(this.prize,prize);
    }

    @Override
    public Detection visit(Wall wall) {
        return new RegularBallAndWallDetection(prize,wall);
    }

    @Override
    public Detection visit(ClosedWall closedWall) {
        return new RegularBallAndClosedWallDetection(closedWall,prize);
    }
}
