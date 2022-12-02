package tasks.oop.shapes;

public class Rectangle extends Shape {
    Point lowerLeft;
    Point upperRight;

    public Rectangle(double lowerLeftPointX,
                     double lowerLeftPointY,
                     double upperRightX,
                     double upperRightY
    ) {
        this(new Point(lowerLeftPointX, lowerLeftPointY), new Point(upperRightX, upperRightY));
    }

    public Rectangle(Point point1, Point point2) {
        super(
                (point1.getX() + point2.getX()) / 2,
                (point1.getY() + point2.getY()) / 2)
        ;

        if (point2.getX() < point1.getX()) {
            double temp = point2.getX();
            point2.setX(point1.getX());
            point1.setX(temp);
        }
        if (point2.getY() < point1.getY()) {
            double temp = point2.getY();
            point2.setY(point1.getY());
            point1.setY(temp);
        }
        this.lowerLeft = point1;
        this.upperRight = point2;
    }

    @Override
    double area() {
        return getSideA() * getSideB();
    }

    @Override
    boolean containsPoint(Point point) {
        return point.getX() <= upperRight.getX() &&
                point.getY() <= upperRight.getY() &&
                point.getX() >= lowerLeft.getX() &&
                point.getY() >= lowerLeft.getY();
    }

    boolean isComprisedOf(Point point) {
        return containsPoint(point) && (
                point.getY() == lowerLeft.getY() ||
                point.getX() == lowerLeft.getX() ||
                point.getY() == upperRight.getY() ||
                point.getX() == upperRight.getX()
        );
    }

    @Override
    double circumference() {
        return 2 * getSideA() + 2 * getSideA();
    }

    @Override
    void draw(Color fillColor, Color centerColor) {
        int startY = (int) Math.round(lowerLeft.getY());
        int endY = (int) Math.round(upperRight.getY());
        int startX = (int) Math.round(lowerLeft.getX());
        int endX = (int) Math.round(upperRight.getX());
        super.draw(startX, endX, startY, endY, fillColor, centerColor);
    }

    public double getSideA() {
        return Math.abs(upperRight.getX() - lowerLeft.getX());
    }

    public double getSideB() {
        return Math.abs(upperRight.getY() - lowerLeft.getY());
    }

    @Override
    public String toString() {
        return String.format("Lower left corner: %s%nUpper right corner: %s%nCenter: %s", lowerLeft, upperRight, center);
    }
}
