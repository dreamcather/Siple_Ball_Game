package sample;

public class HeroVisiter implements BallVisitor<Collision> {
    Hero hero;

    public HeroVisiter(Hero hero) {
        this.hero = hero;
    }

    @Override
    public Collision visit(Enemy enemy) {
        return new RegularCollision(hero,enemy);
    }

    @Override
    public Collision visit(Hero hero) {
        return null;
    }

    @Override
    public Collision visit(Prize prize) {
        return new RegularCollision(hero,prize);
    }
}
