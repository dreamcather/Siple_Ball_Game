package gameObject;

import detection.ObjectDetectVisitor;
import geometry.Line;
import geometry.Point;
import interaction.MotionControl;
import interaction.ObjectInteractVisitor;

public class Wall extends  GameObject{

    private Line mainLine;
    private Line leftParallelLine;
    private Line rightParallelLine;
    private Point start;
    private Point end;

    public Wall(Point _start, Point _end) {

        mainLine = new Line(_start, _end);
        start = _start;
        end = _end;
        leftParallelLine = new Line(start, mainLine.getNormal());
        rightParallelLine = new Line(end, mainLine.getNormal());
        type ="W";

    }

    public Wall(String string) {
        String[] substr = string.split("  ");
        start = new Point(Double.parseDouble(substr[0]),Double.parseDouble(substr[1]));
        end = new Point(Double.parseDouble(substr[2]),Double.parseDouble(substr[3]));
    }

    public Line getLine() {
        return mainLine;
    }

    public Line getLeftParallelLine() {
        return leftParallelLine;
    }

    public Line getRightParallelLine() {
        return rightParallelLine;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Point getStart(Point point) {
        start.add(point);
        return start;
    }

    public Point getEnd(Point point) {

        end.add(point);
        return end;
    }

    public <T> T collisionDetection(ObjectDetectVisitor<T> objectDetectVisitor) {
        return objectDetectVisitor.visit(this);
    }

    @Override
    public void changeVector() {

    }

    @Override
    public void move(MotionControl motionControl) {

    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public Point getPosition() {
        return new Point(0,0);
    }

    @Override
    public String toString() {
        String res = new String(start.getX()+"  "+start.getY()+ "  "+end.getX()+"  "+end.getY()+"\n");
        return res;
    }

    public <T> T collisionReaction(ObjectInteractVisitor<T> objectDetectVisitor) {
        return objectDetectVisitor.visit(this);
    }
}