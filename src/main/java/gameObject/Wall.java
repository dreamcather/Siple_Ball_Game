package gameObject;

import geometry.*;
import control.MotionControl;
import interaction.ObjectInteractionVisitor;
import visual.Camera;
import visual.visualInformation.VisualInformation;
import visual.visualInformation.WallVisualInformation;

public class Wall extends GameObject {

    private Line mainLine;
    public LineSegment lineSegment;
    private Line leftParallelLine;
    private Line rightParallelLine;
    private MyPoint start;
    private MyPoint end;

    public Wall(MyPoint _start, MyPoint _end) {

        mainLine = new Line(_start, _end);
        start = _start;
        end = _end;
        leftParallelLine = new Line(start, mainLine.getNormal());
        rightParallelLine = new Line(end, mainLine.getNormal());
        lineSegment = new MyLineSegment(start, end);
        type = "W";

    }

    public Wall(String string) {
        String[] substr = string.split(" ");
        start = new MyPoint(Double.parseDouble(substr[0]), Double.parseDouble(substr[1]));
        end = new MyPoint(Double.parseDouble(substr[2]), Double.parseDouble(substr[3]));
        type = "W";
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

    public MyPoint getStart() {
        return start;
    }

    public MyPoint getEnd() {
        return end;
    }

    public <T> T collision(ObjectInteractionVisitor<T> objectDetectVisitor) {
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
    public MyPoint getPosition() {
        return new MyPoint(0, 0);
    }

    @Override
    public String toString() {
        return start.getX() + "  " + start.getY() + "  " + end.getX() + "  " + end.getY() + "\n";
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        Wall transformWall = new Wall(camera.transformPoint(getStart()), camera.transformPoint(getEnd()));
        MyPoint start;
        MyPoint end;
        if (camera.isVisible(getStart()))
            start = transformWall.getStart();
        else {
            start = camera.getPoint(transformWall.getStart(), transformWall.lineSegment);
        }
        if (camera.isVisible(getEnd()))
            end = transformWall.getEnd();
        else {
            end = camera.getPoint(transformWall.getEnd(), transformWall.lineSegment);
        }
        if (start == null)
            return null;
        if (end == null)
            return null;
        start.setX(start.getX() + camera.getOffset());
        start.setY(start.getY() + camera.getOffset());
        end.setX(end.getX() + camera.getOffset());
        end.setY(end.getY() + camera.getOffset());
        VisualInformation visualInformation = new WallVisualInformation(start, end,this);
        return visualInformation;
    }

}
