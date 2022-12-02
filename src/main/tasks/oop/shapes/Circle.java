package tasks.oop.shapes;

public class Circle extends Shape {
    double radius;

    public Circle(double centerX, double centerY, double radius) {
        super(centerX, centerY);
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    @Override
    boolean containsPoint(Point point) {
        return Point.distanceBetween(point, center) <= radius;
    }

    @Override
    boolean isComprisedOf(Point point) {
        return Point.distanceBetween(point, center) == radius;
    }

    @Override
    double circumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    void draw(Color fillColor, Color centerColor) {
        int startX = (int) Math.round(center.getX() - radius);
        int endX = (int) Math.round(center.getX() + radius);
        int startY = (int) Math.round(center.getY() - radius);
        int endY = (int) Math.round(center.getY() + radius);
        super.draw(startX, endX, startY, endY, fillColor, centerColor);
    }
}
