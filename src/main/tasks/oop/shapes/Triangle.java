package tasks.oop.shapes;

public class Triangle extends Shape {
    Point pointA;
    Point pointB;
    Point pointC;

    public Triangle(Point pointA, Point pointB, Point pointC) {
        super((pointA.getX() + pointB.getX() + pointC.getX()) / 3, ((pointA.getY()) + pointB.getY() + pointC.getY()) / 2);
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }

    public double getSideA() {
        return Point.distanceBetween(pointB, pointC);
    }

    public double getSideB() {
        return Point.distanceBetween(pointB, pointC);
    }

    public double getSideC() {
        return Point.distanceBetween(pointB, pointC);
    }

    @Override
    double area() {
        return Math.abs(pointA.getX() * (pointB.getY() - pointC.getY()) +
                pointB.getX() * (pointC.getY() - pointA.getY()) +
                pointC.getX() * (pointA.getY() - pointB.getY())) / 2;
    }

    @Override
    boolean containsPoint(Point point) {
        return ((new Triangle(point, pointA, pointB)).area() +
                (new Triangle(point, pointB, pointC)).area() +
                (new Triangle(point, pointC, pointA)).area()) == area();
    }

    @Override
    boolean isComprisedOf(Point point) {
        return new Triangle(point, pointA, pointB).area() == 0 ||
                new Triangle(point, pointB, pointC).area() == 0 ||
                new Triangle(point, pointC, pointA).area() == 0;
    }

    @Override
    double circumference() {
        return getSideA() + getSideB() + getSideC();
    }

    @Override
    void draw(Color fillColor, Color centerColor) {
        int startX = (int) Math.round(Math.min(Math.min(pointA.getX(), pointB.getX()), pointC.getX()));
        int endX = (int) Math.round(Math.max(Math.max(pointA.getX(), pointB.getX()), pointC.getX()));
        int startY = (int) Math.round(Math.max(Math.max(pointA.getY(), pointB.getY()), pointC.getY()));
        int endY = (int) Math.round(Math.min(Math.min(pointA.getY(), pointB.getY()), pointC.getY()));
        super.draw(startX, endX, startY, endY, fillColor, centerColor);
    }
}
