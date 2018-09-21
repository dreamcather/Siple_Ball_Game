package interaction;

import geometry.Line;
import geometry.Point;
import geometry.Vector;
import object.Ball;

public class RegularCollision extends Collision {
    private Ball first;
    private Ball second;

    public RegularCollision(Ball first, Ball second) {
        this.first = first;
        this.second = second;
    }

    private double getDistanceBetweenBall() {
        return first.getPosition().getDistanceToPoint(second.getPosition());
    }

    private void correctionPosition() {
        if (this.getDistanceBetweenBall() < first.getRadius() + second.getRadius()) {
            double speedSum = first.getSpeedOfMotion() + second.getSpeedOfMotion();
            double firstProportion = first.getSpeedOfMotion() / speedSum;
            double secondProportion = second.getSpeedOfMotion() / speedSum;
            Vector firstToSecond = new Vector(first.getPosition(), second.getPosition());
            Vector secondToFirst = new Vector(second.getPosition(), first.getPosition());
            double length = first.getRadius() + second.getRadius() - this.getDistanceBetweenBall();
            firstToSecond.setLength(length * firstProportion);
            secondToFirst.setLength(length * secondProportion);
            first.setPosition(secondToFirst.getEndPointVector(first.getPosition()));
            second.setPosition(firstToSecond.getEndPointVector(second.getPosition()));
        }
    }

    @Override
    public void collide() {
        correctionPosition();

        Line lineParallelCollideThroughBallsCenter = new Line(first.getPosition(), second.getPosition());
        Line linePerpendicularCollideThroughFirstBallCenter = new Line(first.getPosition(),
                                                                       lineParallelCollideThroughBallsCenter.getNormal());
        Line linePerpendicularCollideThroughSecondBallCenter = new Line(second.getPosition(),
                                                                        lineParallelCollideThroughBallsCenter.getNormal());

        Point pointFirstBallVectorEnd = first.getFuturePosition();
        Point pointSecondBallVectorEnd = second.getFuturePosition();

        Point pointFirstBallVectorEndProjectionOnLinePerpendicularCollide = linePerpendicularCollideThroughFirstBallCenter.getProjectionPointToLine(pointFirstBallVectorEnd);
        Point pointFirstBallVectorEndProjectionOnLineParallelCollide = lineParallelCollideThroughBallsCenter.getProjectionPointToLine(pointFirstBallVectorEnd);

        Point pointSecondBallVectorEndProjectionOnLinePerpendicularCollide = linePerpendicularCollideThroughSecondBallCenter.getProjectionPointToLine(pointSecondBallVectorEnd);
        Point pointSecondBallVectorEndProjectionOnLineParallelCollide = lineParallelCollideThroughBallsCenter.getProjectionPointToLine(pointSecondBallVectorEnd);

        Vector firstBallVectorProjectionOnLineParallelCollide = new Vector(first.getPosition(),
                                                                           pointFirstBallVectorEndProjectionOnLineParallelCollide);
        Vector firstBallVectorProjectionOnLinePerpendicularCollide = new Vector(first.getPosition(),
                                                                                pointFirstBallVectorEndProjectionOnLinePerpendicularCollide);

        Vector secondBallVectorProjectionOnLineParallelCollide = new Vector(second.getPosition(),
                                                                            pointSecondBallVectorEndProjectionOnLineParallelCollide);
        Vector secondBallVectorProjectionOnLinePerpendicularCollide = new Vector(second.getPosition(),
                                                                                 pointSecondBallVectorEndProjectionOnLinePerpendicularCollide);

        Vector firstBallResultVector = firstBallVectorProjectionOnLinePerpendicularCollide.sumVector(secondBallVectorProjectionOnLineParallelCollide);
        Vector secondBallResultVector = secondBallVectorProjectionOnLinePerpendicularCollide.sumVector(firstBallVectorProjectionOnLineParallelCollide);

        double firstBallResultVectorLength = firstBallResultVector.getLength();
        double secondBallResultVectorLength = secondBallResultVector.getLength();

        first.changeVector(firstBallResultVector);
        second.changeVector(secondBallResultVector);
        first.setSpeedOfMotion(firstBallResultVectorLength);
        second.setSpeedOfMotion(secondBallResultVectorLength);

    }
}