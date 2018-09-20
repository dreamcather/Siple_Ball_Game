package sample;

public class Point {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point point) {
        double eps = 0.000001;
        boolean resX = false;
        boolean resY = false;
        if (Math.abs(point.x - this.x) < eps)
            resX = true;
        if (Math.abs(point.y - this.y) < eps)
            resY = true;
        return resX && resY;

    }

    public double getDistancePoint(Point point) {
        return Math.sqrt(Math.pow(x - point.getX(), 2) + Math.pow((y - point.getY()), 2));
    }

}
