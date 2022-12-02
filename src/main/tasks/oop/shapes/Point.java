package tasks.oop.shapes;

import java.util.Locale;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void move(double newX, double newY) {
        setX(newX);
        setY(newY);
    }

    public static double distanceBetween(Point p1, Point p2) {
        return Math.sqrt((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p2.getY() - p1.getY()) * (p2.getY() - p1.getY()));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof  Point otherPoint)) {
            return false;
        } else {
            return this.x == otherPoint.getX() && this.y == otherPoint.getY();
        }
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "(%.2f, %.2f)", x, y);
    }
}
