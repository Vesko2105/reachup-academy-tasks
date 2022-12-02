package tasks.oop.shapes;

import java.util.List;

abstract public class Shape {
    Point center;
    protected Shape(double centerX, double centerY) {
        center = new Point(centerX, centerY);
    }

    abstract double area();
    abstract boolean containsPoint(Point point);
    abstract boolean isComprisedOf(Point point);
    abstract double circumference();
    protected void draw(int startX, int endX, int startY, int endY, Color fillColor, Color centerColor) {
        for (int currentY = startY; currentY <= endY; currentY++) {
            for (int currentX = startX; currentX <= endX; currentX++) {
                Point currentPoint = new Point(currentX, currentY);
                if (currentPoint.equals(this.center)) {
                    System.out.print(centerColor + "#  " + Color.RESET);
                } else if (containsPoint(currentPoint)) {
                    System.out.print(fillColor + "*  " + Color.RESET);
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
    abstract void draw(Color fillColor, Color centerColor);
    public void draw() {
        draw(Color.RESET, Color.RESET);
    }

    static boolean allContain(List<Shape> shapes, Point point) {
        return shapes.stream().allMatch(shape -> shape.containsPoint(point));
    }
}
