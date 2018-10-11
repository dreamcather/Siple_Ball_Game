package interaction;

import gameObject.Ball;

public class RegularTwoBallInteraction implements Interaction {
    private Ball first;
    private Ball second;

    public RegularTwoBallInteraction(Ball first, Ball second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean detect() {
        return new RegularTwoBallDetection(first, second).detect();
    }

    @Override
    public void collision() {
        new RegularTwoBallCollision(first, second).collide();
    }
}
