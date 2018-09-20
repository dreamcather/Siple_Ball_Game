package sample;

public class PrizeVisitor implements BallVisitor<Collision> {
    private Prize prize;

    public PrizeVisitor(Prize prize) {
        this.prize = prize;
    }

    @Override
    public Collision visit(Enemy enemy) {
        return new RegularCollision(enemy, prize);
    }

    @Override
    public Collision visit(Hero hero) {
        return new DestroyCollision(hero, prize);
    }

    @Override
    public Collision visit(Prize prize) {
        return new RegularCollision(prize, this.prize);
    }
}
